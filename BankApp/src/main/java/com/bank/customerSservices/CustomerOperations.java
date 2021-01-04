package com.bank.customerSservices;

import com.bank.exception.BussinessException;
import com.bank.modal.Customer;

public interface CustomerOperations {

	public int newCustomerRegistration(Customer customer) throws BussinessException;
	public Customer customerLogin(String email, String pass) throws BussinessException;
	
}
