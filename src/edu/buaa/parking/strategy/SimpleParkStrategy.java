package edu.buaa.parking.strategy;

import java.util.Iterator;

public class SimpleParkStrategy<T extends IParkPlaceCollection> implements
		IParkingStrategy<T> {

	@Override
	public T selectParkArea(Iterator<T> obj) {
		while (obj.hasNext()) {
			T o = obj.next();
			if (o.getFreeCount() > 0)
				return o;
		}

		return null;
	}

}
