package ca.mcgill.ecse321.coop.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import ca.mcgill.ecse321.coop.service.*;
import ca.mcgill.ecse321.coop.dao.*;
import ca.mcgill.ecse321.coop.model.*;
import ca.mcgill.ecse321.coop.dto.*;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@CrossOrigin(origins = "*")
@RestController
public class CoopController {
	
	 @Autowired
	 private CoopSystemService service;
	
	
	
	@RequestMapping("/")
	  public CoopSystemDto greeting(){
	    return convertToDto(service.createCoopSystem());
	  }
	
	@GetMapping(value = { "/login", "/login/" })
	public boolean login( @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) throws IllegalArgumentException {
		// @formatter:on
		return service.login(username,password);
		
	}
	
	@PostMapping(value = { "/coopsystem", "/coopsystem/" })
	public CoopSystemDto createCoopSystem() throws IllegalArgumentException {
		// @formatter:on
		CoopSystem sys=service.createCoopSystem();
		if(sys==null) {throw new IllegalArgumentException();}
		return convertToDto(sys); 
	}
	
	@GetMapping(value = { "/coopsystem", "/coopsystem/" })
	public CoopSystemDto getCoopSystem() throws IllegalArgumentException {
		// @formatter:on
		CoopSystem sys=service.getCoopSystem(); 
		if(sys==null) {throw new IllegalArgumentException();}
		return convertToDto(sys); 
	}
	
	@DeleteMapping(value = { "/coopsystem", "/coopsystem/" })
	public void deleteSystem() throws IllegalArgumentException {
		// @formatter:on
		service.deleteSystem();  
	}
	
	@PostMapping(value = { "/students/{userName}", "/students/{userName}/" })
	public StudentDto createStudent(@PathVariable("userName") String userName) throws IllegalArgumentException {
		// @formatter:on
		Student s=service.createStudent(userName);
		if(s==null) {throw new IllegalArgumentException();}
		return convertToDtob(s); 
	}
	
	@GetMapping(value = { "/students/{userName}", "/students/{userName}/" })
	public StudentDto getStudent(@PathVariable("userName") String userName) throws IllegalArgumentException {
		// @formatter:on
		Student sys=service.findStudentByUsername(userName);
		if(sys==null) {throw new IllegalArgumentException();}
		return convertToDtob(sys); 
	}
	
	@GetMapping(value = { "/coopusers/{userName}", "/coopusers/{userName}/" })
	public CoopUserDto getCoopUser(@PathVariable("userName") String userName) throws IllegalArgumentException {
		// @formatter:on
		CoopUser sys=service.findCoopUserByUsername(userName);
		if(sys==null) {throw new IllegalArgumentException();}
		return convertToDtoa(sys); 
	}
	
	@GetMapping(value = { "/students", "/students/" })
	public ArrayList<StudentDto> getStudents() throws IllegalArgumentException {
		// @formatter:on
		ArrayList<StudentDto> sys=new ArrayList<StudentDto>();
		for(Student s: service.getAllStudents()) {
			sys.add(convertToDtob(s));
		}
		return sys; 
	}
	
	@GetMapping(value = { "/coopusers", "/coopusers/" })
	public ArrayList<CoopUserDto> getCoopUsers() throws IllegalArgumentException {
		// @formatter:on
		ArrayList<CoopUserDto> sys=new ArrayList<CoopUserDto>();
		for(CoopUser s: service.getAllCoopUsers()) {
			sys.add(convertToDtoa(s));
		}
		return sys; 
	}
	
	@PostMapping(value = { "/employers/{userName}", "/employers/{userName}/" })
	public EmployerDto createEmployer(@PathVariable("userName") String userName) throws IllegalArgumentException {
		// @formatter:on
		Employer e=service.createEmployer(userName);
		if(e==null) {throw new IllegalArgumentException();}
		return convertToDtoc(e); 
	}
	
	@GetMapping(value = { "/employers/{userName}", "/employers/{userName}/" })
	public EmployerDto getEmployer(@PathVariable("userName") String userName) throws IllegalArgumentException {
		// @formatter:on
		Employer e=service.findEmployerByUsername(userName);
		if(e==null) {throw new IllegalArgumentException();}
		return convertToDtoc(e); 
	}
	
