package edu.buaa.parking.bean;

import java.util.ArrayList;
import java.util.LinkedList;

import edu.buaa.parking.strategy.IParkPlaceCollection;

//停车场类
public class ParkingPlace implements IParkPlaceCollection {

	private int maxPlace;
	private int id;
	private ArrayList<ParkingSpace> allPlaces = new ArrayList<ParkingSpace>();
	private LinkedList<ParkingSpace> freePlaces = new LinkedList<ParkingSpace>();

	public ParkingPlace(int num, int maxPlace) {
		this.id = num;
		init(maxPlace);
	}

	/*
	 * 空闲车位数
	 */
	public int getFreeCount() {
		return this.freePlaces.size();
	}

	public int getId() {
		return this.id;
	}

	/*
	 * 占用车位数
	 */
	public int getMaxCount() {
		return this.maxPlace;
	}

	private void init(int maxPlace) {
		this.maxPlace = maxPlace;
		for (int i = 0; i < maxPlace; i++) {
			ParkingSpace p = new ParkingSpace(i, "一层第" + i + "个车位");
			this.allPlaces.add(p);
			this.freePlaces.add(p);
		}
	}

	/*
	 * 停车拿票
	 */
	public ParkCard parkCar(Car car, Object context) {
		if (freePlaces.isEmpty())
			return null;

		ParkingSpace p = freePlaces.removeFirst();
		int cookie = p.parkCar(car, context);

		ParkCard res = new ParkCard(this.id, p.getPos(), cookie);
		return res;
	}

	public void printStatus() {
		System.out.println(String.format("停车场编号:%d", this.id));
		System.out.println(String.format("车位数:%d", maxPlace));
		System.out.println(String.format("空位数:%d", freePlaces.size()));
	}

	/*
	 * 根据票据取车
	 */
	public Car removeCar(ParkCard parkCard) {
		int pos = parkCard.getPosition();
		ParkingSpace p = allPlaces.get(pos);

		if (p.isfree())
			return null;

		Car c = p.removeCar(parkCard.getMark());
		if (c != null)
			freePlaces.addFirst(p);

		return c;
	}

}
