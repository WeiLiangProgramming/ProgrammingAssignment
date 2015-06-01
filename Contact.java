import java.sql.Time;
import java.util.Date;

public class Contact
{
	private String customerNumber;
	private Date date;
	private Time time;
	private String operatorName;
	
	public Contact(String customerID, Date dt, Time tm, String opr)
	{
		customerNumber = customerID;
		date = dt;
		time = tm;
		operatorName = opr;
	}
	
	public String getCustomerNumber()
	{
		return customerNumber;
	}
	
	public void setCustomerNumber(String customerID)
	{
		customerNumber = customerID;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public void setDate(Date dt)
	{
		date = dt;
	}
	
	public Time getTime()
	{
		return time;
	}
	
	public void setTime(Time tm)
	{
		time = tm;
	}
	
	public String getOperatorName()
	{
		return operatorName;
	}
	
	public void setOperatorName(String opr)
	{
		operatorName = opr;
	}
}