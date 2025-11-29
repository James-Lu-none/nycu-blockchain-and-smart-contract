package ethSC;

import org.web3j.crypto.Credentials;

public class MessageHandler {
	private Web3j web3;
	private String address;
	private Credentials cr;
	private TransactionManager trm;
	private ContractGasProvider cgp;
	private Message msg;

	public MessageHandler(String data, boolean mode) {
		// 1. Connect to Ethereum client
		web3 = Web3j.build(new UnixIpcService(EthBasis.pipeLine));
		try {
			cr = WalletUtils.loadCredentials(EthBasis.password, EthBasis.credential);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cgp = new DefaultGasProvider();
		trm = new RawTransactionManager(web3, cr, EthBasis.chainID);

		try {
			if (mode){
				System.err.println("Message Create Mode");
				createMsg(data);
			} else {
				System.err.println("Message Read Mode");
				loadMsg(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void createMsg(String sendMsg) throws Exception {
		System.err.println("Deploying Message contract...");
		msg = Message.deploy(web3, trm, cgp).send();
		address = msg.getContractAddress();
		System.out.println("Message contract deployed to: " + address);
		System.err.println("Writing message to contract...");
		msg.setMessage(sendMsg).send();
		System.out.println("Set message to: " + sendMsg);
	}
	private void loadMsg(String address) throws Exception {
		this.address = address;
		msg = Message.load(address, web3, trm, cgp);
	}
	public String getMessage() throws Exception {
        return msg.get().send();
    }
	public String getAddress() {
		return address;
	}
}
