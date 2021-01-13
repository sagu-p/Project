package com.bank.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import com.bank.dao.impl.TransactionServiceDAOImpl;
import com.bank.dao.util.PostresqlConnection;
import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Customer;
import com.bank.modal.Employee;
import com.bank.modal.Transaction;

class TransationServiceDAOTest {

	Connection mockConnection;
	PreparedStatement mockPreparedStatement; 
	ResultSet mockResultSet;
	
	Account mockAccount;
	Employee mockEmployee;
	Customer mockCustomer;
	Transaction mockTransaction;
	
	@BeforeAll
	public static void initalize() throws SQLException, ClassNotFoundException {
		mockStatic(PostresqlConnection.class);
	}
	
	@BeforeEach
	public void setUp() throws ClassNotFoundException, SQLException {
		
		mockConnection = mock(Connection.class);
		mockPreparedStatement = mock(PreparedStatement.class);
		mockResultSet = mock(ResultSet.class);
		
		mockEmployee = mock(Employee.class);
		mockAccount = mock(Account.class);
		mockCustomer = mock(Customer.class);
		mockTransaction = mock(Transaction.class);
		
		when(PostresqlConnection.getConnection()).thenReturn(mockConnection);
		
		//System.out.println("Before Each Test");
	}
	
	// balance of Account
	@Test
	void testBalanceOfAccount() throws SQLException, BussinessException {
		
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setLong(Mockito.anyInt(), Mockito.anyLong());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.TRUE);
		
		TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
		transactionServiceDAO.BalanceOfAccount(mockAccount);
		
		verify(mockConnection, times(1)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(1)).setLong(Mockito.anyInt(), Mockito.anyLong());
		verify(mockPreparedStatement, times(1)).executeQuery();
		verify(mockResultSet, times(1)).next();
		
