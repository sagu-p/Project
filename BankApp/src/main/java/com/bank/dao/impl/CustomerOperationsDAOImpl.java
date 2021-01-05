package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.dao.CustomerOperationsDAO;
import com.bank.dao.util.PostresqlConnection;
import com.bank.exception.BussinessException;
import com.bank.modal.Customer;

public class CustomerOperationsDAOImpl implements CustomerOperationsDAO {

	@Override
	public int newCustomerRegistration(Customer customer) throws BussinessException {
		
		int c =0;
		
		try ( Connection connection = PostresqlConnection.getConnection() ) {
	
			String query = "insert into bank.customer (name, email, pass, number, ssn, gender, dob, address, age) values (?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getEmail());
			preparedStatement.setString(3, customer.getPass());
			preparedStatement.setLong(4, customer.getNumber());
			preparedStatement.setLong(5, customer.getSsn());
			preparedStatement.setString(6, customer.getGender());
			preparedStatement.setDate(7, new java.sql.Date(customer.getDob().getTime()));
			preparedStatement.setString(8, customer.getAddress());
			preparedStatement.setInt(9, customer.getAge());
			
			c = preparedStatement.executeUpdate();
			
			log.trace(c);
			
		} catch (ClassNotFoundException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		
		if(c != 0)
			return c;
		else
			throw new BussinessException("User can't be able to Sign Up. [Try with different email and/or number.] ");
		
	}

	@Override
	public Customer customerLogin(String email, String pass) throws BussinessException {
		Customer customer = null;
		
		try ( Connection connection = PostresqlConnection.getConnection() ) {
			
			String query = "select * from bank.customer c where email = ? and pass = ?;";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, pass);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			if( rs.next() ) {
				customer = new Customer();
				customer.setC_id(rs.getInt("c_id"));
				customer.setName(rs.getString("name"));
				customer.setEmail(rs.getString("email"));
				customer.setPass(rs.getString("pass"));
				customer.setNumber(rs.getLong("number"));
				customer.setSsn(rs.getLong("ssn"));
				customer.setGender(rs.getString("gender"));
				customer.setDob(rs.getDate("dob"));
				customer.setAddress(rs.getString("address"));
				customer.setAge(rs.getInt("age"));
			}
			else
				throw new BussinessException("Log in Failed. Enter valid Email and/or Password.");
				
		} catch (ClassNotFoundException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		
		return customer;
	}

	@Override
	public Customer getCustomerDetailsById(int id) throws BussinessException {
		// TODO Auto-generated method stub
		return null;
	}


}
