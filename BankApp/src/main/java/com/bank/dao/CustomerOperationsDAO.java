package com.bank.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Customer;

public interface CustomerOperationsDAO {
	
	static Logger log = Logger.getLogger("consoleLog.MainBank");
	static Logger logFile = Logger.getLogger("fileLogger.MainBank");
	
	//resgiter new customer
	public int newCustomerRegistration(Customer customer) throws BussinessException;
	
	//customer log in
	public Customer customerLogin(String email, String pass) throws BussinessException;
	
	//Account
	//create new Account
	public int createNewBankAccount(Account account, Customer customer) throws BussinessException;
	
	//pending Account Requests
	public List<Account> getPendingRequesteAccount(Customer customer) throws BussinessException;
	
	//All Accounts of Customer
	public List<Account> getAllAccountsOfCustomer(Customer customer) throws BussinessException;

}
