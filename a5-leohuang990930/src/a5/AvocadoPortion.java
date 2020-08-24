package a5;

public class AvocadoPortion extends Portion { 
	private static Ingredient a = new Avocado();
	public AvocadoPortion (double amount) {
		super(amount, a);
	}
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (this.getIngredient().equals(other.getIngredient())) {
			return new AvocadoPortion(this.getAmount() + other.getAmount());
		} else {
			throw new IllegalArgumentException();
		}
	}
}