	@GetMapping(value = { "/employers", "/employers/" })
	public ArrayList<EmployerDto> getEmloyers() throws IllegalArgumentException {
		// @formatter:on
		ArrayList<EmployerDto> sys=new ArrayList<EmployerDto>();
		for(Employer s: service.getAllEmployers()) {
			sys.add(convertToDtoc(s));
		}
		return sys; 
	}
	
	@DeleteMapping(value = { "/students/{username}", "/students/{username}/" })
	public void deleteStudent(@PathVariable("username") String username) throws IllegalArgumentException {
		// @formatter:on
		service.deleteStudent(username);  
	}
	
	@DeleteMapping(value = { "/employers/{username}", "/employers/{username}/" })
	public void deleteEmployer(@PathVariable("username") String username) throws IllegalArgumentException {
		// @formatter:on
		service.deleteEmployer(username);  
	}
	
	@DeleteMapping(value = { "/coopusers/{username}", "/coopusers/{username}/" })
	public void deleteCoopUser(@PathVariable("username") String username) throws IllegalArgumentException {
		// @formatter:on
		service.deleteCoopUser(username);  
	}
	
	@PostMapping(value = { "/setPassword", "/setPassword/" })
	public CoopUserDto setPassword(@RequestParam(name = "Username") String userName,
			@RequestParam(name = "Password") String password) throws IllegalArgumentException {
		// @formatter:on
		service.setPassword(userName,password);
		CoopUser s= service.findCoopUserByUsername(userName);
		if(s==null) {throw new IllegalArgumentException();}
		return convertToDtoa(s); 
	}
	
	@PostMapping(value = { "/studentPreferences", "/studentPreferences/" })
	public StudentDto setPreferences(@RequestParam(name = "StudentName") String studentName,
			@RequestParam(name = "AllowCV") boolean a,
			@RequestParam(name = "AllowTranscript") boolean b) throws IllegalArgumentException {
		// @formatter:on
		service.setStudentPermissions(studentName,a,b);
		Student s= service.findStudentByUsername(studentName);
		if(s==null) {throw new IllegalArgumentException();}
		return convertToDtob(s); 
	}
	
	@PostMapping(value = { "/studentPersonal", "/studentPersonal/" })
	public StudentDto setPreferences(@RequestParam(name = "StudentName") String studentName,
			@RequestParam(name = "PersonalDocumentsIds") ArrayList<String> personalDocumentsIds) throws IllegalArgumentException {
		// @formatter:on
		service.setPersonalDocuments(studentName,personalDocumentsIds);
		Student s= service.findStudentByUsername(studentName);
		if(s==null) {throw new IllegalArgumentException();}
		return convertToDtob(s); 
	}
	
	@PostMapping(value = { "/createDocument", "/createDocument/" })
	public void createDocument(@RequestParam(name = "DocumentId") String docId,
			@RequestParam(name = "UserName") String userName,
			@RequestParam(name = "Type") DocumentType type
			) throws IllegalArgumentException {
		// @formatter:on
		service.createDocument(docId,userName,type);	
	}
	
	@GetMapping(value = { "/getDocument", "/getDocument/" })
	public DocumentDto getDocument(@RequestParam(name = "DocumentId") String docId) throws IllegalArgumentException {
		// @formatter:on
		return convertToDto(service.findDocumentByDocumentId(docId));
		
	}
	
	@GetMapping(value = { "/getDocuments", "/getDocuments/" })
	public ArrayList<DocumentDto> getDocuments(@RequestParam(name = "AuthorId") String authorId) throws IllegalArgumentException {
		// @formatter:on
		ArrayList<DocumentDto> list= new ArrayList<DocumentDto>();
		for(Document d: service.findDocumentsByAuthor(authorId)) {
			list.add(convertToDto(d));
		}
		return list;
		
	}
	
	@DeleteMapping(value = { "/deleteDocument", "/deleteDocument/" })
	public void deleteDocument(@RequestParam(name = "DocumentId") String docId) throws IllegalArgumentException {
		// @formatter:on
		service.deleteDocument(docId);
		
	}
	
	@PostMapping(value = { "/createEventNotification", "/createeventNotification/" })
	public void createEventNotification(@RequestParam(name = "EventNotificationId") String enId
			) throws IllegalArgumentException {
		// @formatter:on
		service.createEventNotification(enId);	
	}
	
