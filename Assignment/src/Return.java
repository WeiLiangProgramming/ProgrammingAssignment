import java.sql.Time;
import java.util.Date;

public class Return extends Contact
{
	private String referenceNum;
	private String pickupDate;
	private String note;
	private final String itemID;
	private final double price;
	private final int quantity;
	
	public Return(String customerID, String dateTm,
				String refID, String pickup, Order order, String nt)
	{
		super(customerID, dateTm);
		referenceNum = refID;
		pickupDate = pickup;
		note = nt;
		itemID = order.getItemNum();
		price = order.getPrice();
		quantity = order.getQuantity();
	}
	
	public String getReferenceNum()
	{
		return referenceNum;
	}
	
	public void setReferenceNum(String refID)
	{
		referenceNum = refID;
	}
	
	public String getPickupDate()
	{
		return pickupDate;
	}
	
	public void setPickupDate(String pickup)
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
	
	public String getItemNum()
	{
		return itemID;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public double getPrice()
	{
		return price;
	}
}