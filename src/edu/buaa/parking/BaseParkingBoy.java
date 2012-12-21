package edu.buaa.parking;

import java.util.HashMap;

import edu.buaa.parking.bean.Car;
import edu.buaa.parking.bean.ParkCard;
import edu.buaa.parking.bean.ParkingPlace;

public abstract class BaseParkingBoy {
	private int id;
	private String name;
	private HashMap<Integer, ParkingPlace> parkingPlaces = new HashMap<Integer, ParkingPlace>();

	private ParkingManager manager = null;

	public BaseParkingBoy(int id, String name) {
		this.id = id;
		this.name = name;

	}

	public void addParkingPlace(ParkingPlace place) {
		assert (place != null);
		if (!this.parkingPlaces.containsKey(place)) {
			this.parkingPlaces.put(place.getId(), place);
		}
	}

	public int getFreeCount() {
		int count = 0;
		for (ParkingPlace p : parkingPlaces.values())
			count += p.getFreeCount();

		return count;
	}

	public int getId() {
		return this.id;
	}

	public int getMaxCount() {
		int count = 0;
		for (ParkingPlace p : parkingPlaces.values())
			count += p.getMaxCount();

		return count;
	}

	public String getName() {
		return this.name;
	}

	public HashMap<Integer, ParkingPlace> getParkingPlaces() {
		return parkingPlaces;
	}

	public ParkCard parkCar(Car car) {
		if (car == null)
			return null;

		ParkCard card = null;

		ParkingPlace p = selectParkingPlace();
		if (p != null) {
			card = p.parkCar(car, this);
			if (card != null)
				card.setParkingBoyId(id);
		}

		if (card != null)
			System.out.println(String.format("%s 成功将客户的车停入第%d停车场的第%d个位置", name,
					card.getParkingPlaceId(), card.getPosition()));
		else
			System.out.println(String.format("由于%s管理的停车场满，所以，没有将客户的车成功停放!",
					name));

		return card;
	}

	public void printStatus() {
		System.out.println(String.format("停车仔名:%s", name));
		System.out.println("");
		for (ParkingPlace p : parkingPlaces.values()) {
			p.printStatus();
		}
		System.out.println("");
		System.out.println(String.format("Total车位数:%d", getMaxCount()));
		System.out.println(String.format("Total空位数:%d", getFreeCount()));
	}

	public void removeAllParkingPlace() {
		this.parkingPlaces.clear();
	}

	public Car removeCar(ParkCard card) {
		if (card == null)
			return null;

		Car car = null;
		if (!parkingPlaces.containsKey(card.getParkingPlaceId())) {
			if (manager != null)
				car = manager.removeCar(card);
		} else {

			ParkingPlace p = parkingPlaces.get(card.getParkingPlaceId());
			if (p != null)
				car = p.removeCar(card);
		}

		if (car != null)
			System.out.println(String.format("%s 在第%d停车场的第%d个位置成功取出客户的车!",
					name, card.getParkingPlaceId(), card.getPosition()));
		else
			System.out.println(String.format("%s 在第%d停车场的第%d个位置取车失败!", name,
					card.getParkingPlaceId(), card.getPosition()));
		return car;
	}

	protected abstract ParkingPlace selectParkingPlace();

	public void setManager(ParkingManager manager) {
		this.manager = manager;
	}
}
