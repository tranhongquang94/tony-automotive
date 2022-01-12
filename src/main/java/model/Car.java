package model;

public class Car {
	private String name;
	private String imageURL;
	private int price;
	
	public Car(String name, String imageURL, int price) {
		super();
		this.name = name;
		this.imageURL = imageURL;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
