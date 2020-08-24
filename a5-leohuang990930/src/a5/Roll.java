package a5;

public class Roll implements Sushi{
	private String n;
	private IngredientPortion[] a;
	
	public Roll(String name, IngredientPortion[] roll_ingredients) {
		if (roll_ingredients == null) {
			throw new IllegalArgumentException();
		}
		for (int ii = 0; ii < roll_ingredients.length; ii++) {
			if (roll_ingredients[ii] == null) {
				throw new IllegalArgumentException();
			}
		}
		this.n = name;a = roll_ingredients.clone();
		int index = 0; int index1 = 0;
		for(int nx = 0; nx < a.length; nx++) {
			for( int ny = nx+1; ny < a.length;ny++) {
				if (a[nx]!=null&&a[ny]!=null) {
					if (a[nx].getName().equals(a[ny].getName())) {
						a[nx] = a[nx].combine(a[ny]);
						index ++;
					}
					
				}
			}
		}
		IngredientPortion[] nw = new IngredientPortion[a.length - index];
		for (int x = 0; x < a.length; x++) {
			if (a[x] != null&& index1 < nw.length) {
				nw[index1] = a[x];
				index1++;
			}
		}
		a= nw;
		a = check(a);
		 
		 
		 
		 
		
	}
	public boolean test(IngredientPortion b, IngredientPortion[] nw) {
		boolean bb = true;
		for (int xx = 0; nw[xx] != null && xx < nw.length;xx++) {
			if (b.getName().contentEquals(nw[xx].getName())) {
				bb = false;
			}
		}
		return bb;
	}
	public IngredientPortion[] check(IngredientPortion[] cc) {
		boolean xxa = false; int abc = 0;
		for (int nx = 0; nx < cc.length; nx++) {
			if (cc[nx].getName().contentEquals("seaweed")) {
				xxa = true;
				abc = nx;
			}
		}
		if (xxa) {
			if (cc[abc].getAmount() >= 0.1) {
				return cc;
			} else {	
				cc[abc] = new SeaweedPortion(0.1);
			return cc;
			}
		} else {
			IngredientPortion[] te = new IngredientPortion[a.length + 1];
			te[te.length - 1] = new SeaweedPortion(0.1);
			for (int nx = 0; nx< cc.length; nx++) {
				te[nx] = cc[nx];
			}
			cc = te;
			return cc;
		}
	}
		
	public String getName() {
		return n;
	}
	public IngredientPortion[] getIngredients() {
		return check(a);
	}
	public int getCalories() {
		double sum = 0;
		for (int ii = 0; ii < a.length; ii++) {
			sum += a[ii].getCalories();
		}
		return (int) Math.round(sum);
	}
	public double getCost() {
		double sc = 0.00;
		for (int ii = 0; ii < a.length; ii++) {
			sc += a[ii].getCost();
		}
		sc = Math.round(sc*100);
		sc = sc/100;
		return sc;
	}
	public boolean getHasRice() {
		for (int ii = 0; ii < a.length; ii++) {
			if (a[ii].getIsRice()) {
				return true;
			}
		}
		return false;
	}
	public boolean getHasShellfish() {
		for (int ii = 0; ii < a.length; ii++) {
			if (a[ii].getIsShellfish()) {
				return true;
			}
		}
		return false;
	}
	public boolean getIsVegetarian() {
		for (int ii = 0; ii < a.length; ii++) {
			if (a[ii].getIsVegetarian()) {
				return true;
			}
		}
		return false;
	}
	
}
