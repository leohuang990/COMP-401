package a5;

public class RicePortion extends Portion{
	private static Rice a = new Rice();
	public RicePortion (double amount) {
		super(amount, a);
	}

	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (this.getIngredient().equals(other.getIngredient())) {
			return new RicePortion(this.getAmount() + other.getAmount());
		} else {
			throw new IllegalArgumentException();
		}
	}
}
