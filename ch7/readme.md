# ch7
admin.nodeInfo.enode
admin.addPeer("enode://e9771451724c9fdf6f45277e6c1222a2b4301e4e7c12ae7efc5cedacc33a51136f6f227bb894787c9848d543d177e1ff7bb19467866f8ce8df03add9a67c6c84@123.0.225.253:30303")

## init with genesis.json
./geth/geth --datadir ./chain init ./chain/genesis.json
## connect to chain
./geth/geth  --datadir ./chain --networkid 11330023 --http --http.addr 127.0.0.1 --http.port 8545 --http.api eth,web3,net,personal --http.corsdomain=https://remix.ethereum.org --allow-insecure-unlock console

## Compile Message.sol
./solc -o Message --bin --abi --evm-version=london ./Message.sol
## Generate Java Wrapper
java -jar ./bankAppUpdated/lib/web3j-cli-1.7.0-all.jar generate solidity --binFile=./Message/Message.bin --
abiFile=./Message/Message.abi --outputDir=./javaWrapper --package=ethSC
## Copy generated files to messageAppUpdated/src/ethSC
cp ./javaWrapper/ethSC/Message.java ./messageAppUpdated/src/ethSC

## Implement MessageHandler
## Compile
cd ./messageAppUpdated
javac -d bin -cp "/home/user/workspace/temp/bankAppUpdated/lib/web3j-cli-1.7.0-all.jar" src/**/*.java
## Run with geth node running
java -cp "bin:/home/user/workspace/temp/bankAppUpdated/lib/web3j-cli-1.7.0-all.jar" messageApp.Messenger


# bank
./solc -o bank --bin --abi --evm-version=london ./bank.sol
