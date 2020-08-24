package a5;

public class Nigiri implements Sushi{
	public enum NigiriType {TUNA, YELLOWTAIL, EEL, CRAB, SHRIMP};
	private NigiriType s;
	IngredientPortion[] a = new IngredientPortion[2];
	private IngredientPortion i;
	private IngredientPortion r = new RicePortion(0.5);
	public Nigiri(NigiriType type) {
		s = type;
		if (s.equals(NigiriType.TUNA)) {
			i = new TunaPortion(0.75);
		} else if (s.equals(NigiriType.YELLOWTAIL)) {
			i= new YellowtailPortion(0.75);
		} else if (s.equals(NigiriType.EEL)) {
			i= new EelPortion(0.75);
		} else if (s.equals(NigiriType.CRAB)) {
			i= new CrabPortion(0.75);
		} else  if (s.equals(NigiriType.SHRIMP)){
			i= new ShrimpPortion(0.75);
		} else {
			throw new IllegalArgumentException();
		}
	}
	public String getName() {
		return i.getName() + " nigiri";
	}
	public IngredientPortion[] getIngredients() {
		a[0] = i;
		a[1] = r;
		return a;
	}
	public int getCalories() {
		return (int) Math.round(i.getCalories() + r.getCalories());
	}
	public double getCost() {
		return i.getCost()+r.getCost();
	}
	public boolean getHasRice() {
		return true;
	}
	public boolean getHasShellfish() {
		return i.getIsShellfish();
	}
	public boolean getIsVegetarian() {
		return i.getIsVegetarian();
	}
}
