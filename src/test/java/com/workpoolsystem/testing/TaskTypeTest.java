package com.workpoolsystem.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.workpoolsystem.controller.AbstractController;
import com.workpoolsystem.controller.TaskTypeController;
import com.workpoolsystem.entity.TaskType;
import com.workpoolsystem.util.HibernateUtil;



public class TaskTypeTest extends AbstractController{

	TaskType type = new TaskType();
    TaskTypeController controller = new TaskTypeController();
	
	ArrayList<String>  errorMessageList = new ArrayList<>();
	
	//Testing Id
	@Test
	void testId() {
		type.setId(0);
		errorMessageList = controller.validateTaskType(type);
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
			
			type.setName("Administration");	  
			errorMessageList = controller.validateTaskType(type);  
			
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
	

	try { 

       type.setName("Training");

	errorMessageList = controller.validateTaskType(type);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true); 
	    controller.createTaskType(type);
	
	}
	
}catch(Exception e) {
	
	e.printStackTrace();
	assertTrue(false);
}	
}

@Test 
void testGetAllTypes() {
	
	int expected = 2;
	try {
		
		List<TaskType> typeList = controller.getAllTaskType();
		assertEquals(expected, typeList.size());
		System.out.println(typeList.toString());
		     assertTrue(true);   
		    
		   
	
	}
	 catch(HibernateException e) {
				
		e.printStackTrace();
		assertTrue(false);
	}
}

@Test
void testGetTaskTypeById() {
	try {
	int id = 1;
	type.setId(id);
	errorMessageList = controller.validateTaskType(type);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true);
		 controller.getTaskTypeById(id);
	}
		 
		
		
		
	}catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
}
}
@Test 
void testGetTaskTypeByName() {
	

String name =  "Da";
	
try {
	    
		List<TaskType> typeList = controller.getTaskTypeByName(name);
		if(!typeList.isEmpty()) {
			System.out.println(typeList.toString());
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
	int typeToUpdate = 7;
	
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		TaskType update   = (TaskType) getObjectById(TaskType.class, typeToUpdate, session );
	//updating type name
	update.setName("Workpool system");
	
	errorMessageList = controller.validateTaskType(update);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true);
		controller.updateTaskType(update, session);
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
		type.setId(id);
		errorMessageList = controller.validateTaskType(type);
		if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
			assertTrue(true);
			controller.deleteTaskType(id);
		}
	}catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
		}
	
}
}