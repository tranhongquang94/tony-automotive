package model;

public class Car {
	private int id;
	private String name;
	private double price;
	private String imageURL;
	private String make;
	private String model;
	private int year;
	private String created_at;
	private int categoryId;
	private int stock;

	public Car() {
		super();
	}
	public Car(int id, String name, double price, String imageURL, String make, String model, int year,
			String created_at, int categoryId, int stock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.imageURL = imageURL;
		this.make = make;
		this.model = model;
		this.year = year;
		this.created_at = created_at;
		this.categoryId = categoryId;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public int hashCode() {
		return id;
	}
	@Override
	public boolean equals(Object obj) {
	
		if (obj == null) return false;
	    if (!(obj instanceof Car))
	        return false;
	    if (obj == this)
	        return true;
	    return this.getId() == ((Car) obj).getId();
	}
}
