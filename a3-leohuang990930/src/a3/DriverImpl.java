package a3;

public class DriverImpl implements Driver{
	private String firstName;
	private String lastName;
	
	private int ID;
	private Vehicle vehicle;
	
	public DriverImpl(String first, String last, int id, Vehicle vehicle) {
		this.firstName = first;
		this.lastName = last;
		this.ID = id;
		this.vehicle= vehicle;
		if (firstName == null || lastName == null  || this.vehicle ==null) {
			throw new RuntimeException("null");
		}
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFullName() {
		return firstName + " " + lastName;
	}
	public int getID() {
		return ID;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle newVehicle) {
	    this.vehicle = newVehicle;
	    if (newVehicle == null) {
	    	throw new RuntimeException("");
	    }
	}
}
