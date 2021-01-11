package com.bank.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Employee;
import com.bank.modal.Transaction;

public interface TrasactionService {
	
	static Logger log = Logger.getLogger("consoleLog.MainBank");
	static Logger logFile = Logger.getLogger("fileLogger.MainBank");
	
	//for  Transaction
	public int depositeToAccount(Account account, float amount) throws BussinessException;
	public int widrawToAccount(Account account, float amount) throws BussinessException;
	public Account BalanceOfAccount(Account account) throws BussinessException;
	public List<Transaction> getAllTransactionOfAllAccountsByAcount(Account account) throws BussinessException;
	
	//for Transaction Confirmation
	public List<Transaction> getAllTransactionOfAllAccountsByStatus(int status) throws BussinessException;
	public int transactionConfirmationFromEmployee(Employee employee, Transaction transaction, int status) throws BussinessException;
}
