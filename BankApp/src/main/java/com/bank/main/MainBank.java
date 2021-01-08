package com.bank.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.bank.exception.BussinessException;
import com.bank.modal.Account;
import com.bank.modal.Customer;
import com.bank.modal.Employee;
import com.bank.services.CustomerOperations;
import com.bank.services.EmployeeSservices;
import com.bank.services.impl.CustomerOperationsImpl;
import com.bank.services.impl.EmployeeSservicesImpl;

public class MainBank {

	private static Logger log = Logger.getLogger("consoleLog.MainBank");
	private static Logger logFile = Logger.getLogger("fileLogger.MainBank");
	
	public static void main(String[] args) {
		
		Customer customer=null;  
		Employee employee = null;
		Account account = null;
		
		CustomerOperations customerOperations = new CustomerOperationsImpl();
		EmployeeSservices employeeSservices = new EmployeeSservicesImpl(); 
		Scanner scan = new Scanner(System.in);
		
		
		
		String name, email, pass, gender, address, acc_type;
		long number, ssn;
		int age;
		int ch1;
		
			int ch = 0;
			do {
				
				try {
					
					log.info("=========================================");
					log.info("1) For Registration (New Customer)");
					log.info("2) For Log In");
					log.info("3) Employee Log In");
					log.info("9) Exit");
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
								
								//converting dob to age
								LocalDate dobAge = LocalDate.parse(d, DateTimeFormatter.ofPattern("yyyy-MM-dd") );
								logFile.trace("LOCAL DATE OBJ>>> DOB: "+dobAge);
								age = Period.between(dobAge, LocalDate.now()).getYears();
								logFile.trace("age: "+ age);
								
								log.info("Enter Your Address: ");
								address = scan.nextLine();
								log.trace(address);								
								
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
								logFile.trace("\n"+customer+"\n\n");
								
								ch1 = 0;
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
												log.info("Peding Account Requessts>>>\n");
												List<Account> pendingRequesteAccounts = customerOperations.getPendingRequesteAccount(customer);
												log.info(pendingRequesteAccounts+"\n");
												break;
												
											case 4:
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
									} catch ( BussinessException e ) {
										log.error(e.getMessage());
									}
									
								}while(ch1 != 9);
								
								
								
								break;
							
							case 3:
								log.info("Employee Log In.");
								log.info("Enter Your Email: ");
								email = scan.nextLine();
								logFile.trace(email);
								log.info("Enter Your Password: ");
								pass = scan.nextLine();
								logFile.trace(pass);
								
								employee = employeeSservices.employeeLogIn(email, pass);
								logFile.info(employee);
								
								
								ch1 = 0;
								
								do {
									
									try {
										log.info("###########################################################");
										log.info("1) To Get All Csutomer Details Detail");
										log.info("2) To Get All pending Request/s for Account Opening");
										log.info("3) To Get Transaction of an Account");
										log.info("4) Show Account/s Details");
										log.info("9) Log Out");
										log.info("###########################################################");
										log.info("Enter Your Choise: ");
										ch1 = Integer.parseInt( scan.nextLine() );
										logFile.trace(ch1);
										
										switch( ch1 ) {
										
											case 1:
												log.info("This module is under construction");
												break;
												
											case 2:
												log.info("All Account Opening Pending Requests with their the Customer Details:");
												Map<Customer, Account> customerAccountDetails = new LinkedHashMap<>();
												customerAccountDetails = employeeSservices.getAllPendingAccountRequest();
												int count = 1;
												for(Map.Entry<Customer, Account> ca : customerAccountDetails.entrySet() ) {
													log.info("\nRequest : "+ count++);
													log.info(ca.getKey());
													log.info(ca.getValue()+"\n");
												}
												
												
												log.info("Select from above for Approval/Rejection.\nEnter Index:");
												int selection = Integer.parseInt(scan.nextLine()) - 1;
												logFile.trace(selection);;
												if(selection > count )
													throw new BussinessException("Enter valid Choise.");
												List<Customer> customersList = new ArrayList<>(customerAccountDetails.keySet());
												account = customerAccountDetails.get(customersList.get(selection));
												logFile.info(account);
												log.info("1) For Approval.");
												log.info("2) For Rejection.");
												log.info("Enter Choise: ");
												int status = Integer.parseInt(scan.nextLine());
												logFile.info(status);
												employeeSservices.approveAccountOpeningRequest(account, employee, status);
												logFile.info(account);
												log.info("\nAccount Updated Successfully.");
												
												break;
												
											case 3:
												log.info("This module is under construction");
												break;
												
											case 4:
												log.info("This module is under construction");
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
										logFile.error(e);
									} catch (DateTimeParseException e)
									{
										log.error("Enter Valid Date of Birth in (YYYY-MM-DD).");
										logFile.error(e);
									} catch ( IndexOutOfBoundsException e ) {
										log.error("Enter Valid Choise which is presant.");
										logFile.error(e);
									} catch ( BussinessException e ) {
										log.error(e.getMessage());
									}
									
								}while(ch1 != 9);
								
								break;
								
							case 9:
								log.info("Thank You!...");
								break;
							
							default:
								log.info("Enter Valid Choise from Menu.");
								
						}
						
					} catch (BussinessException e) {
						log.error(e.getMessage());
					} 
					
				}catch(NumberFormatException e)
				{
					log.error("Enter Valid digit. Don't Enter Charactures or Special Symbols.");
				}catch (ParseException e) {
					log.error("Enter Valid Date Formate in ( YYYY-MM-DD ).");
				}catch (DateTimeParseException e) {
					log.error("Enter Valid Date Formate in ( YYYY-MM-DD ).");
				}
				
			}while(ch != 9);
			
			
			
			scan.close();
		
	}

}
