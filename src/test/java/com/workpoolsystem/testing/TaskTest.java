package com.workpoolsystem.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.workpoolsystem.controller.AbstractController;
import com.workpoolsystem.controller.EntryFolderController;
import com.workpoolsystem.controller.ResourceController;
import com.workpoolsystem.controller.TaskController;
import com.workpoolsystem.controller.TaskTypeController;

import com.workpoolsystem.entity.EntryFolder;
import com.workpoolsystem.entity.Resource;
import com.workpoolsystem.entity.Task;
import com.workpoolsystem.entity.TaskType;
import com.workpoolsystem.enums.Priority;
import com.workpoolsystem.enums.Status;
import com.workpoolsystem.util.HibernateUtil;

public class TaskTest extends AbstractController {
 Task task = new Task();
 TaskController controller = new TaskController();
 ResourceController resourceController = new ResourceController();
 TaskTypeController typeController = new TaskTypeController();
 EntryFolderController entryController = new EntryFolderController();
 
 ArrayList<String>  errorMessageList = new ArrayList<>();

	//Validate Id
	@Test
	void testId() {
		task.setId(1);
		errorMessageList = controller.validateTask(task);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else{
			assertTrue(false);
		}
	}
	
	
	
	
	//Testing title
		@Test
		void testTitle() {
			
			task.setTitle("   ");	  
			errorMessageList = controller.validateTask(task);  
			
			if(errorMessageList.size() > 0 ){  
				assertTrue(true);
			}
			else {
				assertTrue(false);
			}
		}
 
		//Validate owner
		@Test
		void testOwner() {
			 Resource owner_id = resourceController.getResourceById(3);
			task.setOwner_id(owner_id);
			errorMessageList = controller.validateTask(task);
			if(errorMessageList.size() > 0) {
				assertTrue(true);
			}
			else{
				assertTrue(false);
			}
		}
		

		//Validate assignedTo
		@Test
		void testAssigned() {
			 Resource assignedTo = resourceController.getResourceById(3);
			task.setAssigned_to_id(assignedTo);
			errorMessageList = controller.validateTask(task);
			if(errorMessageList.size() > 0) {
				assertTrue(true);
			}
			else{
				assertTrue(false);
			}
		}
		
		
		
		//Validate task type
		@Test
		void testType() {
			 TaskType type = typeController.getTaskTypeById(1);
			task.setTask_type_id(type);
			errorMessageList = controller.validateTask(task);
			if(errorMessageList.size() > 0) {
				assertTrue(true);
			}
			else{
				assertTrue(false);
			}
		}
		
		//Validate entry
		@Test
		void testEntry() {
			EntryFolder entry = entryController.getEntryById(12);
			task.setEntry_id(entry);
			errorMessageList = controller.validateTask(task);
			if(errorMessageList.size() > 0) {
				assertTrue(true);
			}
			else{
				assertTrue(false);
			}
		}
		
	
		
		
	

//CRUD
@Test
void testCreate(){
	 String dateDate = "2022-05-10";
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		 
	

	try { 
	Date due = dateformat.parse(dateDate);	
	Calendar date_due = Calendar.getInstance();
	date_due.setTimeInMillis(due.getTime());
	
	
     Calendar date_created = Calendar.getInstance();
     Calendar date_next = Calendar.getInstance();
	
	 
     task.setTitle("Using workpool to issue and handle sim cards");
     task.setDescription(null);
     task.setDate_created(date_created);
     task.setDate_completed(null);
     task.setDate_due(date_due);
     task.setDate_next(date_next);
     
     Resource owner_id = resourceController.getResourceById(3);
     task.setOwner_id(owner_id);
     
     Resource assigned_to_id = resourceController.getResourceById(3);
     task.setAssigned_to_id(assigned_to_id);
     
     TaskType task_type_id = typeController.getTaskTypeById(1);
     task.setTask_type_id(task_type_id);

     EntryFolder entry_id = entryController.getEntryById(1);
     task.setEntry_id(entry_id);
     
     
     task.setStatus(Status.Received);
     task.setPriority(Priority.Important);
     
	errorMessageList = controller.validateTask(task);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true); 
	    controller.createTask(task);
	
	}
	
}catch(Exception e) {
	
	e.printStackTrace();
	assertTrue(false);
}	
	
}

@Test 
void testGetAllTasks() {
	
	int expected = 4;
	try {
		
		List<Task> taskList = controller.getAllTasks();
		assertEquals(expected, taskList.size());
		System.out.println(taskList.toString());
		     assertTrue(true);   
		    
		   
	
	}
	 catch(HibernateException e) {
				
		e.printStackTrace();
		assertTrue(false);
	}
}

@Test
void testGetTaskById() {
	try {
	int id = 2;
	task.setId(id);
	errorMessageList = controller.validateTask(task);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true);
		 controller.getTaskById(id);
		System.out.println(controller.getTaskById(id).toString());
	}
		 
		
		
		
	}catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
		}
	
}


@Test 
void testGetTaskByTitle() {
	

 String name =  "Enabl";
	
 try {
	    
		List<Task> taskList = controller.getTaskByTitle(name);
		if(!taskList.isEmpty()) {
			System.out.println(taskList.toString());
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
	int taskToUpdate = 7;

	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Task update   = (Task) getObjectById(Task.class, taskToUpdate, session );
	//updating task TITLE
	update.setTitle("Worpool training");
	errorMessageList = controller.validateTask(update);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true);
		controller.updateTask(update, session);
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
		task.setId(id);
		errorMessageList = controller.validateTask(task);
		if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
			assertTrue(true);
			controller.deleteTask(id);
		}
	}catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
		}
	
}





@Test
void testCreatedBy() {
	
	try {
		int id = 1;
		Resource owner_id = resourceController.getResourceById(id);
	    task.setOwner_id(owner_id);
	    errorMessageList = controller.validateTask(task);
		if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
			assertTrue(true);
		   controller.taskCreatedBy(task);
		   System.out.println(controller.taskCreatedBy(task).toString());
		}
			 
			
			
			
		}catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
			}
	
	
}
}