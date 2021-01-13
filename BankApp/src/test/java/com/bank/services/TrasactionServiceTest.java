package com.bank.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.bank.dao.TransationServiceDAO;
import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Employee;
import com.bank.modal.Transaction;
import com.bank.services.impl.TransactioServiceImpl;

class TrasactionServiceTest {
	
	Employee mockEmployee;
	Account mockAccount;
	Transaction mockTransaction;
	TransationServiceDAO mockTransactionServiceDAO;
	
	
	@BeforeEach
	public void setUp() {
		mockEmployee = mock(Employee.class);
		mockAccount = mock(Account.class);
		mockTransaction = mock(Transaction.class);
		mockTransactionServiceDAO = mock(TransationServiceDAO.class);
	}

	//for deposite
	@Test
	void testDepositeToAccountInvalidAmount() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.depositeToAccount(mockAccount, -100);
			}
		});
		
		assertEquals("Deposite Amount should more than Zero(0).", t.getMessage());
	}
	
	@Test
	void testDepositeToAccountZeroAmount() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.depositeToAccount(mockAccount, 0);
			}
		});
		
		assertEquals("Deposite Amount should more than Zero(0).", t.getMessage());
	}

	//for widraval 
	@Test
	void testWidrawToAccountInvalidAmount() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.widrawToAccount(mockAccount, -100);
			}
		});
		
		assertEquals("Widraval Amount should more than Zero(0).", t.getMessage());
	}
	
	@Test
	void testWidrawToAccountZeroAmount() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.widrawToAccount(mockAccount, 0);
			}
		});
		
		assertEquals("Widraval Amount should more than Zero(0).", t.getMessage());
	}
	
	// transaction by status
	@Test
	void testGetAllTransactionOfAllAccountsByStatus() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.getAllTransactionOfAllAccountsByStatus(45);
			}
		});
		
		assertEquals("Something Went Wrong, Try Again.", t.getMessage());
	}
	
	//transaction Conformation
	@Test
	void testTransactionConfirmationFromEmployee() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.transactionConfirmationFromEmployee(mockEmployee, mockTransaction, 546);
			}
		});
		
		assertEquals("Something Went Wrong, Try Again.", t.getMessage());
	}
	
	//transfer Money to another account
	@Test
	void testTransferMoneyToAccountInvalidAccountNumber() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.transferMoneyToAccount(mockAccount, 123456345L, 1232);
			}
		});
		
		assertEquals("Enter Valid Account Number.", t.getMessage());
	}
	
	@Test
	void testTransferMoneyToAccountInvalidAmount() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.transferMoneyToAccount(mockAccount, 123456L, -123);
			}
		});
		
		assertEquals("Transfer Amount should more than Zero(0).", t.getMessage());
	}
	
	@Test
	void testTransferMoneyToAccountZeroAmount() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.transferMoneyToAccount(mockAccount, 123456L, 0);
			}
		});
		
		assertEquals("Transfer Amount should more than Zero(0).", t.getMessage());
	}
	
	//transfer Money Request Confirmation
	@Test
	void testTransferMoneyRequestsConfirmationZeroStatus() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.transferMoneyRequestsConfirmation(mockTransaction, 0);
			}
		});
		
		assertEquals("Can not Complete the Transaction at the moment, Try Aganin Later", t.getMessage());
	}
	
	@Test
	void testTransferMoneyRequestsConfirmationInvalidStatus() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.transferMoneyRequestsConfirmation(mockTransaction, 123);
			}
		});
		
		assertEquals("Can not Complete the Transaction at the moment, Try Aganin Later", t.getMessage());
	}
	
	//get all transaction by Account Number
	@Test
	void testGetAllTransactionOfAllAccountsByAcountNumber() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.getAllTransactionOfAllAccountsByAcountNumber(1234567897654321L);
			}
		});
		
		assertEquals("Enter Valid Account Number.", t.getMessage());
	}
	
	@Test
	void testGetAllTransactionOfAllAccountsByAcountNumberEmptyAccountNumber() {
		Throwable t = assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TrasactionService transactionService = new TransactioServiceImpl();
				transactionService.getAllTransactionOfAllAccountsByAcountNumber(0);
			}
		});
		
		assertEquals("Enter Valid Account Number.", t.getMessage());
	}
}
