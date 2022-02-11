package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartDTO {
	private HashMap<Car, Integer> cars = new HashMap<Car, Integer>();

	public HashMap<Car, Integer> getCars() {
		return cars;
	}

	public void setCars(HashMap<Car, Integer> cars) {
		this.cars = cars;
	}

	public CartDTO(HashMap<Car, Integer> cars) {
		super();
		this.cars = cars;
	}
}
