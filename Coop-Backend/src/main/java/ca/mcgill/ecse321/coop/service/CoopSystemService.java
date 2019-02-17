package ca.mcgill.ecse321.coop.service;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.coop.model.*;
import ca.mcgill.ecse321.coop.dao.*;

@Service
public class CoopSystemService {
	@Autowired
	CoopSystemRepository coopSystemRepository; //Create a repository for the top level class
	@Autowired
	StudentRepository studentRepository; //Create a repository for students
	
	@Autowired
	EmployerRepository employerRepository; //Create a repository for employers
	
	@Autowired
	DocumentRepository documentRepository; //Create a repository for employers
	
	@Autowired
	EventNotificationRepository eventNotificationRepository;
	
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	CoopJobRepository coopJobRepository;
	
	@Transactional
	public CoopSystem createCoopSystem() //we want to have at any given time only one instance of the toplevel class
	{                                // this instance will always have the String "Main data" as id
		
		
		if(coopSystemRepository.existsById("Main data")) // if it already exists,return it
		{
			  return coopSystemRepository.findById("Main data").get(); 
		}
		 
		CoopSystem coopSystem= new CoopSystem();
		coopSystem.setId("Main data");
		coopSystemRepository.save(coopSystem);
		return coopSystem;
	}
	
	@Transactional
	public CoopSystem getCoopSystem() // we want to make sure that we only have one coop system....
									// we always look for one with the String id "Main data"
	{
		if(coopSystemRepository.existsById("Main data"))
		{
			return coopSystemRepository.findById("Main data").get();
		}
		return null;
	}
	
	@Transactional
	public void deleteSystem()
	{
		coopSystemRepository.deleteAll();
	}
	
	@Transactional
	public Student createStudent (String username) //create a student with unique username
	{
		if(studentRepository.existsById(username) || employerRepository.existsById(username) || username==null) //check the username not used before
		{
			return null;
		}
		Student s =new Student();
		s.setUsername(username);
		CoopSystem coopSystem= getCoopSystem();
		s.setCoopSystem(coopSystem);  //set its top level containing class to the one we are using
		studentRepository.save(s); //add it to the student repository
		coopSystem.getCoopUsers().add(s); // add it to the list of users in the system isntance
		
		return s;
	}
	
	@Transactional
	public Employer createEmployer (String username)
	{
		if(studentRepository.existsById(username) || employerRepository.existsById(username) || username==null) //check the username not used before
		{
			return null;
		}
		Employer e =new Employer();
		e.setUsername(username);
		CoopSystem coopSystem= getCoopSystem();
		e.setCoopSystem(coopSystem);
		employerRepository.save(e);
		coopSystem.getCoopUsers().add(e);
		
		return e;
	}
	
	@Transactional
	public ArrayList<CoopUser> getAllCoopUsers() //get list of all users
	{
		ArrayList<CoopUser> listOfUsers=new ArrayList<CoopUser>();;
		CoopSystem coopSystem= coopSystemRepository.findById("Main data").get();
		Set <CoopUser> dummy=coopSystem.getCoopUsers();
		for(CoopUser a: dummy)
		{
			listOfUsers.add(a);
		}
		return listOfUsers;
	}
	
	
	
	@Transactional
	public ArrayList<Student> getAllStudents() //get list of all students
	{
		Iterable<Student> dummy= studentRepository.findAll();
		ArrayList<Student> toReturn= new ArrayList<Student>();
		for(Student s: dummy)
		{
			toReturn.add(s);
		}
		return toReturn;
	}
	
	@Transactional
	public ArrayList<Employer> getAllEmployers() //get list of all employers
	{
		Iterable<Employer> dummy= employerRepository.findAll();
		ArrayList<Employer> toReturn= new ArrayList<Employer>();
		for(Employer e: dummy)
		{
			toReturn.add(e);
		}
		return toReturn;
	}
	
	@Transactional
	public CoopUser findCoopUserByUsername (String id) // find a user by its id
	{
		ArrayList<CoopUser> setOfUsers= getAllCoopUsers();
		CoopUser foundUser=null;
		for(CoopUser a: setOfUsers)
		{
			if(a.getUsername().equals(id)) {
				foundUser=a; break;
			}
		}
		return foundUser;
	}
	
