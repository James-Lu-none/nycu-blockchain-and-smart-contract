package bankApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class BankFileIO {
	private static final String bankRecordFileName = "bank.dat";
	private static File bankRecordFile;
	private static String[] nameIdx;
	private static String[] address;
	
	BankFileIO() {
		loadBank();
	}
	
	public void addBank(String addNameIdx, String addAddress) {
		for (String singleAddress : address) {
			if (singleAddress.contentEquals(addAddress)) {
				System.out.println("It's already in the list !");
				return;
			}
		}
		String[] tempNameIdx = nameIdx;
		nameIdx = new String[nameIdx.length + 1];
		String[] tempAddress = address;
		address = new String[address.length + 1];
		for (int i = 0; i < nameIdx.length; i++) {
			if (i == nameIdx.length -1) {
				nameIdx[i] = addNameIdx;
				address[i] = addAddress;
			} else {
				nameIdx[i] = tempNameIdx[i];
				address[i] = tempAddress[i];
						
			}
		}
		updateBank();
		
	}
	
	public void removeBank(String rmAddress) {
		boolean removeFlag = false;
		for (String singleAddress : address) {
			if (singleAddress.contentEquals(rmAddress)) {
				removeFlag = true;
			}
		}
		if (removeFlag) {
			String[] tempNameIdx = nameIdx;
			nameIdx = new String[nameIdx.length -1];
			String[] tempAddress = address;
			address = new String[address.length -1];
			boolean afterRemove = false;
			for (int i = 0; i < tempNameIdx.length; i++) {
				if (tempNameIdx[i].contentEquals(rmAddress)) {
					afterRemove = true;
				} else {
					if (afterRemove) {
						nameIdx[i-1] = tempNameIdx[i];
						address[i-1] = tempAddress[i];
					} else {
						nameIdx[i] = tempNameIdx[i];
						address[i] = tempAddress[i];
					}
				}
			}
			updateBank();
		} else {
			System.err.println("Selected Bank does not exist !");
		}
	}
	
	public String[] getAllBanks() {
		String[] returnArray = new String[nameIdx.length];
		for (int i = 0; i < nameIdx.length; i++) {
			returnArray[i] = address[i] + "," + nameIdx[i];
		}
		return returnArray;
		
	}
	
	private void updateBank() {
		bankRecordFile = new File(bankRecordFileName);
		if (bankRecordFile.exists()) {
			bankRecordFile.delete();
		} 
		try {
			bankRecordFile.createNewFile();
			BufferedWriter br = new BufferedWriter(new FileWriter(bankRecordFile));
			
			for (int i = 0; i < nameIdx.length; i++) {
				br.write(address[i] + "," + nameIdx[i] + "\n");
			}
			br.flush();
			br.close();
		} catch (IOException ioe) {
			System.out.println("Cannot update bank record file");
			ioe.printStackTrace();
		}
	}
	
	private void loadBank() {
		bankRecordFile = new File(bankRecordFileName);
		if (bankRecordFile.exists()) {
			try {
				FileInputStream fis = new FileInputStream(bankRecordFile);
				BufferedReader br = new BufferedReader(new InputStreamReader(fis));
				int lineCount  = 0;
				
				while (br.ready()) {
					String readLine = br.readLine();
					if (!readLine.contains(",")) {
						System.err.println("Bad bank record file");
						System.exit(1);
					}
					lineCount++;
				}
				br.close();
				fis.close();
				
				if (lineCount < 1) {
					System.out.println("There's no existing bank record");
					nameIdx = new String[0];
					address = new String[0];
				} else {
					fis = new FileInputStream(bankRecordFile);
					br = new BufferedReader(new InputStreamReader(fis));
					nameIdx = new String[lineCount];
					address = new String[lineCount];
					
					for (int i = 0; i < lineCount; i++) {
						String readLine = br.readLine();
						String[] element = readLine.split(",");
						address[i] = element[0];
						nameIdx[i] = element[1];
					}
					
				}
				
			} catch (IOException ioe) {
				System.err.println("Cannot read bank record file !");
				ioe.printStackTrace();
				System.exit(1);
			}
		} else {
			try {
				bankRecordFile.createNewFile();
				System.out.println("Bank record file does not exist.");
				System.out.println("Create new bank record file.");
			} catch (IOException ioe) {
				System.err.println("Cannot create bank record file");
				ioe.printStackTrace();
				System.exit(1);
			}
			nameIdx = new String[0];
			address = new String[0];
		}
	}
	
	
}
