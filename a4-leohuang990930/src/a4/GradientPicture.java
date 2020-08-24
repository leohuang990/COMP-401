package a4;

public class GradientPicture implements Picture{
	Pixel upperLeft; Pixel upperRight; Pixel lowerLeft; Pixel lowerRight;
	int width; int height;
	Pixel[][] n;
	public GradientPicture(int width, int height, Pixel upper_left, Pixel upper_right, Pixel lower_left, Pixel lower_right) {
		if (width == 0|| height == 0 || upper_left == null||upper_right == null||lower_left == null||lower_right ==null) {
			throw new IllegalArgumentException();
		}
		this.width = width; this.height = height; this.upperLeft = upper_left; this.upperRight = upper_right;
		this.lowerLeft = lower_left; this.lowerRight = lower_right;
		Pixel[][] co = new Pixel[width][height];
		co[0][0] = upper_left; co[co.length-1][0] = upper_right; co[0][co[0].length-1] = lower_left;co[co.length-1][co[0].length-1] = lower_right;
		for (int len = 1; len < co[0].length-1;len++) {
			co[0][len] = co[0][0].blend(co[0][co[0].length-1], (double)len/((double)co[0].length-1));
			co[co.length-1][len] = co[co.length-1][0].blend(co[co.length-1][co[0].length-1], (double) len/(double) (co[0].length-1));
		}
		for (int x = 0; x < co[0].length; x++) {
			 for (int i = 1; i < co.length-1; i++){
				co[i][x] = co[0][x].blend(co[co.length-1][x], (double)i/(double)(co.length-1));
			}
		}
		this.n = co;
	}
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	public Pixel getPixel(int x, int y) {
		if (x > this.n.length-1 || y > this.n[0].length-1 || x<0|| y<0) {
			throw new IllegalArgumentException();
		}
		
		return this.n[x][y];
	}
	public Picture paint(int x, int y, Pixel p) {
		if (p==null) {
			throw new IllegalArgumentException();
		}
		Picture tentative = new MutablePixelArrayPicture(n);
		return tentative.paint(x,y,p);
		
	}
	public Picture paint(int x, int y, Pixel p, double factor) {
		Picture tentative = new MutablePixelArrayPicture(n);
		return tentative.paint(x,y,p,factor);
	}
	public Picture paint(int ax, int ay, int bx, int by, Pixel p) {
		Picture tentative = new MutablePixelArrayPicture(n);
		return tentative.paint(ax,ay,bx,by,p);
	}
	public Picture paint(int ax, int ay, int bx, int by, Pixel p, double factor) {
		Picture tentative = new MutablePixelArrayPicture(n);
		return tentative.paint(ax,ay,bx,by,p,factor);
	}
	public Picture paint(int cx, int cy, double radius, Pixel p) {
		Picture tentative = new MutablePixelArrayPicture(n);
		return tentative.paint(cx,cy,radius,p);
	}
	public Picture paint(int cx, int cy, double radius, Pixel p, double factor) {
		Picture tentative = new MutablePixelArrayPicture(n);
		return tentative.paint(cx,cy,radius,p,factor);
	}}