	@Transactional
	public Student findStudentByUsername (String username) //find a Student by the username
	{
		if(username==null) {return null;}
		if(studentRepository.existsById(username))
		{
			return studentRepository.findById(username).get();
		}
		return null;
	}
	
	@Transactional
	public Employer findEmployerByUsername (String username) //find an employer by the username
	{
		if(username==null) {return null;}
		if(employerRepository.existsById(username))
		{
			return employerRepository.findById(username).get();
		}
		return null;
	}
	
	
	@Transactional
	public void deleteStudent(Student a)
	{
		if(a!=null) {studentRepository.delete(a);}
	}
	
	@Transactional
	public void deleteEmployer(Employer a)
	{
		if(a!=null) {employerRepository.delete(a);}
	}
	
	@Transactional
	public void deleteCoopUser(CoopUser a) // delete any type of user
	{
		if(a!=null)
		{
			if(a instanceof Student)
			{
				studentRepository.delete((Student)a);
			}
			else
			{
				employerRepository.delete((Employer) a);
			}
		}
	}
	
	@Transactional
	public Document createDocument(String documentId, CoopUser author)
	{
		if(documentId==null || documentRepository.existsById(documentId) || author ==null)
		{
			return null;
		}
		CoopSystem c= getCoopSystem();
		Document d= new Document(); //create a new document instance
		
		d.setDocumentId(documentId); //set its id
		d.setAuthor(author);   //set its author
		d.setCoopSystem(c); // set its top-level class to the coop system we are currently using
		
		documentRepository.save(d);
		author.getAuthoredDocuments().add(d); //add this document to the list this user authored
		c.getDocuments().add(d); // add this document to the list of documents in the top level class
		
		
		
		return d;
	}
	
	@Transactional
	public Document findDocumentByDocumentId(String id)
	{
		if(id==null) {return null;}
		if(documentRepository.existsById(id))
		{
			return documentRepository.findById(id).get();
		}
		return null;
	}
	
	@Transactional
	public ArrayList<Document> findDocumentsByAuthor(CoopUser author)
	{
		if(author==null) {return null;}
		Set<Document> set= author.getAuthoredDocuments();
		ArrayList<Document> list= new ArrayList<Document>();
		for(Document d: set) {
			list.add(d);
		}
		return list;
	}
	
	
	@Transactional
	public void deleteDocument(Document d)
	{
		if(d!=null) {documentRepository.delete(d);}
	}
	
	@Transactional
	public void deleteAllDocuments()
	{
		documentRepository.deleteAll();
	}
	
	@Transactional
	public EventNotification createEventNotification(String id)
	{
		if (id==null || eventNotificationRepository.existsById(id))
		{
			return null;
		}
		EventNotification e = new EventNotification();
		e.setName(id);
		CoopSystem s= getCoopSystem();
		e.setCoopSystem(s);
		
		eventNotificationRepository.save(e);
		s.getEventNotifications().add(e);
		for(Employer em: this.getAllEmployers())
		{
			em.getEventNotifications().add(e);  //add this event notification to the list of notifications for all employers
		}
		
		
		return e;
	}
	
	
	@Transactional
	public EventNotification findEventNotificationByName (String name)
	{
		if(name==null) {return null;}
		if(eventNotificationRepository.existsById(name)){
			return eventNotificationRepository.findById(name).get();
			
		}
		return null;
	}
	
	@Transactional
	public ArrayList<EventNotification> findAllEventNotifications()
	{
		Iterable<EventNotification> ite= eventNotificationRepository.findAll();
		ArrayList<EventNotification> list= new ArrayList<EventNotification>();
		for(EventNotification ev :ite) {
			list.add(ev);
			
		}
		return list;
	}
	
	@Transactional
	public void deleteEventNotification(EventNotification e)
	{
		if (e!=null) {eventNotificationRepository.delete(e);}
	}
	
