package ca.mcgill.ecse321.coop;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.*;
import org.junit.jupiter.api.Test;

class interfaceTest {
	
	@Test
	void testCreateSystem() {
		testCreate("https://ecse321-group12.herokuapp.com/coopsystem");
	}
	
	@Test
	void testCreateStudent() {
		testCreate("https://ecse321-group12.herokuapp.com/students/Mahtab");
		testDelete("https://ecse321-group12.herokuapp.com/students/Mahtab");
	}
	
	
	@Test
	void testCreateEmployer() {
		testCreate("https://ecse321-group12.herokuapp.com/employers/Brown");
		testDelete("https://ecse321-group12.herokuapp.com/employers/Brown");
	}	
	

	
	@Test
	void testSetPassword() {
		testCreate("https://ecse321-group12.herokuapp.com/students/Richard");
		testCreate("https://ecse321-group12.herokuapp.com/setPassword?Username=Richard&Password=1234");
		//queryService("https://ecse321-group12.herokuapp.com/students/Richard");
		testDelete("https://ecse321-group12.herokuapp.com/students/Richard");
	}	
	@Test
	void testCreateDocument() {
		testCreate("https://ecse321-group12.herokuapp.com/coopsystem");
		testCreate("https://ecse321-group12.herokuapp.com/students/Cat");
		testCreate("https://ecse321-group12.herokuapp.com/createDocument?DocumentId=moon&UserName=Cat&Type=CV");
		testDelete("https://ecse321-group12.herokuapp.com/students/Cat");
	}	
	
	@Test
	void testDeleteDocument() {
		testDelete("https://ecse321-group12.herokuapp.com/deleteDocument?DocumentId=moon");
	}
	@Test
	void testSetPreferencesStudentPreferences() {
		testCreate("https://ecse321-group12.herokuapp.com/students/John1");
		testCreate("https://ecse321-group12.herokuapp.com/studentPreferences?StudentName=John1&AllowCV=false&AllowTranscript=true");
        testDelete("https://ecse321-group12.herokuapp.com/students/John1");
	}
	@Test
	void testSetPreferencesStudentPersonal() {
		testCreate("https://ecse321-group12.herokuapp.com/students/Johnny1");
		testCreate("https://ecse321-group12.herokuapp.com/studentPersonal?StudentName=Johnny1&PersonalDocumentsIds=moon");
        testDelete("https://ecse321-group12.herokuapp.com/students/Johnny1");
	}	
	
	@Test
	void testGetCoopuser() {
		testCreate("https://ecse321-group12.herokuapp.com/employers/blue");
		testGet("https://ecse321-group12.herokuapp.com/coopusers/blue");  
		
	}
	
	
	@Test
	void testCreateAndSendMessage() {
		testCreate("https://ecse321-group12.herokuapp.com/students/John2");  //Create sender
		testCreate("https://ecse321-group12.herokuapp.com/employers/Johnny2"); //Create receiver		
		testCreate("https://ecse321-group12.herokuapp.com/createDocument?UserName=Blah&DocumentId=sun&Type=CV"); //Create a document
    	testCreate("https://ecse321-group12.herokuapp.com/newMessage?MessageId=Sam1&SenderName=John2&ReceiverName=Johnny2&Content=Yolo&ListofAttachementsIds=[moon]");
	   // testDelete("https://ecse321-group12.herokuapp.com/students/John2");
	    //testDelete("https://ecse321-group12.herokuapp.com/students/Johnny2");
	    //testDelete("https://ecse321-group12.herokuapp.com/newMessage?MessageId=Sam1");
	}
	
	@Test
	void testGetMessage() {
		testGet("https://ecse321-group12.herokuapp.com/Message?MessageId=Hello");
		
		}
	
	@Test
	void testDeleteMessage() {
		testCreate("https://ecse321-group12.herokuapp.com/newMessage?MessageId=Hellow&SenderName=Cat&ReceiverName=Bloch&Content=Yolo&ListofAttachementsIds=[moon]");
		testDelete("https://ecse321-group12.herokuapp.com/Messafe?MessageId=Hellow");
	}
	
