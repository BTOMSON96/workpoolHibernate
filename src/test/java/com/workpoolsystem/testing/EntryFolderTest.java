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
import com.workpoolsystem.controller.EntryFolderController;

import com.workpoolsystem.entity.EntryFolder;
import com.workpoolsystem.util.HibernateUtil;




public class EntryFolderTest extends AbstractController{

	
	
	EntryFolder entry = new EntryFolder();
	EntryFolderController controller = new EntryFolderController();
	
	ArrayList<String>  errorMessageList = new ArrayList<>();
	
	//Testing Id
	@Test
	void testId() {
		entry.setId(0);
		errorMessageList = controller.validateEntry(entry);
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
			
			entry.setName("Development");	  
			errorMessageList = controller.validateEntry(entry);  
			
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
  Calendar date_created = Calendar.getInstance();
  Calendar date_modified= Calendar.getInstance();
  entry.setName("Data Security at workpool");
  entry.setDate_created(date_created);
  entry.setDate_modified(date_modified);
	
	
	errorMessageList = controller.validateEntry(entry);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true); 
	    controller.createEntry(entry);
	
	}
	
}catch(Exception e) {
	
	e.printStackTrace();
	assertTrue(false);
}	
}

@Test 
void testGetAllEntries() {
	
	int expected = 2;
	try {
		
		List<EntryFolder> entryList = controller.getAllEntryFolders();
		assertEquals(expected, entryList.size());
		System.out.println(entryList.toString());
		     assertTrue(true);   
		    
		   
	
	}
	 catch(HibernateException e) {
				
		e.printStackTrace();
		assertTrue(false);
	}
}

@Test
void testGetEntryById() {
	try {
	int id = 1;
	entry.setId(id);
	errorMessageList = controller.validateEntry(entry);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true);
		 controller.getEntryById(id);
	}
		 
		
		
		
	}catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
}
}
@Test 
void testGetEntryByName() {
	

 String name =  "Da";
	
 try {
	    
		List<EntryFolder> groupList = controller.getEntryFolderByName(name);
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
	int folderToUpdate = 7;
	
	try {
		Session session = HibernateUtil.getSessionFactory().openSession();
		EntryFolder update   = (EntryFolder) getObjectById(EntryFolder.class, folderToUpdate, session );
	//updating entry name
	update.setName("Admin folder");
	
	errorMessageList = controller.validateEntry(update);
	if(errorMessageList.size() > 0) {
		assertTrue(false);
	}
	else {
		assertTrue(true);
		controller.updateEntry(update, session);
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
		entry.setId(id);
		errorMessageList = controller.validateEntry(entry);
		if(errorMessageList.size() > 0) {
			assertTrue(false);
		}
		else {
			assertTrue(true);
			controller.deleteEntry(id);
		}
	}catch(Exception e) {
			
	e.printStackTrace();
	assertTrue(false);
		}
	
}

}
