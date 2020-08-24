package a5;

public class YellowtailPortion extends Portion{
	private static Ingredient a = new Yellowtail();
	public YellowtailPortion (double amount) {
		super(amount, a);
	}
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (this.getIngredient().equals(getIngredient())) {
			return new YellowtailPortion(this.getAmount() + other.getAmount());
		} else {
			throw new IllegalArgumentException();
		}
	}
}
