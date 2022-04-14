package com.workpoolsystem.entity;

import java.util.Calendar;

public class EntryFolder {

	
	private int id;
	private String name;
	private Calendar date_created;
	private Calendar date_modified;
	
	public EntryFolder() {
		
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

	public Calendar getDate_created() {
		return date_created;
	}

	public void setDate_created(Calendar date_created) {
		this.date_created = date_created;
	}

	public Calendar getDate_modified() {
		return date_modified;
	}

	public void setDate_modified(Calendar date_modified) {
		this.date_modified = date_modified;
	}

	@Override
	public String toString() {
		return "EntryFolder [id= " + id + ", name= " + name + ", date_created= " + date_created + ", date_modified="
				+ date_modified + "\n"+"]";
	}
	
}
