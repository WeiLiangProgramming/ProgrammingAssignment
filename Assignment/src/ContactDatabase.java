import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.sql.Time;
import java.io.*;

public class ContactDatabase
{
	
	public static Scanner input = new Scanner(System.in);
	
	public static String promptContact()
	{
		System.out.println("What do you wish to do?");
		System.out.println("1. ORDER A PRODUCT");
		System.out.println("2. MAKE AN ENQUIRY ABOUT A PRODUCT");
		System.out.println("3. RETURN PURCHASED PRODUCT");
		System.out.println("4. EXIT THE PROGRAM");
		String user = input.nextLine();
		
		while(!(user.equals("1") || user.equals("2") || user.equals("3") || user.equals("4")))
		{
			System.out.println("SYSTEM ERROR");
			System.out.println("Please enter a valid selection");
			System.out.println("What do you wish to do?");
			System.out.println("1. ORDER A PRODUCT");
			System.out.println("2. MAKE AN ENQUIRY ABOUT A PRODUCT");
			System.out.println("3. RETURN PURCHASED PRODUCT");
			System.out.println("4. EXIT THE PROGRAM");
			user = input.nextLine();
		}
		
		return user;
	}
	
	public static void newEnquiry(Customer current)
	{
		File database = new File("Product.txt");
		Date today = new Date();
		String custNum = current.getCustomerNumber();
		
		System.out.println("Enter the product number that you wish to enquire");
		String productNum = input.nextLine();
		productNum = checkProductNum(database, productNum);

		System.out.println("What would you like to ask about the product?");
		String note = input.nextLine();
		
		Enquiry eq = new Enquiry(custNum, today.toLocaleString(), productNum, note);
		
		writeNewEnquiry(eq);
	}
	
	public static void newOrder(Customer current)
	{
		File database = new File("Product.txt");
		Date today = new Date();
		String custNum = current.getCustomerNumber();
		
		System.out.println("Enter the product number that you wish to purchase");
		String productNum = input.nextLine();
		double price = 0.0;
		
		boolean findProductNum = true; // boolean for checking valid product number
		while (findProductNum) // check for valid product number
		{
			try // try block
			{
				Scanner sc = new Scanner(database); // Scanner for reading
				while(sc.hasNextLine()) // while database have record
				{
					String record = sc.nextLine(); // Read line
					String[] line = record.split("\t"); // Split line into record
					if(line[0].equals(productNum)) // If user input matches the record
					{
						price = Double.parseDouble(line[1]); // set price from product database
						findProductNum = false; // break outer while loop
						break; // break inner while loop
					} // end if
				} // end while loop
				sc.close(); // close database scanner
			} // end try
			catch(FileNotFoundException e)
			{
				System.err.printf("\nException: %s\n", e);
			}
			finally // finally block
			{
				if(findProductNum) // Check for invalid product number
				{
					System.out.println("Cannot find product number"); // Error messages when input invalid product number 
					System.out.println("Please enter a valid product number"); // Prompt user to re-input product number
					productNum = input.nextLine(); // Customer re-input product number
				} // end if
			} // end finally
		} // end while loop
		
		System.out.printf("What is the quantity you would like to purchase for product %s?\n", productNum);
		System.out.println("Note: you can purchase a maximum of 30 quantity per purchase");
		int qty = input.nextInt();
		
		while(qty < 0 || qty > 30)
		{
			System.out.println("SYSTEM ERROR!!");
			System.out.println("Quantity entered does not meet range");
			System.out.println("Please reenter");
			System.out.printf("What is the quantity you would like to purchase for product %s?\n", productNum);
			System.out.println("Note: you can purchase a maximum of 30 quantity per purchase");
			qty = input.nextInt();
		}
		
		input.nextLine();
		
		File orderDb = new File("Order.txt");
		String orderNum = getOrderNumber(orderDb);
		
		Calendar c = Calendar.getInstance();
		c.add(c.DAY_OF_YEAR, 14);
		Date delivery = c.getTime();
		String[] deliveryDay = delivery.toLocaleString().split(" ");
		String deliver = String.format("%s %s %s", deliveryDay[0], deliveryDay[1], deliveryDay[2]);
		
		System.out.printf("Your order number is %s\n", orderNum);
		System.out.printf("Your delivery date is %s\n", deliver);
		
		Order order = new Order(custNum, today.toLocaleString(), productNum, price, qty, orderNum, deliver);
		
		writeNewOrder(order);
	}
	
