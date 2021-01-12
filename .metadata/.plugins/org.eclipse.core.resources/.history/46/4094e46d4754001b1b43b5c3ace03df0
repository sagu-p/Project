package com.bank.dao;

import java.util.Map;

import org.apache.log4j.Logger;

import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Customer;
import com.bank.modal.Employee;

public interface EmployeeOperationsDAO {
	
	static Logger log = Logger.getLogger("consoleLog.MainBank");
	static Logger logFile = Logger.getLogger("fileLogger.MainBank");
	
	public Employee employeeLogIn (String email, String pass) throws BussinessException;
	public Map<Customer, Account> getAllPendingAccountRequest() throws BussinessException;
	public int approveAccountOpeningRequest(Account acoount, Employee employee, int status) throws BussinessException;

}
