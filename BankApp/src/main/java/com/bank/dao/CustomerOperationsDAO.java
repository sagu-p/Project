package com.bank.dao;

import com.bank.exception.BussinessException;
import com.bank.modal.Customer;

public interface CustomerOperationsDAO {
	
	public int newCustomerRegistration(Customer customer) throws BussinessException;
	public Customer customerLogin(String email, String pass) throws BussinessException;

}
