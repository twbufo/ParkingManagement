package edu.buaa.parking.strategy;

import java.util.Iterator;

public class MaxRateParkStrategy<T extends IParkPlaceCollection> implements
		IParkingStrategy<T> {

	@Override
	public T selectParkArea(Iterator<T> objs) {
		T res = null;
		double maxRate = 0;

		while (objs.hasNext()) {
			T obj = objs.next();
			int free_count = obj.getFreeCount();
			double tmpRate = (double) free_count / obj.getMaxCount();
			if ((maxRate == 0 && free_count > 0) || tmpRate > maxRate) {
				maxRate = tmpRate;
				res = obj;
			}
		}

		return res;
	}
}
