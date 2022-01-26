package model;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class CartDTO {
	private Set<Car> cars = new HashSet<Car>();

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public CartDTO(Set<Car> cars) {
		super();
		this.cars = cars;
	}


}
