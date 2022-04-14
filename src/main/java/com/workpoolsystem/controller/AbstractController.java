package com.workpoolsystem.controller;



import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class AbstractController {
	
	 @SuppressWarnings("rawtypes")
	public Object getObjectById(Class c, int id, Session session) {
		  
   	     //Session session = HibernateUtil.getSessionFactory().openSession();
		 Transaction transaction = null;
	     transaction = session.beginTransaction();
	   
		 Query query = session.createQuery("from  "+c.getName()+ " where id="+id);  
	     
		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			
			 transaction.commit();
            
			return query.getResultList().get(0);
		}
		
	}
	

}
