package edu.buaa.parking.strategy;

import java.util.Iterator;

public class MaxParkStrategy<T extends IParkPlaceCollection> implements
		IParkingStrategy<T> {

	@Override
	public T selectParkArea(Iterator<T> objs) {
		T res = null;
		int max_free = 0;

		while (objs.hasNext()) {
			T p = objs.next();
			if (p.getFreeCount() > max_free) {
				max_free = p.getFreeCount();
				res = p;
			}
		}

		return res;
	}

}
