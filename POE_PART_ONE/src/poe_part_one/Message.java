/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe_part_one;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author RC_Student_Lab
 */
class Message {
    //Part 2
String messageID;
    String recipientCellNum;
    String messageText;
    String messageHash;
    static int totalMessages = 0;

    public Message(String recipient, String text) {
        recipientCellNum = recipient;
        messageText = text;
        messageID = createMessageID();
        messageHash = createMessageHash();
        totalMessages++;
    }

    public boolean checkMessageID() {
        return messageID.length() == 10;
    }

    public boolean checkRecipientCellNum() {
        return recipientCellNum.length() <= 10 &&
               (recipientCellNum.startsWith("+27") || recipientCellNum.startsWith("0"));
    }

   
    public String createMessageID() {
        Random rand = new Random();
        String ID = "";
        for (int i = 0; i < 10; i++) {
            ID += rand.nextInt(10); 
        }
        return ID;
    }

    public String createMessageHash() {
        String[] words = messageText.trim().split(" ");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        return messageID.substring(0, 2) + ":" + totalMessages + ":" + (firstWord + lastWord).toUpperCase();
    }

    public String sentMessage(String choice) {
        if (choice.equalsIgnoreCase("send")) {
            return "Message successfully sent.";
        } else if (choice.equalsIgnoreCase("discard")) {
            return "Press 0 to delete message.";
        } else if (choice.equalsIgnoreCase("store")) {
            return "Message successfully stored.";
        } else {
            return "Invalid choice. Please choose: Send, Store or Discard";
        }
    }

    public String printMessage() {
        return "Message ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipientCellNum +
               "\nMessage: " + messageText;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

   //JSON FORMAT
    public void storeMessage() {
        try {
            FileWriter writer = new FileWriter("messages.json", true);

            writer.write("{\n");
            writer.write("  \"messageID\": \"" + messageID + "\",\n");
            writer.write("  \"recipient Number \": \"" + recipientCellNum + "\",\n");
            writer.write("  \"message\": \"" + messageText + "\",\n");
            writer.write("  \"messageHash\": \"" + messageHash + "\"\n");
            writer.write("}\n");

            writer.close();
            System.out.println("Message stored successfully in messages.json");

        } catch (IOException e) {
            System.out.println("Error storing the message: " + e.getMessage());
        }
    }
}

