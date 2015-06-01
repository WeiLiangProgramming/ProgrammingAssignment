import java.sql.Time;
import java.util.Date;

public class Order extends Contact
{
	private String itemNum;
	private double price;
	private int quantity;
	private String orderNum;
	private Date deliveryDate;

	public Order(String customerID, Date dt, Time tm, String opr,
				String itemID, double prc, int qty, String orderID, Date delDt)
	{
		super(customerID, dt, tm, opr);
		itemNum = itemID;
		price = prc;
		quantity = qty;
		orderNum = orderID;
		deliveryDate = delDt;
	}
	
	public String getItemNum()
	{
		return itemNum;
	}
	
	public void setItemNum(String itemID)
	{
		itemNum = itemID;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double prc)
	{
		price = prc;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setQuantity(int qty)
	{
		quantity = qty;
	}
	
	public String getOrderNum()
	{
		return orderNum;
	}
	
	public void setOrderNum(String orderID)
	{
		orderNum = orderID;
	}
	
	public Date getDeliveryDate()
	{
		return deliveryDate;
	}
	
	public void setDeliveryDate(Date delDt)
	{
		deliveryDate = delDt;
	}

}