package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.dao.EmployeeOperationsDAO;
import com.bank.dao.util.PostresqlConnection;
import com.bank.exception.BussinessException;
import com.bank.modal.Employee;

public class EmployeeOperationsDAOImpl implements EmployeeOperationsDAO {

	@Override
	public Employee employeeLogIn(String email, String pass) throws BussinessException {
		Employee employee = null;
		
		try ( Connection connection = PostresqlConnection.getConnection() ){
			
			String query = "select * from bank.employee e where email = ? and password = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, pass);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if( rs.next() ) {
				employee = new Employee();
				employee.setEmp_id(rs.getInt("emp_id"));
				employee.setName(rs.getString("name"));
				employee.setEmail(rs.getString("email"));
				employee.setPass(rs.getString("password"));
				employee.setNumber(rs.getLong("number"));
				employee.setGender(rs.getString("gender"));
				employee.setDob(rs.getDate("dob"));
				
			}
			else
				throw new BussinessException("Log in Failed. Enter valid Email and/or Password.");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employee;
	}

}
