package com.workpoolsystem.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

import com.workpoolsystem.controller.AbstractController;
import com.workpoolsystem.controller.GroupController;
import com.workpoolsystem.controller.ResourceController;

import com.workpoolsystem.entity.Group;
import com.workpoolsystem.entity.Resource;

import com.workpoolsystem.util.HibernateUtil;




public class GroupControllerTest extends AbstractController {
	Group group = new Group();
	GroupController controller = new GroupController();
	
	ArrayList<String>  errorMessageList = new ArrayList<>();
	
	//Testing Id
	@Test
	void testId() {
		group.setId(0);
		errorMessageList = controller.validateGroup(group);
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
			
			group.setName("Development Group");	  
			errorMessageList = controller.validateGroup(group);  
			
			if(errorMessageList.size() > 0 ){  
				assertTrue(true);
			}
			else {
				assertTrue(false);
			}
		}	
	
	
	//Testing manager
    @Test
	void testManager(){
    	ResourceController resourceController = new ResourceController();
		Resource manager = resourceController.getResourceById(42);
		group.setManager(manager);
		errorMessageList = controller.validateGroup(group); 
		
		
		if(errorMessageList.size() > 0 ){  
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
	}
    
	
	//Testing QA
    @Test
	void testQA(){
    	ResourceController resourceController = new ResourceController();
		Resource qa = resourceController.getResourceById(42);
		group.setQualityAssurer(qa);
		errorMessageList = controller.validateGroup(group); 
		
		
		if(errorMessageList.size() > 0 ){  
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
//CRUD
@Test
void testCreate(){
	
	ResourceController resourceController = new ResourceController();
  
	try { 
    Resource manager = resourceController.getResourceById(2);
    Resource qualityAssurer = resourceController.getResourceById(2);
    
    group.setName("Devssssrraae");
    group.setManager(manager);
	System.out.println(" manager= " + manager.getId() + " - " + manager.getLastname());
    group.setQualityAssurer(qualityAssurer);
	System.out.println(" quality assurer= " + qualityAssurer.getId() + " - " + qualityAssurer.getLastname());
	//add resources on a group
	Set<Resource> resources = new HashSet<Resource>();

	resources.add(resourceController.getResourceById(1));
	resources.add(resourceController.getResourceById(2));
	resources.add(resourceController.getResourceById(3));
	resources.add(resourceController.getResourceById(4));

   group.setResources(resources);
   
	errorMessageList = controller.validateGroup(group);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true); 
	    controller.createGroup(group);
	
	}
	
}catch(Exception e) {
	
	e.printStackTrace();
	assertTrue(false);
}
}



@Test 
void testGetAllGroups() {
	
	int expected = 2;
	try {
		
		List<Group> resourceList = controller.getAllGroup();
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
void testGetGroupById() {
	try {
	int id = 1;
	group.setId(id);
	errorMessageList = controller.validateGroup(group);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true);
		 controller.getGroupById(id);
	}
		 
		
		
		
	}catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
}
}
@Test 
void testGetGroupByName() {
	

 String name =  "Q";
	
 try {
	    
		List<Group> groupList = controller.getGroupByName(name);
		if(!groupList.isEmpty()) {
			System.out.println(groupList.toString());
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
	int groupToUpdate = 7;
	
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Group update   = (Group) getObjectById(Group.class, groupToUpdate, session );
		//updating entry name
		update.setName("Developers");
	
	errorMessageList = controller.validateGroup(update);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true);
		controller.updateGroup(update, session);
		//System.out.println(update.toString());
	}
	}catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
		}
	
}
@Test
void testDelete() {
	try {
		int id = 2;
		group.setId(id);
		errorMessageList = controller.validateGroup(group);
		if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
			assertTrue(true);
			controller.deleteGroup(id);
		}
	}catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
		}
	
}


}
