package model;

public class Order_details {
	private int orderId;
	private int carId;
	private int quantity;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Order_details(int orderId, int carId, int quantity) {
		super();
		this.orderId = orderId;
		this.carId = carId;
		this.quantity = quantity;
	}
	public Order_details() {
		super();
	}
	public Order_details(int carId) {
		// TODO Auto-generated constructor stub
	}
	
}
