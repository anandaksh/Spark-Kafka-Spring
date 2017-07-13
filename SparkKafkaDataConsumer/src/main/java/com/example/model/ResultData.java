package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "result")
public class ResultData {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Integer id;

	@Column(name = "branch")
	private String branch;

	@Column(name = "location")
	private String location;

	@Column(name = "numberofbranch")
	private Integer numberofbranch;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public Integer getNumberofbranch() {
		return numberofbranch;
	}

	public void setNumberofbranch(Integer numberofbranch) {
		this.numberofbranch = numberofbranch;
	}

	public ResultData(String branch, String location, Integer numberofbranch) {
		super();
		this.branch = branch;
		this.location = location;
		this.numberofbranch = numberofbranch;
	}
    
	public ResultData()
	{
		
	}
}