import java.sql.Time;
import java.util.Date;

public class Contact
{
	private String customerNumber;
	private String dateTime;
	
	public Contact()
	{
		
	}
	
	public Contact(String customerID, String dateTm)
	{
		customerNumber = customerID;
		dateTime = dateTm;
	}
	
	public String getCustomerNumber()
	{
		return customerNumber;
	}
	
	public void setCustomerNumber(String customerID)
	{
		customerNumber = customerID;
	}

	public String getDateTime()
	{
		return dateTime;
	}
	
	public void setDateTime(String dateTm)
	{
		dateTime = dateTm;
	}
}