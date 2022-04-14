package com.workpoolsystem.entity;

public class TaskType {

	private int id;
	private String name;
	
	public TaskType() {
		
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

	@Override
	public String toString() {
		return "TaskType [id=" + id + ", name=" + name + "\n" +"]";
	}
	
}
