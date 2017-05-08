package com.searchemployeeservice.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

	@JsonProperty("empId")
	private String empId;

	@JsonProperty("empFirstName")
	private String empFirstName;

	@JsonProperty("empLastName")
	private String empLastName;

	@JsonProperty("emailId")
	private String emailId;

	@JsonProperty("phoneNo")
	private Long phoneNo;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpFirstName() {
		return empFirstName;
	}

	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	public String getEmpLastName() {
		return empLastName;
	}

	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailID(String emailId) {
		this.emailId = emailId;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [empId=");
		builder.append(empId);
		builder.append(", empFirstName=");
		builder.append(empFirstName);
		builder.append(", empLastName=");
		builder.append(empLastName);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", phoneNo=");
		builder.append(phoneNo);
		builder.append("]");
		return builder.toString();
	}

}
