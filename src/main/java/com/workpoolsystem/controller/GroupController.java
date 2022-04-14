package com.workpoolsystem.controller;

import java.util.ArrayList;

import java.util.List;


import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


import javax.persistence.criteria.CriteriaQuery;



import com.workpoolsystem.entity.Group;
import com.workpoolsystem.entity.Resource;
import com.workpoolsystem.util.HibernateUtil;

public class GroupController extends AbstractController{

	
	
	public ArrayList<String> validateGroup(Group group) {
		
		ArrayList<String> errorMessageList = new ArrayList<>();	
		
		//create session
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder(); 
		 
		 
		 
	      //validate group id (Check if it exists)
	      Group groupId = (Group) getObjectById(Group.class, group.getId(), session);
			
			if(groupId==null) {	
				errorMessageList.add("Activity  id does not exist");
				System.out.println("Activity id does not exist");
			}
		
			
			
			//Validating Name
			String name = group.getName();
			
			if (name == null || name.trim().isEmpty())   {
			     errorMessageList.add("no group name specified");
			    System.out.println("no group name specified");
			}
			//convert name to char array to use isDigit method
			char[] ch = name.toCharArray();
			for(char c : ch){
			if(Character.isDigit(c)){
				errorMessageList.add("group name must not contain number/s");
				System.out.println("group name must not contain number/s");
				 break;
			}
			}
			//check if group name already exists
			 CriteriaQuery<Group> query = criteriaBuilder.createQuery(Group.class);
			 Root<Group> root = query.from(Group.class);
			 
			//where clause
			 query.where(criteriaBuilder.equal(root.get("name"), name));
			 CriteriaQuery<Group> all = query.select(root);
			 TypedQuery<Group> allQuery = session.createQuery(all);
			 
			if(!allQuery.getResultList().isEmpty()) {
				
				   errorMessageList.add("Group name already exist");
			       System.out.println("Group name already exist");
			}
			
			   
			
			
			
			//validate manager id (check if it exists on Resource table
			if (group.getManager() !=null) {
					Resource manager = (Resource) getObjectById(Resource.class, group.getManager().getId(), session);
					if (manager==null) {
						   errorMessageList.add("Manager id does not exist");
					       System.out.println("Manager id does not exist");
					}
				} else {
					   errorMessageList.add("Manager id does not exist");
				       System.out.println("Manager id does not exist");
				}
			
			
			
			
				
				//validate quality assurer id (check if it exists on Resource table)
				if (group.getQualityAssurer() !=null) {
						Resource qa = (Resource) getObjectById(Resource.class,  group.getQualityAssurer().getId(), session);
						if (qa==null) {
							   errorMessageList.add("Quality Assurer id does not exist");
						       System.out.println("Quality Assurer id does not exist");
						}
					} else {
						   errorMessageList.add("Quality Assurer id does not exist");
					       System.out.println("Quality Assurer id does not exist");
					}
					
	
			
		return errorMessageList;
		
	}
	
	
	
	
	

	  public Group createGroup(Group tempGroup){
		  Session session = HibernateUtil.getSessionFactory().openSession();
		  Transaction transaction = null;
	      transaction = session.beginTransaction();
	      Group group = new Group();
	      try {
	    	  
	           group.setName(tempGroup.getName());
	           group.setManager(tempGroup.getManager());
	           group.setQualityAssurer(tempGroup.getQualityAssurer());
	           group.setResources(tempGroup.getResources());
	      
	      session.save(group);
	      transaction.commit();
	      System.out.println("Group created");
	
	      }catch(HibernateException e) {
	    	  e.printStackTrace();
	      }
		return group;
	   }
	  
	  public List<Group> getAllGroup(){
			
		  Session session = HibernateUtil.getSessionFactory().openSession();
			 CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			 
			 CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
			 Root<Group> root = criteriaQuery.from(Group.class);
			 
			 //select  all 
			 CriteriaQuery<Group> all = criteriaQuery.select(root);
			 TypedQuery<Group> allQuery = session.createQuery(all);
			 
			
			 return allQuery.getResultList();
		  }
	  
	  
	  
	  public Group getGroupById(int id) {
		
		  Session session = HibernateUtil.getSessionFactory().openSession();
	      Group group = (Group) getObjectById(Group.class, id, session);

			return group;
		}
	  
	  
	  
	  
		 public List<Group> getGroupByName(String name){
				
				
			 
			 Session session = HibernateUtil.getSessionFactory().openSession();
			 CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder(); 
			 
			 CriteriaQuery<Group> query = criteriaBuilder.createQuery(Group.class);
			 Root<Group> root = query.from(Group.class);
			 //where clause (restrict rows returned)
			 query.where(criteriaBuilder.like(root.get("name").as(String.class),  name.trim() + "%"));
			
			 //select  all 
			 CriteriaQuery<Group> all = query.select(root);
			 TypedQuery<Group> allQuery = session.createQuery(all);
			
		
			
		     return allQuery.getResultList();
		   
			
		 }
		 
		   
	     public Group updateGroup(Group groupToUpdate, Session session) {
	    	 
	    	 try {
	    		 
	    	
			 Transaction transaction = session.beginTransaction();

		  	 session.update(groupToUpdate);
			 transaction.commit();
			 session.close();
			 
			 System.out.println("Group updated");
			return groupToUpdate;
	    	 }catch(Exception e) {
				 e.printStackTrace();
				 return null;
			 
			 
	    	 } 
	     }
	     
	     
	     public void deleteGroup(int id) {
	    	 
	    	 Session session = HibernateUtil.getSessionFactory().openSession();
			 Transaction transaction = null;
		     transaction = session.beginTransaction();
			 CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			 
			 CriteriaDelete<Group> delete = criteriaBuilder.createCriteriaDelete(Group.class);
			 
			 //delete from
			 Root<Group> root = delete.from(Group.class);
			 
			 // where clause
			 delete.where(criteriaBuilder.equal(root.get("id"), id));
			 
			 //execute delete
			 session.createQuery(delete).executeUpdate();
			  transaction.commit();
			  System.out.println("Group deleted");
			 
	    	 
	     }
	     
	     
	     
	     
	     }
	     
	     
	     
		

