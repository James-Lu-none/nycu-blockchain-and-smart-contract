package eventViewer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.web3j.abi.EventValues;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.tx.Contract;

import ethConn.EventGetter;

public class EventViewer {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the target contract address");
        String targetedAddress = scanner.nextLine();
        System.out.println("Please enter which block you would like to start searching from.");
        System.out.println("Type -1 if you want to search from the start.");
        String startBlock = scanner.nextLine();
        System.out.println("Please enter which block you would like to end searching from.");
        System.out.println("Type -1 if you want to search until the end.");
        String endBlock = scanner.nextLine();

        EventGetter eventsGetter = new EventGetter(targetedAddress, Long.parseLong(startBlock), Long.parseLong(endBlock));
        List<Log> logs = eventsGetter.getLogs();
        int counter = 1;
        if (logs.size() == 0) {
            System.out.println("Target does not emit any events.");
            System.exit(0);
        }

        /* Event for Decode. */
        Event newMessageEvent = new Event(
                "NewMessage",
                Arrays.asList(
                        TypeReference.create(Address.class),
                        TypeReference.create(Utf8String.class),
                        TypeReference.create(Utf8String.class)
                )
        );

        for (Log log : logs) {
            System.out.println("Entry " + Integer.toString(counter));
            System.out.println("\t" + "Emit From: " + log.getAddress());
            System.out.println("\t" + "In Block: " + log.getBlockNumber().toString() + "(" + log.getBlockNumberRaw() + ")");
            System.out.println("\t\t" + "Block Hash: " + log.getBlockHash());
            System.out.println("\t" + "In Transaction: " + log.getTransactionIndex().toString() + "(" + log.getTransactionIndexRaw() + ")");
            System.out.println("\t\t" + "Transaction Hash: " + log.getTransactionHash());
            System.out.println("\t" + "Log Index: " + log.getLogIndex() + "(" + log.getLogIndexRaw() + ")");
            System.out.println("\t" + "Topics: ");
            for (String topic : log.getTopics()) {
                System.out.println("\t\t" + "Topic: " + topic);
            }
            System.out.println("\t" + "Data: " + log.getData());

            System.out.println("\tDecoded Data:");
            /* Decide the data with event decoder */
            EventValues eventValues = Contract.staticExtractEventParameters(newMessageEvent, log);

            if (eventValues == null) {
				String userAddress = "N/A";
				String userTitle = "N/A";
				String userMessage = "N/A";

                System.out.println("\t\t" + "User Address: " + userAddress);
                System.out.println("\t\t" + "Title: " + userTitle);
                System.out.println("\t\t" + "Message: " + userMessage);
                continue;
            }

            counter++;
            System.out.println();
        }

        scanner.close();
    }
}
