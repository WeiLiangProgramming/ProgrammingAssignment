import java.sql.Time;
import java.util.Date;

public class Order extends Contact
{
	private String itemNum;
	private double price;
	private int quantity;
	private String orderNum;
	private String deliveryDate;

	public Order(String customerID, String dateTm,
				String itemID, double prc, int qty, String orderID, String delDt)
	{
		super(customerID, dateTm);
		itemNum = itemID;
		price = prc;
		quantity = qty;
		orderNum = orderID;
		deliveryDate = delDt;
	}
	
	public Order()
	{
		
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
	
	public String getDeliveryDate()
	{
		return deliveryDate;
	}
	
	public void setDeliveryDate(String delDt)
	{
		deliveryDate = delDt;
	}
	
	public double calTotalPrice()
	{
		return quantity * price;
	}

}