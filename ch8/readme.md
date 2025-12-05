# ch8

## Compile Message.sol
./solc -o Message --bin --abi --evm-version=london ./Message.sol
## Generate Java Wrapper
java -jar ./bankAppUpdated/lib/web3j-cli-1.7.0-all.jar generate solidity --binFile=./Message/Message.bin --
abiFile=./Message/Message.abi --outputDir=./javaWrapper --package=ethSC
## Copy generated files to messageAppUpdated/src/ethSC
cp ./javaWrapper/ethSC/Message.java ./eventViewer/src/ethSC

## Implement eventViewer
## Compile
cd ./eventViewer
javac -d bin -cp "./lib/web3j-cli-1.7.0-all.jar" src/**/*.java
## Run with geth node running
java -cp "bin:./lib/web3j-cli-1.7.0-all.jar" eventViewer.EventViewer


## Implement chatRoom

## Compile

cd ./chatRoom
javac -d bin -cp "./lib/web3j-cli-1.7.0-all.jar" src/**/*.java

## Run with geth node running

java -cp "bin:./lib/web3j-cli-1.7.0-all.jar" chatRoom.ChatRoom
