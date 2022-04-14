package com.workpoolsystem.entity;

import java.util.HashSet;
import java.util.Set;

public class Group {

	private int id;
	private String name;
	private Resource manager;
	private Resource qualityAssurer;
	private Set<Resource> resources = new HashSet<Resource>();
	
	
	 
	 public Group() {
			
		}
		
	 
	 
	public Set<Resource> getResources() {
		return resources;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Resource getManager() {
		return manager;
	}
	public void setManager(Resource manager) {
		this.manager = manager;
	}
	public Resource getQualityAssurer() {
		return qualityAssurer;
	}
	public void setQualityAssurer(Resource qualityAssurer) {
		this.qualityAssurer = qualityAssurer;
	}
	@Override
	public String toString() {
		return "Group [id= " + id + ", name= " + name + ", manager= " + manager+ ", qualityAssurer= " + qualityAssurer +"\n"
				+ "]";
	}
	
	
	
}
