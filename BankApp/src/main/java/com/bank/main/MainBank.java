package com.bank.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.bank.customerSservices.CustomerOperations;
import com.bank.customerSservices.impl.CustomerOperationsImpl;
import com.bank.exception.BussinessException;
import com.bank.modal.Customer;

public class MainBank {

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
					System.out.println("\n=========================================");
					System.out.println("1) For Registration (New Customer)");
					System.out.println("2) For Log In");
					System.out.println("3) Exit");
					System.out.println("===========================================");
					System.out.print("Enter Your Choise: ");
					ch = Integer.parseInt( scan.nextLine() );
					
					try {
				
						switch( ch ) {
							
							
							case 1:
								System.out.print("Enter Your Name: ");
								name = scan.nextLine();
								System.out.print("Enter Your Email: ");
								email = scan.nextLine();
								System.out.print("Enter Your Password: ");
								pass = scan.nextLine();
								System.out.print("Enter Your Mobile Number: ");
								number = Long.parseLong(scan.nextLine());
								System.out.print("Enter Your Social Security Number: ");
								ssn = Long.parseLong(scan.nextLine());
								System.out.print("Enter Your Gender: ");
								gender = scan.nextLine();
								System.out.print("Enter Your Date of Birth (YYYY-MM-DD) : ");
								String d = scan.nextLine();
								
								SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
								Date dob = simpleFormat.parse(d);
								
								System.out.print("Enter Your Address: ");
								address = scan.nextLine();
								System.out.print("Enter Your Age: ");
								age = Integer.parseInt(scan.nextLine());
								
								
								
								customer = new Customer(name, email, pass, number, ssn, gender, dob, address, age);
								customerOperations.newCustomerRegistration(customer);
								System.out.println("Registration Successfully...\n");
								
								break;
							case 2:
								System.out.print("Enter Your Email: ");
								email = scan.nextLine();
								System.out.print("Enter Your Password: ");
								pass = scan.nextLine();
								
								customer = customerOperations.customerLogin(email, pass);
								System.out.println("\n"+customer);
								break;
								
							case 3:
								System.out.println("Thank You!...");
								break;
							
							default:
								System.out.println("Enter Valid Choise.");
								
						}
						
					} catch (BussinessException e) {
						System.out.println(e.getMessage());
					} catch (ParseException e) {
						System.out.println("Enter Valid Date Formate.");
					}
					
				}catch(NumberFormatException e)
				{
					System.out.println("Enter Valid digit. Don't Enter Charactures or Special Symbols.");
				}
				
			}while(ch != 9);
			
			
			
			scan.close();
		
	}

}
