package edu.buaa.parking.bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ParkCard {

	private String cardId;
	private Park park;
	private Car car;

	public ParkCard() {
		super();
		this.cardId = generateCardId(park, car);
	}

	public ParkCard(Park park, Car car) {
		super();
		this.cardId = generateCardId(park, car);
		this.park = park;
		this.car = car;
	}

	public String getCardId() {
		return cardId;
	}

	public Park getPark() {
		return park;
	}

	public void setPark(Park park) {
		this.park = park;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	private String generateCardId(Park park, Car car) {
		String cardId = "";
		
		long time = Calendar.getInstance().getTimeInMillis();
		cardId = time + park.getParkName() + car.getNumber();
		
		return cardId;		
	}

}
