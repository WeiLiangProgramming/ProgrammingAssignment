// imports
// input mismatch exception, scanner, and date from utility package
// all file in i/o package
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;
import java.io.*;

// Class for creating new record and extracting existing record from database
public class CustomerDatabase
{
	private static Scanner input = new Scanner(System.in); // allow user input
	
	// Method prompt user
	// Ask user if he or she is a new or old customer
	public static String mainPrompt()
	{
		System.out.println("ARE YOU A NEW CUSTOMER?"); // Prompt user to input
		System.out.println("1. New Customer"); // 1 = new customer
		System.out.println("2. Old Customer"); // 2 = old (existing) customer
		String prompt = input.next(); // user input
		
		while(!(prompt.equals("1") || prompt.equals("2"))) // Check for invalid input
		{
			System.out.println("Please enter a valid input:"); // if wrong input ask user to reenter
			prompt = input.next(); // user re-input
		} // end while
		
		 // return the prompt value the user enters
		return prompt;
		
	} // End prompt user method
	
	// Method write to file
	// Write the new record into database file
	public static void writeToFile(Customer current)
	{
		// Open new PrintWriter to add data
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Customer.txt", true)))) //true = add, false = overwrite
		{ // try block
			// add stream of data into existing record
             out.printf("%s\t%s\t%s\t%s-%s-%s\n", current.getCustomerNumber(),
            		 current.getName(), current.getAddress(),
            		 current.getDateOfBirth().getDate(), current.getDateOfBirth().getMonth(), current.getDateOfBirth().getYear());
             out.close(); // close PrintWriter
        } // end try
		catch (IOException e) // catch all IOExceptions
        {
             System.out.println("Error writing to file");
        } // end catch
		
	} // end method write to file
	
	// Method calculate customer number
	// Calculate customer number by reading all the record
	// Finally, the customer number will be the last record + 1
	public static String calcCustomerNumber(int count, String lastNum)
	{
		String custNum; // customer number variable
		if(count <= 9) // if number is between 0 - 9
		{
			custNum = lastNum.substring(0, 3)+ Integer.toString(count);
		} // end if
		else if(count >= 10 && count <= 99) // if number is between 10 - 99
		{
			custNum = lastNum.substring(0, 2) + Integer.toString(count);
		} // end else if
		else if(count >= 100 && count <= 999) // if number if between 100 - 999
		{
			custNum = lastNum.substring(0, 1) + Integer.toString(count);
		} // end else if
		else // number between 1000 - 9999
		{
			custNum = Integer.toString(count);
		} // end else
		
		// return the set customer number
		return custNum;
		
	} // end calculate customer number method
	
	// Method get customer number
	// Get the customer number from the record
	public static Customer getCustomerNumber(File database, String name, String address, Date dateOfBirth, Customer current)
	{
		try
		{ // try block
			int count = 0; // initial count value
			String lastNum = "0000"; // initial last customer number value
			Scanner sc = new Scanner(database); // scanner for reading database
			
			while(sc.hasNextLine()) // while database has next line
			{
				String record = sc.nextLine(); // read the record
				String[] line = record.split("\t"); // break the record into data
				lastNum = line[0]; // store the first part of record as last customer number
				count = Integer.parseInt(line[0]); // convert customer number into count as integer
			}
			
			count++; // add count by 1 for customer as new record
			
			String custNum = calcCustomerNumber(count, lastNum); // Call calculate customer number to get customer number 
			System.out.printf("Your customer number is %s\n", custNum); // Display new customer number
			current.setAllData(custNum, name, address, dateOfBirth); // set all data to current customer class
			sc.close(); // close scanner
		} // end try
		catch (FileNotFoundException e) // catch file not found exception
		{
			System.err.printf("\nException: %s", e); // print exception statement
		} // end catch
		
		return current; // return current customer
	}
	
	// Method make new record
	// Prompting user to input data and input into the database 
	public static Customer makeNewRecord(File database, Customer current)
	{
		// input check boolean for invalid values
		boolean inputCheck = true;
		
		// while input check is true
		while(inputCheck)
		{
			try
			{ // try block
				System.out.println("Enter your full name"); // prompt full name
				input.nextLine(); // skipping wrong error
				String name = input.nextLine(); // user input name
				System.out.println("Enter your address (first line only)"); // prompt address
				String address = input.nextLine(); // user input address
				System.out.println("Enter your date of birth"); // prompt date of birth
				System.out.println("Format: DD-MM-YYYY"); // date of birth format
				String dob = input.nextLine(); // accept date of birth in string
				String[] date = dob.split("-"); // split the string into day, month, year
				int day = Integer.parseInt(date[0]); // set day
				int month = Integer.parseInt(date[1]); // set month
				int year = Integer.parseInt(date[2]); // set year
				Date dateOfBirth = new Date(year, month, day); // set date
				System.out.println("Is this your correct information? Y/N"); // prompt user if information is correct
				String info = input.next(); // user Y/N
				
				while(!(info.equals("Y") || info.equals("N"))) // if any wrong error
				{
					System.out.println("ERROR INPUT"); // error message
					System.out.println("Please enter correct input"); // prompt to reenter
					info = input.next(); // prompts for re-input
				} // end while
				
				if(info.equals("Y")) // if data is true
				{
					current = getCustomerNumber(database, name, address, dateOfBirth, current); // call get customer number method
					inputCheck = false; // set input check to false to exit while loop
				}
				else // if data is false
				{
					System.out.println("Please reenter your information"); // prompts user to re-input the data
				}
			} // end try
			catch (InputMismatchException e) // catch input mismatch exception 
			{
				System.err.printf("\nException: %s\n", e); // print exception statement
			}
			catch (NumberFormatException e) // catch wrong number format for date
			{
				System.err.printf("\nException: %s\n", e); // print exception statement
			}
		}
	
		// return current customer class
		return current;
		
	}
	
