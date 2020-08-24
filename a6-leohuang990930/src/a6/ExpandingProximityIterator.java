package a6;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExpandingProximityIterator implements Iterator<Driver>{
	private Iterator<Driver> i;private Iterator<Driver> aa;private Iterator<Driver> bb;private Iterator<Driver> cc;private Iterator<Driver> dd;
	private Iterator<Driver> ee;private Iterator<Driver> ff;private Iterator<Driver> gg;private Iterator<Driver> hh;private Iterator<Driver> ii;
	private Iterator<Driver> jj;
	private Iterable<Driver> n;
	private Position position;

	private int l;

	private Driver nextDriver;
	public ExpandingProximityIterator(Iterable<Driver> driver_pool, Position client_position, int proximity_limit) {
		if (driver_pool == null) {
			throw new IllegalArgumentException();
		}
		if (client_position == null) {
			throw new IllegalArgumentException();
		}
		i = driver_pool.iterator();aa = driver_pool.iterator();bb = driver_pool.iterator();cc = driver_pool.iterator();dd = driver_pool.iterator();
		ee = driver_pool.iterator();ff = driver_pool.iterator();gg = driver_pool.iterator();hh = driver_pool.iterator();ii = driver_pool.iterator();
		jj = driver_pool.iterator();
		position = client_position;
		l = proximity_limit; n =driver_pool;
	}
	public boolean hasNext() {
		if (nextDriver != null) {
			return true;
		}
		while (i.hasNext()) {
			Driver a = i.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1) {
				nextDriver = a;
				return true;
			}
		}
		while (aa.hasNext()) {
			Driver a = aa.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1 && a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1+l) {
				nextDriver = a;
				return true;
			}
		}
		while (bb.hasNext()) {
			Driver a = bb.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+l && a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1+2*l) {
				nextDriver = a;
				return true;
			}
		}
		while (cc.hasNext()) {
			Driver a = cc.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+2*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1+3*l) {
				nextDriver = a;
				return true;
			}
		}
		while (dd.hasNext()) {
			Driver a = dd.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+3*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1+4*l) {
				nextDriver = a;
				return true;
			}
		}
		while (ee.hasNext()) {
			Driver a = ee.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+4*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1+5*l) {
				nextDriver = a;
				return true;
			}
		}
		while (ff.hasNext()) {
			Driver a = ff.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+5*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1+6*l) {
				nextDriver = a;
				return true;
			}
		}
		while (gg.hasNext()) {
			Driver a = gg.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+6*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1+7*l) {
				nextDriver = a;
				return true;
			}
		}
		while (hh.hasNext()) {
			Driver a = hh.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+7*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1+8*l) {
				nextDriver = a;
				return true;
			}
		}
		while (ii.hasNext()) {
			Driver a = ii.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+8*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1+9*l) {
				nextDriver = a;
				return true;
			}
		}
		while (jj.hasNext()) {
			Driver a = jj.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+9*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1+10*l) {
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
