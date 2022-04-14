package com.workpoolsystem.controller;





import java.text.ParseException;

import java.util.*;
import java.util.regex.*;
import javax.persistence.*;
import javax.persistence.criteria.*;
import org.hibernate.*;

import org.hibernate.query.Query;

import com.workpoolsystem.entity.*;
import com.workpoolsystem.util.HibernateUtil;


public class ResourceController extends AbstractController {

public ArrayList<String> validateResource(Resource resource) {
		
		
		ArrayList<String>  errorMessageList = new ArrayList<>();
		Pattern pattern = Pattern.compile("[^a-zA-Z]");
		Session session = HibernateUtil.getSessionFactory().openSession();

		//validate resource id
		Resource resourceId = (Resource) getObjectById(Resource.class, resource.getId(), session);
				
		if(resourceId==null) {	
			   errorMessageList.add("Resource id does not exist");
		       System.out.println("Resource id does not exist");
		}
		
		
		
		
		
			
		
		//Validating Name
		String name = resource.getFirstname();
		
		if (name == null || name.trim().isEmpty())   {
		     errorMessageList.add("no name specified");
		    System.out.println("no name specified");
		}
		//convert name to char array to use isDigit method
		char[] ch = name.toCharArray();
		for(char c : ch){
		if(Character.isDigit(c)){
			errorMessageList.add("firstname must not contain number/s");
			System.out.println("firstname must not contain number/s");
			 break;
		}
		}
		
		 Matcher matcher = pattern.matcher(name);
		 boolean containsChar = matcher.find();
		  if (containsChar) {
				 errorMessageList.add("firstname must not contain special characters");
				 System.out.println("firstname must not contain special characters");
		  }
		
		  
		  
		  
		 
		  
		  
		//validate surname
		String surname = resource.getLastname();
			
		if (surname == null || surname.trim().isEmpty())   {
			    errorMessageList.add("no lastname specified");
			    System.out.println("no lastname specified");
			}
		//convert surname to char array to use isDigit method
		char[] chr = surname.toCharArray();
		for(char cr : chr){
		if(Character.isDigit(cr)){
				errorMessageList.add("lastname must not contain number/s");
				System.out.println("lastname must not contain number/s");
				break;
			}
			}
		
		 Matcher matcherSurname = pattern.matcher(surname);
		 boolean surnameContainChar = matcherSurname.find();
		if (surnameContainChar){
				errorMessageList.add("lastname must not contain special characters");
				System.out.println("lastname must not contain special characters");
			 }
			 
			
		
		
		
		//validate DOB
		Calendar today = Calendar.getInstance();
	    Calendar future = Calendar.getInstance();
		Calendar calendar = resource.getDob();
	
		future.set(Calendar.YEAR, 2022);
	    
	
		if(calendar.after(future)) {
			errorMessageList.add("DOB can not be a future date");
			System.out.println("DOB can not be a future date");
			
		}
	
		//get only year, month and day of month from today's date and DOB,, to check if they are equal
		if(calendar.get(calendar.YEAR)  == today.get(today.YEAR) && calendar.get(calendar.MONTH) == today.get(today.MONTH) && calendar.get(calendar.DAY_OF_MONTH) == today.get(today.DAY_OF_MONTH)) {
			errorMessageList.add("DOB can not be a today's date");
			System.out.println("DOB can not be a today's date");
			 
		} 
	 /*
       String dobPattern = "(\\d{4}) - (0?[1-9] | 1[012]) - (0?[1-9] | [12][0-9] | 3[01])"; 
		if(calendar.toString().trim().contains("a-zA-Z") ) {
			errorMessageList.add("DOB cannot contain string");
			System.out.println("DOB cannot contain string");
		}
		*/
		
		
		
				
		//Validating address
		String address = resource.getAddress();
						
		if (address == null || address.trim().isEmpty())   {
							
			errorMessageList.add("address can't be null");
			System.out.println("address can't be null");
			}
			  
				
				
		
		 
		//Validate email
		String emailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		String email = resource.getEmail();
								
		if (email == null || email.matches(emailPattern) == false)   {
									
			errorMessageList.add("Invalid email address");
			System.out.println("Invalid email address");
		}

				
					
				
	
		//validate username
		String userName = resource.getUsername();
				
		if (userName == null || userName.trim().isEmpty()){
								
			errorMessageList.add("no username specified");
			System.out.println("no username specified");
					
	   }
				
				 
				
				 CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder(); 
				 
				 CriteriaQuery<Resource> query = criteriaBuilder.createQuery(Resource.class);
				 Root<Resource> root = query.from(Resource.class);
				 
				//where clause
				 query.where(criteriaBuilder.equal(root.get("username"), userName));
				 CriteriaQuery<Resource> all = query.select(root);
				 TypedQuery<Resource> allQuery = session.createQuery(all);
				 
				if(!allQuery.getResultList().isEmpty()) {
					
					   errorMessageList.add("username already exist");
				       System.out.println("username already exist");
				}
				
				  
				
		
			       
			     
				
				
			  //Validating Password
			String password = resource.getPassword();
							
			if (password == null || password.trim().isEmpty())   {
								
					 errorMessageList.add("no password specified");
					 System.out.println("no password specified");
							
				}
		       
			if(password.trim().length() > 13) {
				 errorMessageList.add("password must not exceed 13 characters");
				 System.out.println("password must not exceed 13 characters");
				
			}
			String sPattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
			
			if(password.matches(sPattern) == false){
				errorMessageList.add("password needs to include both lower and uppercase characters, atleast one number, one special character and must be atleast 8 characters long");
				System.out.println("password needs to include both lower and uppercase characters, atleast one number and one special character and must be atleast 8 characters long");
			
				 
			} 
				
			
			
			  
		
		return errorMessageList;
	}
	
	
	
	
	  public Resource createResource(Resource tempResource){
		  Session session = HibernateUtil.getSessionFactory().openSession();
		  Transaction transaction = null;
	      transaction = session.beginTransaction();
	      Resource resource = new Resource();
	      try {
	    	  
	      
	      resource.setFirstname(tempResource.getFirstname());
	      resource.setLastname(tempResource.getLastname());
	      resource.setDob(tempResource.getDob());
	      resource.setAddress(tempResource.getAddress());
	      resource.setEmail(tempResource.getEmail());
	      resource.setUsername(tempResource.getUsername());
	      resource.setPassword(tempResource.getPassword());
	      resource.setManager(tempResource.getManager());
	      resource.setType(tempResource.getType());
	      
	      
	      session.save(resource);
	      transaction.commit();
	      System.out.println("Resource created");
	
	      }catch(HibernateException e) {
	    	  e.printStackTrace();
	      }
		return resource;
	   }
	  
	  
	  public List<Resource> getAllResource(){
	
		  Session session = HibernateUtil.getSessionFactory().openSession();
			 CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			 
			 CriteriaQuery<Resource> criteriaQuery = criteriaBuilder.createQuery(Resource.class);
			 Root<Resource> root = criteriaQuery.from(Resource.class);
			 
			 //select  all 
			 CriteriaQuery<Resource> all = criteriaQuery.select(root);
			 TypedQuery<Resource> allQuery = session.createQuery(all);
			 
			
			 return allQuery.getResultList();
		  }
	  
	  
	  
