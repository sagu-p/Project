package com.bank.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao.TransationServiceDAO;
import com.bank.dao.util.PostresqlConnection;
import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Employee;
import com.bank.modal.Transaction;

public class TransactionServiceDAOImpl implements TransationServiceDAO {

	@Override
	public int depositeToAccount(Account account, float amount) throws BussinessException {
		
		int c= 0;
		String msg = "Amount Debit to Account";
		String t_type = "debit";
		float balance = account.getBalance() + amount;
		try ( Connection connection = PostresqlConnection.getConnection() ) {
			
			String query = "insert into bank.transaction (acc_num, date, msg, amount, t_type, balance, time) values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, account.getAcc_num());
			preparedStatement.setDate(2, new Date(new java.util.Date().getTime()));
			preparedStatement.setString(3, msg);
			preparedStatement.setFloat(4, amount);
			preparedStatement.setString(5, t_type);
			preparedStatement.setFloat(6, balance);
			preparedStatement.setTime(7, new Time(new java.util.Date().getTime()));
			c = preparedStatement.executeUpdate();
			
			logFile.trace(c);
			
		} catch (ClassNotFoundException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		
		if(c != 0)
			return c;
		else
			throw new BussinessException("Transaction Incomplited / Transaction is not Done , Try Again Later.");

	}

	@Override
	public int widrawToAccount(Account account, float amount) throws BussinessException {

		int c= 0;
		
		if(amount > account.getBalance()) 
			throw new BussinessException("There is not enough Balance, Try agani with Less Amount.");
		
		String msg = "Amount Credit from Account";
		String t_type = "credit";	
		float balance = account.getBalance() - amount;
		
		try ( Connection connection = PostresqlConnection.getConnection() ) {
			
			String query = "insert into bank.transaction (acc_num, date, msg, amount, t_type, balance, time) values (?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, account.getAcc_num());
			preparedStatement.setDate(2, new Date(new java.util.Date().getTime()));
			preparedStatement.setString(3, msg);
			preparedStatement.setFloat(4, amount);
			preparedStatement.setString(5, t_type);
			preparedStatement.setFloat(6, balance);
			preparedStatement.setTime(7, new Time(new java.util.Date().getTime()));
			c = preparedStatement.executeUpdate();
			
			logFile.trace(c);
			
		} catch (ClassNotFoundException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		
		if(c != 0)
			return c;
		else
			throw new BussinessException("Transaction Incomplited / Transaction is not Done , Try Again Later.");

	}

	@Override
	public Account BalanceOfAccount(Account account) throws BussinessException {
		Account accountGet = null;
		
		try ( Connection connection = PostresqlConnection.getConnection() ){
			
			String quey = "select * from bank.account a where acc_num = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(quey);
			preparedStatement.setLong(1, account.getAcc_num());
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				
				accountGet = new Account();
				accountGet.setAcc_num(rs.getInt("acc_num"));
				accountGet.setBalance(rs.getFloat("balance"));
				accountGet.setOpen_date(rs.getDate("open_date"));
				accountGet.setAcc_type(rs.getString("acc_type"));
				accountGet.setStatus(rs.getInt("status"));
			}
			
			if(accountGet == null)
				throw new BussinessException("Something went Wrong, Try again later.");
			
		}catch (ClassNotFoundException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		
		
		return accountGet;
	}

	@Override
	public List<Transaction> getAllTransactionOfAllAccountsByStatus(int status) throws BussinessException {
		List<Transaction> transactionList = new ArrayList<>();
		Transaction transaction =null;
		
		try ( Connection connection = PostresqlConnection.getConnection() ){
			
			String quey = "select * from bank.transaction where status =? order by t_id";
			PreparedStatement preparedStatement = connection.prepareStatement(quey);
			preparedStatement.setInt(1, status);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				transaction = new Transaction();
				transaction.setT_id(rs.getInt("t_id"));
				transaction.setAcc_num(rs.getLong("acc_num"));
				transaction.setDate(rs.getDate("date"));
				transaction.setMsg(rs.getString("msg"));
				transaction.setAmount(rs.getFloat("amount"));
				transaction.setT_type(rs.getString("t_type"));
				transaction.setBalance(rs.getFloat("balance"));
				transaction.setStatus(rs.getInt("status"));
				transactionList.add(transaction);
				
			}
			
			if(transactionList.size() == 0)
				throw new BussinessException("No Transation/s found.");
			
		}catch (ClassNotFoundException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		
		return transactionList;
	}

	@Override
	public int transactionConfirmationFromEmployee(Employee employee, Transaction transaction, int status) throws BussinessException {
		int c = 0;
		int c1 = 0;
		try ( Connection connection = PostresqlConnection.getConnection() ) {
			
			String query = "update bank.transaction set status = ?, emp_id = ? where t_id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, status);
			preparedStatement.setInt(2, employee.getEmp_id());
			preparedStatement.setLong(3, transaction.getT_id());
			c = preparedStatement.executeUpdate();
			logFile.trace(c);
			
			if(status == 1) {
				String sql = "update bank.account set balance =? where acc_num =?";
				PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
				preparedStatement1.setFloat(1, transaction.getBalance());
				preparedStatement1.setLong(2, transaction.getAcc_num());
				c1 = preparedStatement1.executeUpdate();
				logFile.trace(c1);
			}
			
			
		} catch (ClassNotFoundException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		
		if(c != 0 && c1 != 0)
			return c+c1;
		else
			throw new BussinessException("Transaction Approval / Rejection is not Done , Try Again Later.");
	}

	@Override
	public List<Transaction> getAllTransactionOfAllAccountsByAcount(Account account) throws BussinessException {
		List<Transaction> transactionList = new ArrayList<>();
		Transaction transaction =null;
		
		try ( Connection connection = PostresqlConnection.getConnection() ){
			
			String quey = "select * from bank.transaction where acc_num = ? and status = 1 or status = 2 order by t_id;";
			PreparedStatement preparedStatement = connection.prepareStatement(quey);
			preparedStatement.setLong(1, account.getAcc_num());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				transaction = new Transaction();
				transaction.setT_id(rs.getInt("t_id"));
				transaction.setAcc_num(rs.getLong("acc_num"));
				transaction.setDate(rs.getDate("date"));
				transaction.setMsg(rs.getString("msg"));
				transaction.setAmount(rs.getFloat("amount"));
				transaction.setT_type(rs.getString("t_type"));
				transaction.setBalance(rs.getFloat("balance"));
				transaction.setStatus(rs.getInt("status"));
				transactionList.add(transaction);
			}
			
			if(transactionList.size() == 0)
				throw new BussinessException("No Transation/s found.");
			
		}catch (ClassNotFoundException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		
		return transactionList;
	}

}