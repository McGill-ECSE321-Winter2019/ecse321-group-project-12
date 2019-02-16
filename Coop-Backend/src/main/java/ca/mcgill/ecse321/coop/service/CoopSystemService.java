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
	
	
	
	@Transactional
	public CoopSystem createSystem() //we want to have at any given time only one instance of the toplevel class
	{                                // this instance will always have the String "Main data" as id
		
		if(coopSystemRepository.existsById("Main data")) // if it already exists, return it
		{
			return coopSystemRepository.findById("Main data").get();
		}
		CoopSystem coopSystem= new CoopSystem();
		coopSystem.setId("Main data");
		coopSystemRepository.save(coopSystem);
		return coopSystem;
	}
	
	@Transactional
	public void deleteSystem()
	{
		coopSystemRepository.deleteAll();
	}
	
	@Transactional
	public Student createStudent (String username) //create a student with unique username
	{
		if(studentRepository.existsById(username) || employerRepository.existsById(username)) //check the username not used before
		{
			return null;
		}
		Student s =new Student();
		s.setUsername(username);
		CoopSystem coopSystem= coopSystemRepository.findById("Main data").get();
		coopSystem.getCoopUsers().add(s); // add it to the list of users in the system isntance
		studentRepository.save(s); //add it to the student repository
		return s;
	}
	
	@Transactional
	public Employer createEmployer (String username)
	{
		if(studentRepository.existsById(username) || employerRepository.existsById(username)) //check the username not used before
		{
			return null;
		}
		Employer e =new Employer();
		e.setUsername(username);
		CoopSystem coopSystem= coopSystemRepository.findById("Main data").get();
		coopSystem.getCoopUsers().add(e);
		employerRepository.save(e);
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
		if(studentRepository.existsById(username))
		{
			return studentRepository.findById(username).get();
		}
		return null;
	}
	
	@Transactional
	public Employer findEmployerByUsername (String username) //find an employer by the username
	{
		if(employerRepository.existsById(username))
		{
			return employerRepository.findById(username).get();
		}
		return null;
	}
	
	
	@Transactional
	public void deleteStudent(Student a)
	{
		
	}
	
	
	
	

}
