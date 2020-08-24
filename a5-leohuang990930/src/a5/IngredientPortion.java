package a5;

public interface IngredientPortion {

	/* getIngredient()
	 * 
	 * Getter for Ingredient object representing the ingredient
	 * this is object is a portion of.
	 */
	Ingredient getIngredient();

	/* getAmount()
	 * 
	 * Getter for the size (in ounces) of the ingredient portion.
	 */
	double getAmount();

	/* getName()
	 * getIsVegetarian()
	 * getIsRice()
	 * getIsShellfish()
	 * 
	 * Getters for characteristics of the ingredient.
	 */

	default String getName() {
		return getIngredient().getName();
	}
	default boolean getIsVegetarian() {
		return getIngredient().getIsVegetarian();
	}
	default boolean getIsRice() {
		return getIngredient().getIsRice();
	}
	default boolean getIsShellfish() {
		return getIngredient().getIsShellfish();
	}

	
	/*
	 * getCalories()
	 * getCost()
	 * 
	 * Getters for the calorie and price information associated with
	 * this specific portion. This value is not rounded.
	 */
	
	default double getCalories() {
		return getIngredient().getCaloriesPerOunce()*getAmount();
	}
	default double getCost() {
		return getAmount() * getIngredient().getPricePerOunce();
	}
	
	/*
	 * combine(IngredientPortion other)
	 * 
	 * If other is null, returns this ingredient portion.
	 * Otherwise...
	 * If Ingredient associated with other is equal
	 * to this ingredient, returns new ingredient portion which
	 * represents the combined amounts of this ingredient portion and
	 * the other ingredient portion. Uses equals() method of the
	 * Ingredient interface to test equality.
	 * 
	 * If other ingredient is not equal to this ingredient, 
	 * throws an IllegalArgumentException().
	 */
	
	IngredientPortion combine(IngredientPortion other);
}
