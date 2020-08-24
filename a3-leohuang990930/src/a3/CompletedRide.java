package a3;

/*
 * CompletedRide
 * Represents information about how a specific ride request was completed.
 * 
 * getRequest()
 *    Returns the RideRequest associated with this completed ride.
 *    
 * getDriver()
 *    Returns the Driver associatd with this completed ride.
 *    
 * getWaitTime()
 *    Returns the Manhattan distance between the position of the driver's vehicle
 *    and the position of the client making the request at the time that the
 *    ride was completed. This is intended to be a measure of how long the
 *    client needed to wait for the driver to arrive.
 *    
 * getTotalTime()
 *    The sum of the wait time and the ride time associated with the ride request.
 * 
 * getCost()
 *    Calculates the cost of providing the ride. This cost is calculated as
 *    one-half of the ride time summed with one-tenth of the wait time. 
 *    Note: while wait and ride times are integer values, cost is a real value.
 *    
 * getPrice()
 *    Calculates the price charged for the ride. This price is a function
 *    of the ride time based on the wait time as follows:
 *    
 *    Wait Time		Price of Ride
 *    < 25			two and one-half times the ride time
 *    25 to 49		twice the ride time
 *    50 to 99		equal to ride time
 *    >= 100		half of ride time
 *  
 * getProfit()
 *    Calculates the profit (or potentially the loss) generated by 
 *    the completed ride. This is simply cost subtracted from price.
 */

public interface CompletedRide {
	RideRequest getRequest();
	Driver getDriver();
	int getWaitTime();
	int getTotalTime();
	double getCost();
	double getPrice();
	double getProfit();
}