	@PostMapping(value = { "/setEventSettings", "/setEventSettings/" })
	public void setEventNotificationSettings(@RequestParam(name = "EventNotificationId") String enId,
			@RequestParam(name = "Type") Event type, @RequestParam(name = "Location") String location,
			@RequestParam(name = "Date") String date, @RequestParam(name = "StartTime") String startTime,
			@RequestParam(name = "EndTime") String endTime
			) throws IllegalArgumentException {
		// @formatter:on
		service.setEventNotificationSettings(enId,type,location,Date.valueOf(date),Time.valueOf(startTime),
				Time.valueOf(endTime));	
		
	}
	
	@GetMapping(value = { "/getEventNotifications", "/getEventNotifications/" })
	public ArrayList<EventNotificationDto> getEventNotifcations() throws IllegalArgumentException {
		// @formatter:on
		ArrayList<EventNotificationDto> list= new ArrayList<EventNotificationDto>();
		for(EventNotification d: service.findAllEventNotifications()) {
			list.add(convertToDto(d));
		}
		return list;
		
	}
	
	@GetMapping(value = { "/getEventNotification", "/getEventNotification/" })
	public EventNotificationDto getEventNotification(@RequestParam(name = "EventNotificationId") String eventNotificationId) throws IllegalArgumentException {
		// @formatter:on
		return convertToDto(service.findEventNotificationByName(eventNotificationId));
		
	}
	
	
	
	private CoopSystemDto convertToDto(CoopSystem sys) {
		if(sys==null) {return null;}
		CoopSystemDto sysD= new CoopSystemDto();
		sysD.id= sys.getId();
		
		sysD.eventNotificationsNames=new HashSet<String>();
		for(EventNotification en: service.findAllEventNotifications()) {
			sysD.eventNotificationsNames.add(en.getName());
		}
		
		sysD.coopJobsIds=new HashSet<String>();
		for(CoopJob j: service.getAllCoopJobs()) {
			sysD.coopJobsIds.add(j.getJobId());
		}
		
		sysD.messagesIds=new HashSet<String>();
		for(Message m: service.getAllMessages()) {
			sysD.messagesIds.add(m.getMessageId());
		}
		
		sysD.coopUsersNames=new HashSet<String>();
		for(CoopUser u: service.getAllCoopUsers()) {
			sysD.coopUsersNames.add(u.getUsername());
		}
		
		sysD.documentsIds=new HashSet<String>();
		for(Document d: service.getAllDocuments()) {
			sysD.documentsIds.add(d.getDocumentId());
		}
		return sysD;
	}
	
	private EventNotificationDto convertToDto(EventNotification e) {
		if(e==null) {return null;}
		EventNotificationDto eD=new EventNotificationDto();
		eD.coopSystemName=e.getCoopSystem().getId();
		eD.coopSystemName=e.getName();
		eD.type=e.getType().toString();
		eD.location=e.getLocation();
		eD.date=(Date)e.getDate().clone();
		eD.startTime=(Time)e.getStartTime().clone();
		eD.endTime=(Time)e.getEndTime().clone();
		return eD;
	}
	
	private CoopJobDto convertToDto(CoopJob job) {
		if(job==null) {return null;}
		CoopJobDto jobD=new CoopJobDto();
		jobD.internName=job.getIntern().getUsername();
		jobD.employerName=job.getEmployer().getUsername();
		jobD.coopSystemName=job.getCoopSystem().getId();
		jobD.name=job.getName();
		jobD.startDate=(Date) job.getStartDate().clone();
		jobD.endDate=(Date) job.getEndDate().clone();
		jobD.jobId= job.getJobId();
		jobD.state=job.getState().toString();
		jobD.coopJobDocumentsIds=new HashSet<String>();
		for(Document d: service.getCoopJobDocuments(job.getJobId())) {
			jobD.coopJobDocumentsIds.add(d.getDocumentId());
		}
		return jobD;
	}
	
	private CoopUserDto convertToDtoa(CoopUser user) {
		if(user==null) {return null;}
		else if(user instanceof Student) {return (CoopUserDto) convertToDtob((Student) user);}
		else {return (CoopUserDto) convertToDtoc((Employer) user);}
		
	}
	
