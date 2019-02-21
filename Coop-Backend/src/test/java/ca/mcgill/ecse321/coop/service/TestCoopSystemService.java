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

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.coop.dao.*;
import ca.mcgill.ecse321.coop.model.*;

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
	
	@After
	public void clearDatabase() {
		eventNotificationRepository.deleteAll();
		messageRepository.deleteAll();
		coopJobRepository.deleteAll();
		documentRepository.deleteAll();
		employerRepository.deleteAll();
		studentRepository.deleteAll();
		coopSystemRepository.deleteAll();
	}
	
	
	@Test
	public void donothing(){}
	/*@Test
    public void testCreateDocument() {
		service.createCoopSystem();
         Document doc = new Document();
        assertEquals(0, service.getAllDocuments().size());
        String id = "0";
        CoopUser smith = new Employer();

        try {
            service.createDocument(id, smith);
        } catch (IllegalArgumentException e) {
            fail();
        }
        List<Document> documents = service.getAllDocuments();
        assertEquals(1, documents.size());
        assertEquals(id, documents.get(0).getDocumentId());
    }*/
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
	public void testCreate() {
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
	
	
}
