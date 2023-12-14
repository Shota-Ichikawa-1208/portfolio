package beans;

import java.sql.Date;

public class My_OrderBeans {
	private int orderId;
	private String productName;
	private String size;
	private int quantity;
	private Date orderDate;
	
	public int getOrderId() {
		return orderId;
	}
	public String getProductName() {
		return productName;
	}
	public String getSize() {
		return size;
	}
	public int getQuantity() {
		return quantity;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public void setProductName(String product_Name) {
		this.productName = product_Name;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setOrderDate(Date order_Date) {
		this.orderDate = order_Date;
	}
	
	
	
	 
}
