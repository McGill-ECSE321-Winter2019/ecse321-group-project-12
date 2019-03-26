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
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

@CrossOrigin(origins = "*")
@RestController
public class CoopController {
	
	 @Autowired
	 private CoopSystemService service;
	
	
	
	@RequestMapping("/")
	  public CoopSystemDto greeting(){ //create the coopsystem
	    return convertToDto(service.createCoopSystem());
	  }
	
	@GetMapping(value = { "/login", "/login/" }) //login
	public boolean login( @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) throws IllegalArgumentException {
		// @formatter:on
		return service.login(username,password);
		
	}
	
	@PostMapping(value = { "/coopsystem", "/coopsystem/" }) // create a coopsystem
	public CoopSystemDto createCoopSystem() throws IllegalArgumentException {
		// @formatter:on
		CoopSystem sys=service.createCoopSystem();
		if(sys==null) {throw new IllegalArgumentException();}
		return convertToDto(sys); 
	}
	
	@GetMapping(value = { "/coopsystem", "/coopsystem/" }) //retrive the info from a coopsystem
	public CoopSystemDto getCoopSystem() throws IllegalArgumentException {
		// @formatter:on
		CoopSystem sys=service.getCoopSystem(); 
		if(sys==null) {throw new IllegalArgumentException();}
		return convertToDto(sys); 
	}
	
	@DeleteMapping(value = { "/coopsystem", "/coopsystem/" }) //delete the system (clear the database)
	public void deleteSystem() throws IllegalArgumentException {
		// @formatter:on
		service.deleteSystem();  
	}
	
	@PostMapping(value = { "/students/{userName}", "/students/{userName}/" }) //create a new student
	public StudentDto createStudent(@PathVariable("userName") String userName) throws IllegalArgumentException {
		// @formatter:on
		Student s=service.createStudent(userName);
		if(s==null) {throw new IllegalArgumentException();}
		return convertToDtob(s); 
	}
	
	@GetMapping(value = { "/students/{userName}", "/students/{userName}/" }) // retieve a student by its username
	public StudentDto getStudent(@PathVariable("userName") String userName) throws IllegalArgumentException {
		// @formatter:on
		Student sys=service.findStudentByUsername(userName);
		if(sys==null) {throw new IllegalArgumentException();}
		return convertToDtob(sys); 
	}
	
	@GetMapping(value = { "/coopusers/{userName}", "/coopusers/{userName}/" }) //retrieve a user by its username
	public CoopUserDto getCoopUser(@PathVariable("userName") String userName) throws IllegalArgumentException {
		// @formatter:on
		CoopUser sys=service.findCoopUserByUsername(userName);
		if(sys==null) {throw new IllegalArgumentException();}
		return convertToDtoa(sys); 
	}
	
	@GetMapping(value = { "/students", "/students/" }) //retrieve all students
	public ArrayList<StudentDto> getStudents() throws IllegalArgumentException {
		// @formatter:on
		ArrayList<StudentDto> sys=new ArrayList<StudentDto>();
		for(Student s: service.getAllStudents()) {
			sys.add(convertToDtob(s));
		}
		return sys; 
	}
	
	@GetMapping(value = { "/coopusers", "/coopusers/" }) //retieve allcoopusers
	public ArrayList<CoopUserDto> getCoopUsers() throws IllegalArgumentException {
		// @formatter:on
		ArrayList<CoopUserDto> sys=new ArrayList<CoopUserDto>();
		for(CoopUser s: service.getAllCoopUsers()) {
			sys.add(convertToDtoa(s));
		}
		return sys; 
	}
	
	@PostMapping(value = { "/employers/{userName}", "/employers/{userName}/" }) //create a new employer
	public EmployerDto createEmployer(@PathVariable("userName") String userName) throws IllegalArgumentException {
		// @formatter:on
		Employer e=service.createEmployer(userName);
		if(e==null) {throw new IllegalArgumentException();}
		return convertToDtoc(e); 
	}
	
	@GetMapping(value = { "/employers/{userName}", "/employers/{userName}/" }) //retrive an employer by itsudername
	public EmployerDto getEmployer(@PathVariable("userName") String userName) throws IllegalArgumentException {
		// @formatter:on
		Employer e=service.findEmployerByUsername(userName);
		if(e==null) {throw new IllegalArgumentException();}
		return convertToDtoc(e); 
	}
	
