package a3;

public class CompletedRideImpl implements  CompletedRide{
	private RideRequest request;
	private Driver driver;
	private int waitTime;
	private double cost;
	public CompletedRideImpl(RideRequest request, Driver driver) {
		if (request == null || driver == null) {
			throw new RuntimeException();
		}
		this.request = request;
		this.driver = driver;
		waitTime = driver.getVehicle().getPosition().getManhattanDistanceTo(request.getClientPosition());
		
	}
	
	public RideRequest getRequest() {
		return request;
	}
	public Driver getDriver() {
		return driver;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public int getTotalTime() {
		return waitTime + request.getRideTime();
	}
	public double getCost() {
		return cost = waitTime*0.1 + request.getRideTime()*0.5;
	}
	public double getPrice() {
		if (getWaitTime() < 25) {
			return 2.5 * request.getRideTime();
		} else if (getWaitTime() >= 25 && getWaitTime() <=49) {
			return 2 * request.getRideTime();
		} else if (getWaitTime() >= 50 && getWaitTime() <=99) {
			return request.getRideTime();
		} else {
			return 0.5 * request.getRideTime();
		}
	}
	public double getProfit() {
		return getPrice() - getCost();
	}
}
