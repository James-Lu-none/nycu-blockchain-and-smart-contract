package ethConn;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.EthLog.LogResult;

import org.web3j.protocol.core.methods.response.Log;	

public class EventGetter {

    private EthConn ethConn;
    private String toListenAddress = "";
    private BigInteger startBlock;
    private BigInteger endBlock;

    public EventGetter() {
        ethConn = new EthConn();
    }

    public EventGetter(String newToListenAddress, Long newStartBlock, Long newEndBlock) {
        ethConn = new EthConn();
        setToListenAddress(newToListenAddress);
        setStartBlock(newStartBlock);
        setEndBlock(newEndBlock);
    }

    public void setToListenAddress(String newToListenAddress) {
        this.toListenAddress = newToListenAddress;
    }

    public void setStartBlock(Long newStartBlock) {
        this.startBlock = BigInteger.valueOf(newStartBlock);
    }

    public void setEndBlock(Long newEndBlock) {
        this.endBlock = BigInteger.valueOf(newEndBlock);
    }

    public List<Log> getLogs() {
		Event newMessageEvent = new Event(
			"NewMessage",
			Arrays.asList(
				TypeReference.create(Address.class),
				TypeReference.create(Utf8String.class),
				TypeReference.create(Utf8String.class)
			)
		);
        EthFilter logFilter = new EthFilter(
                (this.startBlock == BigInteger.valueOf(-1)) ? DefaultBlockParameterName.EARLIEST : new DefaultBlockParameterNumber(this.startBlock),
                (this.endBlock == BigInteger.valueOf(-1)) ? DefaultBlockParameterName.LATEST : new DefaultBlockParameterNumber(this.endBlock),
                this.toListenAddress
        );

		logFilter.addSingleTopic(EventEncoder.encode(newMessageEvent));
        try {
            EthLog ethLog = this.ethConn.returnConn().ethGetLogs(logFilter).send();
            List<Log> logs = new ArrayList<>();
            for (LogResult logResult : ethLog.getLogs()) {
                Object logObject = logResult.get();
                if (logObject instanceof Log) {
                    logs.add((Log) logObject);
                }
            }
            return logs;
        } catch (Exception e) {
            e.printStackTrace();
			return null;
        }
    }
}
