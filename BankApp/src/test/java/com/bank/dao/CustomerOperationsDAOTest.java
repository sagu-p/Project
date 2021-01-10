package com.bank.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import com.bank.dao.impl.CustomerOperationsDAOImpl;
import com.bank.dao.util.PostresqlConnection;
import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Customer;

class CustomerOperationsDAOTest {

	Connection mockConnection;
	PreparedStatement mockPreparedStatement; 
	ResultSet mockResultSet;
	
	Account mockAccount;
	Customer mockCustomer;
	
	CustomerOperationsDAO customerOperationsDAO = new CustomerOperationsDAOImpl();
	
	@BeforeAll
	public static void initalize() throws SQLException, ClassNotFoundException {
		mockStatic(PostresqlConnection.class);
	}
	
	@BeforeEach
	public void setUp() throws ClassNotFoundException, SQLException {
		
		mockConnection = mock(Connection.class);
		mockPreparedStatement = mock(PreparedStatement.class);
		mockResultSet = mock(ResultSet.class);
		
		mockAccount = mock(Account.class);
		mockCustomer = mock(Customer.class);
		
		when(PostresqlConnection.getConnection()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		
		//System.out.println("Before Each Test");
	}
	
	//New Customer Registration Test
	@Test
	public void testNewCustomerRegistration() throws SQLException, BussinessException {
		
		Date d = mock(Date.class);
		when(mockCustomer.getDob()).thenReturn(d);
		doNothing().when(mockPreparedStatement).setString(Mockito.anyInt(), Mockito.anyString());
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		doNothing().when(mockPreparedStatement).setLong(Mockito.anyInt(), Mockito.anyLong());
		doNothing().when(mockPreparedStatement).setDate(Mockito.anyInt(), Mockito.any(java.sql.Date.class));
		
		when(mockPreparedStatement.executeUpdate()).thenReturn(1);
		
		assertNotNull(customerOperationsDAO.newCustomerRegistration(mockCustomer));
		System.out.println("Test for Customer Registration Successful.");
	}
	
	@Test
	public void testNewCustomerRegistrationException() throws SQLException, BussinessException {
		
		Date d = mock(Date.class);
		when(mockCustomer.getDob()).thenReturn(d);
		doNothing().when(mockPreparedStatement).setString(Mockito.anyInt(), Mockito.anyString());
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		doNothing().when(mockPreparedStatement).setLong(Mockito.anyInt(), Mockito.anyLong());
		doNothing().when(mockPreparedStatement).setDate(Mockito.anyInt(), Mockito.any(java.sql.Date.class));
		
		when(mockPreparedStatement.executeUpdate()).thenReturn(0);
		
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				customerOperationsDAO.newCustomerRegistration(mockCustomer);
			}
		});
		System.out.println("Test for Customer not get Registered throws BussinessException().");
	}
	
	//Customer Log In
	@Test
	public void testEmployeeLogIn() throws BussinessException, SQLException, RuntimeException {
		
		doNothing().when(mockPreparedStatement).setString(Mockito.anyInt(), Mockito.anyString());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		
		when(mockResultSet.next()).thenReturn(Boolean.TRUE);
		
		assertNotNull(customerOperationsDAO.customerLogin("", ""));
		//assertNotNull(customerOperationsDAO.customerLogin("gyfiwek", "readsik"));
		//assertNotNull(customerOperationsDAO.customerLogin("85634", "32431"));
		//assertNotNull(customerOperationsDAO.customerLogin("!@#$%$%^&", "!@#$%^&"));
		//assertNotNull(customerOperationsDAO.customerLogin("gvdjhbskj", "876543"));
		//assertNotNull(customerOperationsDAO.customerLogin("!@#$%^&", "tfhgjk"));
		//assertNotNull(customerOperationsDAO.customerLogin("12345678", "trdyugyih"));
		
		System.out.println("Customer Log In Test Successful.");
	}
	
	@Test
	public void testEmployeeLogInWithException() throws BussinessException, SQLException, RuntimeException {
		
		doNothing().when(mockPreparedStatement).setString(Mockito.anyInt(), Mockito.anyString());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		
		when(mockResultSet.next()).thenReturn(Boolean.FALSE);
		
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				customerOperationsDAO.customerLogin("abc", "abc");
			}
		});
		
		System.out.println("Customer Log In Test Successful throws BussinessException().");
	}
	
	// creating new bank Account
	@Test
	public void testCreateNewBankAccount() throws SQLException, BussinessException {
		
		Date d = mock(Date.class);
		when(mockAccount.getOpen_date()).thenReturn(d);
		doNothing().when(mockPreparedStatement).setString(Mockito.anyInt(), Mockito.anyString());
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		doNothing().when(mockPreparedStatement).setDate(Mockito.anyInt(), Mockito.any(java.sql.Date.class));
		
		when(mockPreparedStatement.executeUpdate()).thenReturn(1);
		
		assertNotNull(customerOperationsDAO.createNewBankAccount(mockAccount, mockCustomer));
		System.out.println("Test for Create new Bank Account Successful.");
	}
	
	@Test
	public void testCreateNewBankAccountWithException() throws SQLException, BussinessException {
		
		Date d = mock(Date.class);
		when(mockAccount.getOpen_date()).thenReturn(d);
		doNothing().when(mockPreparedStatement).setString(Mockito.anyInt(), Mockito.anyString());
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		doNothing().when(mockPreparedStatement).setDate(Mockito.anyInt(), Mockito.any(java.sql.Date.class));
		
		when(mockPreparedStatement.executeUpdate()).thenReturn(0);
		
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				customerOperationsDAO.createNewBankAccount(mockAccount, mockCustomer);
			}
		});
		
		System.out.println("Test for Create new Bank Account Successful.");
	}
	
	//Customer Log In
	@Test
	public void testGetPendingRequesteAccount() throws BussinessException, SQLException, RuntimeException {
			
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
			
		when(mockResultSet.next()).thenReturn(Boolean.TRUE,Boolean.TRUE,Boolean.FALSE);
		
		assertNotNull(customerOperationsDAO.getPendingRequesteAccount(mockCustomer));
			
		System.out.println("Customer Account Opening Pending Request are Received.");
	}
	
	@Test
	public void testGetPendingRequesteAccountWithException() throws BussinessException, SQLException, RuntimeException {
			
		doNothing().when(mockPreparedStatement).setInt(Mockito.anyInt(), Mockito.anyInt());
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
			
		when(mockResultSet.next()).thenReturn(Boolean.FALSE);
		
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				customerOperationsDAO.getPendingRequesteAccount(mockCustomer);
			}
		});
			
		System.out.println("Customer Account Opening Pending Request are no recevied and throws BussinessException().");
	}
}
