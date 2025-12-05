package ethInfo;

public class EthBasis {
    // 1. Setting up Ethereum Basis

    public static long chainID = Long.parseLong("11330023");
    public static String datastore = "/home/user/workspace/nycu-blockchain-and-smart-contract/chain";
    // what is geth.ipc?
    // it is a file for inter-process communication (IPC) between the Ethereum client (Geth) and other applications.
    // It allows applications to interact with the Ethereum network through the Geth client.
    // to create geth.ipc, you need to run a Geth node with IPC enabled.
    // geth --datadir ./chain console
    public static String pipeLine = "/home/user/workspace/nycu-blockchain-and-smart-contract/chain/geth.ipc";
    public static String credential = "/home/user/workspace/nycu-blockchain-and-smart-contract/chain/keystore";
    public static String password = "nycu";
}
