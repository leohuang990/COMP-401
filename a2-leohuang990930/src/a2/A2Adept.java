package a2;

import java.util.Scanner;

public class A2Adept {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int number = scan.nextInt();
		String[] name = new String[number];
		double[] cost = new double[number];
		Boolean[] n = new Boolean[number];
		double[] calories = new double[number];
		for (int i = 0; i < number; i++) {
			name[i] = scan.next();
			cost[i] = scan.nextDouble();
			n[i] = scan.nextBoolean();
			calories[i] = scan.nextDouble();	
		}
		// store input and create arrays
		
		int number1 = scan.nextInt();
		String[] rolls = new String[number1];
		double[] totalCal= new double[number1];
		double[] totalCost = new double[number1];
		Boolean[] veg = new Boolean[number1];
		// build up arrays to store properties of each roll
		for (int ve = 0; ve < veg.length; ve++) {
			veg[ve] = true;
		}
		for (int i = 0; i < number1; i++) {
			rolls[i] = scan.next();
			int num = scan.nextInt();
			for (int x = 0; x< num; x++) {
				String m = scan.next();
				double nn = scan.nextDouble();
				for (int s = 0; s < name.length; s++) {
					if (name[s].equals(m)) {
						totalCal[i] += calories[s]*nn;
						totalCost[i] += cost[s]*nn;
						if (n[s] == false) {
							veg[i] = false;
						}
					}
				}
			}
		}
		for (int xy = 0; xy < rolls.length; xy++) {
			System.out.println("" + rolls[xy] + ":");
			System.out.println("  " + ((int) (totalCal[xy] + 0.5)) + " calories");
			System.out.println("  " + "$" + String.format("%.2f", totalCost[xy]));
			if (veg[xy] == true) {
				System.out.println("  " + "Vegetarian");
			} else {
				System.out.println("  Non-Vegetarian");
			}
		}
		
	}
	
	// You can define helper methods here if needed.
	
}