	@GetMapping(value = { "/employers", "/employers/" }) //retiee all employers
	public ArrayList<EmployerDto> getEmloyers() throws IllegalArgumentException {
		// @formatter:on
		ArrayList<EmployerDto> sys=new ArrayList<EmployerDto>();
		for(Employer s: service.getAllEmployers()) {
			sys.add(convertToDtoc(s));
		}
		return sys; 
	}
	
	@DeleteMapping(value = { "/students/{username}", "/students/{username}/" }) //delete a student
	public void deleteStudent(@PathVariable("username") String username) throws IllegalArgumentException {
		// @formatter:on
		service.deleteStudent(username);  
	}
	
	@DeleteMapping(value = { "/employers/{username}", "/employers/{username}/" }) //delete an employer
	public void deleteEmployer(@PathVariable("username") String username) throws IllegalArgumentException {
		// @formatter:on
		service.deleteEmployer(username);  
	}
	
	@DeleteMapping(value = { "/coopusers/{username}", "/coopusers/{username}/" }) //delete a user
	public void deleteCoopUser(@PathVariable("username") String username) throws IllegalArgumentException {
		// @formatter:on
		service.deleteCoopUser(username);  
	}
	
	@PostMapping(value = { "/setPassword", "/setPassword/" }) //set the password
	public CoopUserDto setPassword(@RequestParam(name = "Username") String userName,
			@RequestParam(name = "Password") String password) throws IllegalArgumentException {
		// @formatter:on
		service.setPassword(userName,password);
		CoopUser s= service.findCoopUserByUsername(userName);
		if(s==null) {throw new IllegalArgumentException();}
		return convertToDtoa(s); 
	}
	
	@PostMapping(value = { "/studentPreferences", "/studentPreferences/" }) //set privacy settings
	public StudentDto setPreferences(@RequestParam(name = "StudentName") String studentName,
			@RequestParam(name = "AllowCV") boolean a,
			@RequestParam(name = "AllowTranscript") boolean b) throws IllegalArgumentException {
		// @formatter:on
		service.setStudentPermissions(studentName,a,b);
		Student s= service.findStudentByUsername(studentName);
		if(s==null) {throw new IllegalArgumentException();}
		return convertToDtob(s); 
	}
	
	@PostMapping(value = { "/studentPersonal", "/studentPersonal/" }) //set the student personal documents
	public StudentDto setPreferences(@RequestParam(name = "StudentName") String studentName,
			@RequestParam(name = "PersonalDocumentsIds") ArrayList<String> personalDocumentsIds) throws IllegalArgumentException {
		// @formatter:on
		service.setPersonalDocuments(studentName,personalDocumentsIds);
		Student s= service.findStudentByUsername(studentName);
		if(s==null) {throw new IllegalArgumentException();}
		return convertToDtob(s); 
	}
	
	@PostMapping(value = { "/createDocument", "/createDocument/" }) //create a document
	public void createDocument(@RequestParam(name = "DocumentId") String docId,
			@RequestParam(name = "UserName") String userName,
			@RequestParam(name = "Type") DocumentType type
			) throws IllegalArgumentException {
		// @formatter:on
		service.createDocument(docId,userName,type);	
	}
	
	@GetMapping(value = { "/getDocument", "/getDocument/" }) //get a documet by its id
	public DocumentDto getDocument(@RequestParam(name = "DocumentId") String docId) throws IllegalArgumentException {
		// @formatter:on
		return convertToDto(service.findDocumentByDocumentId(docId));
		
	}
	
	@GetMapping(value = { "/getDocuments", "/getDocuments/" }) //get a lost of documents by the author
	public ArrayList<DocumentDto> getDocuments(@RequestParam(name = "AuthorId") String authorId) throws IllegalArgumentException {
		// @formatter:on
		ArrayList<DocumentDto> list= new ArrayList<DocumentDto>();
		for(Document d: service.findDocumentsByAuthor(authorId)) {
			list.add(convertToDto(d));
		}
		return list;
		
	}
	
	@DeleteMapping(value = { "/deleteDocument", "/deleteDocument/" }) //delete a document
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
	
	@GetMapping(value = { "/getEventNotifications", "/getEventNotifications/" }) //get all theevent notifications
	public ArrayList<EventNotificationDto> getEventNotifcations() throws IllegalArgumentException {
		// @formatter:on
		ArrayList<EventNotificationDto> list= new ArrayList<EventNotificationDto>();
		for(EventNotification d: service.findAllEventNotifications()) {
			list.add(convertToDto(d));
		}
		return list;
		
	}
	
