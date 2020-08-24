package a5;

public abstract class Portion implements IngredientPortion{
	public double am;
	protected Ingredient nn;
	public Portion(double amount, Ingredient Ing) {
		if (amount <= 0) {
			throw new IllegalArgumentException();
		}
		am = amount;
		nn = Ing;
		
	}
	public Ingredient getIngredient() {
		return nn;
	}
	
	public double getAmount() {
		return am;
	}
	
	public abstract IngredientPortion combine(IngredientPortion other);
}