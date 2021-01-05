package com.bank.services;

import org.apache.log4j.Logger;

import com.bank.exception.BussinessException;
import com.bank.modal.Employee;

public interface EmployeeSservices {

	static Logger log = Logger.getLogger("consoleLog.MainBank");
	static Logger logFile = Logger.getLogger("fileLogger.MainBank");
	
	public Employee employeeLogIn (String email, String pass) throws BussinessException;

}