	@GetMapping(value = { "/getEventNotification", "/getEventNotification/" }) //get an event notification by id
	public EventNotificationDto getEventNotification(@RequestParam(name = "EventNotificationId") String eventNotificationId) throws IllegalArgumentException {
		// @formatter:on
		return convertToDto(service.findEventNotificationByName(eventNotificationId));
		
	}
	
	@GetMapping(value = { "/getEventNotificationsinXdays", "/getEventNotificationsinXdays/" }) //get an event notification in the coming x days
	public ArrayList<EventNotificationDto> getEventNotificationinXdays(@RequestParam(name = "Number of days") int x) throws IllegalArgumentException {
		// @formatter:on
		ArrayList<EventNotificationDto> list= new ArrayList<EventNotificationDto>();
		for(EventNotification d: service.getEventsInXDays(x)) {
			list.add(convertToDto(d));
		}
		return list;
	}
	
	@DeleteMapping(value = { "/Event", "/Event/" }) //delete an event notification from the database
	public void deleteEventNotification(@RequestParam(name = "EentId") String mId) throws IllegalArgumentException {
		// @formatter:on
		service.deleteEventNotification(mId);
		
	}
	
	
	@PostMapping(value = { "/newMessage", "/newMessage/" }) //create a message
	public void createAndSendMessage(@RequestParam(name = "MessageId") String mId,
			@RequestParam(name = "SenderName") String sender, @RequestParam(name = "ReceiverName") String receiver,
			@RequestParam(name = "Content") String content, @RequestParam(name = "ListofAttachementsIds") ArrayList<String> attachementsIds
			) throws IllegalArgumentException {
		// @formatter:on
		service.createMessage(mId,sender,receiver,content,attachementsIds);	
		
	}
	
	@GetMapping(value = { "/Message", "/Message/" }) //get a message by id
	public MessageDto getMessage(@RequestParam(name = "MessageId") String mId) throws IllegalArgumentException {
		// @formatter:on
		return convertToDto(service.findMessageByMessageId(mId));
		
	}
	
	
	@DeleteMapping(value = { "/Message", "/Message/" }) //delete a message by id
	public void deleteMessage(@RequestParam(name = "MessageId") String mId) throws IllegalArgumentException {
		// @formatter:on
		service.deleteMessage(mId);
		
	}
	
	@GetMapping(value = { "/SentMessages", "/SentMessages/" }) //get messages by sender
	public ArrayList<MessageDto> getSentMessages(@RequestParam(name = "SenderName") String sender) throws IllegalArgumentException {
		// @formatter:on
		ArrayList<MessageDto> list= new ArrayList<MessageDto>();
		for(Message m: service.findMessagesBySender(sender)) {
			list.add(convertToDto(m));
		}
		return list;
	}
	
	@GetMapping(value = { "/ReceivedMessages", "/ReceivedMessages/" }) //get messages by receiver
	public ArrayList<MessageDto> getReceivedMessages(@RequestParam(name = "ReceiverName") String receiver) throws IllegalArgumentException {
		// @formatter:on
		ArrayList<MessageDto> list= new ArrayList<MessageDto>();
		for(Message m: service.findMessagesByReceiver(receiver)) {
			list.add(convertToDto(m));
		}
		return list;
	}
	
	@GetMapping(value = { "/OneWayMessages", "/OneWayMessages/" }) //get messages by sender and receiver
	public ArrayList<MessageDto> getOneWay(@RequestParam(name = "SenderName") String sender,
			@RequestParam(name = "ReceiverName") String receiver) throws IllegalArgumentException {
		// @formatter:on
		ArrayList<MessageDto> list= new ArrayList<MessageDto>();
		for(Message m: service.findMessagesBySenderAndReceiver(sender,receiver)) {
			list.add(convertToDto(m));
		}
		return list;
	}
	
	
	@PostMapping(value = { "/newJob", "/newJob/" }) //create a new coopjob
	public CoopJobDto createJob(@RequestParam(name = "JobId") String jobId,
			@RequestParam(name = "EmployerName") String employerName, @RequestParam(name = "StudentName") String studentName
			) throws IllegalArgumentException {
		// @formatter:on
		return convertToDto(service.createCoopJob(jobId,employerName,studentName));	
		
	}
	
	@GetMapping(value = { "/CoopJob", "/CoopJob/" }) //get a coopjob by id
	public CoopJobDto getJob(@RequestParam(name = "JobId") String jobId) throws IllegalArgumentException {
		// @formatter:on
		return convertToDto(service.findCoopJobByJobId(jobId));
		
	}
	
