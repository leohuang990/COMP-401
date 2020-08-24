package a3;

public class ShortestWaitDispatcher implements Dispatcher {
 
 public Driver chooseDriver(Driver[] a, RideRequest request) {
  if (a == null||request == null) {
   throw new RuntimeException("missing values");
  }
  Driver min = a[0];
  for (int i = 0; i< a.length; i++) {
   if (a[i].getVehicle().getPosition().getManhattanDistanceTo(request.getClientPosition()) <= min.getVehicle().getPosition().getManhattanDistanceTo(request.getClientPosition())) {
    min = a[i];
   }
  }
  return min;
 }
 
}