package com.workpoolsystem.testing;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;


import com.workpoolsystem.enums.ResourceType;
import com.workpoolsystem.util.HibernateUtil;
import com.workpoolsystem.controller.AbstractController;
import com.workpoolsystem.controller.ResourceController;
import com.workpoolsystem.entity.Resource;


public class ResourceControllerTest extends AbstractController {
	Resource resource = new Resource();
	ResourceController controller = new ResourceController();
	
	ArrayList<String>  errorMessageList = new ArrayList<>();
	
	//Validate Id
	@Test
	void testId() {
		resource.setId(0);
		errorMessageList = controller.validateResource(resource);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else{
			assertTrue(false); 
		}
	}
	
	
	
	
	//Testing Name
		@Test
		void testName() {
			
			resource.setFirstname("Buntu@");	  
			errorMessageList = controller.validateResource(resource);  
			
			if(errorMessageList.size() > 0 ){  
				assertTrue(true);
			}
			else {
				assertTrue(false);
			}
		}
		
		//Testing surname
		@Test
		void testSurname() {
					
		resource.setLastname("Buntu@#");	  
		errorMessageList = controller.validateResource(resource);  
					
		if(errorMessageList.size() > 0 ){  
			assertTrue(true);
			}
			else {
			assertTrue(false);
		}
	}
		
		
		
		//Testing DOB
		@Test
		void testDob() throws ParseException {
			
	    String dateofbirth = "2019-03-04";
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		 
		  
	    Date date = dateformat.parse(dateofbirth);
			
		//convert date to calendar
		Calendar calender = Calendar.getInstance();
		calender.setTimeInMillis(date.getTime());
				
		resource.setDob(calender);  
		errorMessageList = controller.validateResource(resource);  
				
		if(errorMessageList.size() > 0 ){  
		assertTrue(true);
				}
		else {
		assertTrue(false);
				}
			}
		
		
		
		
		
		//Testing address
		@Test
		void testAddress() {
				
		resource.setAddress("   ");	  
		errorMessageList = controller.validateResource(resource);  
				
		if(errorMessageList.size() > 0 ){  
			assertTrue(true);
				}
		else {
		assertTrue(false);
				}
			}
		
		
		
		//Testing email
		@Test
		void testEmail() {
		
		resource.setEmail("tomsonb1");
		errorMessageList = controller.validateResource(resource);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
		}
		
		
		
		
		//Testing username
		@Test
		void testUsername() {
				
		resource.setUsername("john");  
		errorMessageList = controller.validateResource(resource);  
				
		if(errorMessageList.size() > 0 ){  
		assertTrue(true);
		 }
		else {
		assertTrue(false);
			}
		}
		
		
		
		
		//Testing password
		@Test
		void testPassword() {
		
		resource.setPassword("BBb@hgggg111237");
		errorMessageList = controller.validateResource(resource);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
		}
		
		
		
		
		
		
	//CRUD
	@Test
	void testCreate(){
		String dateofbirth = "1988-08-26";
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 
	  
		try { 
		Date date = dateformat.parse(dateofbirth);
		
		//convert date to calendar
		Calendar calender = Calendar.getInstance();
		calender.setTimeInMillis(date.getTime());
		
	
		resource.setFirstname("Paul");
		resource.setLastname("Pogba");
		resource.setDob(calender);
		resource.setAddress("Parow");
		resource.setEmail("paul@gmail.com");
		resource.setUsername("paul.pvogfbana");
		resource.setPassword("Pogba456&");
		Resource manager = controller.getResourceById(1);
		//System.out.println("ResourceControllerTesting.testCreate() manager=" + manager.getId() + " - " + manager.getUsername());
		resource.setManager(manager);
		resource.setType(ResourceType.External);
		
		
		errorMessageList = controller.validateResource(resource);
		if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
			assertTrue(true); 
		    controller.createResource(resource);
		
		}
		
	}catch(Exception e) {
		
		e.printStackTrace();
		assertTrue(false);
	}
  }
	
	@Test 
	void testGetAllResource() {
		
		int expected = 2;
		try {
			
			List<Resource> resourceList = controller.getAllResource();
			assertEquals(expected, resourceList.size());
			System.out.println(resourceList.toString());
			     assertTrue(true);   
			    
			   
		
		}
		 catch(HibernateException e) {
					
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	
	@Test
	void testGetResourceById() {
		try {
		int id = 2;
		resource.setId(id);
		errorMessageList = controller.validateResource(resource);
		if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
			assertTrue(true);
			 controller.getResourceById(id);
		}
			 
			
			
			
		}catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
			}
		
	}
	
	@Test 
	void testGetResourceByName() {
		
	
	 String name =  "bun";
		
	 try {
		    
			List<Resource> resourceList = controller.getResourceByName(name);
			if(!resourceList.isEmpty()) {
				System.out.println(resourceList.toString());
			     assertTrue(true);  
			}
			  else {
			    assertTrue(false);
		
			  }
			
		}catch(HibernateException e) {
					
			e.printStackTrace();
			
		}
	}
	
	@Test
	void testUpdate() {
		int activityToUpdate = 7;
		
		try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Resource update = (Resource) getObjectById(Resource.class, activityToUpdate, session);
		//updating resource name
		update.setFirstname("GHJK");
		errorMessageList = controller.validateResource(update);
		if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
			assertTrue(true);
			controller.updateResource(update, session);
		}
		}catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
			}
		
	}
	@Test
	void testDelete() {
		try {
			int id = 3;
			resource.setId(id);
			errorMessageList = controller.validateResource(resource);
			if(errorMessageList.size() > 0) {
				assertTrue(false);
			}
			else {
				assertTrue(true);
				controller.deleteResource(id);
			}
		}catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
			}
		
	}
	
	@Test
	void testResourceAndTasksTheyCreated(){
		
		try {
			//int id = 1;
			assertTrue(true);
			controller.resourceAndTasksTheyCreated();
			
			}catch(Exception e) {
					
			e.printStackTrace();
			assertTrue(false);
				}
	
	}
	
	@Test
	void testResourceAndTheirGroups(){
		
		try {
			
			assertTrue(true);
			controller.resourceAndTheirGroups();
			
				
			}catch(Exception e) {
					
			e.printStackTrace();
			assertTrue(false);
				}
		
		
	}

	
}
