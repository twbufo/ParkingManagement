package edu.buaa.parking;

import java.util.HashMap;
import java.util.Iterator;

import edu.buaa.parking.bean.Car;
import edu.buaa.parking.bean.ParkCard;
import edu.buaa.parking.bean.ParkingPlace;
import edu.buaa.parking.strategy.IParkingStrategy;

public class ParkingManager extends ParkingBoy {

	private ParkingDirector director = null;
	private HashMap<Integer, BaseParkingBoy> parkingBoys = new HashMap<Integer, BaseParkingBoy>();

	public ParkingManager(int NO, String name,
			IParkingStrategy<ParkingPlace> parkstrategy) {
		super(NO, name, parkstrategy);
	}

	public void addParkingBoy(BaseParkingBoy boy) {
		if (!parkingBoys.containsKey(boy.getId())) {
			parkingBoys.put(boy.getId(), boy);

			boy.setManager(this);
			Iterator<ParkingPlace> it = boy.getParkingPlaces().values()
					.iterator();
			while (it.hasNext()) {
				ParkingPlace p = it.next();
				this.getParkingPlaces().put(p.getId(), p);
			}
		}
	}

	public ParkingDirector getDirector() {
		return director;
	}

	public ParkCard parkCarBySubordinate(Car car) {
		if (car == null)
			return null;

		for (BaseParkingBoy boy : parkingBoys.values()) {
			if (boy.getFreeCount() > 0) {
				ParkCard card = boy.parkCar(car);
				if (card != null)
					return card;
			}
		}

		return null;
	}

	@Override
	public void printStatus() {

		System.out.println(String.format("Õ£≥µæ≠¿Ì:%s", this.getName()));
		for (BaseParkingBoy boy : parkingBoys.values()) {
			boy.printStatus();
		}
	}

	public void removeAllParkingBoy() {
		for (BaseParkingBoy p : parkingBoys.values())
			p.setManager(null);

		parkingBoys.clear();
		this.getParkingPlaces().clear();
	}

	public void setDirector(ParkingDirector director) {
		this.director = director;
	}
}
