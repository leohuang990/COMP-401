package a1;

import java.util.Scanner;

public class A1Adept {


	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		String[] name = new String[110];
		
		double[] amount = new double[110];
		String[] goodName=new String[100];
		double[] goodPrice=new double[100];
		for (int i = 0; i < a; i++) {
			// this for loop is used to create a list of food
			goodName[i] = scan.next();
			goodPrice[i]= scan.nextDouble();
	    }
		int d = scan.nextInt();
		for (int z = 0; z < d; z++) {
			// this for loop stands for each customer
			String n = scan.next();
			String m = scan.next();
			String combined = n + " " + m;
			name[z] = combined;
			
			int x = scan.nextInt();
			double individual = 0;
			for (int k = 0; k < x; k++) {
				// this for loop stands for each good
				int number = scan.nextInt();
				String food = scan.next();
				for (int i = 0; i < goodName.length; i++) {
					if (food.equals(goodName[i])) {
						individual += number*goodPrice[i];
					}
				}
				
			}
			amount[z] = individual;
		}
		double max = amount[0];
		double min = amount[0];
		double average = 0;
		for (int i = 0; i < 20; i++) {
			// function for getting max and min
			average += amount[i];
			if (amount[i] > max) {
				max = amount[i];
			}
			if (amount[i] < min && amount[i] !=0) {
				min = amount[i];
			}
		}
		average = average/d;
		String maxName = "";
		String minName = "";
		for (int i = 0; i < amount.length; i++) {
			if (amount[i] == max) {
				maxName = name[i];
				
			}
			if (amount[i] == min) {
				minName = name[i];
				
			}
				
		}
		
		System.out.println("Biggest:" + " " + maxName + " (" + String.format("%,.2f", max) + ")");
		System.out.println("Smallest:" + " " + minName + " (" + String.format("%,.2f", min) + ")");
		System.out.println("Average:" + " " + String.format("%,.2f", average));
	}
}
