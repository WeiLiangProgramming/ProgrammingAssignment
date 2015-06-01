import java.sql.Time;
import java.util.Date;

public class Enquiry extends Contact
{
	private String itemNum;
	private String note;
	
	public Enquiry(String customerID, Date dt, Time tm, String opr,
				String itemID, String nt)
	{
		super(customerID, dt, tm, opr);
		itemNum = itemID;
		note = nt;
	}
	
	public String getItemNum()
	{
		return itemNum;
	}
	
	public void setItemNum(String itemID)
	{
		itemNum = itemID;
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