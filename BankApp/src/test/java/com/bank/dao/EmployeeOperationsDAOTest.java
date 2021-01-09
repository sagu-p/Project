package com.bank.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
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

import com.bank.dao.impl.EmployeeOperationsDAOImpl;
import com.bank.dao.util.PostresqlConnection;
import com.bank.exception.BussinessException;
import com.bank.modal.Employee;


class EmployeeOperationsDAOTest {
	
	static Connection mockConnection = mock(Connection.class);
	static PreparedStatement mockPreparedStatement = mock(PreparedStatement.class); 
	static ResultSet mockResultSet = mock(ResultSet.class);;

	Employee mockEmployee = mock(Employee.class);;
	
	EmployeeOperationsDAO employeeOperationsDAO = new EmployeeOperationsDAOImpl();
	
	@BeforeAll
	public static void initalize() throws SQLException, ClassNotFoundException {
		mockStatic(PostresqlConnection.class);
		
		when(PostresqlConnection.getConnection()).thenReturn(mockConnection);
		when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
		
		
		doNothing().when(mockPreparedStatement).setString(Mockito.anyInt(), Mockito.anyString());
		//doNothing().when(mockPreparedStatement).setString(1, "abc");
		//doNothing().when(mockPreparedStatement).setString(2, "abc");
		
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
		
		System.out.println("Success Before ALL");
	}
	
	@Test
	public void testEmployeeLogInWithoutExceprion() throws BussinessException, SQLException, RuntimeException {
		
		when(mockResultSet.next()).thenReturn(Boolean.TRUE);
		assertNotNull(employeeOperationsDAO.employeeLogIn("abc", "abc"));
		System.out.println("Employee Log In Test Successful without exception.");
	}
	
	@Test
	public void testEmployeeLogInWithException() throws SQLException {
		
		when(mockResultSet.next()).thenReturn(Boolean.FALSE);
		assertThrows(BussinessException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				// TODO Auto-generated method stub
				employeeOperationsDAO.employeeLogIn("abc", "abc");
			}
		});
		System.out.println("Employee Log In Test Successful with exception.");
	}
}
