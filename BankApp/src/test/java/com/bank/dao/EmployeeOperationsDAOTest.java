package com.bank.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.dao.impl.EmployeeOperationsDAOImpl;
import com.bank.exception.BussinessException;
import com.bank.modal.Employee;

class EmployeeOperationsDAOTest {
	
	static EmployeeOperationsDAO employeeOperationDAO;
	Employee employee = null;
	
	@BeforeAll
	public static void setUp() {
		employeeOperationDAO = new EmployeeOperationsDAOImpl();
	}

	@Test
	void testEmployeeLogIn() {
		try {
			assertNotNull(employeeOperationDAO.employeeLogIn("sam@gmail.com", "sam123"));
		} catch (BussinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}