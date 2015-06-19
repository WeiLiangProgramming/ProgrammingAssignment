import java.sql.Time;
import java.util.Date;

public class Enquiry extends Contact
{
	private String itemNum;
	private String note;
	
	public Enquiry(String customerID, String dateTm,
				String itemID, String nt)
	{
		super(customerID, dateTm);
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