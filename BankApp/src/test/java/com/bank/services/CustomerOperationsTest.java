package com.bank.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.bank.dao.CustomerOperationsDAO;
import com.bank.dao.impl.CustomerOperationsDAOImpl;
import com.bank.exception.BussinessException;
import com.bank.modal.Customer;
import com.bank.services.impl.CustomerOperationsImpl;

class CustomerOperationsTest {
	
	/*
	 private int c_id;
	private String name;
	private String email;
	private String pass;
	private long number;
	private long ssn;
	private String gender;
	private Date dob;
	private String address;
	private int age;
	 */
	
	Customer mockCustomer = mock(Customer.class);
	CustomerOperationsDAO mockCustomerOperationsDAO = mock(CustomerOperationsDAO.class);
	
	
	@BeforeEach
	public void setUp() {
		mockCustomer = mock(Customer.class);
		mockCustomerOperationsDAO = mock(CustomerOperationsDAO.class);
	}

	@Test
	void testNewCustomerRegistration() throws BussinessException {
		when(mockCustomer.getGender()).thenReturn("male");
		when(mockCustomer.getEmail()).thenReturn("ganesh@gmail.com");
		when(mockCustomer.getNumber()).thenReturn(1023456789L);
		when(mockCustomer.getSsn()).thenReturn(102345678L);
		
		when(mockCustomerOperationsDAO.newCustomerRegistration(mockCustomer)).thenReturn(0);
		
		CustomerOperations service = new CustomerOperationsImpl();
		service.newCustomerRegistration(mockCustomer);
		
		verify(mockCustomerOperationsDAO, times(1)).newCustomerRegistration(mockCustomer);
		
		System.out.println("Done");
	}

}




