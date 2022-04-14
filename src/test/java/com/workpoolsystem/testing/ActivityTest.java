package com.workpoolsystem.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import com.workpoolsystem.controller.AbstractController;
import com.workpoolsystem.controller.ActivityController;
import com.workpoolsystem.controller.EntryFolderController;
import com.workpoolsystem.controller.ResourceController;
import com.workpoolsystem.controller.TaskController;
import com.workpoolsystem.controller.TaskTypeController;
import com.workpoolsystem.entity.Activity;
import com.workpoolsystem.entity.EntryFolder;
import com.workpoolsystem.entity.Resource;
import com.workpoolsystem.entity.Task;
import com.workpoolsystem.entity.TaskType;
import com.workpoolsystem.util.HibernateUtil;

public class ActivityTest extends AbstractController {
Activity activity = new Activity();
ActivityController controller = new ActivityController();
ResourceController resourceController = new ResourceController();
TaskTypeController typeController = new TaskTypeController();
TaskController taskController = new TaskController();
EntryFolderController entryController = new EntryFolderController();

ArrayList<String> errorMessageList = new ArrayList<>();
	


//Validate Id
@Test
void testId() {
	activity.setId(13);
	errorMessageList = controller.validateActivity(activity);
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
		
		activity.setTitle("   ");	  
		errorMessageList = controller.validateActivity(activity);  
		if(errorMessageList.size() > 0 ){  
			assertTrue(true);
		}
		else {
			assertTrue(false);
		}
		
		//activity.setTitle(null);
		
		//activity.setTitle("");
		
		//activity.setTitle("This is a long activity title. This is a long activity title. This is a long activity title. This is a long activity title. This is a long activity title. This is a long activity title. This is a long activity title. This is a long activity title. This is a long activity title. ");
		

		
	}

	
	//Validate author
	@Test
	void testAuthor() {
		 Resource author = resourceController.getResourceById(2);
		activity.setAuthor(author);
		errorMessageList = controller.validateActivity(activity);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else{
			assertTrue(false);
		}
	}
	
	
	//validate task type id
	@Test
	void testType() {
		 TaskType type = typeController.getTaskTypeById(2);
		activity.setType_id(type);
		errorMessageList = controller.validateActivity(activity);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else{
			assertTrue(false);
		}
	}
	
	//validate task  id
	@Test
	void testTaskId() {
		Task task_id = taskController.getTaskById(2);
		activity.setTask_id(task_id);
		errorMessageList = controller.validateActivity(activity);
		if(errorMessageList.size() > 0) {
			assertTrue(true);
		}
		else{
			assertTrue(false);
		}
	}
	
	
	//validate entry  id
		@Test
		void testEntryId() {
		
				
			
			EntryFolder entry = entryController.getEntryById(32);
			activity.setEntry_id(entry);
			errorMessageList = controller.validateActivity(activity);
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


	try { 		
	 Calendar start = Calendar.getInstance();
	 Calendar end = Calendar.getInstance();

	activity.setTitle("Code review");
	activity.setDescription(null);
	activity.setStart(start);
	activity.setEnd(end);
	
    Resource author = resourceController.getResourceById(1);
	activity.setAuthor(author);
	
	TaskType type_id = typeController.getTaskTypeById(2);
	activity.setType_id(type_id);
	
	Task task_id = taskController.getTaskById(2);
	activity.setTask_id(task_id);
	
	
	EntryFolder entry = entryController.getEntryById(1);
	activity.setEntry_id(entry);
   
	errorMessageList = controller.validateActivity(activity);
	if(errorMessageList.size() > 0) {
		assertTrue(false);

	}
	else {
		assertTrue(true); 
	    controller.createActivity(activity);
	    System.out.println(activity.toString());
	}
	
}catch(Exception e) {
	
	e.printStackTrace();
	assertTrue(false);
 
}	
	
}

@Test 
void testGetAllActivities() {
	
	int expected = 8;
	try {
		
		List<Activity> activityList = controller.getAllActivities();
		assertEquals(expected, activityList.size());
		System.out.println(activityList.toString());
		     assertTrue(true);   
		    
		   
	
	}
	 catch(HibernateException e) {
				
		e.printStackTrace();
		assertTrue(false);
	}
}

@Test
void testGetActivityById() {
int id = 7;
	try {
	     assertTrue(true);  
		controller.getActivityById(id);
		System.out.println(controller.getActivityById(id).toString());
		
		
	}catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
		}
	
}


@Test 
void testGetActivityByTitle() {
	

 String name =  "tty";
	
 try {
	    
		List<Activity> activityList = controller.getActivityByTitle(name);
		if(!activityList.isEmpty()) {
			System.out.println(activityList.toString());
		     assertTrue(true);  
		}
		if(activityList.isEmpty()) {
			
			System.out.println("No activity found");
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

		Activity update   = (Activity) getObjectById(Activity.class, activityToUpdate, session );
	//updating task id and type id
	TaskType type_id = typeController.getTaskTypeById(1);
	update.setType_id(type_id);
	Task task_id = taskController.getTaskById(1);
	update.setTask_id(task_id);
	
	errorMessageList = controller.validateActivity(update);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true);
		controller.updateActivity(update, session);
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
		int id = 3;
		activity.setId(id);
		errorMessageList = controller.validateActivity(activity);
		if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
			assertTrue(true);
			controller.deleteActivity(id);
		}
	}catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
		}
	
}



@Test
void testCreatedBy() {
	
	try {
		int id = 2;
		Resource author = resourceController.getResourceById(id);
	    activity.setAuthor(author);
	    errorMessageList = controller.validateActivity(activity);
		if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
			assertTrue(true);
		   controller.activitiesCreatedBy(activity);
		   System.out.println(controller.activitiesCreatedBy(activity).toString());
		}
			 
			
			
			
		}catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
			}
	
	
}

@Test
void testActivityCount() {
	
	
	   try {
		assertTrue(true);
		controller.countActivities();
		System.out.println(controller.countActivities());
		
			 
		}catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
			}
	
}


@Test
void testActivityBetween() {
	
	
	   try {
	
	    Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.set(2022, Calendar.MARCH, 8, 14,35,48);
		end.set(2022, 02, 11, 14,36,25);
		
		start.setTime(start.getTime());
		end.setTime(end.getTime());
		
		
		
		assertTrue(true);
		controller.activityCreatedBetween(start, end  );
		System.out.println(controller.activityCreatedBetween(start, end).toString());
		System.out.println(start.getTime());
			 
		}catch(Exception e) {
				
		e.printStackTrace();
		assertTrue(false);
			}
	
}



}
