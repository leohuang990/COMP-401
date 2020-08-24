package a5;

public class Sashimi implements Sushi{
	private SashimiType s;
	IngredientPortion[] a = new IngredientPortion[1];
	private IngredientPortion i;
	public enum SashimiType {TUNA, YELLOWTAIL, EEL, CRAB, SHRIMP};
	public Sashimi(SashimiType type) {
		s = type;
		if (s.equals(SashimiType.TUNA)) {
			i = new TunaPortion(0.75);
			a[0] = i;
		} else if (s.equals(SashimiType.YELLOWTAIL)) {
			i= new YellowtailPortion(0.75);
			a[0] = i;
		} else if (s.equals(SashimiType.EEL)) {
			i= new EelPortion(0.75);
			a[0] = i;
		} else if (s.equals(SashimiType.CRAB)) {
			i= new CrabPortion(0.75);a[0] = i;
		} else  if (s.equals(SashimiType.SHRIMP)){
			i= new ShrimpPortion(0.75);a[0] = i;
		} else {
			throw new IllegalArgumentException();
		}
	}
	public String getName() {
		return i.getName() + " sashimi";
	}
	public IngredientPortion[] getIngredients() {
		return a;
	}
	public int getCalories() {
		return (int) Math.round(i.getCalories());
	}
	public double getCost() {
		return i.getCost();
	}
	public boolean getHasRice() {
		return i.getIsRice();
	}
	public boolean getHasShellfish() {
		return i.getIsShellfish();
	}
	public boolean getIsVegetarian() {
		return i.getIsVegetarian();
	}
}
