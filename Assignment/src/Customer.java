import java.util.Date;

public class Customer
{
	private String customerNumber; // Customer number
	private String name; // Customer's name
	private String address; // Customer's address
	private Date dateOfBirth; // Customer's date of birth
	
	public Customer() // default constructor
	{

	} // end default constructor
	
	public Customer(String ID, String nm, String addr, Date dob) // constructor with all parameters
	{
		customerNumber = ID;
		name = nm;
		address = addr;
		dateOfBirth = dob;
	} // end constructor
	
	public String getCustomerNumber() // customer number getter
	{
		return customerNumber;
	} // end customer number getter
	
	public void setCustomerNumber(String ID) // customer number setter
	{
		customerNumber = ID;
	} // end customer number setter
	
	public String getName() // customer's name getter
	{
		return name;
	} // end customer's name getter
	
	public void setName(String nm) // customer's name setter
	{
		name = nm;
	} // end customer's name setter
	
	public String getAddress() // customer's address getter
	{
		return address;
	} // end customer's address getter
	
	public void setAddress(String addr) // customer's address setter
	{
		address = addr;
	} // end customer's address setter
	
	public Date getDateOfBirth() // customer's date of birth getter
	{
		return dateOfBirth;
	} // end customer's date of birth getter
	
	public void setDateOfBirth(Date dob) // customer's date of birth setter
	{
		dateOfBirth = dob;
	} // end customer's date of birth setter
	
	public void setAllData(String ID, String nm, String addr, Date dob)
	{
		setCustomerNumber(ID);
		setName(nm);
		setAddress(addr);
		setDateOfBirth(dob);
	}
	
	public String toString() // toString method
	{
		return String.format("%s\n%s: %s\n%s: %s\n%s: %s\n%s: %s-%s-%s",
				"DISPLAY CUSTOMER INFORMATION", 
				"Customer Number", customerNumber, "Name", name,
				"Address", address, "Date Of Birth", dateOfBirth.getDate(), dateOfBirth.getMonth(), dateOfBirth.getYear());
	} // end toString method
	
} // end Customer class