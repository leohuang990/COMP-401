package a5;

public class CrabPortion extends Portion{
	private static Ingredient a = new Crab();
	public CrabPortion (double amount) {
		super(amount, a);
	}
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (this.getIngredient().equals(other.getIngredient())) {
			return new CrabPortion(this.getAmount() + other.getAmount());
		} else {
			throw new IllegalArgumentException();
		}
	}
}