	public static void newReturn(Customer current)
	{
		File orderFile = new File("Order.txt");
		Date today = new Date();
		String custNum = current.getCustomerNumber();
		
		System.out.println("Enter the order number that you wish to return");
		String orderNum = input.nextLine();
		Order order = checkOrderNum(orderFile, orderNum);
		
		File returnDb = new File("Return.txt");
		String refNum = getRefNumber(returnDb);
		
		Calendar c = Calendar.getInstance();
		c.add(c.DAY_OF_YEAR, 14);
		Date returns = c.getTime();
		String[] returnDay = returns.toLocaleString().split(" ");
		String willReturn = String.format("%s %s %s", returnDay[0], returnDay[1], returnDay[2]);
		
		System.out.printf("Your reference number is %s", refNum);
		System.out.printf("Your return date is %s", willReturn);
		
		System.out.println("Why you want to return the product?");
		String note = input.nextLine();
		
		Return rt = new Return(today.toLocaleString(), custNum, refNum, willReturn, order, note);
		
		writeNewReturn(rt);
	}
	
	public static void writeNewEnquiry(Enquiry eq)
	{	
		// Open new PrintWriter to add data
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Enquiry.txt", true)))) //true = add, false = overwrite
		{ // try block
			// add stream of data into existing record
			out.printf("%s\t%s\t%s\n%s\n", eq.getDateTime(), eq.getCustomerNumber(),
					eq.getItemNum(), eq.getNote());
			out.close(); // close PrintWriter
		} // end try
		catch (IOException e) // catch all IOExceptions
		{
			System.out.println("Error writing to file");
		} // end catch
		finally
		{
			// System.out.println("Clear exceptions");
		}
	}
	
	public static void writeNewOrder(Order order)
	{	
		// Open new PrintWriter to add data
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Order.txt", true)))) //true = add, false = overwrite
		{ // try block
			// add stream of data into existing record
			out.printf("%s\t%s\t%s\t%s\t%s\n%d\t%.2f\t%.2f\n", order.getDateTime(), order.getCustomerNumber(),
					order.getOrderNum(), order.getDeliveryDate(), order.getItemNum(),
					order.getQuantity(), order.getPrice(), order.calTotalPrice());
			out.close(); // close PrintWriter
		} // end try
		catch (IOException e) // catch all IOExceptions
		{
			System.out.println("Error writing to file");
		} // end catch
		finally
		{
			// System.out.println("Clear exceptions");
		}
	}
	
	public static void writeNewReturn(Return rt)
	{	
		// Open new PrintWriter to add data
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Enquiry Database.txt", true)))) //true = add, false = overwrite
		{ // try block
			// add stream of data into existing record
			out.printf("%s\t%s\n" + "%s\t%.2f\t%d\t%s\t%s\n" + "%s\n",
					rt.getDateTime(), rt.getCustomerNumber(),
					rt.getItemNum(), rt.getPrice(), rt.getQuantity(), rt.getReferenceNum(), rt.getPickupDate(),
					rt.getNote());
			out.close(); // close PrintWriter
		} // end try
		catch (IOException e) // catch all IOExceptions
		{
			System.out.println("Error writing to file");
		} // end catch
		finally
		{
			// System.out.println("Clear exceptions");
		}
	}
	
	public static String checkProductNum(File database, String productNum)
	{
		boolean findProductNum = true; // boolean for checking valid product number
		
		while (findProductNum) // check for valid product number
		{
			try // try block
			{
				Scanner sc = new Scanner(database); // Scanner for reading
				while(sc.hasNextLine()) // while database have record
				{
					String record = sc.nextLine(); // Read line
					String[] line = record.split("\t"); // Split line into record
					System.out.println(line[0]);
					if(line[0].equals(productNum)) // If user input matches the record
					{
						findProductNum = false; // break outer while loop
						break; // break inner while loop
					} // end if
				} // end while loop
				
				sc.close(); // close database scanner
			} // end try
			catch (FileNotFoundException e)
			{
				System.err.printf("\nException: %s\n", e);
			}
			finally // finally block
			{
				if(findProductNum) // Check for invalid customer number
				{
					System.out.println("Cannot find product number"); // Error messages when input invalid customer number 
					System.out.println("Please enter a valid product number"); // Prompt user to re-input customer number
					productNum = input.nextLine(); // Customer re-input customer number
				} // end if
				
			} // end finally
		} // end while loop
		
		return productNum;
	}

	public static String calcOrderNumber(int count, String lastNum)
	{
		String orderNum; // customer number variable
		if(count <= 9) // if number is between 0 - 9
		{
			orderNum = lastNum.substring(0, 3)+ Integer.toString(count);
		} // end if
		else if(count >= 10 && count <= 99) // if number is between 10 - 99
		{
			orderNum = lastNum.substring(0, 2) + Integer.toString(count);
		} // end else if
		else if(count >= 100 && count <= 999) // if number if between 100 - 999
		{
			orderNum = lastNum.substring(0, 1) + Integer.toString(count);
		} // end else if
		else // number between 1000 - 9999
		{
			orderNum = Integer.toString(count);
		} // end else
		
		// return the set customer number
		return orderNum;
		
	} // end calculate customer number method

