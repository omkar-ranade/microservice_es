package com.searchemployeeservice.bean;

import java.util.Date;

import org.springframework.data.elasticsearch.annotations.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

@Document(indexName = "employee")
public class Employee {

@JsonProperty("empId")
	private String empId;

	@JsonProperty("empFirstName")
	private String empFirstName;

	@JsonProperty("empLastName")
	private String empLastName;

	@JsonProperty("dob")
	private Date dob;

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@JsonProperty("city")
	private String city;

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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
		builder.append(", dob=");
		builder.append(dob);
		builder.append(", city=");
		builder.append(city);
		builder.append("]");
		return builder.toString();
	}

}
