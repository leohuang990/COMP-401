package a2;

import java.util.Scanner;

public class A2Novice {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int number = scan.nextInt();
		String[] name = new String[number];
		double[] cost = new double[number];
		Boolean[] n = new Boolean[number];
		double[] calories = new double[number];
		// Store all input and create respective arrays
		
		int veg = 0;
		double[] ratio = new double[number];
		double max = ratio[0];
		double min = 10000;
		String a = "";
		String b = "";
		for (int i = 0; i < number; i++) {
			// this for loop stands for each material
			name[i] = scan.next();
			cost[i] = scan.nextDouble();
			n[i] = scan.nextBoolean();
			calories[i] = scan.nextDouble();
			if (n[i] == true) {
				veg++;
			}
			// make judgment of its vegetarian status
			ratio[i] = calories[i]/cost[i];
		}
		for (int i = 0; i < ratio.length; i++) {
			if (max < ratio[i]) {
				max = ratio[i];
			}
			if (min > ratio[i] && min!= 0) {
				min =  ratio[i];
			}
		}
		for (int i = 0; i < ratio.length; i++) {
			if (max == ratio[i]) {
				a = name[i];
			}
			if (min == ratio[i]) {
				b = name[i];
			}
		}
		
		System.out.println("Number of vegetarian ingredients: " + veg);
		System.out.println("Highest cals/$: " + a);
		System.out.println("Lowest cals/$: " + b);
	}
	
	// You can define helper methods here if needed.
	
}
