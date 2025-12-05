# ch7

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
