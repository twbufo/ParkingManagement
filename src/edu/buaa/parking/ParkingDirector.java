package edu.buaa.parking;

import java.util.HashMap;

public class ParkingDirector {

	HashMap<Integer, ParkingManager> parkingManagers = new HashMap<Integer, ParkingManager>();

	public ParkingDirector() {

	}

	public void addParkingManager(ParkingManager manager) {
		if (!parkingManagers.containsKey(manager.getId())) {
			parkingManagers.put(manager.getId(), manager);
			manager.setDirector(this);
		}
	}

	public void printReport() {

		for (ParkingManager manager : parkingManagers.values()) {
			manager.printStatus();
		}
	}

	public void removeAllParkingManager() {
		for (ParkingManager p : parkingManagers.values())
			p.setManager(null);

		parkingManagers.clear();
	}
}
