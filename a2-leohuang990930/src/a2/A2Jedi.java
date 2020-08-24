package a2;

import java.util.Scanner;

public class A2Jedi {

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
		// get data about materials
		double[] amount = new double[number];
		
		int number1 = scan.nextInt();
		

		String[] rolls = new String[number1];
		
		
		String[][] rollsTotal = new String[number1][1000];
		double[][] amou = new double[number1][1000];
		// use 1000 to guarantee the length of the array; use 2d arrays to represent materials used and amount
		for (int i = 0; i < number1; i++) {
			rolls[i] = scan.next();
			int num = scan.nextInt();
			
			for (int x = 0; x < num ; x++) {
				rollsTotal[i][x] = scan.next();
				amou[i][x] = scan.nextDouble();
				
			}
			
		}
		// get data about rolls
		for (int abc = 0; abc > -1; abc++) {
			// compare input with known strings and operate addition accordingly
			String sample = scan.next();
			if (sample.equals("EndOrder")){
				break;
			} else {
				for (int nx = 0; nx < rolls.length; nx++) {
					if (sample.equals(rolls[nx])) {
						for (int ax = 0; rollsTotal[nx][ax] != null; ax++) {
							for (int acc = 0; acc < name.length; acc++) {
								if (rollsTotal[nx][ax].equals(name[acc])) {
									amount[acc] += amou[nx][ax];
								}
							}
						
						}
					}
				}
			}
			
		}
		System.out.println("The order will require:");
		for (int ab = 0; ab< amount.length; ab++) {
			System.out.println(String.format("%.2f", amount[ab]) + " ounces of " + name[ab]);
		
		}
		
	}
}

	

