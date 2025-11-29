package bankApp;

import java.util.Scanner;

import ethInfo.EthAccount;
import ethSC.BankManagerHandler;

public class BankManager {

	private static Scanner sc = new Scanner(System.in);
	
	public static void callManager() {
		System.out.println("Welcome to Bank Manager !");
		System.out.println("Please choose your operation");
		System.out.println("1. Create a new Bank");
		System.out.println("2. Manage an existing Bank");
		System.out.println("Your choice (1/2) ?");
		int input = 0;
		input = Integer.parseInt(sc.nextLine());
		
		if (input == 1) {
			createBank();
		} else if (input == 2) {
			maintainBank();
		} else {
			System.err.println("I'm not sure what you would like me to do.");
			System.exit(1);
		}
	}
	
	private static void createBank() {
		EthAccount eta = new EthAccount();
		System.out.println("Good. Whose name should this new Bank under ?");
		for (int i = 0; i < eta.getTotalCount(); i++) {
			String account[] = eta.getAccount(i);
			System.out.println(i + ". " + account[0]);
		}
		System.out.println("Your choice ? (Default = 0)");
		int input = 0;
		input = Integer.parseInt(sc.nextLine());
		
		if (input > eta.getTotalCount() -1) {
			System.out.println("Account does not exist!");
			System.exit(1);
		}
		
		System.out.println("Good. What is the name of your new Bank ?");
		String name = "";
		name = sc.nextLine();
		System.out.println("Good. The name of your Bank is " + name);
		BankManagerHandler bh = new BankManagerHandler("", true, input);
		try {
			bh.setBankName(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BankFileIO bfio = new BankFileIO();
		bfio.addBank(Integer.toString(input), bh.getAddress());
		sc.close();
	}
	
	private static void maintainBank() {
		BankFileIO bfio = new BankFileIO();
		EthAccount eta = new EthAccount();
		String[] bankList = bfio.getAllBanks();
		
		if (bankList.length == 0) {
			System.out.println("There are no bank in the list !");
			System.exit(1);
		}
				
		for (int i = 0; i < bankList.length; i++) {
			String[] elementBank = bankList[i].split(",");
			String outputString = "";
			outputString += Integer.toString(i);
			outputString += " Bank : ";
			outputString += elementBank[0];
			outputString += " By ";
			outputString += eta.getAccount(Integer.parseInt(elementBank[1]))[0];
			System.out.println(outputString);
		}
		System.out.println("Your choice ? (Default = 0)");
		int choice = 0;
		choice = Integer.parseInt(sc.nextLine());
		
		if (choice > bankList.length -1) {
			System.err.println("There's no such option !");
			System.exit(1);
		}
		
		String[] selectedBank = bankList[choice].split(",");
		//String[] selectedAccount = eta.getAccount(Integer.parseInt(selectedBank[1]));
		
		BankManagerHandler bh = new BankManagerHandler(selectedBank[0], false, Integer.parseInt(selectedBank[1]));
		try {
			System.out.println("Welcome to " + bh.getBankName() + " Bank.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("What do you like to do with this Bank ?");
		System.out.println("1. Change The Name of the Bank");
		System.out.println("2. Get The Total Balance of the Bank");
		System.out.println("Your choice (1/2) ?");
		
		
		int operation = 0;
		operation = Integer.parseInt(sc.nextLine());
		
		if (operation == 1) {
			System.out.println("What is the new name of the Bank ?");
			String newBankName;
			newBankName = sc.nextLine();
			try {
				bh.setBankName(newBankName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Bank renamed !");
		} else if (operation == 2) {
			String amountOutput = "The bank has ";
			try {
				amountOutput += Double.toString(bh.getTotalBalance().doubleValue() / (Math.pow(10, 18)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			amountOutput += " ETH";
			
			System.out.println(amountOutput);
		} else {
			System.err.println("There's no such options !");
		}
		
	}

}
