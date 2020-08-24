package a5;

public class SeaweedPortion extends Portion{
	private static Ingredient a = new Seaweed();
	public SeaweedPortion (double amount) {
		super(amount, a);
	}

	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (this.getIngredient().equals(other.getIngredient())) {
			return new SeaweedPortion(this.getAmount()+ other.getAmount());
		} else {
			throw new IllegalArgumentException();
		}
	}
}
