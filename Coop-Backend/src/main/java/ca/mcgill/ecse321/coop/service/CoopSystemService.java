package ca.mcgill.ecse321.coop.service;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

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
	CoopUserRepository coopUserRepository; //Create a repository for employers
	
	@Autowired
	DocumentRepository documentRepository; //Create a repository for documents
	
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
		coopSystem.setCoopJobs(new HashSet<CoopJob>());
		coopSystem.setDocuments(new HashSet<Document>());
		coopSystem.setCoopUsers(new HashSet<CoopUser>());
		coopSystem.setMessages(new HashSet<Message>());
		coopSystem.setEventNotifications(new HashSet<EventNotification>());
		coopSystemRepository.save(coopSystem);
		return coopSystem;
	}
	
	@Transactional
	public void clear() {
		deleteAllEventNotifications();
		deleteAllDocuments();
		messageRepository.deleteAll();
		coopJobRepository.deleteAll();
		deleteAllStudents();
		employerRepository.deleteAll();
		coopUserRepository.deleteAll();
		coopSystemRepository.deleteAll();
		
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
	public void deleteSystem() //delete the only system we have
	{
		coopSystemRepository.deleteAll();
	}
	
	@Transactional
	public Student createStudent (String username) //create a student with unique username
	{	//make sure this username is unique for any employer of student
		if(username==null || studentRepository.existsById(username) || employerRepository.existsById(username) ||coopUserRepository.existsById(username) ) //check the username not used before
		{
			return null;
		}
		Student s =new Student();
		s.setUsername(username);
		CoopSystem coopSystem= getCoopSystem();
		s.setCoopSystem(coopSystem);  //set its top level containing class to the one we are using
		s.setAuthoredDocuments(new HashSet<Document>());
		s.setCoopJobs(new HashSet<CoopJob>());
		s.setPersonalDocuments(new HashSet<Document>());
		s.setReceivedMessages(new HashSet<Message>());
		s.setSentMessages(new HashSet<Message>());
		
		coopSystem.getCoopUsers().add(s); // add it to the list of users in the system isntance
		coopSystemRepository.save(coopSystem);
		studentRepository.save(s);
		coopUserRepository.save(s);
		
		return s;
	}
	
	@Transactional
	public Employer createEmployer (String username)
	{   
		if( username==null || coopUserRepository.existsById(username) || studentRepository.existsById(username) || employerRepository.existsById(username) ) //check the username not used before
		{
			return null;
		}
		Employer e =new Employer();
		e.setUsername(username);
		CoopSystem coopSystem= getCoopSystem();
		e.setCoopSystem(coopSystem);
		e.setAuthoredDocuments(new HashSet<Document>());
		e.setCoopJobs(new HashSet<CoopJob>());
		e.setArchivedInterns(new HashSet<Student>());
		e.setReceivedMessages(new HashSet<Message>());
		e.setSentMessages(new HashSet<Message>());
		
		coopSystem.getCoopUsers().add(e);
		
		coopSystemRepository.save(coopSystem);
		employerRepository.save(e);
		coopUserRepository.save(e);
		return e;
	}
	
	@Transactional
	public void setPassword(String username, String password) //set the password for a CoopUser
	{
		if(username==null || password==null) {return;}
		CoopUser s=getCoopUser(username);
		if(s==null) {return;}
		s.setPassword(password);
		saveCoopUser(s);
	}
	
	@Transactional  //find a coopuser by its username
	public CoopUser getCoopUser(String username) {
		if(username==null) {return null;}
		if(studentRepository.existsById(username)) {return studentRepository.findById(username).get();}
		else if(employerRepository.existsById(username)) {return employerRepository.findById(username).get();}
		return null;
	}
	
	@Transactional //find a student by its username
	public Student getStudent(String username) {
		if(username==null) {return null;}
		if(studentRepository.existsById(username)) {return studentRepository.findById(username).get();}
		return null;
	}
	
	@Transactional
	public Employer getEmployer(String username) {
		if(username==null) {return null;}
		if(employerRepository.existsById(username)) {return employerRepository.findById(username).get();}
		return null;
	}
	
	@Transactional  //save a coopuser to the corresponding repository
	public void saveCoopUser (CoopUser s) {
		if(s==null) {return;}
		if(s instanceof Student) {coopUserRepository.save(s);studentRepository.save((Student) s);}
		else {coopUserRepository.save(s);employerRepository.save((Employer) s);}
	}
	
	@Transactional  //set the privacy settings for the student
	public void setStudentPermissions(String username, boolean allowCV, boolean allowTranscript)
	{
		if(username==null) {return;}
		Student s= getStudent(username);
		if(s!=null)
		{
			if(allowCV || !allowTranscript)
			{
				s.setAllowCV(allowCV); s.setAllowTranscript(allowTranscript);
			}
			else { //if the parameters allow Transcript but forbid cv, change nothing
				
			}
		}
		coopUserRepository.save(s);
		studentRepository.save(s);
	}
	
	
	
	@Transactional
	public ArrayList<CoopUser> getAllCoopUsers() //get list of all users
	{
		ArrayList<CoopUser> listOfUsers=new ArrayList<CoopUser>();
		CoopSystem coopSystem= coopSystemRepository.findById("Main data").get();
		Set <CoopUser> dummy=coopSystem.getCoopUsers(); //get the list of allusers from coopsystem
		for(CoopUser a: dummy)
		{
			listOfUsers.add(a); //we refer working with arraylists than with set-implmenting structures
		}
		return listOfUsers;
	}
	
	
	
	@Transactional
	public ArrayList<Student> getAllStudents() //get list of all students
	{
		Iterable<Student> dummy= studentRepository.findAll(); //get all students in the student repository
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
		for(CoopUser a: setOfUsers) //seearch in all coopusers
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
		if(studentRepository.existsById(username)) // if a student with such id exists in the repository
		{
			return studentRepository.findById(username).get(); //get it
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
	public void deleteStudent(String username)
	{
		Student a= getStudent(username);
		if(a==null) {return;}
		for(Employer e: employerRepository.findAll()) {
			if(e.getArchivedInterns().contains(a)) {
				e.getArchivedInterns().remove(a);
				saveCoopUser(e);
			}
		}
		studentRepository.delete(a);
	}
	
	@Transactional
	public void deleteAllStudents()
	{
		
		for(Student s: studentRepository.findAll()) {
			deleteStudent(s.getUsername());
		}
	}
	
	@Transactional
	public void deleteEmployer(String username)
	{
		Employer a =getEmployer(username);
		if(a!=null) {employerRepository.delete(a);}
	}
	
	@Transactional
	public void deleteCoopUser(String username) // delete any type of user
	{
		CoopUser a=getCoopUser(username);
		if(a!=null)
		{
			if(a instanceof Student) //if this is a student
			{
				studentRepository.delete((Student)a);//coopUserRepository.delete(a);
			}
			else // if this is an employer
			{
				employerRepository.delete((Employer) a);//coopUserRepository.delete(a);
			}
		}
	}
	
	@Transactional
	public void setPersonalDocuments(String username, ArrayList<String> docIds) //set the persona
	{
		if(username==null) {return;}
		Student stu =getStudent(username);
		if(stu!=null && docIds!=null ) //make sure they are 2 max	
		{
			stu.setPersonalDocuments(new HashSet<Document>());
			for(String id: docIds)
			{
				Document doc=findDocumentByDocumentId(id); //set only CVs and transcripts authored by the student
				if(doc!=null && doc.getAuthor().getUsername().equals(username)
					&& (doc.getType()==DocumentType.CV || doc.getType()==DocumentType.Transcript)	)
{stu.getPersonalDocuments().add(doc);}
				
			}
			coopUserRepository.save(stu);
			studentRepository.save(stu);
			
		}
		
	}
	
	@Transactional  //create a new document
	public Document createDocument(String documentId, String authorUsername, DocumentType type)
	{	//unique id
		if(documentId==null || documentRepository.existsById(documentId) || authorUsername ==null)
		{
			return null;
		}
		CoopUser author= getCoopUser(authorUsername);
		CoopSystem c= getCoopSystem();
		Document d= new Document(); //create a new document instance
		
		d.setDocumentId(documentId); //set its id
		d.setAuthor(author);   //set its author
		d.setCoopSystem(c); // set its top-level class to the coop system we are currently using
		d.setSubmissionDate(new Date(System.currentTimeMillis())); //set submission date and time to current time
		d.setSubmissionTime(new Time(System.currentTimeMillis()));
		d.setType(type);
		
		
		author.getAuthoredDocuments().add(d); //add this document to the list this user authored
		c.getDocuments().add(d); // add this document to the list of documents in the top level class
		
		coopSystemRepository.save(c);
		documentRepository.save(d);
		saveCoopUser(author);
		
		return d;
	}
	
	@Transactional
	public Document findDocumentByDocumentId(String id) //find documents by id
	{
		if(id==null) {return null;}
		if(documentRepository.existsById(id))
		{
			return documentRepository.findById(id).get();
		}
		return null;
	}
	
	@Transactional
	public ArrayList<Document> findDocumentsByAuthor(String authorUsername) //find documents by author
	{
		if(authorUsername==null) {return null;}
		CoopUser author= getCoopUser(authorUsername);
		Set<Document> set= author.getAuthoredDocuments();
		ArrayList<Document> list= new ArrayList<Document>();
		for(Document d: set) {
			list.add(d);
		}
		return list;
	}
	
	
	@Transactional
	public void deleteDocument(String id) //delete the document, taking into account referential integrity
	{
		if(id==null) {return;}
		Document d= findDocumentByDocumentId(id);
		if(d==null) {return;}
		for(Message m: messageRepository.findAll()) {
			if(m.getAttachements().contains(d)) {
				m.getAttachements().remove(d);
				messageRepository.save(m);
			}
		}
		for(Student s:  studentRepository.findAll()) {
			if(s.getPersonalDocuments().contains(d)) {
				s.getPersonalDocuments().remove(d);
				saveCoopUser(s);
			}
		}
		for(CoopJob j:  coopJobRepository.findAll()) {
			if(j.getCoopJobDocuments().contains(d)) {
				j.getCoopJobDocuments().remove(d);
				coopJobRepository.save(j);
			}
		}
		documentRepository.delete(d);
	}
	
	@Transactional
	public void deleteAllDocuments()
	{
		for(Document d: documentRepository.findAll()) {
			deleteDocument(d.getDocumentId());
		}
	}
	
	@Transactional
	public EventNotification createEventNotification(String id) //new event notification
	{	//unique id
		if (id==null || eventNotificationRepository.existsById(id))
		{
			return null;
		}
		EventNotification e = new EventNotification();
		e.setName(id); //set its id
		CoopSystem s= getCoopSystem();
		e.setCoopSystem(s); //set its container


		s.getEventNotifications().add(e);
		for(Employer em: employerRepository.findAll())
		{
			em.getEventNotifications().add(e);  //add this event notification to the list of notifications for all employers
			employerRepository.save(em);
		}
		
		coopSystemRepository.save(s);
		eventNotificationRepository.save(e);
		return e;
	}
	
	@Transactional
	public void setEventNotificationSettings(String eventName, Event typeEvent, String location, Date date, Time startTime, Time endTime)
	{
		if(eventName==null) {return;}
		EventNotification e = findEventNotificationByName(eventName);
		if(e==null) {return;}
		if(endTime.compareTo(startTime)>=0 && date.compareTo(new Date(System.currentTimeMillis()))>=0) //change only if endTime>=startTime and the date is after the day we are setting this
		{
			if(location!=null) {e.setLocation(location);}
			e.setDate(date);
			e.setEndTime(endTime);
			e.setStartTime(startTime);
			e.setType(typeEvent);
		}
		eventNotificationRepository.save(e);
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
	public void deleteEventNotification(String eventname)
	{
		if(eventname==null) {return;}
		EventNotification e = findEventNotificationByName(eventname);
		if (e==null) {return;}
		for(Employer em: employerRepository.findAll()) {
			em.getEventNotifications().remove(e);
			saveCoopUser(em);
		}
		eventNotificationRepository.delete(e);
		
	}
	
	@Transactional
	public void deleteAllEventNotifications()
	{
		for(EventNotification e: eventNotificationRepository.findAll()) {
			deleteEventNotification(e.getName());
		}
		
	}
	
	@Transactional  //new message
	public Message createMessage(String messageId, String senderName, String receiverName, String content, ArrayList<String> attachementNames)
	{	//unique id, sender and receiver exist
		if(messageId==null || senderName== null ||  receiverName==null || messageRepository.existsById(messageId))
		{
			return null;
		}
		CoopUser sender=getCoopUser(senderName);
		CoopUser receiver= getCoopUser(receiverName);
		
		Message m= new Message();
		CoopSystem c=getCoopSystem();
		m.setMessageId(messageId);
		m.setSender(sender);
		m.setReceiver(receiver);
		m.setCoopSystem(c);
		m.setDate(new Date(System.currentTimeMillis()));
		m.setTime(new Time(System.currentTimeMillis()));
		m.setAttachements(new HashSet<Document>());
		if(content!=null) {m.setContent(content);}
		if(attachementNames!=null)
		{
			for(String docId: attachementNames)
			{
				Document doc= findDocumentByDocumentId(docId);
				if(doc!=null) {m.getAttachements().add(doc);}
			}
		}
		c.getMessages().add(m);
		sender.getSentMessages().add(m); //add this message to the list of messages for each user
		receiver.getReceivedMessages().add(m);
		coopSystemRepository.save(c);
		messageRepository.save(m);
		saveCoopUser(sender);saveCoopUser(receiver);
		return m;
	}
	
	@Transactional  //find a specific message
	public Message findMessageByMessageId(String id)
	{
		if(id==null) {return null;}
		if(messageRepository.existsById(id)) {return messageRepository.findById(id).get();}
		return null;
	}
	
	@Transactional  // sent messages
	public ArrayList<Message> findMessagesBySender(String senderName)
	{
		if(senderName==null) {return null;}
		CoopUser sender =getCoopUser(senderName);
		if(sender==null) {return null;}
		ArrayList<Message> list= new ArrayList<Message>();
		Set<Message> set= sender.getSentMessages();
		for(Message m: set)
		{
			list.add(m);
		}
		return list;
	}
	
	@Transactional  //received messages
	public ArrayList<Message> findMessagesByReceiver(String receiverName)
	{
		if(receiverName==null) {return null;}
		CoopUser receiver=getCoopUser(receiverName);
		ArrayList<Message> list= new ArrayList<Message>();
		Set<Message> set= receiver.getReceivedMessages();
		for(Message m: set)
		{
			list.add(m);
		}
		return list;
	}
	
	@Transactional //all messages
	public ArrayList<Message> getAllMessages(){
		ArrayList<Message> list= new ArrayList<Message>();
		for(Message m: messageRepository.findAll()) {
			list.add(m);
		}
		return list;
	}
	
	@Transactional //messages between sender and receiver
	public ArrayList<Message> findMessagesBySenderAndReceiver(String senderName, String receiverName)
	{
		if(receiverName==null || senderName==null) {return null;}
		CoopUser sender=getCoopUser(senderName);
		CoopUser receiver=getCoopUser(receiverName);
		if(receiver==null || sender==null) {return null;}
		ArrayList<Message> listo= findMessagesBySender(senderName);
		ArrayList<Message> listn = new ArrayList<Message>();
		for(Message m: listo) //get the messages where threciever is indeed the receiver
		{
			if(m.getReceiver().equals(receiver))
			{
				listn.add(m);
			}
		}
		return listn;
	}
	
	
	
	@Transactional // delete a message
	public void deleteMessage(String messageId)
	{
		if(messageId==null) {return;}
		Message m= findMessageByMessageId(messageId);
		if(m!=null) {messageRepository.delete(m);}
	}
	
	@Transactional // create a coopob
	public CoopJob createCoopJob(String jobId, String employerName, String studentName)
	{
		if(jobId==null|| employerName==null || studentName==null || coopJobRepository.existsById(jobId) ) {
		return null;
		}
		Student student=getStudent(studentName);
		Employer employer=getEmployer(employerName);
		CoopSystem c=getCoopSystem();
		if(student==null || employer==null){return null;}
		CoopJob job =new CoopJob();
		job.setJobId(jobId);
		job.setEmployer(employer);
		job.setIntern(student);
		job.setCoopSystem(c);
		job.setCoopJobDocuments(new HashSet<Document>());
		
		employer.getCoopJobs().add(job);
		student.getCoopJobs().add(job);
		c.getCoopJobs().add(job);
		if(!employer.getArchivedInterns().contains(student)){ // if this the first time this student does a coop
															// a coop with this employer, add this employer to
															//archived interns
			employer.getArchivedInterns().add(student);
		}
		coopJobRepository.save(job);
		coopSystemRepository.save(c);
		studentRepository.save(student);coopUserRepository.save(student);
		employerRepository.save(employer);coopUserRepository.save(employer);
		return job;
	}
	
	@Transactional // find a specific coopob
	public CoopJob findCoopJobByJobId(String id)
	{
		if(id==null) {return null;}
		if(coopJobRepository.existsById(id)) {return coopJobRepository.findById(id).get();}
		return null;
	}
	
	@Transactional //find coopobs by employer
	public ArrayList<CoopJob> findCoopJobsByEmployer(String employerName)
	{
		if(employerName==null) {return null;}
		Employer em=getEmployer(employerName);
		if(em==null) {return null;}
		Set<CoopJob> set= em.getCoopJobs();
		ArrayList<CoopJob> list= new ArrayList<CoopJob>();
		for (CoopJob job: set)
		{
			list.add(job);
		}
		return list;
	}
	
	@Transactional //find coopjobs by student
	public ArrayList<CoopJob> findCoopJobsByStudent(String studentName)
	{
		if(studentName==null) {return null;}
		Student s=getStudent(studentName);
		if(s==null) {return null;}
		Set<CoopJob> set= s.getCoopJobs();
		ArrayList<CoopJob> list= new ArrayList<CoopJob>();
		for (CoopJob job: set)
		{
			list.add(job);
		}
		return list;
	}
	
	@Transactional //find the coopjobs between an employer and a student
	public ArrayList<CoopJob> findCoopJobsByEmployerAndStudent(String employerName, String studentName)
	{
		if(studentName==null|| employerName==null) {return null;}
		Student s=getStudent(studentName);
		Employer em=getEmployer(employerName);
		if(s==null || em==null) {return null;}
		Set<CoopJob> set= s.getCoopJobs();
		ArrayList<CoopJob> list= new ArrayList<CoopJob>();
		for (CoopJob job: set)
		{
			if(job.getEmployer().equals(em)) {list.add(job);}
		}
		return list;
	}
	
	@Transactional
	public void deleteCoopJob(String jobId) {
		CoopJob job=findCoopJobByJobId(jobId);
		if(job!=null) {coopJobRepository.delete(job);}
	}
	
	@Transactional //set the settings
	public void setCoopJobSettings(String jobId, Date startDate, Date endDate, String name, CoopState state) {
		CoopJob job=findCoopJobByJobId(jobId);
		if(job==null || endDate.compareTo(startDate)<=0) { //chnage nothing if job is null, or enddate<=startDate
			return;
		}
		job.setStartDate(startDate);
		job.setEndDate(endDate);
		
		if(name!=null) {job.setName(name);}
		if(state!=null) {job.setState(state);}
		coopJobRepository.save(job);
		
	}
	
	@Transactional //set the state
	public void setCoopJobState(String jobId, CoopState state) {
		CoopJob job=findCoopJobByJobId(jobId);
		if(job==null ) { //chnage nothing if job is null, or enddate<=startDate
			return;
		}
		
		if(state!=null) {job.setState(state);}
		coopJobRepository.save(job);
		
	}
	
	@Transactional //upload documents to the coopob
	public void addDocumentToCoopJob(String jobId, String documentId)
	{
		CoopJob job=findCoopJobByJobId(jobId);
		Document d=findDocumentByDocumentId(documentId);
		if(job==null || d==null) {return;}
		job.getCoopJobDocuments().add(d);
		coopJobRepository.save(job);
	}
	
	@Transactional
	public ArrayList<CoopJob> getAllCoopJobs()
	{
		Set<CoopJob> set= getCoopSystem().getCoopJobs();
		ArrayList<CoopJob> list= new ArrayList<CoopJob>();
		for(CoopJob j: set)
		{
			list.add(j);
		}
		return list;
	}
	
	@Transactional
	public ArrayList<Document> getAllDocuments()
	{
		Set<Document> set= getCoopSystem().getDocuments();
		ArrayList<Document> list= new ArrayList<Document>();
		for(Document j: set)
		{
			list.add(j);
		}
		return list;
	}
	
	@Transactional
	public ArrayList<Document> getPersonalDocumentsByStudent(String studentName){
		Student s= getStudent(studentName);
		if(s==null) {return null;}
		Set<Document> set=s.getPersonalDocuments();
		ArrayList<Document> list= new ArrayList<Document>();
		for(Document d: set)
		{
			list.add(d);
		}
		return list;
	}
	
	@Transactional
	public ArrayList<Document> getAuthoredDocuments(String username)
	{
		CoopUser user= getCoopUser(username);
		if(user==null) {return null;}
		Set<Document> set=user.getAuthoredDocuments();
		ArrayList<Document> list= new ArrayList<Document>();
		for(Document d: set)
		{
			list.add(d);
		}
		return list;
	}
	
	@Transactional
	public ArrayList<Document> getAttachements(String messageId)
	{
		Message m= findMessageByMessageId(messageId);
		if(m==null) {return null;}
		Set<Document> set=m.getAttachements();
		ArrayList<Document> list= new ArrayList<Document>();
		for(Document d: set)
		{
			list.add(d);
		}
		return list;
	}
	
	@Transactional
	public ArrayList<Document> getCoopJobDocuments(String jobId)
	{
		CoopJob job= findCoopJobByJobId(jobId);
		if(job==null) {return null;}
		Set<Document> set=job.getCoopJobDocuments();
		ArrayList<Document> list= new ArrayList<Document>();
		for(Document d: set)
		{
			list.add(d);
		}
		return list;
	}
	
	@Transactional //get the list of students who wprked for an employer
	public ArrayList<Student> getArchivedInterns (String employerUserName){
		Employer em= getEmployer(employerUserName);
		if(em==null) {return null;}
		ArrayList<Student> list= new ArrayList<Student>();
		for(Student s: em.getArchivedInterns()) {
			list.add(s);
		}
		return list;
	}
	
	@Transactional //get the personal documents of all interns
	public HashMap<String,ArrayList<Document>> getInternsDocuments (String employerUserName){
		ArrayList<Student> interns =getArchivedInterns(employerUserName);
		if(interns==null ) {return null;}
		HashMap<String,ArrayList<Document>> map= new HashMap<String,ArrayList<Document>>();
		
		for(Student intern: interns) {
			if(intern.isAllowCV() && intern.isAllowTranscript()) {
				map.put(intern.getUsername(),getPersonalDocumentsByStudent(intern.getUsername()));
			}
			else if (intern.isAllowCV()) {
				ArrayList<Document> docs=getPersonalDocumentsByStudent(intern.getUsername());
				Document CVdoc=null;
				for(Document d: docs){
					if(d.getType()==DocumentType.CV) {CVdoc=d;break;}
				}
				ArrayList<Document> toAdd= new ArrayList<Document>();
				toAdd.add(CVdoc);
				map.put(intern.getUsername(),toAdd);
				
			}
		}
		return map;
	}
	
	@Transactional
	public ArrayList<Document> getInternDocuments(String employerUserName, String studentUserName){
		if(employerUserName==null || studentUserName==null) {return null;}
		
		return getInternsDocuments(employerUserName).get(studentUserName);
	}
	
	@Transactional
	public boolean login(String username, String password) {
		CoopUser u=findCoopUserByUsername(username);
		if(u==null) {return false;}
		if(u.getPassword().equals(password)) {return true;}
		return false;
	}
	
	@Transactional //find a coopjob between an employer and a student
	public CoopJob findCoopJobByEmployerAndStudentAndEndDate(String employerName, String studentName, Date endDate)
	{
		if(studentName==null|| employerName==null) {return null;}
		Student s=getStudent(studentName);
		Employer em=getEmployer(employerName);
		if(s==null || em==null) {return null;}
		Set<CoopJob> set= s.getCoopJobs();
		CoopJob j=null;
		for (CoopJob job: set)
		{
			if(job.getEmployer().equals(em) && endDate.equals(job.getEndDate())) {j=job;break;}
		}
		return j;
	}
	
	@Transactional
	public ArrayList<EventNotification> getEventsInXDays(int x){
		if(x<=0) {return null;}
		List<EventNotification>list1= findAllEventNotifications();
		ArrayList<EventNotification>list2= new ArrayList<EventNotification>();
		Date limit= new Date(System.currentTimeMillis()+(x+1)*86400000); //current date + (x+1) days
		for(EventNotification e : list1) {
			if(e.getDate().compareTo(limit)<0) {
				list2.add(e);
			}
		}
		return list2;
	}
	
	
}
