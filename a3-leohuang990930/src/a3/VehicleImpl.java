package a3;

public class VehicleImpl implements Vehicle{
	private String make;
	private String model;
	private String plate;
	private Position position;
	private int mileage =0;
	public VehicleImpl(String make, String model, String plate, Position position) {
		this.make = make;
		this.model = model;
		this.plate = plate;
		this.position = position;
		if (this.make == null || this.model == null||this.plate == null|| position == null) {
			throw new RuntimeException("nul");
		}
	}
	public String getMake() {
		return this.make;
	}
	public String getModel() {
		return this.model;
	}
	public String getPlate() {
		return this.plate;
	}
	public int getMileage() {
		return mileage;
	}
	public Position getPosition() {
		return position;
	}
	public void moveToPosition(Position p) {
		if (p == null) {
			throw new RuntimeException("");
		}
		mileage += p.getManhattanDistanceTo(position);
		position = p;
		
	}
}