	@Transactional
	public Message createMessage(String messageId, CoopUser sender, CoopUser receiver)
	{
		if(messageId==null || sender== null ||  receiver==null || messageRepository.existsById(messageId))
		{
			return null;
		}
		
		Message m= new Message();
		m.setMessageId(messageId);
		m.setSender(sender);
		m.setReceiver(receiver);
		m.setCoopSystem(getCoopSystem());
		messageRepository.save(m);
		
		getCoopSystem().getMessages().add(m);
		sender.getSentMessages().add(m);
		receiver.getReceivedMessages().add(m);
		return m;
	}
	
	@Transactional
	public Message findMessageByMessageId(String id)
	{
		if(id==null) {return null;}
		if(messageRepository.existsById(id)) {return messageRepository.findById(id).get();}
		return null;
	}
	
	@Transactional
	public ArrayList<Message> findMessagesBySender(CoopUser sender)
	{
		if(sender==null) {return null;}
		ArrayList<Message> list= new ArrayList<Message>();
		Set<Message> set= sender.getSentMessages();
		for(Message m: set)
		{
			list.add(m);
		}
		return list;
	}
	
	@Transactional
	public ArrayList<Message> findMessagesByReceiver(CoopUser receiver)
	{
		if(receiver==null) {return null;}
		ArrayList<Message> list= new ArrayList<Message>();
		Set<Message> set= receiver.getReceivedMessages();
		for(Message m: set)
		{
			list.add(m);
		}
		return list;
	}
	
	@Transactional
	public ArrayList<Message> findMessagesBySenderAndReceiver(CoopUser sender, CoopUser receiver)
	{
		if(receiver==null || sender==null) {return null;}
		ArrayList<Message> listo= findMessagesBySender(sender);
		ArrayList<Message> listn = new ArrayList<Message>();
		for(Message m: listo)
		{
			if(m.getReceiver().equals(receiver))
			{
				listn.add(m);
			}
		}
		return listn;
	}
	
	@Transactional
	public void deleteMessage(Message m)
	{
		if(m!=null) {messageRepository.delete(m);}
	}
	
	@Transactional
	public CoopJob createCoopJob(String jobId, Employer employer, Student student)
	{
		if(jobId==null|| employer==null || student==null || coopJobRepository.existsById(jobId) ) {
		return null;
		}
		CoopJob job =new CoopJob();
		job.setJobId(jobId);
		job.setEmployer(employer);
		job.setIntern(student);
		job.setCoopSystem(getCoopSystem());
		coopJobRepository.save(job);
		
		employer.getCoopJobs().add(job);
		student.getCoopJobs().add(job);
		getCoopSystem().getCoopJobs().add(job);
		if(!employer.getArchivedInterns().contains(student)){ // if this the first time this student does a coop
															// a coop with this employer, add this employer to
															//archived interns
			employer.getArchivedInterns().add(student);
		}
		return job;
	}
	
	@Transactional
	public CoopJob findCoopJobByJobId(String id)
	{
		if(id==null) {return null;}
		if(coopJobRepository.existsById(id)) {coopJobRepository.findById(id);}
		return null;
	}
	
	@Transactional
	public ArrayList<CoopJob> findCoopJobsByEmployer(Employer em)
	{
		if(em==null) {return null;}
		Set<CoopJob> set= em.getCoopJobs();
		ArrayList<CoopJob> list= new ArrayList<CoopJob>();
		for (CoopJob job: set)
		{
			list.add(job);
		}
		return list;
	}
	
	@Transactional
	public ArrayList<CoopJob> findCoopJobsByStudent(Student s)
	{
		if(s==null) {return null;}
		Set<CoopJob> set= s.getCoopJobs();
		ArrayList<CoopJob> list= new ArrayList<CoopJob>();
		for (CoopJob job: set)
		{
			list.add(job);
		}
		return list;
	}
	
	@Transactional
	public ArrayList<CoopJob> findCoopJobsByEmployerAndStudent(Employer em, Student s)
	{
		if(s==null|| em==null) {return null;}
		Set<CoopJob> set= s.getCoopJobs();
		ArrayList<CoopJob> list= new ArrayList<CoopJob>();
		for (CoopJob job: set)
		{
			if(job.getEmployer().equals(em)) {list.add(job);}
		}
		return list;
	}
	
	@Transactional
	public void deleteCoopJob(CoopJob job) {
		if(job!=null) {coopJobRepository.delete(job);}
	}
}
