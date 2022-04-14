package com.workpoolsystem.entity;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;


import com.workpoolsystem.enums.ResourceType;

public class Resource {
	  private int id;    
	  private String firstName;
	  private String lastName;
	  private Calendar dob ;
	  private String address;
	  private String email;        
      private String username;            
	  private String password;
	  private Resource manager;
	  private ResourceType type;
	  private Set<Group> groups = new HashSet<Group>();
	 
	   
	  public Resource() {
			 
		 }
	  

	public Set<Group> getGroups() {
		return groups;

	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstName;
	}
	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}
	public String getLastname() {
		return lastName;
	}
	public void setLastname(String lastname) {
		this.lastName = lastname;
	}
	public Calendar getDob() {
		return dob;
	}
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email; 
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ResourceType getType() {
		return type;
	}
	public void setType(ResourceType type) {
		this.type = type;
	}    
	  
	public Resource getManager() {
		return manager;
	}
	public void setManager(Resource manager) {
		this.manager = manager;
	}
	     
	  
	  
		@Override
		public String toString() {
			return "Resource [id=" + id + ", firstname=" + firstName + ", lastname=" + lastName + ", dob=" + dob.getTime()
					+ ", address=" + address + ", email=" + email + ", username=" + username + ", password=" + password
					+  ", type=" + type +", manager_id=" + manager.getId()+"\n"+ "]";
		}

}