		System.out.println("Account of Balance.");
	}
	
	@Test
	void testBalanceOfAccountWithException() throws SQLException, BussinessException {
		
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setLong(Mockito.anyInt(), Mockito.anyLong());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.FALSE);
		
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
				transactionServiceDAO.BalanceOfAccount(mockAccount);
			}
		});
		
		
		verify(mockConnection, times(1)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(1)).setLong(Mockito.anyInt(), Mockito.anyLong());
		verify(mockPreparedStatement, times(1)).executeQuery();
		verify(mockResultSet, times(1)).next();
		
		System.out.println("Account of Balance Exception.");
	}

	//get transaction by status
	@Test
	public void testGetAllTransactionOfAllAccountsByStatus() throws SQLException , BussinessException {
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
		
		TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
		transactionServiceDAO.getAllTransactionOfAllAccountsByStatus(1);
		
		verify(mockConnection, times(1)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(1)).setInt(Mockito.anyInt(), Mockito.anyInt());
		verify(mockPreparedStatement, times(1)).executeQuery();
		verify(mockResultSet, times(3)).next();
		
		System.out.println("Account of Balance.");
	}
	
	@Test
	public void testGetAllTransactionOfAllAccountsByStatusWithException() throws SQLException , BussinessException {
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.FALSE);
		
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
				transactionServiceDAO.getAllTransactionOfAllAccountsByStatus(0);
			}
		});
		
		verify(mockConnection, times(1)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(1)).setInt(Mockito.anyInt(), Mockito.anyInt());
		verify(mockPreparedStatement, times(1)).executeQuery();
		verify(mockResultSet, times(1)).next();
		
		System.out.println("Account of Balance Exception.");
	}
	
	// transaction Confirmation
	@Test
	public void testTransactionConfirmation() throws SQLException , BussinessException {
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		doNothing().when(mockPreparedStatement).setLong(Mockito.anyInt(), Mockito.anyLong());
		doNothing().when(mockPreparedStatement).setFloat(Mockito.anyInt(), Mockito.anyFloat());
		when(mockPreparedStatement.executeUpdate()).thenReturn(1);
		
		
		TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
		transactionServiceDAO.transactionConfirmationFromEmployee(mockEmployee, mockTransaction, 1);
		
		verify(mockConnection, times(2)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(2)).setInt(Mockito.anyInt(), Mockito.anyInt());
		verify(mockPreparedStatement, times(2)).setLong(Mockito.anyInt(), Mockito.anyLong());
		verify(mockPreparedStatement, times(1)).setFloat(Mockito.anyInt(), Mockito.anyFloat());
		verify(mockPreparedStatement, times(2)).executeUpdate();
		
		System.out.println("Account of Balance.");
	}
	
	@Test
	public void testTransactionConfirmationRejection() throws SQLException , BussinessException {
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		doNothing().when(mockPreparedStatement).setLong(Mockito.anyInt(), Mockito.anyLong());
		doNothing().when(mockPreparedStatement).setFloat(Mockito.anyInt(), Mockito.anyFloat());
		when(mockPreparedStatement.executeUpdate()).thenReturn(1);
		
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
				transactionServiceDAO.transactionConfirmationFromEmployee(mockEmployee, mockTransaction, 2);
			}
		});
		
		
		verify(mockConnection, times(1)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(2)).setInt(Mockito.anyInt(), Mockito.anyInt());
		verify(mockPreparedStatement, times(1)).setLong(Mockito.anyInt(), Mockito.anyLong());
		verify(mockPreparedStatement, times(0)).setFloat(Mockito.anyInt(), Mockito.anyFloat());
		verify(mockPreparedStatement, times(1)).executeUpdate();
		
		System.out.println("Account of Balance.");
	}
	
	//get all transaction of account by Account
	@Test
	void testGetAllTransactionOfAllAccountsByAcount() throws SQLException, BussinessException {
		
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setLong(Mockito.anyInt(), Mockito.anyLong());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
		
		TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
		transactionServiceDAO.getAllTransactionOfAllAccountsByAcount(mockAccount);
		
		verify(mockConnection, times(1)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(1)).setLong(Mockito.anyInt(), Mockito.anyLong());
		verify(mockPreparedStatement, times(1)).executeQuery();
		verify(mockResultSet, times(3)).next();
		
		System.out.println("Account of Balance.");
	}
	
	@Test
	void testGetAllTransactionOfAllAccountsByAcountWithException() throws SQLException, BussinessException {
		
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setLong(Mockito.anyInt(), Mockito.anyLong());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.FALSE);
		
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
				transactionServiceDAO.getAllTransactionOfAllAccountsByAcount(mockAccount);
			}
		});
		
		
		verify(mockConnection, times(1)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(1)).setLong(Mockito.anyInt(), Mockito.anyLong());
		verify(mockPreparedStatement, times(1)).executeQuery();
		verify(mockResultSet, times(1)).next();
		
		System.out.println("Account of Balance.");
	}
	
	//transfer Money to Account
	@Test
	public void testTransferMoneyRequest() throws SQLException, BussinessException {
		
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
		
		TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
		transactionServiceDAO.transferMoneyRequests(mockAccount);
		
		verify(mockConnection, times(1)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(1)).setInt(Mockito.anyInt(), Mockito.anyInt());
		verify(mockPreparedStatement, times(1)).executeQuery();
		verify(mockResultSet, times(3)).next();
	}
	
	@Test
	public void testTransferMoneyRequestWithException() throws SQLException, BussinessException {
		
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.FALSE);
		
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
				transactionServiceDAO.transferMoneyRequests(mockAccount);
			}
		});
		
		
		verify(mockConnection, times(1)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(1)).setInt(Mockito.anyInt(), Mockito.anyInt());
		verify(mockPreparedStatement, times(1)).executeQuery();
		verify(mockResultSet, times(1)).next();
	}
	
	//get all transaction by account number
	@Test
	public void testGetAllTransactionOfAllAccountsByAcountNumber() throws SQLException, BussinessException {
		
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setLong(Mockito.anyInt(), Mockito.anyLong());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
		
		TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
		transactionServiceDAO.getAllTransactionOfAllAccountsByAcountNumber(123456L);
		
		verify(mockConnection, times(1)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(1)).setLong(Mockito.anyInt(), Mockito.anyLong());
		verify(mockPreparedStatement, times(1)).executeQuery();
		verify(mockResultSet, times(3)).next();
	}
	
	@Test
	public void testGetAllTransactionOfAllAccountsByAcountNumberException() throws SQLException, BussinessException {
		
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		doNothing().when(mockPreparedStatement).setLong(Mockito.anyInt(), Mockito.anyLong());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		when(mockResultSet.next()).thenReturn(Boolean.FALSE);
		
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				TransationServiceDAO transactionServiceDAO = new TransactionServiceDAOImpl();
				transactionServiceDAO.getAllTransactionOfAllAccountsByAcountNumber(0);
			}
		});
		
		
		verify(mockConnection, times(1)).prepareStatement(Mockito.anyString());
		verify(mockPreparedStatement, times(1)).setLong(Mockito.anyInt(), Mockito.anyLong());
		verify(mockPreparedStatement, times(1)).executeQuery();
		verify(mockResultSet, times(1)).next();
	}
}