	private DocumentDto convertToDto(Document doc) {
		if(doc==null) {return null;}
		DocumentDto docD=new DocumentDto();
		docD.documentId=doc.getDocumentId();
		docD.size= doc.getSize();
		docD.coopSystemName=doc.getCoopSystem().getId();
		docD.authorName=doc.getAuthor().getUsername();
		docD.type=doc.getType().toString();
		docD.submissionDate=(Date)doc.getSubmissionDate().clone();
		docD.submissionTime=(Time)doc.getSubmissionTime().clone();
		return docD;
	}
	
	private EmployerDto convertToDtoc(Employer u) {
		if(u==null) {return null;}
		EmployerDto uD= new EmployerDto();
		uD.coopSystemName=u.getCoopSystem().getId();
		uD.username=u.getUsername();
		uD.password=u.getPassword();
		
		uD.authoredDocumentsIds= new HashSet<String>();
		for(Document doc : service.findDocumentsByAuthor(u.getUsername())){
			uD.authoredDocumentsIds.add(doc.getDocumentId());
		}
		
		uD.sentMessagesIds= new HashSet<String>();
		for(Message m : service.findMessagesBySender(u.getUsername())){
			uD.sentMessagesIds.add(m.getMessageId());
		}
		
		uD.receivedMessagesIds= new HashSet<String>();
		for(Message m : service.findMessagesByReceiver(u.getUsername())){
			uD.receivedMessagesIds.add(m.getMessageId());
		}
		
		uD.coopJobsIds= new HashSet<String>();
		for(CoopJob j : service.findCoopJobsByStudent(u.getUsername())){
			uD.coopJobsIds.add(j.getJobId());
		}
		
		uD.eventNotificationsNames= new HashSet<String>();
		for(EventNotification e : service.findAllEventNotifications()){
			uD.eventNotificationsNames.add(e.getName());
		}
		
		uD.archivedInternsNames= new HashSet<String>();
		for(Student s : service.getArchivedInterns(u.getUsername())){
			uD.archivedInternsNames.add(s.getUsername());
		}
		return uD;
	}
	
	private MessageDto convertToDto(Message m) {
		if(m==null) {return null;}
		MessageDto mD=new MessageDto();
		mD.coopSystemName=m.getCoopSystem().getId();
		mD.content=m.getContent();
		mD.senderName=m.getSender().getUsername();
		mD.receiverName=m.getReceiver().getUsername();
		mD.messageId=m.getMessageId();
		mD.date=(Date) m.getDate().clone();
		mD.time=(Time) m.getTime().clone();
		
		mD.attachementsIds= new HashSet<String>();
		for(Document doc : service.getAttachements(m.getMessageId())){
			mD.attachementsIds.add(doc.getDocumentId());
		}
		
		return mD;
	}
	
	private StudentDto convertToDtob(Student u) {
		if(u==null) {return null;}
		StudentDto uD= new StudentDto();
		uD.coopSystemName=u.getCoopSystem().getId();
		uD.username=u.getUsername();
		uD.password=u.getPassword();
		
		uD.authoredDocumentsIds= new HashSet<String>();
		for(Document doc : service.findDocumentsByAuthor(u.getUsername())){
			uD.authoredDocumentsIds.add(doc.getDocumentId());
		}
		
		uD.sentMessagesIds= new HashSet<String>();
		for(Message m : service.findMessagesBySender(u.getUsername())){
			uD.sentMessagesIds.add(m.getMessageId());
		}
		
		uD.receivedMessagesIds= new HashSet<String>();
		for(Message m : service.findMessagesByReceiver(u.getUsername())){
			uD.receivedMessagesIds.add(m.getMessageId());
		}
		
		uD.allowCV=u.isAllowCV();
		uD.allowTranscript=u.isAllowTranscript();
		
		uD.personalDocumentsIds= new HashSet<String>();
		for(Document doc : service.getPersonalDocumentsByStudent(u.getUsername())){
			uD.personalDocumentsIds.add(doc.getDocumentId());
		}
		
		uD.coopJobsIds= new HashSet<String>();
		for(CoopJob j : service.findCoopJobsByStudent(u.getUsername())){
			uD.coopJobsIds.add(j.getJobId());
		}
		return uD;
	}
	

}
