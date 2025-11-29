package ethInfo;

public class EthAccount {
	private static final String[] name = {
			"NYCU"
	};
	
	private static final String[] keyFile = {
			"UTC--2025-11-13T13-39-44.590794049Z--02f396ad6ba37602441be148ac2876ff595e2aea"
	};
	
	private static final String[] password = {
			"nycu"
	};
	
	public int getTotalCount() {
		return name.length;
	}
	
	public String[] getAccount(int index) {
		int correctSize = name.length;
		if (index >= correctSize) {
			System.err.println("You did not have that much account in the system");
			System.exit(1);
		}
		String[] returnArray = new String[4];
		returnArray[0] = name[index];
		returnArray[1] = EthBasis.keystore + keyFile[index];
		returnArray[2] = "0x" + (keyFile[index]).substring(37, (keyFile[index]).length());
		returnArray[3] = password[index];
		return returnArray;
	}

}
