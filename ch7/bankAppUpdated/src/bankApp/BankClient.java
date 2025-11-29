package bankApp;

import java.util.Scanner;

import ethInfo.EthAccount;
import ethSC.BankClientHandler;

public class BankClient {
	
	private static Scanner sc = new Scanner(System.in);

	public static void callClient() {
		EthAccount eta = new EthAccount();
		System.out.println("Welcome, Who are you ?");
		for (int i = 0; i < eta.getTotalCount(); i++) {
			String account[] = eta.getAccount(i);
			System.out.println(i + ". " + account[0]);
		}
		System.out.println("Your choice ? (Default = 0)");
		int input = 0;
		
		input = Integer.parseInt(sc.nextLine());
		
		if (input > eta.getTotalCount() -1) {
			System.err.println("Account does not exist!");
			System.exit(1);
		}
		
		BankFileIO bfio = new BankFileIO();
		String[] bankList = bfio.getAllBanks();
		
		if (bankList.length == 0) {
			System.out.println("There are no bank in the list !");
			System.out.println("Please create a bank before use !");
			System.exit(1);
		}
		
		System.out.println("Which bank are you going to use today ?");
				
		for (int i = 0; i < bankList.length; i++) {
			String[] elementBank = bankList[i].split(",");
			String outputString = "";
			outputString += Integer.toString(i);
			outputString += " Bank : ";
			outputString += elementBank[0];
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
		
		BankClientHandler bh = new BankClientHandler(selectedBank[0], input);
		
		try {
			System.out.println("Hello " + eta.getAccount(input)[0]);
			System.out.println("Welcome to the " + bh.getBankName() + " Bank");
		} catch (Exception e) {
			System.err.println("Something went wrong.");
			e.printStackTrace();
		}
		
		System.out.println("How can I help you today ?");
		System.out.println("1. Check my balance on hand.");
		System.out.println("2. Check my balance in the bank.");
		System.out.println("3. Deposit amount into my account");
		System.out.println("4. Withdraw amount from my account");
		System.out.println("Your choice ? (1/2/3/4)");
		
		int srvChoice = Integer.parseInt(sc.nextLine());
		
		if (srvChoice == 1) {
			try {
				System.out.println("Sure ! You have " + Double.toString(bh.getLocalBalance()) + " ETH on hand");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (srvChoice == 2) {
			try {
				System.out.println("Sure ! You have " + Double.toString(bh.getBalance()) + " ETH in the bank");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (srvChoice == 3) {
			try {
				System.out.println("Sure ! How much would you like to deposit (ETH) ?");
				int depost = Integer.parseInt(sc.nextLine());
				bh.deposit(depost);
				System.out.println("Done !");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if (srvChoice == 4) {
			try {
				System.out.println("Sure ! How much would you like to withdraw (ETH) ?");
				int withdraw = Integer.parseInt(sc.nextLine());
				bh.withdraw(withdraw);
				System.out.println("Done !");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("That is not a valid option");
			System.exit(1);
		}
		
	}

}
