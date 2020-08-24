package a3;

public class PositionImpl implements Position{
	private int x;
	private int y;
	
	public PositionImpl(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public int getManhattanDistanceTo(Position p) {
		return Math.abs(getX() - p.getX()) + Math.abs(getY() - p.getY());
	}
}
