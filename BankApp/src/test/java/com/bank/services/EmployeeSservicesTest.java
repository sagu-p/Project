package com.bank.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.bank.dao.EmployeeOperationsDAO;
import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Employee;
import com.bank.services.impl.EmployeeSservicesImpl;

class EmployeeSservicesTest {
	
	Employee mockEmployee;
	Account mockAccount;
	EmployeeOperationsDAO mockEmployeeOperationDAO;
	
	
	@BeforeEach
	public void setUp() {
		mockEmployee = mock(Employee.class);
		mockAccount = mock(Account.class);
		mockEmployeeOperationDAO = mock(EmployeeOperationsDAO.class);
	}

	@Test
	void testNewCustomerLogInInvalidEmail() throws BussinessException {
		
		Throwable t= assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				EmployeeSservices employeeService = new EmployeeSservicesImpl();
				employeeService.employeeLogIn("abc", "fghbjdsk");
			}
		});
		
		assertEquals("Enter Valid Email Address.", t.getMessage());
		verify(mockEmployeeOperationDAO, times(0)).employeeLogIn("abc", "fghbjdsk");
	}
	
	@Test
	void yestApproveAccountOpeningRequest() throws BussinessException {
		
		Throwable t= assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				EmployeeSservices employeeService = new EmployeeSservicesImpl();
				employeeService.approveAccountOpeningRequest(mockAccount, mockEmployee, 0);
			}
		});
		
		assertEquals("Select Valid Approval or Rejection Choise.", t.getMessage());
		verify(mockEmployeeOperationDAO, times(0)).approveAccountOpeningRequest(mockAccount, mockEmployee, 0);
	}

}
