package a6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SnakeOrderAcrossPoolsIterator implements Iterator<Driver>{
	private ArrayList<Iterator<Driver>> it;boolean cc;
	private Driver nextDriver;
	private int num;
	private boolean nnn;
	private boolean forward;
	public SnakeOrderAcrossPoolsIterator(List<Iterable<Driver>> driver_pools) {
		
		if (driver_pools == null) {
			throw new IllegalArgumentException();
		}
		it = new ArrayList<Iterator<Driver>>(); cc=true;
		for (int i = 0; i < driver_pools.size();i++) {
			if (driver_pools.get(i).iterator().hasNext()) {
				it.add(driver_pools.get(i).iterator());
			}
		}
		num = 0; 
		forward = true;
		
	}
	public boolean hasNext() {
		boolean n = false;
		for (int i=0; i< it.size();i++) {
			if (it.get(i).hasNext() == true) {
				n = true;
				break;
			}
		}
		if (n == false) {
			nnn=n;
			return n;
			
		}
		if (it.get(num).hasNext()) {
			Driver nn = it.get(num).next();
			nextDriver = nn;
			deal();
			nnn=n;
			return nnn;
		} else {
			deal();
			return hasNext();
		}

	}
	public Driver next() {
		
		if (!nnn) {
			throw new NoSuchElementException();
		}
		Driver d =  nextDriver;
		nextDriver = null;
		return d;
	}
	public void deal() {
		if (num == it.size() - 1 && forward ) {
			forward = false;
		} else if (num == 0 && !forward) {
			forward = true;
			
		} else if (forward) {
			num++;
		} else if (!forward) {
			num--;
		}
	}
}
