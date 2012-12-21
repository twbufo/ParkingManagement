package edu.buaa.parking.strategy;


public interface IParkPlaceCollection {
	int getFreeCount();

	int getMaxCount();
	// public Iterator<Place> enumPlaceUsed();
}
