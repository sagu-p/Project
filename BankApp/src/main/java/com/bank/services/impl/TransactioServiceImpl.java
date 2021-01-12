package com.bank.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.bank.dao.TransationServiceDAO;
import com.bank.dao.impl.TransactionServiceDAOImpl;
import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Employee;
import com.bank.modal.Transaction;
import com.bank.services.TrasactionService;

public class TransactioServiceImpl implements TrasactionService {

	TransationServiceDAO transationServiceDAO = new TransactionServiceDAOImpl();
	
	@Override
	public int depositeToAccount(Account account, float amount) throws BussinessException {
		
		int c = 0;
		if(amount > 0) {
			c = transationServiceDAO.depositeToAccount(account, amount);
		}
		else
			throw new BussinessException("Deposite Amount should more than Zero(0).");
		if(c == 0)
			throw new BussinessException("Transaction Incompleted.");
		return c;
	}

	@Override
	public int widrawToAccount(Account account, float amount) throws BussinessException {
		int c = 0;
		if(amount > 0) {
			c = transationServiceDAO.widrawToAccount(account, amount);
		}
		else
			throw new BussinessException("Widraval Amount should more than Zero(0).");
		if(c == 0)
			throw new BussinessException("Transaction Incompleted.");
		return c;
	}

	@Override
	public Account BalanceOfAccount(Account account) throws BussinessException {
		Account accountGet = null;
		
		accountGet = transationServiceDAO.BalanceOfAccount(account);
		
		if(accountGet == null)
			throw new BussinessException("Something went Wrong.");
		
		return accountGet;
	}

	@Override
	public List<Transaction> getAllTransactionOfAllAccountsByStatus(int status) throws BussinessException {
		List<Transaction> transactionList = new ArrayList<>();
		
		if(status == 0 || status == 1 || status == 2)
			transactionList = transationServiceDAO.getAllTransactionOfAllAccountsByStatus(status);
		else
			throw new BussinessException("Something Went Wrong, Try Again.");
		
		if(transactionList.size() == 0)
			throw new BussinessException("No Transation/s found.");
		
		return transactionList;
	}

	@Override
	public int transactionConfirmationFromEmployee(Employee employee, Transaction transaction, int status) throws BussinessException {
		int c = 0;
		
		if(status == 1 || status == 2)
			c = transationServiceDAO.transactionConfirmationFromEmployee(employee, transaction, status);
		else
			throw new BussinessException("Something Went Wrong, Try Again.");
		
		if(c != 0)
			return c;
		else
			throw new BussinessException("Transaction Approval / Rejection is not Done , Try Again Later.");
	}

	@Override
	public List<Transaction> getAllTransactionOfAllAccountsByAcount(Account account) throws BussinessException {
		
		List<Transaction> transactionList = new ArrayList<>();
			
		transactionList = transationServiceDAO.getAllTransactionOfAllAccountsByAcount(account);
				
		if(transactionList.size() == 0)
			throw new BussinessException("No Transation/s found.");
		
		return transactionList;
	}

	@Override
	public int transferMoneyToAccount(Account account, long toAccount, float amount) throws BussinessException {
		int c = 0;
		String accCheck = toAccount + "";
		
		if(account.getAcc_num() != toAccount) {
			if(accCheck.matches("[0-9]{6}")) {
				if(amount > 0) {
					c = transationServiceDAO.transferMoneyToAccount(account, toAccount, amount);
				}
				else
					throw new BussinessException("Widraval Amount should more than Zero(0).");
			} else
				throw new BussinessException("Enter Valid Account Number.");
		}else
			throw new BussinessException("You Can not Transfer Money to Your Own Account.");
		
		if(c == 0)
			throw new BussinessException("Transaction Incompleted.");
		return c;
	}

	@Override
	public List<Transaction> transferMoneyRequests(Account account) throws BussinessException {
		List<Transaction> transactionList = new ArrayList<>();
		
		transactionList = transationServiceDAO.transferMoneyRequests(account);
		
		if(transactionList.size() == 0)
			throw new BussinessException("No Transation/s found.");
		
		return transactionList;
	}

	@Override
	public int transferMoneyRequestsConfirmation(Transaction transaction, int status) throws BussinessException {
		int c = 0;
		
		if(status  == 1 || status == 2)
			c = transationServiceDAO.transferMoneyRequestsConfirmation(transaction, status);
		else
			throw new BussinessException("Can not Complete the Transaction at the moment, Try Aganin Later");
		
		if(c == 0)
			throw new BussinessException("Transaction Incomplited / Transaction is not Done , Try Again Later.");
		
		return c;
	}

	@Override
	public List<Transaction> getAllTransactionOfAllAccountsByAcountNumber(long accountNum) throws BussinessException {
		List<Transaction> accountTransactionList = new ArrayList<>();
		
		String matchAccount = accountNum + "" ;
		if(matchAccount.matches("\\d{6}")) {
			accountTransactionList = transationServiceDAO.getAllTransactionOfAllAccountsByAcountNumber(accountNum);
		}else
			throw new BussinessException("Enter Valid Account Number.");
		if(accountTransactionList.size() == 0)
			throw new BussinessException("There is NoTransaction found.");
		
		return accountTransactionList;
	}
}
