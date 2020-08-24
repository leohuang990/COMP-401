
package a6;

import java.util.Iterator;


import java.util.NoSuchElementException;

public class ProximityIterator implements Iterator<Driver>{

	private Iterator<Driver> i;

	private Position position;

	private int l;

	private Driver nextDriver;
	
	public ProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {
		if (driver_pool == null) {
			throw new IllegalArgumentException();
		}
		if (client_position == null) {
			throw new IllegalArgumentException();
		}
		i = driver_pool.iterator();
		position = client_position;
		l = proximity_limit;
	}
	public boolean hasNext() {
		if (nextDriver != null) {
			return true;
		}
		while (i.hasNext()) {
			Driver a = i.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) <= l) {
				nextDriver = a;
				return true;
			}
		}
		return false;
	}
	public Driver next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		Driver d = nextDriver;
		nextDriver = null;
		return d;
	}
}