package edu.buaa.parking.bean;

public class ParkCard {

	private int parkingBoyId;
	private int parkingPlaceId;
	private int position;
	private int mark;

	public ParkCard(int parkingPlaceId, int position, int mark) {
		this.position = position;
		this.parkingPlaceId = parkingPlaceId;
		this.parkingBoyId = 0;
		this.mark = mark;
	}

	public int getMark() {
		return mark;
	}

	public int getParkingBoyId() {
		return this.parkingBoyId;
	}

	public int getParkingPlaceId() {
		return this.parkingPlaceId;
	}

	public int getPosition() {
		return this.position;
	}

	public void setParkingBoyId(int parkingBoyId) {
		this.parkingBoyId = parkingBoyId;
	}
}
