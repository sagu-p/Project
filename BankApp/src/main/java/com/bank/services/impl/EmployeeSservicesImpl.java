package com.bank.services.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.bank.dao.EmployeeOperationsDAO;
import com.bank.dao.impl.EmployeeOperationsDAOImpl;
import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Customer;
import com.bank.modal.Employee;
import com.bank.services.EmployeeSservices;

public class EmployeeSservicesImpl implements EmployeeSservices {
	
	EmployeeOperationsDAO employeeOperationsDAO = new EmployeeOperationsDAOImpl();

	@Override
	public Employee employeeLogIn(String email, String pass) throws BussinessException {
		Employee employee = null;
		
		if(email.matches("^(.+)@(\\S+)\\.\\S+$"))
		{
			employee = employeeOperationsDAO.employeeLogIn(email, pass);
		}
		else
			throw new BussinessException("Enter Valid Email Address.");
		
		return employee;
	}

	@Override
	public Map<Customer, Account> getAllPendingAccountRequest() throws BussinessException {
		Map<Customer, Account> customerAccountDetails = new LinkedHashMap<>();
		
		customerAccountDetails = employeeOperationsDAO.getAllPendingAccountRequest();
		
		if(customerAccountDetails.size() == 0 )
			throw new BussinessException("There is no Pending Request/s for Account Opening");
		return customerAccountDetails;
	}

	@Override
	public int approveAccountOpeningRequest(Account acoount, Employee employee, int status) throws BussinessException {
		int c = 0;
		if( status == 1 || status == 2 ) 
			c = employeeOperationsDAO.approveAccountOpeningRequest(acoount, employee, status);
		else
			throw new BussinessException("Select Valid Approval or Rejection Choise.");
		if(c == 0)
			throw new BussinessException("None Account is Approved or Rejected.");
		return c;
	}

	@Override
	public List<Customer> getAllCustomers() throws BussinessException {
		List<Customer> allCustomerList = new ArrayList<>();
		
		allCustomerList = employeeOperationsDAO.getAllCustomers();
		
		if(allCustomerList.size() == 0 )
			throw new BussinessException("There is no Customer Found.");
		
		return allCustomerList;
	}

}
