package a5;

public class TunaPortion extends Portion{
	private static Ingredient a = new Tuna();
	public TunaPortion (double amount) {
		super(amount, a);
	}

	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (this.getIngredient().equals(other.getIngredient())) {
			return new TunaPortion(this.getAmount() + other.getAmount());
		} else {
			throw new IllegalArgumentException();
		}
	}
}