	@Test
	void testSentMessages() {
		testGet("https://ecse321-group12.herokuapp.com/SentMessages?SenderName=John2");
	}
	
	
	@Test
	void testDeleteSystem() {	
		testDelete("https://ecse321-group12.herokuapp.com/coopsystem");  //Clears the whole database 
	}
	@Test
	void testCreateEventNotification() {

		testCreate("https://ecse321-group12.herokuapp.com/createEventNotification?EventNotificationId=HelloWorld");
	
	}	
	
	
		
	
	@Test
	void testSetEventNotificationSettings() {
		testCreate("https://ecse321-group12.herokuapp.com/coopsystem");
		
		testCreate("https://ecse321-group12.herokuapp.com/createEventNotification?EventNotificationId=HelloWorld");
		
		                                               ///setEventSettings?EventNotificationId={enId}&Type={type}&Location={location}&Date={date}&StartTime={startTime}&EndTime={endTime}
		testCreate("https://ecse321-group12.herokuapp.com/setEventSettings?EventNotificationId=HelloWorld&Type=conferece&Location=Amsterdam&Date=2019-02-21&StartTime=13:10:00&EndTime=18:30:00");
	
	}
	
	@Test
	void testGetEventNotification() {
		
		testGet("https://ecse321-group12.herokuapp.com/getEventNotifications");
		testGet("https://ecse321-group12.herokuapp.com/getEventNotifications?EventNotificationId=HelloWorld");
		
	}
	
	@Test
	void testDeleteNotification() {
		
		testDelete("https://ecse321-group12.herokuapp.com/Event?EentId=HelloWorld");
	}
	
	@Test
	void testCreateJob() {
		testCreate("https://ecse321-group12.herokuapp.com/students/Dog");
		testCreate("https://ecse321-group12.herokuapp.com/employers/SamSam");
		testCreate("https://ecse321-group12.herokuapp.com/newJob?JobId=manager&EmployerName=SamSam&StudentName=Dog");
		
	}
	
	
	//send URL of query
	void queryService(String s) {	
    			
		testGet(s);
	}


	void testDelete(String url) {
		
    try {		
	   		URL targetUrl = new URL(url);			 
            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();          
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("DELETE");
            httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
 
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                    + httpConnection.getResponseCode());
            }

            //System.out.println(httpConnection.getResponseCode());
            httpConnection.disconnect();            
          } catch (MalformedURLException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();            
          }		
	}

	void testCreate(String url) {
		
		try {		
			
	   		URL targetUrl = new URL(url);			 
            HttpURLConnection httpConnection = (HttpURLConnection) targetUrl.openConnection();          
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("POST");
            httpConnection.setRequestProperty("Content-Type", "application/json");
 
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                    + httpConnection.getResponseCode());
            }
            
            InputStreamReader input = new InputStreamReader((httpConnection.getInputStream()));	 
            BufferedReader responseBuffer = new BufferedReader(input);
 
            if(url.contentEquals("https://ecse321-group12.herokuapp.com/coopsystem")) {
            	
            }else {
            	String output;
            System.out.println("Output from Server:\n");
            while ((output = responseBuffer.readLine()) != null) {
                System.out.println(output);
            }
            }
            input.close();
            httpConnection.disconnect();            
          } catch (MalformedURLException e) { 
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();         
          }		
	}
	

	void testGet(String url) {
		try {
			 
            URL restServiceURL = new URL(url);
 
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/json");
 
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }
 
            BufferedReader responseBuffer = new BufferedReader(new InputStreamReader(
                (httpConnection.getInputStream())));
 
            String output;
            System.out.println("Output from Server:  \n");
 
            while ((output = responseBuffer.readLine()) != null) {
                System.out.println(output);
            }
            httpConnection.disconnect();
          } catch (MalformedURLException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }      
	}

}