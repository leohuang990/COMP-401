package a4;

public class ImmutablePixelArrayPicture implements Picture{
	int width;
	int height;
	Pixel initialValue;
	Pixel[][] array;
	
	public ImmutablePixelArrayPicture(Pixel[][] pixel_array) {
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
	public ImmutablePixelArrayPicture(int width, int height, Pixel initial_value) {
		if (width == 0|| height == 0 || initial_value == null) {
			throw new IllegalArgumentException();
		}
		
		this.width = width;
		this.height = height;
		this.initialValue = initial_value;
		Pixel[][] co = new Pixel[width][height];
		for (int x= 0; x < co.length; x++) {
			for (int y = 0; y < co[0].length; y++) {
				co[x][y] = initial_value;
			}
		}
		this.array = co;
	}
	public ImmutablePixelArrayPicture(int width, int height) {
		this.width = width;
		this.height = height;
		this.initialValue = new GrayPixel(0.5);
		Pixel[][] co = new Pixel[width][height];
		for (int x= 0; x < co.length; x++) {
			for (int y = 0; y < co[0].length; y++) {
				co[x][y] = this.initialValue;
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
		if (x<0||y<0 || x>=width||y>=height) {
			throw new IllegalArgumentException();
		}
		return array[x][y];
	}
	public Picture paint(int x, int y, Pixel p) {
		if (p==null) {
			throw new IllegalArgumentException();
		}
		Picture tentative = new MutablePixelArrayPicture(array);
		return tentative.paint(x,y,p);
		
	}
	public Picture paint(int x, int y, Pixel p, double factor) {
		Picture tentative = new MutablePixelArrayPicture(array);
		return tentative.paint(x,y,p,factor);
	}
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		Picture tentative = new MutablePixelArrayPicture(array);
		return tentative.paint(ax,ay,bx,by,p);
	}
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		Picture tentative = new MutablePixelArrayPicture(array);
		return tentative.paint(ax,ay,bx,by,p,factor);
	}
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		Picture tentative = new MutablePixelArrayPicture(array);
		return tentative.paint(cx,cy,radius,p);
	}
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		Picture tentative = new MutablePixelArrayPicture(array);
		return tentative.paint(cx,cy,radius,p,factor);
	}}


