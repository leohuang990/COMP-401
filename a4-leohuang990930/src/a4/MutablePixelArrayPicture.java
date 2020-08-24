package a4;

public class MutablePixelArrayPicture implements Picture{
	int width;
	int height;
	Pixel a;
	Pixel[][] array;
	
	public MutablePixelArrayPicture(Pixel[][] pixel_array) {
		if (pixel_array == null || pixel_array.length == 0 || pixel_array[0].length == 0) {
			throw new IllegalArgumentException();
		} 
		
		for (int len = 0; len < pixel_array.length -1; len++) {
			if (pixel_array[len] == null || pixel_array[len+1] == null) {
				throw new IllegalArgumentException();
			}
			if (pixel_array[len].length != pixel_array[len+1].length) {
					throw new IllegalArgumentException();
			}
			for (int lan = 0; lan <pixel_array[len].length;lan++) {
				if (pixel_array[len][lan] == null || pixel_array[len+1][lan]==null) {
					throw new IllegalArgumentException();
				}
			}
			}
		this.width = pixel_array.length;
		this.height = pixel_array[0].length;
			
		Pixel[][] copy = new Pixel[pixel_array.length][pixel_array[0].length];
		for (int i = 0; i < pixel_array.length; i++) {
			for (int n = 0; n < pixel_array[0].length; n++) {
				copy[i][n] = pixel_array[i][n];
			}
		}
		this.array = copy;
	}
	
	public MutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		if (width == 0|| height == 0 || initial_value == null) {
			throw new IllegalArgumentException();
		}
		
		this.width = width;
		this.height = height;
		this.a = initial_value;
		Pixel[][] co = new Pixel[width][height];
		for (int x= 0; x < co.length; x++) {
			for (int y = 0; y < co[0].length; y++) {
				co[x][y] = initial_value;
			}
		}
		this.array = co;
	}
	public MutablePixelArrayPicture(int width, int height) {
		this.width = width;
		this.height = height;
		this.a = new GrayPixel(0.5);
		Pixel[][] co = new Pixel[width][height];
		for (int x= 0; x < co.length; x++) {
			for (int y = 0; y < co[0].length; y++) {
				co[x][y] = this.a;
			}
		}
		this.array = co;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public Pixel getPixel(int x, int y) {
		if (x > this.array.length-1 || y > this.array[0].length-1 || x<0|| y<0) {
			throw new IllegalArgumentException();
		}
		return this.array[x][y];
	}
	public Picture paint(int x, int y, Pixel p) {
		this.array[x][y] = p;
		return this;
	}
	public Picture paint(int x, int y, Pixel p, double factor) {
		if (x < 0|| y< 0 ||x >=array.length||y>= array[0].length) {
			throw new IllegalArgumentException();
		}
		if (p == null) {
			throw new IllegalArgumentException();
		}
		this.array[x][y].blend(p, factor);
		return this;
	}
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		int bigX; int bigY; int smallX; int smallY;
		if (ax < bx) {
			bigX = bx; smallX = ax;
		} else {
			bigX = ax; smallX =bx;
		}
		if (ay < by) {
			bigY = by; smallY = ay;
		} else {
			bigY = ay; smallY =by;
		}
		for (int i = smallX; i <= bigX; i++) {
			for (int n = smallY; n <= bigY; n++) {
				paint(i,n,p);
			}
		}
		return this;
	}
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		int bigX; int bigY; int smallX; int smallY;
		if (ax < bx) {
			bigX = bx; smallX = ax;
		} else {
			bigX = ax; smallX =bx;
		}
		if (ay < by) {
			bigY = by; smallY = ay;
		} else {
			bigY = ay; smallY =by;
		}
		for (int i = smallX; i <= bigX; i++) {
			for (int n = smallY; n <= bigY; n++) {
				paint(i,n,p, factor);
			}
		}
		return this;
	}
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		int rad = (int)radius; int left = cx - rad; int top = cy -rad;
		if (cx - rad < 0) {
			left = 0;
		}
		if (cy - rad < 0) {
			top = 0;
		}
		for (int x = left; x < cx + radius; x++) {
			for (int y = top; y < cy + radius; y++) {
				double a = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
				if (a <= radius) {
					this.array[x][y] = p;
				}
			}
		}
		
		return this;
	}
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		if (cx<0||cy<0) {
			throw new IllegalArgumentException();
		}
		int rad = (int)radius;
		for (int x = cx - rad; x < cx + radius; x++) {
			for (int y = cy - rad; y < cy + radius; y++) {
				double a = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
				if (a <= radius) {
					this.array[x][y].blend(p, factor);
				}
			}
		}
		return this;
	}
	
	
	
	
	
	
}
