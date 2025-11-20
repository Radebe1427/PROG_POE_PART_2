/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe_part_one;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author RC_Student_Lab
 */

    import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Objects;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;

public class Message {

    // Fields
    private String messageID;
    private String recipientCellNum;
    private String messageText;
    private String messageHash;
    private String flag; // "Sent", "Stored", "Disregard"

    // Counters 
    private static int sentCount = 0;
    private static int storedCount = 0;
    private static int discardedCount = 0;

    // Constructor
    public Message(String recipient, String text, String flag) {
        this.recipientCellNum = recipient;
        this.messageText = text;
        this.flag = (flag == null || flag.isBlank()) ? "Pending" : flag;
        this.messageID = createMessageID();
        this.messageHash = createMessageHash();
    }

    // Validation
    public boolean checkMessageID() {
        return messageID != null && messageID.length() == 10 && messageID.chars().allMatch(Character::isDigit);
    }

    public boolean checkRecipientCellNum() {
        if (recipientCellNum == null || recipientCellNum.isBlank()) return false;
        String r = recipientCellNum.trim();
        boolean plus27 = r.startsWith("+27") && r.length() >= 12 && r.substring(3).chars().allMatch(Character::isDigit);
        boolean zeroLocal = r.startsWith("0") && r.length() >= 10 && r.substring(1).chars().allMatch(Character::isDigit);
        return plus27 || zeroLocal;
    }

    // ID and Hash
    private String createMessageID() {
        Random rand = new Random();
        StringBuilder ID = new StringBuilder(10);
        for (int i = 0; i < 10; i++) {
            ID.append(rand.nextInt(10));
        }
        return ID.toString();
    }

    private String createMessageHash() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String base = messageID + "|" + recipientCellNum + "|" + messageText + "|" + flag;
            byte[] bytes = md.digest(base.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            return messageID; // fallback
        }
    }

    // Status text
    public String sentMessage(String choice) {
        if (choice.equalsIgnoreCase("send")) {
            sentCount++;
            flag = "Sent";
            return "Message successfully sent.";
        } else if (choice.equalsIgnoreCase("discard")) {
            discardedCount++;
            flag = "Disregard";
            return "Message disregarded.";
        } else if (choice.equalsIgnoreCase("store")) {
            storedCount++;
            flag = "Stored";
            return "Message successfully stored.";
        } else {
            return "Invalid choice. Please choose: Send, Store or Discard";
        }
    }

    // Print message
    public String printMessage() {
        return "Message ID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipientCellNum +
                "\nMessage: " + messageText +
                "\nFlag: " + flag;
    }

    // Counters
    public static int returnTotalMessages() { return sentCount + storedCount + discardedCount; }
    public static int returnSentMessages() { return sentCount; }
    public static int returnStoredMessages() { return storedCount; }
    public static int returnDiscardedMessages() { return discardedCount; }

    // JSON conversion 
    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        obj.put("messageID", messageID);
        obj.put("recipient", recipientCellNum);
        obj.put("message", messageText);
        obj.put("messageHash", messageHash);
        obj.put("flag", flag);
        return obj;
    }

    public static Message fromJSON(JSONObject obj) {
        Message m = new Message(
            obj.getString("recipient"),
            obj.getString("message"),
            obj.getString("flag")
        );
        m.setCustomMessageID(obj.getString("messageID"));
        m.messageHash = obj.getString("messageHash"); 
        return m;
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getRecipientCellNum() { return recipientCellNum; }
    public String getMessageText() { return messageText; }
    public String getMessageHash() { return messageHash; }
    public String getFlag() { return flag; }

    // Setters
    public void setFlag(String flag) {
        this.flag = flag;
        this.messageHash = createMessageHash();
    }

    public void setCustomMessageID(String id) {
        if (id != null && id.length() == 10 && id.chars().allMatch(Character::isDigit)) {
            this.messageID = id;
        } else {
            this.messageID = createMessageID();
        }
        this.messageHash = createMessageHash();
    }

    // Equality
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Message other = (Message) obj;
        return Objects.equals(messageID, other.messageID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageID);
    }
}