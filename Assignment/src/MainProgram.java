import java.io.File;
import java.util.Scanner;
import java.util.Date;

public class MainProgram
{
	public static Customer current = new Customer();
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args)
	{
		String identity = checkUserIdentity();
		
		switch(identity)
		{
		case "1": 	ForCustomer();
					break;
		case "2":	//ForStaff();
					break;
		}
	}
	
	public static void ForCustomer()
	{
		current = customerInterface(current); // customer interface method
		contactInterface(current);
	}
	
	// Method customer interface
	// Describe how the users can add or read data from the file 
	public static Customer customerInterface(Customer current)
	{
		File database = new File("Customer.txt"); // reading customer database file
		String prompt = CustomerDatabase.mainPrompt(); // prompt user to ask if they are new or old customer
		
		switch(prompt) // switch prompt
		{
		case "1":	current = CustomerDatabase.makeNewRecord(database, current); // make new record in database
					CustomerDatabase.writeToFile(current); // write new record into database
					break;
		case "2":	String custNum = CustomerDatabase.userInputCustomerNumber(); // prompt user to input customer number
					current = CustomerDatabase.findCustomerRecord(custNum, database, current); // find input customer number in database
					current = CustomerDatabase.readFromFile(database, current); // read the customer record from database
					break;
		} // end switch
	
		CustomerDatabase.displayCustomerDetails(current); // display customer details
		
		// return current customer class
		return current;
		
	} // end customer interface
	
	public static void contactInterface(Customer current)
	{
		String user = ContactDatabase.promptContact();
		
		while (user != "4")
		{
			switch(user)
			{
			case "1": 	ContactDatabase.newOrder(current);
						break;
			case "2": 	ContactDatabase.newEnquiry(current);
						break;
			case "3":	ContactDatabase.newReturn(current);
						break;
			case "4":	System.out.println("Thank you. Please come again");
						System.out.println("SYSTEM TERMINATED");
						System.exit(0);
			}
			
			user = ContactDatabase.promptContact();
		}
		
	}

	public static String checkUserIdentity()
	{
		
		System.out.println("Are you a customer or a staff?");
		System.out.println("1. Customer");
		System.out.println("2. Staff");
		String user = input.next();
		
		while(!(user.equals("1") || user.equals("2")))
		{
			System.out.println("SYSTEM ERROR");
			System.out.println("Please enter a valid selection");
			System.out.println("Are you a customer or a staff?");
			System.out.println("1. Customer");
			System.out.println("2. Staff");
			user = input.next();
		}
		
		return user;
	}
} // end main program