	@GetMapping(value = { "/JobsByEmployer", "/JobsByEmployer/" }) //get coopjobs by employer
	public ArrayList<CoopJobDto> getCoopJobsByEmployer(@RequestParam(name = "EmployerName") String employerName) throws IllegalArgumentException {
		// @formatter:on
		ArrayList<CoopJobDto> list= new ArrayList<CoopJobDto>();
		for(CoopJob j: service.findCoopJobsByEmployer(employerName)) {
			list.add(convertToDto(j));
		}
		return list;
	}
	
	
	@GetMapping(value = { "/JobsByStudent", "/JobsByStudent/" }) //get coopjobs by employer
	public ArrayList<CoopJobDto> getCoopJobsByStudent(@RequestParam(name = "StudentName") String studentName) throws IllegalArgumentException {
		// @formatter:on
		ArrayList<CoopJobDto> list= new ArrayList<CoopJobDto>();
		for(CoopJob j: service.findCoopJobsByStudent(studentName)) {
			list.add(convertToDto(j));
		}
		return list;
	}
	
	@GetMapping(value = { "/JobsByEmployerAndStudent", "/JobsByEmployerAndStudent/" }) //get coopjobs by employer
	public ArrayList<CoopJobDto> getCoopJobsByEmployerAndStudent(@RequestParam(name = "EmployerName") String employerName,
			@RequestParam(name = "StudentName") String studentName) throws IllegalArgumentException {
		// @formatter:on
		ArrayList<CoopJobDto> list= new ArrayList<CoopJobDto>();
		for(CoopJob j: service.findCoopJobsByEmployerAndStudent(employerName,studentName)) {
			list.add(convertToDto(j));
		}
		return list;
	}
	
	@GetMapping(value = { "/JobByEmployerAndStudentAndEndDate", "/JobByEmployerAndStudentAndEndDate/" }) //get a coopjob by id
	public CoopJobDto getJobByEmployerAndStudentAndEndDate(@RequestParam(name = "EmployerName") String employerName,
			@RequestParam(name = "StudentName") String studentName,
			@RequestParam(name = "EndDate") String endDate) throws IllegalArgumentException{
			// @formatter:on
		return convertToDto(service.findCoopJobByEmployerAndStudentAndEndDate(employerName,studentName,Date.valueOf(endDate)));
		
	}
	
	@PostMapping(value = { "/JobSettings", "/JobSettings/" }) //set the coopjob settings
	public void setJobSettings(@RequestParam(name = "JobId") String jobId,
			@RequestParam(name = "StartDate") String startDate, @RequestParam(name = "EndDate") String endDate,
			@RequestParam(name = "JobName") String name,
			@RequestParam(name = "State") CoopState state
			) throws IllegalArgumentException {
		// @formatter:on
		service.setCoopJobSettings(jobId,Date.valueOf(startDate),Date.valueOf(endDate),name,state);	
		
	}
	
	@PostMapping(value = { "/JobState", "/JobState/" }) //set the coopjob state
	public void setJobState(@RequestParam(name = "JobId") String jobId,
			@RequestParam(name = "State") CoopState state
			) throws IllegalArgumentException {
		// @formatter:on
		service.setCoopJobState(jobId,state);	
		
	}
	
	@PostMapping(value = { "/addDocumentToJob", "/addDocumentToJob/" }) //add document to coop job
	public void addDocumentJob(@RequestParam(name = "JobId") String jobId,
			@RequestParam(name = "DocumentId") String documentId
			) throws IllegalArgumentException {
		// @formatter:on
		service.addDocumentToCoopJob(jobId,documentId);	
		
	}
	
	@GetMapping(value = { "/AuthoredDocuments", "/AuthoredDocuments/" }) //get list of authored documents
	public ArrayList<DocumentDto> getAuthoredDocuments(@RequestParam(name = "userName") String username) throws IllegalArgumentException{
			// @formatter:on
		ArrayList<DocumentDto> list= new ArrayList<DocumentDto>();
		for(Document d: service.getAuthoredDocuments(username)) {
			list.add(convertToDto(d));
		}
		return list;
	}
	
	
	@GetMapping(value = { "/Attachements", "/Attachements/" }) //get list of attachements in message
	public ArrayList<DocumentDto> getAttachements(@RequestParam(name = "MessageId") String messageId) throws IllegalArgumentException{
			// @formatter:on
		ArrayList<DocumentDto> list= new ArrayList<DocumentDto>();
		for(Document d: service.getAttachements(messageId)) {
			list.add(convertToDto(d));
		}
		return list;
	}
	