	// Method set current from record
	// Set the existing customer record as current customer
	public static Customer setCurrentFromRecord(String[] record, Customer current)
	{
		if(record[0].equals(current.getCustomerNumber())) // If customer number matches current customer number
		{
			String[] date = record[3].split("-"); // Split date
			int day = Integer.parseInt(date[0]); // Set day
			int month = Integer.parseInt(date[1]); // Set month
			int year = Integer.parseInt(date[2]); // Set year
			Date dateOfBirth = new Date(year, month, day); // Combine date
			current.setAllData(record[0], record[1], record[2], dateOfBirth); // set all data to current customer class
		}
		
		// return current customer class
		return current;
		
	} // end set current from record method
	
	// Method find customer record
	// Find customer record from the database file
	public static Customer findCustomerRecord(String custNum, File database, Customer current)
	{
		boolean findCustNum = true; // boolean for checking valid customer number
		
		while (findCustNum) // check for valid customer number
		{
			try // try block
			{
				Scanner sc = new Scanner(database); // Scanner for reading
				while(sc.hasNextLine()) // while database have record
				{
					String record = sc.nextLine(); // Read line
					String[] line = record.split("\t"); // Split line into record
					if(line[0].equals(custNum)) // If user input matches the record
					{
						current.setCustomerNumber(custNum); // set customer number
						findCustNum = false; // break outer while loop
						break; // break inner while loop
					} // end if
				} // end while loop
				
				sc.close(); // close database scanner
			} // end try
			catch (FileNotFoundException e) // Catch file not found exception
			{
				System.err.printf("\nException: %s", e); // Print exception statement
			} // end catch
			finally // finally block
			{
				if(findCustNum) // Check for invalid customer number
				{
					System.out.println("Cannot find customer number"); // Error messages when input invalid customer number 
					System.out.println("Please enter a valid customer number"); // Prompt user to re-input customer number
					custNum = input.nextLine(); // Customer re-input customer number
				} // end if
				
			} // end finally
		} // end while loop
		
		// return current customer class with initiated values 
		return current;
	}
	
	// Method user input customer number
	// Prompt user to input customer number
	public static String userInputCustomerNumber()
	{
		System.out.println("Enter your customer number"); // Prompt to input customer number
		input.nextLine(); // Prevention of unable to input
		String custNum = input.nextLine(); // Customer input customer number
		return custNum;
	} // end user input customer number method
	
	// Method read from file
	// Read customer data from customer database file
	public static Customer readFromFile(File database, Customer current)
	{
		try // try block
		{
			Scanner sc = new Scanner(database); // Scanner for reading
			
			while(sc.hasNextLine())  // while database have record
			{
				String record = sc.nextLine(); // Read line from file
				String[] line = record.split("\t"); // Split record
				current = setCurrentFromRecord(line, current); // call set current from record method to set record to current customer class
			} // end while
			
			sc.close(); // Close scanner
			
		} // end try
		catch (FileNotFoundException e) // Catch file not found exception
		{
			System.err.printf("\nException: %s", e); // Print error statement 
		} // end catch
		catch (ArrayIndexOutOfBoundsException e) // Catch array out of bound exception
		{
			System.out.printf("\nException: %s", e); // Print error statement
		} // end catch
		finally
		{ // finally block
			
		} // end finally

		// return current customer class
		return current;
		
	} // end read from file method
	
	// Method display customer details
	// Display customer's detail and
	// Tell the customer he or she is the current customer 
	public static void displayCustomerDetails(Customer current)
	{
		try // try block
		{
			System.out.println(current.toString()); // Print customer information	
		} // end try
		catch (NullPointerException e) // Catch null value exception
		{
			System.err.printf("\nException: %s", e);
		} // end catch
		
		System.out.println("Your are the current customer"); // print statement stating customer is now the current customer
		
	} // end display customer details method 

} // end Customer Database class