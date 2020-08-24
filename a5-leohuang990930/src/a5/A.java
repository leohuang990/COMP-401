package a5;

import a6.Driver;

public class A {
	public boolean hasNext() {
		if (nextDriver != null) {
			return true;
		}
		while (ii.hasNext()) {
			Driver a = ii.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1) {
				nextDriver = a;
				return true;
			}
		}
		while (aa.hasNext()) {
			Driver a = aa.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1 + l && a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1) {
				nextDriver = a;
				return true;
			}
		}
		while (bb.hasNext()) {
			Driver a = bb.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1 + 2*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+l) {
				nextDriver = a;
				return true;
			}
		}
		while (cc.hasNext()) {
			Driver a = cc.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1 + 3*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+2*l) {
				nextDriver = a;
				return true;
			}
		}
		while (dd.hasNext()) {
			Driver a = dd.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1 + 4*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+3*l) {
				nextDriver = a;
				return true;
			}
		}
		while (ee.hasNext()) {
			Driver a = ee.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1 + 5*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+4*l) {
				nextDriver = a;
				return true;
			}
		}
		while (ff.hasNext()) {
			Driver a = ff.next();
			if (a.getVehicle().getPosition().getManhattanDistanceTo(position) <= 1 + 6*l && a.getVehicle().getPosition().getManhattanDistanceTo(position) > 1+5*l) {
				nextDriver = a;
				return true;
			}
		}
		return false;
	}
}
