package com.bank.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bank.customerSservices.CustomerOperations;
import com.bank.customerSservices.impl.CustomerOperationsImpl;
import com.bank.exception.BussinessException;
import com.bank.modal.Customer;

public class MainBank {

	private static Logger log = Logger.getLogger("consoleLog.MainBank");
	private static Logger logFile = Logger.getLogger("fileLogger.MainBank");
	
	public static void main(String[] args) {
		
		Customer customer=null;  
		CustomerOperations customerOperations = new CustomerOperationsImpl();
		Scanner scan = new Scanner(System.in);
		
		
		
		String name, email, pass, gender, address;
		long number, ssn;
		int age, id;
		
			int ch = 0;
			do {
				
				try {
					
					log.info("=========================================");
					log.info("1) For Registration (New Customer)");
					log.info("2) For Log In");
					log.info("3) Exit");
					log.info("===========================================");
					log.info("Enter Your Choise: ");
					ch = Integer.parseInt( scan.nextLine() );
					logFile.trace(ch);
					
					try {
				
						switch( ch ) {
							
							
							case 1:
								log.info("Enter Your Name: ");
								name = scan.nextLine();
								logFile.trace(name);
								log.info("Enter Your Email: ");
								email = scan.nextLine();
								logFile.trace(email);
								log.info("Enter Your Password: ");
								pass = scan.nextLine();
								logFile.trace(pass);
								log.info("Enter Your Mobile Number: ");
								number = Long.parseLong(scan.nextLine());
								logFile.trace(number);
								log.info("Enter Your Social Security Number: ");
								ssn = Long.parseLong(scan.nextLine());
								logFile.trace(ssn);
								log.info("Enter Your Gender: ");
								gender = scan.nextLine();
								logFile.trace(gender);
								log.info("Enter Your Date of Birth (YYYY-MM-DD) : ");
								String d = scan.nextLine();
								logFile.trace(d);
								
								SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
								Date dob = simpleFormat.parse(d);
								logFile.trace("Coverted to Date Obj: "+dob);
								
								log.info("Enter Your Address: ");
								address = scan.nextLine();
								log.trace(address);
								
								//log.info("Enter Your Age: ");
								//age = Integer.parseInt(scan.nextLine());
								
								//converting dob to age
								LocalDate dobAge = LocalDate.parse(d, DateTimeFormatter.ofPattern("yyyy-MM-dd") );
								age = Period.between(dobAge, LocalDate.now()).getYears();
								logFile.trace("age: "+ age);
								
								customer = new Customer(name, email, pass, number, ssn, gender, dob, address, age);
								customerOperations.newCustomerRegistration(customer);
								log.info("Registration Successfully...\n\n");
								
								break;
							case 2:
								log.info("Enter Your Email: ");
								email = scan.nextLine();
								logFile.trace(email);
								log.info("Enter Your Password: ");
								pass = scan.nextLine();
								logFile.trace(pass);
								
								customer = customerOperations.customerLogin(email, pass);
								log.info("\n"+customer+"\n\n");
								break;
								
							case 3:
								log.info("Thank You!...");
								break;
							
							default:
								log.info("Enter Valid Choise.");
								
						}
						
					} catch (BussinessException e) {
						log.error(e.getMessage());
					} catch (ParseException e) {
						log.error("Enter Valid Date Formate.");
					}
					
				}catch(NumberFormatException e)
				{
					log.error("Enter Valid digit. Don't Enter Charactures or Special Symbols.");
				}
				
			}while(ch != 3);
			
			
			
			scan.close();
		
	}

}
