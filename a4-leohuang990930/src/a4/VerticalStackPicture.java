package a4;

public class VerticalStackPicture implements Picture{
	Picture t; Picture b;Picture comb;	Pixel[][] array;
	public VerticalStackPicture(Picture top, Picture bottom) {
		if (top == null||bottom == null) {
			throw new IllegalArgumentException();
		}
		this.t = top; this.b = bottom;
		Pixel[][] co= new Pixel[top.getWidth() ][top.getHeight()+bottom.getHeight()];
		for (int i = 0; i < t.getWidth(); i++) {
			for (int n = 0; n < t.getHeight(); n++) {
				co[i][n] = t.getPixel(i, n);
			}
		}
		for (int e = 0;e < co.length; e++) {
			for (int d = top.getHeight(); d < co[0].length; d++) {
				co[e][d] = b.getPixel(e, d - t.getHeight());
			}
		}
		array = co;
		comb = new MutablePixelArrayPicture(co);
	}
	public int getWidth() {
		return this.comb.getWidth();
	}
	public int getHeight() {
		return this.comb.getHeight();
	}
	public Pixel getPixel(int x, int y) {
		if (x > this.array.length-1 || y > this.array[0].length-1 || x<0|| y<0) {
			throw new IllegalArgumentException();
		}
		return this.array[x][y];
	}
	public Picture paint(int x, int y, Pixel p) {
		
		if (p == null) {
			throw new IllegalArgumentException();
		}
		if (y < t.getHeight()) {
			t= t.paint(x, y, p);
			array[x][y] = p;
		} else {
			b= b.paint(x, y - t.getHeight(), p);
			array[x][y] = p;
		}
		
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
		Picture temp = new MutablePixelArrayPicture(array);
		return temp;
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
		Picture temp = new MutablePixelArrayPicture(array);
		return temp;
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
		Picture temp = new MutablePixelArrayPicture(array);
		return temp;
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
		
		Picture temp = new MutablePixelArrayPicture(array);
		return temp;
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
		Picture temp = new MutablePixelArrayPicture(array);
		return temp;
	}
}
