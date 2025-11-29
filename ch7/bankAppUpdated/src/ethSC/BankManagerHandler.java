package ethSC;

import java.math.BigInteger;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.ipc.UnixIpcService;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;

import ethInfo.EthAccount;
import ethInfo.EthBasis;

public class BankManagerHandler {
	// 3. BankManager Handler Function
	private static Web3j web3;
	private static Credentials cr;
	private static TransactionManager trm;
	private static ContractGasProvider cgp;
	private static Bank back;
	private static int accIdx;
	private static String address = "";

	public BankManagerHandler(String toAddress, int nameIdx) {
		accIdx = nameIdx;
		EthAccount eta = new EthAccount(accIdx);
		String[] accountData = eta.getAccount(nameIdx);
		web3j = Web3j.build(new UnixIpcService(EthBasis.pipeLine));
		try {
			cr = WalletUtils.loadCredentials(accountData[3], accountData[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		cgp = new DefaultGasProvider();
		trm = new RawTransactionManager(web3j, cr, EthBasis.chainID);
		address = toAddress;
		back = Bank.load(address, web3j, trm, cgp);
	}
	public double getLocalBalance() throws Exception {
		EthAccocunt eta = new EthAccount(accIdx);
		String[] accountInfo = eta.getAccount(accIdx);

		EthGetBalance ethGetBalance = web3j.ethGetBalance(
			accountInfo[2], 
			org.web3j.protocol.core.DefaultBlockParameterName.LATEST
			).sendAsync().get();
		BigInteger wei = ethGetBalance.getBalance();
		return wei.doubleValue() / 1e18;
	}
	public double getContractBalance() throws Exception {
		BigInteger wei = back.getBalance().send();
		return wei.doubleValue() / 1e18;
	}
	public void deposit(int ethAmount) throws Exception {
		EthAccount eta = new EthAccount();
		Admin admin = Admin.build(new UnixIpcService(EthBasis.pipeLine));
		admin.personalUnlockAccount(cr.getAddress(), eta.getAccount(accIdx)[3]).send();
		String encodedFunction = back.deposit().encodedFunctionCall();

		BigInteger wei = BigInteger.valueOf(ethAmount);
		wei = wei.multiply(BigInteger.valueOf(1000000000000000000L));
	}
}
