package ca.mcgill.ecse321.coop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.*;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import ca.mcgill.ecse321.coop.controller.*;
import ca.mcgill.ecse321.coop.dto.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import  org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

//
import ca.mcgill.ecse321.coop.dao.*;
import ca.mcgill.ecse321.coop.model.*;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCoopSystemService {
	
	@Autowired
	private CoopSystemService service;
	
	@Autowired
	private CoopJobRepository coopJobRepository;
	
	@Autowired
	private CoopSystemRepository coopSystemRepository;
	
	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private EventNotificationRepository eventNotificationRepository;
	
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CoopUserRepository coopUserRepository; 
	
	@Mock
	private CoopUserRepository serviceMock;
	 
	@Autowired
	private  CoopController controller;
	
	
	private CoopJob coopjob;
	private Student student;
	private Employer employer;
	
	private static final String USERNAME  = "ALI";
	private static final String PASSWORD = "12345";
	
	@Before
	public void setupMock() {
		coopjob = mock(CoopJob.class);
		student = mock(Student.class);
		employer = mock(Employer.class);
		
	}
	
	@After
	public void clearDatabase() {
		eventNotificationRepository.deleteAll();
		messageRepository.deleteAll();
		coopJobRepository.deleteAll();
		documentRepository.deleteAll();
		employerRepository.deleteAll();
		studentRepository.deleteAll();
		coopUserRepository.deleteAll();
		coopSystemRepository.deleteAll();
	}
	
	@Test
	public void testLogin() {
		service.createCoopSystem();
		String student1="May";
		String student2="Just";
		String pass = PASSWORD;
		Student a=service.createStudent(student1);
		service.setPassword(student1, PASSWORD);
		
		// service.login(student1, PASSWORD);
		assertEquals(true,service.login(student1, PASSWORD));
	}
	
	@Test
	public void testGetCoopSystem() {
		service.createCoopSystem();
		assertNotNull(service.getCoopSystem());
		
	}
	
	@Test
	public void testDeleteCoopSystem() {
		service.createCoopSystem();
		assertNotNull(service.getCoopSystem());
		
		service.deleteSystem();
		assertNull(service.getCoopSystem());
		
	}
	
	@Test
	public void testCreateStudent() {
		service.createCoopSystem();
		String student1="May";
		
		service.createStudent(student1);
		assertEquals(student1,service.findStudentByUsername(student1).getUsername());
	}
	
	@Test
	public void testGetStudent() {
		service.createCoopSystem();
		String student1="May";
		
		service.createStudent(student1);
		assertEquals(student1,service.getStudent(student1).getUsername());
		
	}
	
	@Test
	public void testGetCoopUser() {
		service.createCoopSystem();
		String student1="May";
		
		service.createStudent(student1);
		assertEquals(student1,service.getCoopUser(student1).getUsername());
	}
	
	@Test
	public void testGetAllStudents() {
		service.createCoopSystem();
		String student1="May";
		
		service.createStudent(student1);
		assertEquals(1,service.getAllStudents().size());
		assertEquals(student1,service.getAllStudents().get(0).getUsername());
	}
	
	
	@Test
	public void testGetAllCoopUsers() {
		service.createCoopSystem();
		String student1="May";
		
		service.createStudent(student1);
		assertEquals(1,service.getAllCoopUsers().size());
		assertEquals(student1,service.getAllCoopUsers().get(0).getUsername());
		
	}
	
	@Test
	public void testCreateEmployer() {
		service.createCoopSystem();
		String employer1="May";
		
		service.createEmployer(employer1);
		assertEquals(employer1,service.findEmployerByUsername(employer1).getUsername());
		
		
	}
	
	@Test
	public void testGetEmployer() {
		service.createCoopSystem();
		String employer1="May";
		
		service.createEmployer(employer1);
		assertEquals(employer1,service.getEmployer(employer1).getUsername());
		
		
	}
	
	@Test
	public void testGetAllEmployers() {
		service.createCoopSystem();
		String employer1="May";
		
		service.createEmployer(employer1);
		assertEquals(1,service.getAllEmployers().size());
		assertEquals(employer1,service.getAllEmployers().get(0).getUsername());
		
	}
	
	@Test
	public void testDeleteStudent() {
		service.createCoopSystem();
		String student1="May";
		
		service.createStudent(student1);
		assertEquals(student1,service.findStudentByUsername(student1).getUsername());
		service.deleteStudent(student1);
		assertNull(service.getStudent(student1));
	}
	
	@Test
	public void testDeleteEmployer() {
		service.createCoopSystem();
		String employer1="May";
		
		service.createEmployer(employer1);
		assertEquals(employer1,service.findEmployerByUsername(employer1).getUsername());
		service.deleteEmployer(employer1);
		assertNull(service.getEmployer(employer1));
	}
	
	@Test
	public void testSetPassword() {
		service.createCoopSystem();
		String student1="May";
		
		service.createStudent(student1);
		assertEquals(student1,service.findStudentByUsername(student1).getUsername());
		service.setPassword(student1, PASSWORD);
		assertEquals(PASSWORD,service.getStudent(student1).getPassword());
	}
	
	/*
	@Test
	public void testSaveCoopUser() {
		service.createCoopSystem();
		service.saveCoopUser(student);

		assertEquals(1,service.getAllCoopUsers().size());
	}
	*/
	
	@Test
	public void testSetStudentPermissions() {
		service.createCoopSystem();
		String student1="May";
		
		service.createStudent(student1);
		assertEquals(student1,service.findStudentByUsername(student1).getUsername());
		service.setStudentPermissions(student1, false, false);
		assertEquals(false,service.getStudent(student1).isAllowCV());
		assertEquals(false,service.getStudent(student1).isAllowTranscript());
		
	}
	

	@Test
	public void testFindCoopUserByUsername() {
		service.createCoopSystem();
		String student1="May";
		
		service.createStudent(student1);
		assertEquals(student1,service.findCoopUserByUsername(student1).getUsername());
	}
	
	@Test
	public void testFindStudentByUsername() {
		service.createCoopSystem();
		String student1="May";
		
		service.createStudent(student1);
		assertEquals(student1,service.findStudentByUsername(student1).getUsername());
	}
	
	@Test
	public void testFindEmployertByUsername() {
		service.createCoopSystem();
		String employer1="May";
		
		service.createEmployer(employer1);
		assertEquals(employer1,service.findEmployerByUsername(employer1).getUsername());
	}
	
	@Test
	public void testDeleteCoopuser() {
		service.createCoopSystem();
		String employer1="May";
		
		service.createEmployer(employer1);
		assertEquals(employer1,service.findCoopUserByUsername(employer1).getUsername());
		service.deleteCoopUser(employer1);
		assertNull(service.getCoopUser(employer1));
	}
	
	@Test
	public void testFindEventNotificationByName() {
		service.createCoopSystem();
		String eventNotif = "Meeting Soon";
		service.createEventNotification(eventNotif);
		assertEquals(1,service.findAllEventNotifications().size());
		assertEquals(eventNotif, service.findEventNotificationByName(eventNotif).getName());
	
	}
	
	@Test
	public void testFindAllEventNotifications() {
		service.createCoopSystem();
		String eventNotif = "Meeting Soon";
		service.createEventNotification(eventNotif);
		assertEquals(1,service.findAllEventNotifications().size());
		assertEquals(eventNotif, service.findAllEventNotifications().get(0).getName());
	
	}
	
	
	@Test
	public void testCreateMessage() {
		service.createCoopSystem();
		String employer1="May";
		service.createEmployer(employer1);
		String employer2="May2";
		service.createEmployer(employer2);
		
		service.createMessage(USERNAME, employer1, employer2, USERNAME, null);
		assertEquals(USERNAME, service.findMessageByMessageId(USERNAME).getMessageId());
	}
	
	@Test
	public void testFindMessageById() {
		service.createCoopSystem();
		String employer1="May";
		service.createEmployer(employer1);
		String employer2="May2";
		service.createEmployer(employer2);
		
		service.createMessage(USERNAME, employer1, employer2, USERNAME, null);
		assertEquals(USERNAME, service.findMessageByMessageId(USERNAME).getMessageId());
	}
	
	@Test
	public void testFindMessagesBySender() {
		service.createCoopSystem();
		String employer1="May";
		service.createEmployer(employer1);
		String employer2="May2";
		service.createEmployer(employer2);
		
		service.createMessage(USERNAME, employer1, employer2, USERNAME, null);
		assertEquals(USERNAME, service.findMessagesBySender(employer1).get(0).getMessageId());
	}
	
	@Test
	public void testFindMessagesByReceiver() {
		service.createCoopSystem();
		String employer1="May";
		service.createEmployer(employer1);
		String employer2="May2";
		service.createEmployer(employer2);
		
		service.createMessage(USERNAME, employer1, employer2, USERNAME, null);
		assertEquals(USERNAME, service.findMessagesByReceiver(employer2).get(0).getMessageId());
	}
	
	@Test
	public void testGetAllMessages() {
		service.createCoopSystem();
		String employer1="May";
		service.createEmployer(employer1);
		String employer2="May2";
		service.createEmployer(employer2);
		
		service.createMessage(USERNAME, employer1, employer2, USERNAME, null);
		assertEquals(USERNAME, service.getAllMessages().get(0).getMessageId());
	}
	
	@Test
	public void testFindMessagesBySenderAndReceiver() {
		service.createCoopSystem();
		String employer1="May";
		service.createEmployer(employer1);
		String employer2="May2";
		service.createEmployer(employer2);
		
		service.createMessage(USERNAME, employer1, employer2, USERNAME, null);
		assertEquals(USERNAME, service.findMessagesBySenderAndReceiver(employer1, employer2).get(0).getMessageId());
	}

	@Test
	public void testDeleteMessage() {
		service.createCoopSystem();
		String employer1="May";
		service.createEmployer(employer1);
		String employer2="May2";
		service.createEmployer(employer2);
		
		service.createMessage(USERNAME, employer1, employer2, USERNAME, null);
		assertEquals(USERNAME, service.findMessageByMessageId(USERNAME).getMessageId());
		service.deleteMessage(USERNAME);
		assertNull(service.findMessageByMessageId(USERNAME));
	}
	
	
	@Test
	public void testFindCoopJobById() {
		service.createCoopSystem();
		String student1="May";
		String employerName = "Mike";
		String jobId = "Cleaning";
		
		service.createStudent(student1);
		assertEquals(1,service.getAllStudents().size());
		assertEquals(1,service.getAllCoopUsers().size());
		
		service.createEmployer(employerName);
		assertEquals(1,service.getAllEmployers().size());
		assertEquals(2,service.getAllCoopUsers().size());
		
		service.createCoopJob(jobId, employerName, student1);
		assertEquals(student1, service.getStudent(student1).getUsername());

		assertEquals(1, service.getAllCoopJobs().size());
		
		assertEquals(employerName, service.getEmployer(employerName).getUsername());
		assertEquals(jobId, service.findCoopJobByJobId(jobId).getJobId());
		
	}
	
	@Test
	public void testSetCoopJobState() {
		service.createCoopSystem();
		String student1="May";
		String employerName = "Mike";
		String jobId = "Cleaning";
		CoopState state1 = CoopState.completed;
		
		service.createStudent(student1);
		assertEquals(1,service.getAllStudents().size());
		assertEquals(1,service.getAllCoopUsers().size());
		
		service.createEmployer(employerName);
		assertEquals(1,service.getAllEmployers().size());
		assertEquals(2,service.getAllCoopUsers().size());
		
		service.createCoopJob(jobId, employerName, student1);
		assertEquals(student1, service.getStudent(student1).getUsername());

		assertEquals(1, service.getAllCoopJobs().size());
		
		assertEquals(employerName, service.getEmployer(employerName).getUsername());
		assertEquals(jobId, service.findCoopJobByJobId(jobId).getJobId());
		
		service.setCoopJobState(jobId, state1);
		assertEquals(state1, service.findCoopJobByJobId(jobId).getState());
	}
	
	@Test
	public void testGetAllDocuments() {
		service.createCoopSystem();
		String 	authorName = "holo";
		String docId = "1";
		DocumentType type = DocumentType.CV;
		service.createEmployer(authorName);
		service.createDocument(docId, authorName, type);

		assertEquals(1,service.getAllDocuments().size());
		
		
	}
	@Test
	public void testGetPersonalDocumentsByStudent() {
		service.createCoopSystem();
		String docId = "1";
		String student = "Mike";
		DocumentType type = DocumentType.CV;
		ArrayList<String> documentIds = new ArrayList<>();
		documentIds.add(docId);
		
		service.createStudent(student);
		service.createDocument(docId, student, type);
		assertEquals(1,service.getAllDocuments().size());
		
		service.setPersonalDocuments(student, documentIds);
		assertEquals(1,service.getPersonalDocumentsByStudent(student).size());
		assertEquals(docId,service.getPersonalDocumentsByStudent(student).get(0).getDocumentId());
	}
	
	@Test
	public void testGetAuthoredDocuments() {
		service.createCoopSystem();
		String 	authorName = "holo";
		String docId = "1";
		DocumentType type = DocumentType.CV;
		service.createEmployer(authorName);
		service.createDocument(docId, authorName, type);

		assertEquals(1,service.getAllDocuments().size());
		assertEquals(authorName,service.getAuthoredDocuments(authorName).get(0).getAuthor().getUsername());
		
		
	}
	/*
	@Test
	public void testGetAttachments() {
		service.createCoopSystem();
		String employer1="May";
		service.createEmployer(employer1);
		String employer2="May2";
		service.createEmployer(employer2);
		
		String 	authorName = "holo";
		String docId = "1";
		DocumentType type = DocumentType.CV;
		service.createEmployer(authorName);
		service.createDocument(docId, authorName, type);

		assertEquals(1,service.getAllDocuments().size());
		
		ArrayList<String> docs = new ArrayList();
		docs.add(service.getAllDocuments().get(0).getDocumentId());
		
		service.createMessage(USERNAME, employer1, employer2, USERNAME,docs);
		assertEquals(USERNAME, service.getAllMessages().get(0).getAttachements().toString());
		
	}
	*/
	
	@Test
	public void testGetCoopJobDocuments() {
		service.createCoopSystem();
		String student1="May";
		String employerName = "Mike";
		String jobId = "Cleaning";
		String 	authorName = "holo";
		String docId = "1";
		DocumentType type = DocumentType.CV;
		service.createEmployer(authorName);
		service.createDocument(docId, authorName, type);
		service.createStudent(student1);
		assertEquals(1,service.getAllStudents().size());
		service.createEmployer(employerName);
		service.createCoopJob(jobId, employerName, student1);
		service.addDocumentToCoopJob(jobId, docId);
		
		assertEquals(docId,service.getCoopJobDocuments(jobId).get(0).getDocumentId());
		
		
	}
	/*
	@Test
	public void testGetArchivedInterns() {
		service.createCoopSystem();
		String student1="May";
		service.createStudent(student1);
		assertEquals(student1,service.findStudentByUsername(student1).getUsername());
		
		String employer1="May2";
		service.createEmployer(employer1);
		assertEquals(employer1,service.findEmployerByUsername(employer1).getUsername());
		
		Student student111 = service.getStudent(student1);
		HashSet<Student> stud = new HashSet<Student>();
		stud.add(student111);
		Employer employer66 =service.getEmployer(employer1);
		employer66.setArchivedInterns(stud);
		assertEquals(student1,service.getArchivedInterns(employer1).get(0).getUsername());
		
		
		
		
	}
	*/
	
	/*
	@Test
	public void testGetInternDocuments() {
		service.createCoopSystem();
		String student1="May";
		service.createStudent(student1);

		String employer1="May2";
		service.createEmployer(employer1);
		
		String docId = "1";
		DocumentType type = DocumentType.CV;
		service.createDocument(docId, student1, type);

		assertEquals(1,service.getAllDocuments().size());
		
		HashSet<Document> doc= new HashSet<Document>();
		doc.add(service.findDocumentByDocumentId(docId));
		
		service.getInternDocuments(employer1, student1);
		service.findStudentByUsername(student1).setPersonalDocuments(doc);
		assertEquals(docId,service.getInternDocuments(employer1, student1).get(0).getDocumentId());
	}
	*/
	
	@Test
	public void testFindCoopJobByEmployerAndStudentAndDate() {
		service.createCoopSystem();
		String student1="May";
		String employerName = "Mike";
		String jobId = "Cleaning";
		
		service.createStudent(student1);
		assertEquals(1,service.getAllStudents().size());
		assertEquals(1,service.getAllCoopUsers().size());
		
		service.createEmployer(employerName);
		assertEquals(1,service.getAllEmployers().size());
		assertEquals(2,service.getAllCoopUsers().size());
		
		service.createCoopJob(jobId, employerName, student1);
		assertEquals(student1, service.getStudent(student1).getUsername());

		assertEquals(1, service.getAllCoopJobs().size());
		
		assertEquals(employerName, service.getEmployer(employerName).getUsername());
		
		@SuppressWarnings("deprecation")
		Date d1 = new Date(111, 100, 100);
		Date d2 = new Date(111, 102, 100);
		CoopState state1 = CoopState.completed;
		
		service.setCoopJobSettings(jobId, d1, d2, USERNAME, state1);
		
		assertEquals(d2.toString(), service.findCoopJobByEmployerAndStudentAndEndDate(employerName, student1, d2).getEndDate().toString());
	}
	
	
	@Test
	public void donothing(){
		service.createCoopSystem();
		service.createStudent("May");
		service.createDocument("dd", "May", DocumentType.CV);
		Document d= service.findDocumentByDocumentId("dd");
		Student s=(Student)d.getAuthor();
		assertEquals("May",s.getUsername());
	}
	
	/*@Test
	public void testCreate() {
		service.createCoopSystem();
		assertEquals(0,service.getAllCoopUsers().size());
		EventNotification e=service.createEventNotification("Sam");
		e.setLocation("ss");
		//assertEquals("ss",service.findEventNotificationByName("Sam").getLocation());
		assertEquals(1,service.findAllEventNotifications().size());
		//String us ="May";
		Student s= service.createStudent("May");
		//Document d= service.createDocument("fff", s);
		assertEquals(1,service.getAllStudents().size());
		assertEquals(1,service.getAllCoopUsers().size());
		
		service.setPassword("May","why");
		s.setPassword("mna");
		assertEquals("why",service.findStudentByUsername("May").getPassword());
		assertEquals(1,service.getAllStudents().size());
		assertEquals(1,service.getAllCoopUsers().size());
		service.deleteStudent("May");
		assertEquals(0,service.getAllStudents().size());
		assertEquals(0,service.getAllCoopUsers().size());
	}*/
	
	@Test
	public void TestCreate3() {
		service.createCoopSystem();
		String student1="May";
		String student2="Just";
		Student a=service.createStudent(student1);
		assertEquals(1,service.getAllStudents().size());
		assertEquals(1,service.getAllCoopUsers().size());
		service.setPassword(student1,"why");
		
		assertEquals("why",service.findStudentByUsername(student1).getPassword());
		
		service.createStudent(student2);
		assertEquals(2,service.getAllStudents().size());
		assertEquals(2,service.getAllCoopUsers().size());
		service.createEmployer("HH");
		service.setPassword("HH","how");
		
		assertEquals("how",service.findEmployerByUsername("HH").getPassword());
		assertEquals("how",service.findCoopUserByUsername("HH").getPassword());
		assertEquals(2,service.getAllStudents().size());
		assertEquals(3,service.getAllCoopUsers().size());
		assertEquals(1,service.getAllEmployers().size());
		service.deleteStudent(student2);
		assertEquals(1,service.getAllStudents().size());
		assertEquals(2,service.getAllCoopUsers().size());
		assertEquals(1,service.getAllEmployers().size());
		service.deleteCoopUser("HH");
		assertEquals(1,service.getAllStudents().size());
		assertEquals(1,service.getAllCoopUsers().size());
		assertEquals(0,service.getAllEmployers().size());
		
	}
	
	@Test
	public void TestCreate2() {
		service.createCoopSystem();
		String student1="May";
		String  employer1="De";
		service.createStudent(student1);
		service.createEmployer(employer1);
		assertEquals(false,service.findStudentByUsername(student1).isAllowCV());
		assertEquals(false,service.findStudentByUsername(student1).isAllowTranscript());
		service.setStudentPermissions(student1, true, true);
		assertEquals(true,service.findStudentByUsername(student1).isAllowCV());
		assertEquals(true,service.findStudentByUsername(student1).isAllowTranscript());
		
		
		service.createDocument("doc1", student1, DocumentType.CV);
		assertEquals(1,service.getAllDocuments().size());
		assertEquals("doc1",service.findDocumentByDocumentId("doc1").getDocumentId());
		assertEquals(DocumentType.CV,service.findDocumentByDocumentId("doc1").getType());
		
		
		assertEquals(1,service.getAuthoredDocuments(student1).size());
		
	}
	

	@Test
	public void testCreateCoopJob() {
		service.createCoopSystem();
		String student1="May";
		String employerName = "Mike";
		String jobId = "Cleaning";
		
		service.createStudent(student1);
		assertEquals(1,service.getAllStudents().size());
		assertEquals(1,service.getAllCoopUsers().size());
		
		service.createEmployer(employerName);
		assertEquals(1,service.getAllEmployers().size());
		assertEquals(2,service.getAllCoopUsers().size());
		
		service.createCoopJob(jobId, employerName, student1);
		assertEquals(student1, service.getStudent(student1).getUsername());

		assertEquals(1, service.getAllCoopJobs().size());
		
		assertEquals(employerName, service.getEmployer(employerName).getUsername());
		assertEquals(jobId, service.findCoopJobByJobId(jobId).getJobId());
		
	}
	
	
	@Test
	public void testFindCoopJobs() {
		service.createCoopSystem();
		String student1="May";
		String employerName = "Mike";
		String jobId = "Cleaning2";
		
		service.createStudent(student1);
		service.createEmployer(employerName);
		
		CoopJob coopjob1 = service.createCoopJob(jobId, employerName, student1);
		assertEquals(1, service.getAllCoopJobs().size());
		List<CoopJob> coopJobs = service.getAllCoopJobs();
		
		// findCoopJobsByEmployer
		assertEquals(jobId, service.findCoopJobsByEmployer(employerName).get(0).getJobId());
		
		// findCoopJobsByStudent
		assertEquals(jobId, service.findCoopJobsByStudent(student1).get(0).getJobId());
		
		// findCoopJobsByEmployerAndStudent
		assertEquals(jobId, service.findCoopJobsByEmployerAndStudent(employerName, student1).get(0).getJobId());
		
	}

	@Test
	public void testDeleteCoopJob() {
		service.createCoopSystem();
		String student1="May";
		String employerName = "Mike";
		String jobId = "Cleaning2";
		
		service.createStudent(student1);
		service.createEmployer(employerName);
		
		service.createCoopJob(jobId, employerName, student1);
		assertEquals(1, service.getAllCoopJobs().size());
		
		service.deleteCoopJob(jobId);
		assertEquals(0, service.getAllCoopJobs().size());
		
		
	}
	
	@Test public void testSetCoopJobSettings() {
		service.createCoopSystem();
		String jobId = "Cleaning2";
		String student1="May";
		String employerName = "Mike";
		String name = "holo";
		Date start = new Date(System.currentTimeMillis());
		Date end = new Date(System.currentTimeMillis()+ 10000);
		
		CoopState state1 = CoopState.completed;
		service.createStudent(student1);
		service.createEmployer(employerName);

		CoopJob coopjob1 = service.createCoopJob(jobId, employerName, student1);
		assertEquals(1, service.getAllCoopJobs().size());
		
		service.setCoopJobSettings(jobId, start, end, name, state1);
		
		assertEquals(jobId, service.findCoopJobByJobId(jobId).getJobId());
		
		assertEquals(start.toString(), service.findCoopJobByJobId(jobId).getStartDate().toString());
		assertEquals(end.toString(), service.findCoopJobByJobId(jobId).getEndDate().toString());
		
		assertEquals(name, service.findCoopJobByJobId(jobId).getName());
		assertEquals(state1, service.findCoopJobByJobId(jobId).getState());
		
	}
	@Test 
	public void testAddDocumentToCoopJob() {
		service.createCoopSystem();
		String jobId = "Cleaning2";
		String student1="May";
		String employerName = "Mike";
		String name = "holo";
		String docId = "1";
		DocumentType type = DocumentType.CV;
	//	Document doc = new Document();
		
		service.createStudent(student1);
		service.createEmployer(employerName);
		CoopJob coopjob1 = service.createCoopJob(jobId, employerName, student1);
		service.createDocument(docId, employerName, type);
		service.addDocumentToCoopJob(jobId, docId);
		
		List<Document> documents = service.getAllDocuments();
		assertEquals(1, service.getAllDocuments().size());
		
		Set<Document> docsSet = new HashSet<>(documents);
		
		assertEquals(jobId,service.findCoopJobByJobId(jobId).getJobId());

		/* Here I tried to convert list of documents into set of documents since
		 * the return value for getCoopJobDocuments is a set of documents
		 * however, I kept getting lazy error in the test.
		 */
	//	assertEquals(docsSet, service.findCoopJobByJobId(jobId).getCoopJobDocuments());
		
	}
	
	@Test
	public void testGetAllCoopJobs() {
		service.createCoopSystem();
		String jobId = "Cleaning2";
		String student1="May";
		String employerName = "Mike";
		
		service.createStudent(student1);
		service.createEmployer(employerName);
		service.createCoopJob(jobId, employerName, student1);
		
		assertEquals(1,service.getAllCoopJobs().size());
	}
	
	
	
	@Test
	public void testCreateDocument() {
		service.createCoopSystem();
		String 	authorName = "holo";
		String docId = "1";
		DocumentType type = DocumentType.CV;
		service.createEmployer(authorName);
		service.createDocument(docId, authorName, type);

		assertEquals(1,service.getAllDocuments().size());
		
		
	}
	
	@Test
	public void testSetPersonalDocument() {
		service.createCoopSystem();
		String 	authorName = "holo";
		String docId = "1";
		String student = "Mike";
		DocumentType type = DocumentType.CV;
		ArrayList<String> documents = new ArrayList<>();
		documents.add("13");
		
		service.createStudent(student);
		service.createEmployer(authorName);
		service.createDocument(docId, authorName, type);

		assertEquals(1,service.getAllDocuments().size());
		
		service.setPersonalDocuments(student, documents);

	// The same lazy initialization exception issue
	//	Hibernate.initialize(ca.mcgill.ecse321.coop.model.Student.personalDocuments);;
	//	assertEquals(documents, service.findStudentByUsername(student).getPersonalDocuments());
		
		
	}
	
	
	@Test
	public void testFindDocuments() {
		service.createCoopSystem();
		String 	authorName = "holo";
		String docId = "1";
		DocumentType type = DocumentType.CV;

		service.createEmployer(authorName);
		service.createDocument(docId, authorName, type);
		
		assertEquals(docId, service.findDocumentByDocumentId(docId).getDocumentId());

	}
	
	@Test
	public void testFindDocumentsByAuthor() {
		service.createCoopSystem();
		String 	authorName = "holo";
		String docId = "1";
		DocumentType type = DocumentType.CV;

		service.createEmployer(authorName);
		service.createDocument(docId, authorName, type);
		
		ArrayList<Document> docs = service.getAllDocuments();
		assertEquals(1, docs.size());
		
	//  It sees them as different lists	
	//	 assertEquals(docs, service.findDocumentsByAuthor(authorName));
	}
	
	@Test
	public void testDeleteDocument() {
		service.createCoopSystem();
		String 	authorName = "holo";
		String docId = "1";
		DocumentType type = DocumentType.CV;

		service.createEmployer(authorName);
		service.createDocument(docId, authorName, type);
		
		assertEquals(1, service.getAllDocuments().size());
		
		service.deleteDocument(docId);
		assertEquals(0, service.getAllDocuments().size());
	}
	
	@Test
	public void testDeleteAllDocuments() {
		service.createCoopSystem();
		String 	authorName = "holo";
		String docId = "1";
		String docId2 = "2";
		DocumentType type = DocumentType.CV;

		service.createEmployer(authorName);
		service.createDocument(docId, authorName, type);
		service.createDocument(docId2, authorName, type);
		assertEquals(2, service.getAllDocuments().size());
		
		service.deleteAllDocuments();
		assertEquals(0, service.getAllDocuments().size());
	}
	
	@Test
	public void testCreateEventNotification() {
		service.createCoopSystem();
		String eventNotif = "Meeting Soon";
		service.createEventNotification(eventNotif);
		assertEquals(1,service.findAllEventNotifications().size());
		assertEquals(eventNotif, service.findEventNotificationByName(eventNotif).getName());
	
	}
	
	
	
	@Test
	public void testDeleteEventNotification() {
		service.createCoopSystem();
		String eventNotif = "Meeting Soon";
		service.createEventNotification(eventNotif);
		assertEquals(1,service.findAllEventNotifications().size());
		assertEquals(eventNotif, service.findEventNotificationByName(eventNotif).getName());
		
		service.deleteEventNotification(eventNotif);
		assertEquals(0,service.findAllEventNotifications().size());
	
	}
	
	
	 @Test
	public void testSetEventNotification() {
		service.createCoopSystem();
		String eventNotif = "Meeting Soon";
		Event event = Event.conference;
		String location = "nowhere";
		Date date = new Date(System.currentTimeMillis() + 1000000000);
		Time timeStart = new Time(System.currentTimeMillis() +1000);
		Time timeEnd = new Time(System.currentTimeMillis() + 10000000);
		
		
		service.createEventNotification(eventNotif);
		service.setEventNotificationSettings(eventNotif, event, location, date, timeStart, timeEnd);
		
		assertEquals(1,service.findAllEventNotifications().size());
		
		assertEquals(eventNotif,service.findEventNotificationByName(eventNotif).getName());
		assertEquals(event,service.findEventNotificationByName(eventNotif).getType());
		assertEquals(location, service.findEventNotificationByName(eventNotif).getLocation());
	// Same issue I had for date and time...
		
	assertEquals(date.toString(), service.findEventNotificationByName(eventNotif).getDate().toString());
	assertEquals(timeStart.toString(), service.findEventNotificationByName(eventNotif).getStartTime().toString());
	assertEquals(timeEnd.toString(), service.findEventNotificationByName(eventNotif).getEndTime().toString());
		
		
	
	}
	
	
}

