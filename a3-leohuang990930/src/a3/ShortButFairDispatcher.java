package a3;

import java.util.ArrayList;
import java.util.Arrays;

public class ShortButFairDispatcher implements Dispatcher{
		
		ArrayList<Driver> completedOrder=new ArrayList<Driver>();
		
		public Driver chooseDriver(Driver[] a, RideRequest request) {
		 	ArrayList<Driver> without = new ArrayList<Driver>();
		 	
			 ArrayList<Driver> arraylist= new ArrayList<Driver>(Arrays.asList(a));
			 for (int i = 0; i < arraylist.size(); i++) {
					if (checkMatch(completedOrder,arraylist.get(i))) {
						without.add(arraylist.get(i));
						
					}
			 }
			 set(minDriver(without, request));
			
			 return minDriver(without, request);
			 
			
	 }
	 public boolean checkMatch(ArrayList<Driver> completed, Driver origin) {
		 	if (completed.size() == 0) {
		 		return true;
		 	}
		 
		 	if (completed.size() <= 5) {
		 		for (int nm = 0; nm < completed.size(); nm++) {
		 			 if (origin.equals(completed.get(nm))) {
						 return false;
					 }
		 		}
		 	} else if (completed.size() > 5) {
		 		for (int nn = 0; nn >-5; nn--) {
		 			if (origin.equals(completed.get(completed.size()+nn))) {
						 return false;
					 }
		 		}
		 	}
			 return true;
		 
	 }
	 public Driver minDriver(ArrayList<Driver> aa, RideRequest request) {
		 if (aa == null||request == null) {
			   throw new RuntimeException("missing values");
			  }
			  Driver min = aa.get(0);
			  for (int i = 0; i< aa.size(); i++) {
			   if (aa.get(i).getVehicle().getPosition().getManhattanDistanceTo(request.getClientPosition()) <= min.getVehicle().getPosition().getManhattanDistanceTo(request.getClientPosition())) {
			    min = aa.get(i);
			   }
			  }
			  return min;
	}
	public void set (Driver a) {
		completedOrder.add(a);
		
	}

	
}


	 