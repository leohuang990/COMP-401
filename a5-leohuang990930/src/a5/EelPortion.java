package a5;

public class EelPortion extends Portion{
	private static Ingredient a = new Eel();
	public EelPortion (double amount) {
		
		super(amount, a);
	}
	public IngredientPortion combine(IngredientPortion other) {
		if (other == null) {
			return this;
		}
		if (this.getIngredient().equals(other.getIngredient())) {
			return new EelPortion(this.getAmount() + other.getAmount());
		} else {
			throw new IllegalArgumentException();
		}
	}
}
