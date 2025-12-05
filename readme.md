# connect to the existing blockchain

admin.nodeInfo.enode
admin.addPeer("enode://e9771451724c9fdf6f45277e6c1222a2b4301e4e7c12ae7efc5cedacc33a51136f6f227bb894787c9848d543d177e1ff7bb19467866f8ce8df03add9a67c6c84@123.0.225.253:30303")

## init with genesis.json

./geth/geth --datadir ./chain init ./chain/genesis.json

## connect to chain

./geth/geth  --datadir ./chain --networkid 11330023 --http --http.addr 127.0.0.1 --http.port 8545 --http.api eth,web3,net,personal --http.corsdomain=https://remix.ethereum.org --allow-insecure-unlock console
