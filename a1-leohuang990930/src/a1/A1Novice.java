package a1;

import java.util.Scanner;

public class A1Novice {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int a=scan.nextInt();
		for (int i = 0; i < a; i++) {
			// this for loop stands for each properties of customers
			String b=scan.next();
			String c=scan.next();
			double d=scan.nextInt();
			double total=0;
			// this for stands for properties of goods
			for (int z = 0; z < d; z++) {
				int e=scan.nextInt();
				String f=scan.next();
				double g=scan.nextDouble();
				double h = e*g;
				total +=h;
			}
			System.out.println(b.substring(0, 1) + "." + " " + c + ": " + String.format("%,.2f", total));
		}
	}
}