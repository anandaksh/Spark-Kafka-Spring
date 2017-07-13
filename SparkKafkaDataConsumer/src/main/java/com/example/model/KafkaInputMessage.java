package com.example.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KafkaInputMessage implements Serializable {

	@JsonProperty(value = "studentname")
	private String studentname;

	@JsonProperty(value = "location")
	private String location;

	@JsonProperty(value = "emailid")
	private String emailid;

	@JsonProperty(value = "mobnumber")
	private Integer mobnumber;
	
	@JsonProperty(value = "branch")
	private String branch;
	
	
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public KafkaInputMessage()
	{
		
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public Integer getMobnumber() {
		return mobnumber;
	}

	public void setMobnumber(Integer mobnumber) {
		this.mobnumber = mobnumber;
	}

	public KafkaInputMessage(String studentname, String location, String emailid, Integer mobnumber) {
		super();
		this.studentname = studentname;
		this.location = location;
		this.emailid = emailid;
		this.mobnumber = mobnumber;
	}

	@Override
	public String toString() {
		return "KafkaInputMessage [studentname=" + studentname + ", location=" + location + ", emailid=" + emailid
				+ ", mobnumber=" + mobnumber + "]";
	}
}