	public static String getOrderNumber(File database)
	{
		String orderNum = "";
		
		try
		{ // try block
			int count = 0; // initial count value
			String lastNum = "0000"; // initial last customer number value
			Scanner sc = new Scanner(database); // scanner for reading database
			
			int counter = 1;
			while(sc.hasNextLine()) // while database has next line
			{
				String record = sc.nextLine(); // read the record
				String[] line = record.split("\t"); // break the record into data
				if(counter % 2 != 0)
				{
					lastNum = line[2]; // store the first part of record as last customer number
					count = Integer.parseInt(line[2]); // convert customer number into count as integer
				}
				counter++;
			}
			
			if(count > 1)
			{
				count++; // add count by 1 for customer as new record
			}
			orderNum = calcOrderNumber(count, lastNum); // Call calculate customer number to get customer number 
			
			sc.close(); // close scanner
		} // end try
		catch (FileNotFoundException e)
		{
			System.err.printf("\nException: %s\n", e);
		}
		finally
		{
			
		}
		
		return orderNum; // return current customer
	}
	
	public static Order checkOrderNum(File database, String orderNum)
	{
		Order order = new Order();
		boolean findOrderNum = true; // boolean for checking valid product number
		boolean first = false;
		
		while (findOrderNum) // check for valid product number
		{
			try // try block
			{
				Scanner sc = new Scanner(database); // Scanner for reading
				while(sc.hasNextLine()) // while database have record
				{
					String record = sc.nextLine(); // Read line
					String[] line = record.split("\t"); // Split line into record
					if(line[2].equals(orderNum)) // If user input matches the record
					{
						order.setDateTime(line[0]);
						order.setCustomerNumber(line[1]);
						order.setOrderNum(orderNum);
						order.setDeliveryDate(line[3]);
						order.setItemNum(line[4]);
						first = true;
						break; // break inner while loop
					} // end if
					
					if(first)
					{
						order.setPrice(Integer.parseInt(line[0]));
						order.setPrice(Double.parseDouble(line[1]));
						findOrderNum = false; // break outer while loop
					}
				} // end while loop
				
				sc.close(); // close database scanner
			} // end try
			catch (FileNotFoundException e)
			{
				System.err.printf("\nException: %s\n", e);
			}
			finally // finally block
			{
				if(findOrderNum) // Check for invalid customer number
				{
					System.out.println("Cannot find order number"); // Error messages when input invalid customer number 
					System.out.println("Please enter a valid order number"); // Prompt user to re-input customer number
					orderNum = input.nextLine(); // Customer re-input customer number
				} // end if
				
			} // end finally
		} // end while loop
		
		return order;
	}
	
	public static String getRefNumber(File database)
	{
		String refNum = "";
		
		try
		{ // try block
			int count = 0; // initial count value
			String lastNum = "0000"; // initial last customer number value
			Scanner sc = new Scanner(database); // scanner for reading database
			
			int counter = 1;
			while(sc.hasNextLine()) // while database has next line
			{
				String record = sc.nextLine(); // read the record
				String[] line = record.split("\t"); // break the record into data
				if(counter % 2 != 0)
				{
					lastNum = line[3]; // store the first part of record as last customer number
					count = Integer.parseInt(line[2]); // convert customer number into count as integer
				}
				counter++;
				if(counter == 3)
				{
					counter = 1;
				}
			}
			
			if(count > 1)
			{
				count++; // add count by 1 for customer as new record
			}
			refNum = calcRefNumber(count, lastNum); // Call calculate customer number to get customer number 
			
			sc.close(); // close scanner
		} // end try
		catch (FileNotFoundException e)
		{
			System.err.printf("\nException: %s\n", e);
		}
		finally
		{
			
		}
		
		return refNum; // return current customer
	}
	
	public static String calcRefNumber(int count, String lastNum)
	{
		String refNum; // customer number variable
		if(count <= 9) // if number is between 0 - 9
		{
			refNum = lastNum.substring(0, 3)+ Integer.toString(count);
		} // end if
		else if(count >= 10 && count <= 99) // if number is between 10 - 99
		{
			refNum = lastNum.substring(0, 2) + Integer.toString(count);
		} // end else if
		else if(count >= 100 && count <= 999) // if number if between 100 - 999
		{
			refNum = lastNum.substring(0, 1) + Integer.toString(count);
		} // end else if
		else // number between 1000 - 9999
		{
			refNum = Integer.toString(count);
		} // end else
		
		// return the set customer number
		return refNum;
		
	} // end calculate customer number method
}
