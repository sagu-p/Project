package com.bank.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

import com.bank.dao.CustomerOperationsDAO;
import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Customer;
import com.bank.services.impl.CustomerOperationsImpl;

class CustomerOperationsTest {
	
	
	Customer mockCustomer;
	Account mockAccount;
	CustomerOperationsDAO mockCustomerOperationsDAO = mock(CustomerOperationsDAO.class);
	
	
	@BeforeEach
	public void setUp() {
		mockCustomer = mock(Customer.class);
		mockAccount = mock(Account.class);
		mockCustomerOperationsDAO = mock(CustomerOperationsDAO.class);
	}

	//customer Registration
	@Test
	void testNewCustomerRegistrationInvalidGender() throws BussinessException {
		when(mockCustomer.getGender()).thenReturn("abc");
		when(mockCustomer.getEmail()).thenReturn("ganesh@gmail.com");
		when(mockCustomer.getNumber()).thenReturn(1023456789L);
		when(mockCustomer.getSsn()).thenReturn(102345678L);
		
		when(mockCustomerOperationsDAO.newCustomerRegistration(mockCustomer)).thenReturn(1);
		
		Throwable t= assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				CustomerOperations service = new CustomerOperationsImpl();
				service.newCustomerRegistration(mockCustomer);
			}
		});
		
		assertEquals("Enter Valid Gender", t.getMessage());
		verify(mockCustomer, times(1)).getGender();
	}
	
	@Test
	void testNewCustomerRegistrationInvalidEmail() throws BussinessException {
		when(mockCustomer.getGender()).thenReturn("male");
		when(mockCustomer.getEmail()).thenReturn("abcdef");
		when(mockCustomer.getNumber()).thenReturn(1023456789L);
		when(mockCustomer.getSsn()).thenReturn(102345678L);
		
		when(mockCustomerOperationsDAO.newCustomerRegistration(mockCustomer)).thenReturn(1);
		
		Throwable t= assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				CustomerOperations service = new CustomerOperationsImpl();
				service.newCustomerRegistration(mockCustomer);
			}
		});
		
		assertEquals("Enter Valid Email.", t.getMessage());
		verify(mockCustomer, times(1)).getGender();
		verify(mockCustomer, times(1)).getEmail();
	}
	
	@Test
	void testNewCustomerRegistrationInvalidNumberl() throws BussinessException {
		when(mockCustomer.getGender()).thenReturn("male");
		when(mockCustomer.getEmail()).thenReturn("abcdef@gmail.com");
		when(mockCustomer.getNumber()).thenReturn(213456L);
		when(mockCustomer.getSsn()).thenReturn(102345678L);
		
		when(mockCustomerOperationsDAO.newCustomerRegistration(mockCustomer)).thenReturn(1);
		
		Throwable t= assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				CustomerOperations service = new CustomerOperationsImpl();
				service.newCustomerRegistration(mockCustomer);
			}
		});
		
		assertEquals("Enter Valid Numberof 10 Digits.", t.getMessage());
		verify(mockCustomer, times(1)).getGender();
		verify(mockCustomer, times(1)).getEmail();
		verify(mockCustomer, times(1)).getNumber();
	}
	
	@Test
	void testNewCustomerRegistrationInvalidSsn() throws BussinessException {
		when(mockCustomer.getGender()).thenReturn("male");
		when(mockCustomer.getEmail()).thenReturn("abcdef@gmail.com");
		when(mockCustomer.getNumber()).thenReturn(1023456789L);
		when(mockCustomer.getSsn()).thenReturn(102L);
		
		when(mockCustomerOperationsDAO.newCustomerRegistration(mockCustomer)).thenReturn(1);
		
		Throwable t= assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				CustomerOperations service = new CustomerOperationsImpl();
				service.newCustomerRegistration(mockCustomer);
			}
		});
		
		assertEquals("Enter Valid SSN of 9 Digits.", t.getMessage());
		verify(mockCustomer, times(1)).getGender();
		verify(mockCustomer, times(1)).getEmail();
		verify(mockCustomer, times(1)).getNumber();
		verify(mockCustomer, times(1)).getSsn();
	}
	
	//customer Log in
	@Test
	void testNewCustomerLogInInvalidEmail() throws BussinessException {
		
		
		when(mockCustomerOperationsDAO.customerLogin(Mockito.anyString(), Mockito.anyString())).thenReturn(mockCustomer);
		
		Throwable t= assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				CustomerOperations service = new CustomerOperationsImpl();
				service.customerLogin("abcsf", "td");
			}
		});
		
		assertEquals("Enter Valid Email Address.", t.getMessage());
		verify(mockCustomerOperationsDAO, times(0)).customerLogin("abcsf", "td");
	}
}




