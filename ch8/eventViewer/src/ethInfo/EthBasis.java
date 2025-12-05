package ethInfo;

// public class EthBasis {
// 	public static final long chainID = Long.parseLong("11330023");
// 	public static final String datastore = "/home/nycu/chain/";
// 	public static final String pipeLine = datastore + "geth.ipc";
// 	public static final String credential = datastore + "keystore/UTC--2025-11-13T13-39-44.590794049Z--02f396ad6ba37602441be148ac2876ff595e2aea";
// 	public static final String password = "nycu";
// }

public class EthBasis {
    // 1. Setting up Ethereum Basis

    public static final long chainID = Long.parseLong("11330023");
    public static final String datastore = "/home/user/workspace/nycu-blockchain-and-smart-contract/chain";
    // what is geth.ipc?
    // it is a file for inter-process communication (IPC) between the Ethereum client (Geth) and other applications.
    // It allows applications to interact with the Ethereum network through the Geth client.
    // to create geth.ipc, you need to run a Geth node with IPC enabled.
    // geth --datadir ./chain console
    public static final String pipeLine = "/home/user/workspace/nycu-blockchain-and-smart-contract/chain/geth.ipc";
    public static final String credential = "/home/user/workspace/nycu-blockchain-and-smart-contract/chain/keystore/UTC--2025-11-13T13-39-44.590794049Z--02f396ad6ba37602441be148ac2876ff595e2aea";
    public static final String password = "nycu";
}