	@GetMapping(value = { "/CoopJobDocuments", "/CoopJobDocuments/" }) //get list of documents of coopjob
	public ArrayList<DocumentDto> getCoopJobDocuments(@RequestParam(name = "JobId") String jobId) throws IllegalArgumentException{
			// @formatter:on
		ArrayList<DocumentDto> list= new ArrayList<DocumentDto>();
		for(Document d: service.getCoopJobDocuments(jobId)) {
			list.add(convertToDto(d));
		}
		return list;
	}
	
	@GetMapping(value = { "/CoopJobDocumentsType", "/CoopJobDocumentsType/" }) //get list of specific-type documents of coopjob
	public ArrayList<DocumentDto> getCoopJobDocumentsType(@RequestParam(name = "JobId") String jobId,
			@RequestParam(name = "DocumentType") DocumentType type) throws IllegalArgumentException{
			// @formatter:on
		ArrayList<DocumentDto> list= new ArrayList<DocumentDto>();
		for(Document d: service.getCoopJobDocuments(jobId)) {
			if(d.getType()==type) {list.add(convertToDto(d));}
		}
		return list;
	}
	
	@GetMapping(value = { "/ArchivedInterns", "/ArchivedInterns/" }) //get list of students who worked at this emplyoer
	public ArrayList<StudentDto> getArchivedInterns(@RequestParam(name = "EmloyerName") String employerName
			) throws IllegalArgumentException{
			// @formatter:on
		ArrayList<StudentDto> list= new ArrayList<StudentDto>();
		for(Student t: service.getArchivedInterns(employerName)) {
			list.add(convertToDtob(t));
		}
		return list;
	}
	
	@GetMapping(value = { "/ArchivedInternDocuments", "/ArchivedInternDocuments/" }) //get personal documents of a tsudent who worked at this employer
	public ArrayList<DocumentDto> getArchivedInternDocuments(@RequestParam(name = "EmloyerName") String employerName,
			
			@RequestParam(name = "StudentName") String studentName) throws IllegalArgumentException{
			// @formatter:on
		ArrayList<DocumentDto> list= new ArrayList<DocumentDto>();
		for(Document d: service.getInternDocuments(employerName,studentName)) {
			list.add(convertToDto(d));
		}
		return list;
	}
	
	@GetMapping(value = { "/ArchivedInternsDocuments", "/ArchivedInternsDocuments/" }) //get personal documents of all students who worked at this employer
	public ArrayList<ArrayList<DocumentDto>> getArchivedInternsDocuments(@RequestParam(name = "EmloyerName") String employerName
			) throws IllegalArgumentException{
			// @formatter:on
		ArrayList<ArrayList<DocumentDto>> list= new ArrayList<ArrayList<DocumentDto>>();
		ArrayList<DocumentDto> dl=new ArrayList<DocumentDto>();
		for(ArrayList<Document> d: service.getInternsDocuments(employerName).values()) {
			if(d!=null) {
				dl=new ArrayList<DocumentDto>();
				for(Document doc: d) {
					if(doc!=null) {dl.add(convertToDto(doc));}
				}
				list.add(dl);
			}
		}
		return list;
	}
	
	

	private CoopSystemDto convertToDto(CoopSystem sys) { //convert the coopsystem to a dto
		if(sys==null) {return null;}
		CoopSystemDto sysD= new CoopSystemDto();
		sysD.id= sys.getId();
		
		sysD.eventNotificationsNames=new HashSet<String>(); 
		for(EventNotification en: service.findAllEventNotifications()) { //create an arraylist containing the ids
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
	
	private EventNotificationDto convertToDto(EventNotification e) { //convert to a dto
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
	
	private CoopJobDto convertToDto(CoopJob job) { //convert to a dto
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
	
	private CoopUserDto convertToDtoa(CoopUser user) { //convert to a dto
		if(user==null) {return null;}
		else if(user instanceof Student) {return (CoopUserDto) convertToDtob((Student) user);}
		else {return (CoopUserDto) convertToDtoc((Employer) user);}
		
	}
	
	private DocumentDto convertToDto(Document doc) { //convert to a dto
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
	
	private EmployerDto convertToDtoc(Employer u) { //convert to a dto
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
		for(CoopJob j : service.findCoopJobsByEmployer(u.getUsername())){
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
	
	private MessageDto convertToDto(Message m) { //convert to a dto
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
	
	private StudentDto convertToDtob(Student u) { //convert to a dto
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
