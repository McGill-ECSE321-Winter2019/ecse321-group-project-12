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

}
