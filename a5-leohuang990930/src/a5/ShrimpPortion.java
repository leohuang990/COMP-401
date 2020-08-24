package a5;

public class ShrimpPortion extends Portion{
	private static Ingredient a = new Shrimp();
	public ShrimpPortion (double amount) {
		super(amount, a);
	}
	
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (this.getIngredient().equals(other.getIngredient())) {
			return new ShrimpPortion(this.getAmount() + other.getAmount());
		} else {
			throw new IllegalArgumentException();
		}
	}
}
