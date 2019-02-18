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
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.coop.*;
import ca.mcgill.ecse321.coop.model.CoopUser;
import ca.mcgill.ecse321.coop.model.Document;
import ca.mcgill.ecse321.coop.model.Employer;
import ca.mcgill.ecse321.coop.model.Student;

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
	public void testCreate() {
		service.createCoopSystem();
		assertEquals(0,service.getAllCoopUsers().size());
		EventNotification e=service.createEventNotification("Sam");
		e.setName("ffff");
		assertEquals("ffff",e.getName());
		assertEquals(1,service.findAllEventNotifications().size());
		Student s= service.createStudent("May");
		s.setPassword("mmm");
		assertEquals("mmm",s.getPassword());
	}
	
	// Write test for CoopJob Class
	
	@Test
	public void testCreateCoopJob() {
		assertEquals(0, service.getAllCoopJobs().size());
		String id = "0";
		Employer apple =  new Employer();
		Student mike = new Student();
		try {
			service.createCoopJob(id, apple, mike);
		} catch (IllegalArgumentException e) {
			fail();
		}
		List<CoopJob> coopjobs = service.getAllCoopJobs();
		assertEquals(1, coopjobs.size());
		assertEquals(id, coopjobs.get(0).getJobId());
	}
	
	
	// Write test for  Document class
	
	 @Test
		public void testCreateDocument() {
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
		}
	 
	 
	 @Test
	    public void testSetEventNotification() {
	        
	        assertEquals(0, service.findAllEventNotification().size());
	        String name = "Speed Networking Event";
	        EventNotification e = service.createEventNotification(name);
	        Calendar c = Calendar.getInstance();
	        String location = "McConnell Engineering Building"
	        c.set(2019, Calendar.MARCH, 16, 9, 0, 0);
	        Date eventDate = new Date(c.getTimeInMillis());
	        LocalTime startTime = LocalTime.parse("09:00");
	        c.set(2019, Calendar.MARCH, 16, 10, 30, 0);
	        LocalTime endTime = LocalTime.parse("10:30");
	        
	        try {
	            service.setEventNotification(e, conference,location, eventDate, Time.valueOf(startTime) , Time.valueOf(endTime));
	        }
	        
	        catch (IllegalArgumentException e) {
	            fail();
	        }

	        checkResultEvent(name, eventType, Location, eventDate, startTime, endTime);
	    }
	    
	    private void checkResultEvent(String name, Event eventType, String Location, Date eventDate, LocalTime startTime, LocalTime endTime) {
	        assertEquals(name, service.getAllEventNotification().get(0).getName());
	        assertEquals(name, service.getAllEventNotification().get(0).getType());
	        assertEquals(name, service.getAllEventNotification().get(0).getLocation());
	        assertEquals(eventDate.toString(), service.getAllEventNotification().get(0).getDate().toString());
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	        assertEquals(startTime.format(formatter).toString(), service.getAllEventEventNotification().get(0).getStartTime().toString());
	        assertEquals(endTime.format(formatter).toString(), service.getAllEventNotification().get(0).getEndTime().toString());
	        assertEquals(0, service.getAllRegistrations().size());
	    }
	    
	    
	    @Test
	    public void testPassword(){
	      u.setPassword("mmm");
	      assertEquals("mmm",u.getPassword());
	    }
	    @Test
	    public void testUsername(){
	        
	      u.setUsername("Sam");
	      assertEquals("Sam",u.getUsername());
	    }
	    @Test
	    public void testCoopSystem(){
	        
	      CoopSystem system = new CoopSystem();
	      u.setCoopSystem(system);
	      assertEquals(system,u.getCoopSystem());
	    }
	    @Test
	    public void testAuthoredDocuments(){
	      Set<Document> authored;
	      Document d = new Document();
	      d.setAuthor(u);
	      authored.add(d);
	      u.setAuthoredDocuments(authored);
	      assertEquals(true,u.getAuthoredDocuments().contains(d));
	        
	    }
	    @Test
	    public void testSentMessages(){
	      Set<Message> sent;
	      Message m = new Message();
	      m.setSender(u);
	      sent.add(m);
	      u.setSentMessages(sent);
	      assertEquals(true,u.getSentMessages().contains(m));
	    }
	    @Test
	    public void testReceivedMessages(){
	      Set<Message> received;
	      Message m1 = new Message();
	      m1.setReceiver(u);
	      received.add(m1);
	      u.setReceivedMessages(received);
	      assertEquals(true,u.getReceivedMessages().contains(m1));
	        
	    }
	    public void testCreateStudent() {
	        assertEquals(0, service.getAllStudents().size());
	        String username = "Oscar";
	        boolean allowCV = false;
	        boolean allowTranscript= false;
	        try {
	            service.createStudent(username);
	        } catch (IllegalArgumentException e) {
	            // Check that no error occurred
	            fail();
	        }
	        List<Student> allStudents = service.getAllStudents();
	        assertEquals(1, allStudents.size());
	        assertEquals(username, allStudents.get(0).getUsername());
	        assertEquals(allowCV,false);
	        assertEquals(allowTranscript,false);
	    }
	    public void testCreateStudentNull() {
	        assertEquals(0, service.getAllStudents().size());
	        String username = null;
	        String error = null;
	        try {
	            service.createStudent(username);
	        } catch (IllegalArgumentException e) {
	            error = e.getMessage();
	        }
	        // check error
	        assertEquals("Student username cannot be empty!", error);
	        // check no change in memory
	        assertEquals(0, service.getAllStudents().size());
	    }
	    public void testCreateStudentEmpty() {
	        assertEquals(0, erc.getAllStudents().size());
	        String username = "";
	        String error = null;
	        try {
	            erc.createStudent(username);
	        } catch (IllegalArgumentException e) {
	            error = e.getMessage();
	        }
	        // check error
	        assertEquals("Student username cannot be empty!", error);
	        // check no change in memory
	        assertEquals(0, erc.getAllStudents().size());
	    }
	    @Test
	    public void testCreateStudentSpaces() {
	        assertEquals(0, erc.getAllStudents().size());
	        String username = " ";
	        String error = null;
	    
	        try {
	            erc.createStudent(username);
	        } catch (IllegalArgumentException e) {
	            error = e.getMessage();
	        }
	        // check error
	        assertEquals("Student username cannot be empty!", error);
	        // check no change in memory
	        assertEquals(0, erc.getAllStudents().size());
	    }

		

}
