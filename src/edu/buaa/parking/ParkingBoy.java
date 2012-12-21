package edu.buaa.parking;

import edu.buaa.parking.bean.ParkingPlace;
import edu.buaa.parking.strategy.IParkingStrategy;

public class ParkingBoy extends BaseParkingBoy {

	private IParkingStrategy<ParkingPlace> strategy;

	public ParkingBoy(int id, String name,
			IParkingStrategy<ParkingPlace> parkStrategy) {
		super(id, name);
		strategy = parkStrategy;
	}

	@Override
	protected ParkingPlace selectParkingPlace() {
		return strategy.selectParkArea(this.getParkingPlaces().values()
				.iterator());
	}
}
