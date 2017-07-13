package com.example.model;

import java.io.Serializable;


public class BranchLocation implements Serializable {
	private static final long serialVersionUID = 1L;
	private String branch;
	private String location;
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BranchLocation(String branch, String location) {
		super();
		this.branch = branch;
		this.location = location;
	}
	
	public BranchLocation()
	{
		
	}
}
