package com.bank.modal;

import java.util.Date;

public class Customer {
	
	private int c_id;
	private String name;
	private String email;
	private String pass;
	private long number;
	private long ssn;
	private String gender;
	private Date dob;
	private String address;
	private int age;
	
	public Customer() {
		
	}
	
	//without id
	public Customer(String name, String email, String pass, long number, long ssn, String gender, Date dob,
			String address, int age) {
		super();
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.number = number;
		this.ssn = ssn;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.age = age;
	}
	
	public Customer(int c_id, String name, String email, String pass, long number, long ssn, String gender, Date dob,
			String address, int age) {
		super();
		this.c_id = c_id;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.number = number;
		this.ssn = ssn;
		this.gender = gender;
		this.dob = dob;
		this.address = address;
		this.age = age;
	}

	public int getC_id() {
		return c_id;
	}

	public void setC_id(int c_id) {
		this.c_id = c_id;
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

	public long getSsn() {
		return ssn;
	}

	public void setSsn(long ssn) {
		this.ssn = ssn;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "\nCustomer's Detail:\nName: " + name + "\nEmail: " + email + "\nPersonal Number: "+ number + "\nSocial Security Number: " + ssn 
				+ "\nGender: " + gender + "\nData of Birth: " + dob + "\nAddress: " + address + "\nAge:" + age + "\n";
	}
	
	
	

}
