package com.bank.services.impl;

import com.bank.dao.CustomerOperationsDAO;
import com.bank.dao.impl.CustomerOperationsDAOImpl;
import com.bank.exception.BussinessException;
import com.bank.modal.Customer;
import com.bank.services.CustomerOperations;

public class CustomerOperationsImpl implements CustomerOperations {
	
	private CustomerOperationsDAO customerOperationsDAO = new CustomerOperationsDAOImpl();

	@Override
	public int newCustomerRegistration(Customer customer) throws BussinessException {
		int c = 0;
		String gender = customer.getGender();
		if(gender.equalsIgnoreCase("male") | gender.equalsIgnoreCase("female")) {
			gender = gender.toUpperCase().charAt(0)+"";
			customer.setGender(gender);
			logFile.trace("Coverted to DB Value type >>> Gender: "+gender);
			
		} else {
			throw new BussinessException("Enter Valid Gender");
		}
		
		if(customer.getEmail().matches("^(.+)@(.+)$")) {
			if((customer.getNumber()+"").length() == 10) {
				if((customer.getSsn()+"").length() == 9)
					c = customerOperationsDAO.newCustomerRegistration(customer);
				else
					throw new BussinessException("Enter Valid SSN of 9 Digits.");
			}else
				throw new BussinessException("Enter Valid Numberof 10 Digits.");
		}else
			throw new BussinessException("Enter Valid Email.");
			
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

	@Override
	public Customer getCustomerDetailsById(int id) throws BussinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
