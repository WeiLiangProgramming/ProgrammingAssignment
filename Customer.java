import java.util.Date;

public class Customer
{
	private String customerNumber;
	private String name;
	private String address;
	private Date dateOfBirth;
	
	public Customer()
	{

	}
	
	public Customer(String ID, String nm, String addr, Date dob)
	{
		customerNumber = ID;
		name = nm;
		address = addr;
		dateOfBirth = dob;
	}
	
	public String getCustomerNumber()
	{
		return customerNumber;
	}
	
	public void setCustomerNumber(String ID)
	{
		customerNumber = ID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String nm)
	{
		name = nm;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String addr)
	{
		address = addr;
	}
	
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dob)
	{
		dateOfBirth = dob;
	}
	
}