package com.bank.dao;

import org.apache.log4j.Logger;

import com.bank.exception.BussinessException;
import com.bank.modal.Employee;

public interface EmployeeOperationsDAO {
	
	static Logger log = Logger.getLogger("consoleLog.MainBank");
	static Logger logFile = Logger.getLogger("fileLogger.MainBank");
	
	public Employee employeeLogIn (String email, String pass) throws BussinessException;

}
