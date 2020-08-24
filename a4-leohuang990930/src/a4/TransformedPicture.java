package a4;

public class TransformedPicture implements Picture {
	Picture xx;
	PixelTransformation f;
	Pixel[][] array;
	public TransformedPicture (Picture source, PixelTransformation xform) {
		this.xx = source; this.f = xform;
	}
	public int getWidth() {
		return this.xx.getWidth();
	}
	public int getHeight() {
		return this.xx.getHeight();
	}
	public Pixel getPixel(int x, int y) {
		xx.paint(x, y, f.transform(xx.getPixel(x, y)));
	return xx.getPixel(x, y);
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
	}
}
