package ethConn;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.ipc.UnixIpcService;

import ethInfo.EthBasis;

public class EthConn {
	private static Web3j web3j;
	
	public EthConn() {
		if (web3j == null) {
			web3j = Web3j.build(new UnixIpcService(EthBasis.pipeLine));
		}
	}
	
	public Web3j returnConn() {
		return web3j;
	}
}
