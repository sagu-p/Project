package com.bank.modal;

import java.util.Date;

public class Employee {
	
	private int emp_id;
	private String name;
	private String email;
	private String pass;
	private long number;
	private String gender;
	private Date dob;
	
	public Employee() {
		
	}

	public Employee(int emp_id, String name, String email, String pass, long number, String gender, Date dob) {
		super();
		this.emp_id = emp_id;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.number = number;
		this.gender = gender;
		this.dob = dob;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Employee \nemp_id: " + emp_id + "\nName: " + name + "\nEmail: " + email + "\nnumber: "
				+ number + "\nGender:" + gender + "\nDate of Birth:" + dob;
	}
	
	

}
