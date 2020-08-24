package a5;

public class Food implements Ingredient{
	 String name;
	 int cpo;
	 double ppo;
	 boolean veg;
	 boolean rice;
	 boolean shellfish;
	 public Food(String n, int c, double p, boolean v, boolean r, boolean s) {
		 cpo = c;
		 name = n;
		 ppo = p;
		 veg = v;
		 rice = r;
		 shellfish = s;
	 }
	public String getName() {
		 return name;
	}
	
	public int getCaloriesPerOunce() {
		return cpo;
	}
	 public double getPricePerOunce() {
		 return ppo;
	 }
	 public double getCaloriesPerDollar() {
		 return cpo/ppo;
	 }
	 
	 public boolean getIsVegetarian() {
		 return veg;
	 }
	 public boolean getIsRice() {
		 return rice;
	 }
	 public boolean getIsShellfish() {
		 return shellfish;
	 }
	 public boolean equals(Ingredient other) {
		 if (other == null) {
			 return false;
		 }
		 if (this.getName().equals(other.getName()) != true) {
			return false;
		 } else if (this.getCaloriesPerOunce() == other.getCaloriesPerOunce() && this.getPricePerOunce() == other.getPricePerOunce() && this.getIsRice() == other.getIsRice() && this.getIsShellfish() == other.getIsShellfish()
				 && this.getIsVegetarian() == other.getIsVegetarian()){
			 return true;
		 } else {
			 return false;
		 }
	 
	 }
}
