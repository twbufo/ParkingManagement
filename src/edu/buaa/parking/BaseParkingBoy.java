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
			System.out.println(String.format("%s �ɹ����ͻ��ĳ�ͣ���%dͣ�����ĵ�%d��λ��", name,
					card.getParkingPlaceId(), card.getPosition()));
		else
			System.out.println(String.format("����%s�����ͣ�����������ԣ�û�н��ͻ��ĳ��ɹ�ͣ��!",
					name));

		return card;
	}

	public void printStatus() {
		System.out.println(String.format("ͣ������:%s", name));
		System.out.println("");
		for (ParkingPlace p : parkingPlaces.values()) {
			p.printStatus();
		}
		System.out.println("");
		System.out.println(String.format("Total��λ��:%d", getMaxCount()));
		System.out.println(String.format("Total��λ��:%d", getFreeCount()));
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
			System.out.println(String.format("%s �ڵ�%dͣ�����ĵ�%d��λ�óɹ�ȡ���ͻ��ĳ�!",
					name, card.getParkingPlaceId(), card.getPosition()));
		else
			System.out.println(String.format("%s �ڵ�%dͣ�����ĵ�%d��λ��ȡ��ʧ��!", name,
					card.getParkingPlaceId(), card.getPosition()));
		return car;
	}

	protected abstract ParkingPlace selectParkingPlace();

	public void setManager(ParkingManager manager) {
		this.manager = manager;
	}
}
