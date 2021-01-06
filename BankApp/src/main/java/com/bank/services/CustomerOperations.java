package com.bank.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Customer;

public interface CustomerOperations {
	
	static Logger log = Logger.getLogger("consoleLog.MainBank");
	static Logger logFile = Logger.getLogger("fileLogger.MainBank");

	public int newCustomerRegistration(Customer customer) throws BussinessException;
	public Customer customerLogin(String email, String pass) throws BussinessException;
	public Customer getCustomerDetailsById(int id) throws BussinessException;
	
	public int createNewBankAccount(Account account, Customer customer) throws BussinessException;
	public List<Account> getPendingRequesteAccount(Customer customer) throws BussinessException;
	
	
}
