package edu.buaa.parking.bean;

import java.util.Random;

public class ParkingSpace {
	private int pos;
	private String posDesc;
	private Car car;
	private Object context;
	private int mark;

	public ParkingSpace(int pos, String desc) {
		this.pos = pos;
		this.posDesc = desc;
		this.car = null;
	}

	public Car getCar() {
		return this.car;
	}

	public Object getContext() {
		return this.context;
	}

	public int getPos() {
		return this.pos;
	}

	public String getPosDesc() {
		return this.posDesc;
	}

	public boolean isfree() {
		return this.car == null;
	}

	public int parkCar(Car car, Object context) {

		this.car = car;
		this.context = context;

		Random rnd = new Random();
		this.mark = rnd.nextInt();

		return this.mark;
	}

	public Car removeCar(int mark) {
		Car res = null;
		if (car != null && this.mark == mark) {
			res = this.car;
			this.car = null;
			this.context = null;
		}

		return res;
	}
}
