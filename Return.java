import java.sql.Time;
import java.util.Date;

public class Return extends Contact
{
	private String referenceNum;
	private Date pickupDate;
	private String note;
	
	public Return(String customerID, Date dt, Time tm, String opr,
				String refID, Date pickup, String nt)
	{
		super(customerID, dt, tm, opr);
		referenceNum = refID;
		pickupDate = pickup;
		note = nt;
	}
	
	public String getReferenceNum()
	{
		return referenceNum;
	}
	
	public void setReferenceNum(String refID)
	{
		referenceNum = refID;
	}
	
	public Date getPickupDate()
	{
		return pickupDate;
	}
	
	public void setPickupDate(Date pickup)
	{
		pickupDate = pickup;
	}
	
	public String getNote()
	{
		return note;
	}
	
	public void setNote(String nt)
	{
		note = nt;
	}
}