	public Resource getResourceById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Resource resource = (Resource) getObjectById(Resource.class, id, session);
		//System.out.println(resource.toString());
		return resource;
	}
	
	
	 public List<Resource> getResourceByName(String name){
		
			
		 
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder(); 
		 
		 CriteriaQuery<Resource> query = criteriaBuilder.createQuery(Resource.class);
		 Root<Resource> root = query.from(Resource.class);
		 //where clause (restrict rows returned)
		 query.where(criteriaBuilder.or(criteriaBuilder.like(root.get("firstName").as(String.class),  name.trim() + "%"), criteriaBuilder.like(root.get("lastName").as(String.class), name.trim() + "%"))
	
				 );
		
		 //select  all 
		 CriteriaQuery<Resource> all = query.select(root);
		 TypedQuery<Resource> allQuery = session.createQuery(all);
		
	
		
	     return allQuery.getResultList();
	   
		
	 }
	 
	 
	   
     public Resource updateResource(Resource resourceToUpdate, Session session) {
    	 
       try {

			 Transaction transaction = session.beginTransaction();

		  	 session.update(resourceToUpdate);
			 transaction.commit();
			 session.close();
			 
			 System.out.println("Resource updated");
			return resourceToUpdate;
	    	 }catch(Exception e) {
				 e.printStackTrace();
				 return null;
			 }
		 
    	 
     }
     
     
     public void deleteResource(int id) {
    	 
    	 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction transaction = null;
	     transaction = session.beginTransaction();
		 CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		 
		 CriteriaDelete<Resource> delete = criteriaBuilder.createCriteriaDelete(Resource.class);
		 
		 //delete from
		 Root<Resource> root = delete.from(Resource.class);
		 
		 // where clause
		 delete.where(criteriaBuilder.equal(root.get("id"), id));
		 
		 //execute delete
		 session.createQuery(delete).executeUpdate();
		  transaction.commit();
		  System.out.println("Resource deleted");
		 
    	 
     }
     
     
     
     
     
     
     
     
     /*
      * -----------------------------HQL Queries------------------------
      */
     
     
     
     
     
     
     @SuppressWarnings("rawtypes")
	public List<Object> resourceAndTasksTheyCreated() throws ParseException{
    	 
    	 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction transaction = null;
	     transaction = session.beginTransaction(); 
	     
	     
		Query query = session.createQuery("select r.firstName , r.lastName, t.title, t.date_created from Resource r inner join Task t ON r.id = t.owner_id");
		
		
		 @SuppressWarnings("unchecked")
	    List<Object> results = (List<Object>) query.list(); 
		Iterator iterator = results.iterator();
	     while(iterator.hasNext()) {
	    	 Object[] obj  = (Object[]) iterator.next();
	    	 
	    	 String name = String.valueOf(obj[0]);
	    	 String surname = String.valueOf(obj[1]);
	    	 String title = String.valueOf(obj[2]);
	     	 Calendar dateCreated = (Calendar) obj[3];
		     
	         
	         System.out.println("Resource name = " + name  + " , " + " Surname =  " + surname + " , " + " Task created = " + title + " , "   +" Date created = " +dateCreated.getTime());
	      
	     }
  
    	 transaction.commit();
    	
    	return results;
    	 
     }
     
     

     
     @SuppressWarnings("rawtypes")
	public List<Object> resourceAndTheirGroups() throws ParseException{
    	 
    	 Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction transaction = null;
	     transaction = session.beginTransaction(); 
	     
	     Query query = session.createQuery("select  r.firstName, r.lastName, g.name from Group g join g.resources r ");
	     
	     
	     @SuppressWarnings("unchecked")
		List<Object> results = (List<Object>) query.list(); 
	     Iterator iterator = results.iterator();
	     
	     while(iterator.hasNext()) {
	    	 Object[] obj  = (Object[]) iterator.next();
	    	 
	    	 String name = String.valueOf(obj[0]);
	    	 String surname = String.valueOf(obj[1]);
	    	 String groupName = String.valueOf(obj[2]);
	     	 
		     
	         
	         System.out.println("Resource name = " + name  + " , " + " Surname =  " + surname + " , " + " Group name = " +groupName  );
	      }
	     
	    transaction.commit();
	    return results;
	     
}
}
	 
	

		 
		 
		
		 
		
	
		 
	  
	  
		

	

