package a4;

public class GammaCorrect implements PixelTransformation{
	double g;
	public GammaCorrect (double gamma) {
		g = gamma;
	}
	public Pixel transform(Pixel p) {
		
		return new ColorPixel(Math.pow(p.getRed(), (1.0/g)),Math.pow(p.getGreen(), (1.0/g)),Math.pow(p.getBlue(), (1.0/g)));
	}
}
