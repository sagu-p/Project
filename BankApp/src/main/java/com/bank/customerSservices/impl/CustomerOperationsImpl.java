package com.bank.customerSservices.impl;

import com.bank.customerSservices.CustomerOperations;
import com.bank.dao.CustomerOperationsDAO;
import com.bank.dao.impl.CustomerOperationsDAOImpl;
import com.bank.exception.BussinessException;
import com.bank.modal.Customer;

public class CustomerOperationsImpl implements CustomerOperations {
	
	private CustomerOperationsDAO customerOperationsDAO = new CustomerOperationsDAOImpl();

	@Override
	public int newCustomerRegistration(Customer customer) throws BussinessException {
		int c = 0;
		if(customer.getEmail().matches("^(.+)@(.+)$") && (customer.getNumber()+"").length() == 10 && (customer.getSsn()+"").length() == 9)
			c = customerOperationsDAO.newCustomerRegistration(customer);
		else
			throw new BussinessException("Enter Valid Details.");
		
		return c;
	}

	@Override
	public Customer customerLogin(String email, String pass) throws BussinessException {
		Customer customer = null;
		
		if(email.matches("^(.+)@(.+)$"))
			customer = customerOperationsDAO.customerLogin(email, pass);
		else
			throw new BussinessException("Enter Valid Email Address.");
		
		
		return customer;
	}

}
