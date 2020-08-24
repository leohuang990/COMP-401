package a4;

public class MonochromePicture implements Picture{
	int width;
	int height;
	Pixel a;
	
	public MonochromePicture(int width, int height, Pixel value) {
		if ((width == 0|| height == 0 || value == null)) {
			throw new IllegalArgumentException();
		}
		this.width = width;
		this.height = height;
		this.a = value;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	
	public Pixel getPixel(int x, int y) {
		if (x<0||y<0 || x>=width||y>=height) {
			throw new IllegalArgumentException();
		}
		return a;
	}
	public Picture paint(int x, int y, Pixel p) {
		if (p==null) {
			throw new IllegalArgumentException();
		}
		Picture tentative = new MutablePixelArrayPicture(width, height, a);
		return tentative.paint(x,y,p);
		
	}
	public Picture paint(int x, int y, Pixel p, double factor) {
		Picture tentative = new MutablePixelArrayPicture(width, height, a);
		return tentative.paint(x,y,p,factor);
	}
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		Picture tentative = new MutablePixelArrayPicture(width, height, a);
		return tentative.paint(ax,ay,bx,by,p);
	}
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		Picture tentative = new MutablePixelArrayPicture(width, height, a);
		return tentative.paint(ax,ay,bx,by,p,factor);
	}
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		Picture tentative = new MutablePixelArrayPicture(width, height, a);
		return tentative.paint(cx,cy,radius,p);
	}
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		Picture tentative = new MutablePixelArrayPicture(width, height, a);
		return tentative.paint(cx,cy,radius,p,factor);
	}
}
