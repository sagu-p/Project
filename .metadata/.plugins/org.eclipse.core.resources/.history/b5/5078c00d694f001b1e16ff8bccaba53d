package com.bank.dao;

import org.apache.log4j.Logger;

import com.bank.exception.BussinessException;
import com.bank.modal.Customer;

public interface CustomerOperationsDAO {
	
	static Logger log = Logger.getLogger(CustomerOperationsDAO.class);
	
	public int newCustomerRegistration(Customer customer) throws BussinessException;
	public Customer customerLogin(String email, String pass) throws BussinessException;

}
