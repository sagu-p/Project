package com.bank.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.customerSservices.CustomerOperations;
import com.bank.customerSservices.impl.CustomerOperationsImpl;
import com.bank.exception.BussinessException;
import com.bank.modal.Customer;

public class MainBank {

	private static Logger log = Logger.getLogger(MainBank.class);
	
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
					//System.out.println("\n=========================================");
					log.info("\n=========================================");
					//System.out.println("1) For Registration (New Customer)");
					log.info("1) For Registration (New Customer)");
					//System.out.println("2) For Log In");
					log.info("2) For Log In");
					//System.out.println("3) Exit");
					log.info("3) Exit");
					//System.out.println("===========================================");
					log.info("===========================================");
					//System.out.print("Enter Your Choise: ");
					log.info("Enter Your Choise: ");
					ch = Integer.parseInt( scan.nextLine() );
					
					try {
				
						switch( ch ) {
							
							
							case 1:
								log.info("Enter Your Name: ");
								name = scan.nextLine();
								log.info("Enter Your Email: ");
								email = scan.nextLine();
								log.info("Enter Your Password: ");
								pass = scan.nextLine();
								log.info("Enter Your Mobile Number: ");
								number = Long.parseLong(scan.nextLine());
								log.info("Enter Your Social Security Number: ");
								ssn = Long.parseLong(scan.nextLine());
								log.info("Enter Your Gender: ");
								gender = scan.nextLine();
								log.info("Enter Your Date of Birth (YYYY-MM-DD) : ");
								String d = scan.nextLine();
								
								SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
								Date dob = simpleFormat.parse(d);
								
								log.info("Enter Your Address: ");
								address = scan.nextLine();
								log.info("Enter Your Age: ");
								age = Integer.parseInt(scan.nextLine());
								
								
								
								customer = new Customer(name, email, pass, number, ssn, gender, dob, address, age);
								customerOperations.newCustomerRegistration(customer);
								log.info("Registration Successfully...\n");
								
								break;
							case 2:
								log.info("Enter Your Email: ");
								email = scan.nextLine();
								log.info("Enter Your Password: ");
								pass = scan.nextLine();
								
								customer = customerOperations.customerLogin(email, pass);
								log.info("\n"+customer);
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