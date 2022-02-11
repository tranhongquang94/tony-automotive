package model;

public class Order {
	private int id;
	private int userId;
	private String orderTime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	
	public Order(int id, int userId, String orderTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.orderTime = orderTime;
	}
	public Order() {
		super();
	}
	
}
