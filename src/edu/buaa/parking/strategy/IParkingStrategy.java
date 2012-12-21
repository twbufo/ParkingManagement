package edu.buaa.parking.strategy;

import java.util.Iterator;

public interface IParkingStrategy<T extends IParkPlaceCollection> {
	public T selectParkArea(Iterator<T> obj);
}