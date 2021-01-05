package com.bank.services.impl;

import com.bank.dao.EmployeeOperationsDAO;
import com.bank.dao.impl.EmployeeOperationsDAOImpl;
import com.bank.exception.BussinessException;
import com.bank.modal.Employee;
import com.bank.services.EmployeeSservices;

public class EmployeeSservicesImpl implements EmployeeSservices {
	
	EmployeeOperationsDAO employeeOperationsDAO = new EmployeeOperationsDAOImpl();

	@Override
	public Employee employeeLogIn(String email, String pass) throws BussinessException {
		Employee employee = null;
		
		if(email.matches("^(.+)@(.+)$"))
		{
			employee = employeeOperationsDAO.employeeLogIn(email, pass);
		}
		else
			throw new BussinessException("Enter Valid Email Address.");
		
		return employee;
	}

}