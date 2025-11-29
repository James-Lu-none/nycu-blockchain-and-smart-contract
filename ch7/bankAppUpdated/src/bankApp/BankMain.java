package bankApp;

import java.util.Scanner;

public class BankMain {
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Welcome to Bank App !");
		System.out.println("You are ...");
		System.out.println("1. I'm a Bank Manager");
		System.out.println("2. I'm a Comsumer");
		System.out.println("Your choice (1/2) ?");
		int input = 0;
		input = Integer.parseInt(sc.nextLine());
		
		if (input == 1) {
			BankManager.callManager();
		} else if (input == 2) {
			BankClient.callClient();
		} else {
			System.err.println("I'm not sure what you would like me to do.");
			System.exit(1);
		}

	}

}
