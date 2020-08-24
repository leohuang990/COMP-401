package a3;

public class RideRequestImpl implements RideRequest{
	private Position position;
	private Position destination;
	private boolean complete = false;
	private CompletedRide completedeRide;
	
	public RideRequestImpl(Position clientPosition, Position destination) {
		
			this.position = clientPosition;
			this.destination = destination;
	if (clientPosition == null || destination == null) {
		throw new RuntimeException();
	}
	}
	public Position getClientPosition() {
		return position;
	}
	public Position getDestination() {
		return destination;
	}
	public boolean getIsComplete() {
		return complete;
	}
	public CompletedRide complete(Driver driver) {
		if (driver == null) {
			throw new RuntimeException();
		}
		if (getIsComplete() == false) {
			completedeRide = new CompletedRideImpl(this,driver);
			driver.getVehicle().moveToPosition(position);
			driver.getVehicle().moveToPosition(destination);
			complete = true;
			return completedeRide;
		} else {
			return completedeRide;
		}
	}
	public int getRideTime() {
		return position.getManhattanDistanceTo(destination);
	}
}
