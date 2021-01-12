package com.bank.services;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Customer;
import com.bank.modal.Employee;

public interface EmployeeSservices {

	static Logger log = Logger.getLogger("consoleLog.MainBank");
	static Logger logFile = Logger.getLogger("fileLogger.MainBank");
	
	//Employee Log in
	public Employee employeeLogIn (String email, String pass) throws BussinessException;
	
	//get all pendind request of Account opening of Customer (get Customer and Account Information)
	public Map<Customer, Account> getAllPendingAccountRequest() throws BussinessException;
	
	//Approval of pending Account Requests
	public int approveAccountOpeningRequest(Account acoount, Employee employee, int status) throws BussinessException;
	
	//get All customers
	public List<Customer> getAllCustomers() throws BussinessException;

}
