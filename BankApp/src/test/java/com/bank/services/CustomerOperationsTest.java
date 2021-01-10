package com.bank.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
	
	Customer mockCustomer;
	CustomerOperationsDAO mockCustomerOperationsDAO;
	CustomerOperations customerOperations = new CustomerOperationsImpl();
	
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
		
		System.out.println(mockCustomer.getSsn());
		
		when(mockCustomerOperationsDAO.newCustomerRegistration(Mockito.any(Customer.class))).thenReturn(2);
		
		System.out.println(13234);
		System.out.println(mockCustomerOperationsDAO.newCustomerRegistration(mockCustomer));
		
		assertNotNull(customerOperations.newCustomerRegistration(mockCustomer));
		System.out.println(mockCustomerOperationsDAO.newCustomerRegistration(mockCustomer) + "cygwduhak");
	}

}

/*
 Customer cus = new Customer();
		cus.setName("sagar");
		cus.setEmail("sagar@gmail.com");
		cus.setPass("qwerty");
		cus.setNumber(1234567890);
		cus.setSsn(123456789);
		cus.setGender("male");
		cus.setDob(new Date());
		cus.setAddress("");
		cus.setAge(0);
		
 */


