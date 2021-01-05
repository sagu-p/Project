package com.bank.main;

import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Customer;
import com.bank.services.CustomerOperations;
import com.bank.services.EmployeeSservices;
import com.bank.services.impl.CustomerOperationsImpl;
import com.bank.services.impl.EmployeeSservicesImpl;

public class CustomerAccount {
	
	private static Logger log = Logger.getLogger("consoleLog.MainBank");
	private static Logger logFile = Logger.getLogger("fileLogger.MainBank");
	
	String name, email, pass, gender, address, acc_type;
	long number, ssn;
	int age;
	
	public void doCustomerOperation(Customer customer ) {
		
		Account account = null;
		
		CustomerOperations customerOperations = new CustomerOperationsImpl();
		EmployeeSservices employeeSservices = new EmployeeSservicesImpl(); 
		Scanner scan = new Scanner(System.in);
		
		int ch1 = 0;
		do {
			
			try {
				log.info("*************************************");
				log.info("1) To Get Personal Detail");
				log.info("2) To Open new Account");
				log.info("3) To See Pending Requests of Account Create");
				log.info("4) Show Account/s Details");
				log.info("9) Log Out");
				log.info("*************************************");
				log.info("Enter Your Choise: ");
				ch1 = Integer.parseInt( scan.nextLine() );
				logFile.trace(ch1);
				
				switch( ch1 ) {
				
					case 1:
						log.info("\nYour Details: "+customer+"\n\n");
						break;
						
					case 2:
						log.info("Create New Bank Account >>>");
						log.info("1) Savings Account");
						log.info("2) Checking Account");
						log.info("Enter Your Choise:");
						int temp = Integer.parseInt(scan.nextLine());
						if(temp == 1)
							acc_type = "saving";
						else
							acc_type = "checking";
						logFile.trace("Account Type: "+acc_type);
						Date open_date = new Date();
						logFile.trace("Account open date: "+open_date);
						account = new Account(open_date, acc_type);
						
						customerOperations.createNewBankAccount(account, customer);
						
						log.info("Opening an Account Requested Successfully.");
						logFile.trace(account);
						
							
						
						break;
						
					case 3:
						log.info("This module is under construction >>> Show Account/s Details");
						break;
						
					case 9:
						log.info("You are Logged Out.");
						break;
						
					default:
						log.info("Enter Valid Choise from Menu.");
				
				}
			} catch(NumberFormatException e)
			{
				log.error("Enter Valid digit. Don't Enter Charactures or Special Symbols.");
			} catch (DateTimeParseException e)
			{
				log.error("Enter Valid Date of Birth in (YYYY-MM-DD).");
			} catch (BussinessException e) {
				log.error(e.getMessage());
			} 
			
		}while(ch1 != 9);
		
	}

}
