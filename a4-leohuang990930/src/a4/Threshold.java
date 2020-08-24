package a4;

public class Threshold implements PixelTransformation{
	double t;
	Pixel a;
	public Threshold (double threshold) {
		this.t = threshold;
	}
	public Pixel transform(Pixel p) {
		if (p.getIntensity() > t) {
			return Pixel.WHITE;
		} else {
			return Pixel.BLACK;
		}
	}
}
