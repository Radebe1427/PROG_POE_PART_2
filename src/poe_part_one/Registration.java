/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe_part_one;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author RC_Student_Lab
 */
class Registration {
    
    // User credentials
String registeredUserName;
String registeredPhoneNumber;
String registeredUserPassword;
String userFirstName;
String userLastName;

    //  Security question 
String securityQuestion;
String securityAnswer;

// Part 3 arrays (declared at class level)
private final ArrayList<Message> sentMessages = new ArrayList<>();
private final ArrayList<Message> storedMessages = new ArrayList<>();
private final ArrayList<Message> disregardedMessages = new ArrayList<>();
private final ArrayList<String> messageIds = new ArrayList<>();
private final ArrayList<String> messageHashes = new ArrayList<>();
private String currentUser; // tracks who is logged in

    
        // Username validation
    public boolean userNameCheck(String Username) {
        if (Username.contains("_") && Username.length() <= 5) {
            JOptionPane.showMessageDialog(null, "Your Username has been successfully captured.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, """
                Error!!! Your Username is not correctly formatted.
                It should contain an underscore '_' and not be more than 5 characters long.""");
            return false;
        }
    }

    // Cellphone number validation
    public boolean userPhoneNumCheck(String cellphoneNumber) {
        return cellphoneNumber != null &&
               (cellphoneNumber.startsWith("0") || cellphoneNumber.startsWith("+")) &&
               cellphoneNumber.length() <= 10;
    }

    // Password validation
    public boolean userPasswordCheck(String Password) {
        if (Password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$")) {
            JOptionPane.showMessageDialog(null, "Your Password has been successfully captured.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, """
                Error!!! Your Password is not correctly formatted.
                It must contain at least 8 characters, one capital letter, one number, and one special character.
                Weak password. Please try again.""");
            return false;
        }
    }

    // Saving login info 
    public void saveLoginInfo() {
    JSONArray users = new JSONArray();

    // Loading existing users if file exists
    try (BufferedReader reader = new BufferedReader(new FileReader("userlogin.json"))) {
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        users = new JSONArray(json.toString());
    } catch (Exception ignored) {}

    // Creating new user details
    JSONObject newUser = new JSONObject();
    newUser.put("username", registeredUserName.trim());
    newUser.put("password", registeredUserPassword.trim());
    newUser.put("firstName", userFirstName.trim());
    newUser.put("lastName", userLastName.trim());
    newUser.put("securityQuestion", securityQuestion.trim());
    newUser.put("securityAnswer", securityAnswer.trim());

    users.put(newUser); // Add new user to the existing users list

    // Save updated list
    try (FileWriter writer = new FileWriter("userlogin.json")) {
        writer.write(users.toString(4));
    } catch (IOException e) {
        System.out.println("Error saving login info: " + e.getMessage());
    }
}


    public boolean loadLoginInfo(String enteredUsername) {
    try (BufferedReader reader = new BufferedReader(new FileReader("userlogin.json"))) {
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        JSONArray users = new JSONArray(json.toString());

        for (int i = 0; i < users.length(); i++) {
    JSONObject obj = users.getJSONObject(i);

    if (!obj.has("username")) continue; 

    String storedUsername = obj.getString("username").trim();
    if (storedUsername.equalsIgnoreCase(enteredUsername.trim())) {
        registeredUserName = storedUsername;
        registeredUserPassword = obj.getString("password").trim();
        userFirstName = obj.getString("firstName").trim();
        userLastName = obj.getString("lastName").trim();
        securityQuestion = obj.getString("securityQuestion").trim();
        securityAnswer = obj.getString("securityAnswer").trim();
        return true;
    }
}


    } catch (Exception e) {
        System.out.println("Error loading login info: " + e.getMessage());
        return false;
    }

    return false;
}

    
    public JSONObject getUserByUsername(String username) {
    try (BufferedReader reader = new BufferedReader(new FileReader("userlogin.json"))) {
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }

        JSONArray users = new JSONArray(json.toString());
        for (int i = 0; i < users.length(); i++) {
            JSONObject obj = users.getJSONObject(i);

            // Normalize both sides
            if (obj.getString("username").trim().equalsIgnoreCase(username.trim())) {
                return obj;
            }
        }
    } catch (Exception e) {
        // ignored
    }
    return null;
}

    
    
    public boolean signUp() {
        if (!userPersonalDetails()) return false;
        if (!Username()) return false;
        if (!userPhoneNumber()) return false;
        if (!userPassword()) return false;
        if (!userSecurityDetails()) return false;

        JOptionPane.showMessageDialog(null, "SignUp successful for " + userFirstName + " " + userLastName);
        saveLoginInfo();

        int another = JOptionPane.showConfirmDialog(null, "Would you like to register another user?", "Register Again", JOptionPane.YES_NO_OPTION);
        if (another == JOptionPane.YES_OPTION) {
        return signUp();
        }

        return true;
    }

    
    
    public boolean userPersonalDetails() {
        userFirstName = JOptionPane.showInputDialog("Enter your First Name:");
        if (userFirstName == null) return false;

        userLastName = JOptionPane.showInputDialog("Enter your Last Name:");
        if (userLastName == null) return false;

        return true;
    }
    
    public boolean Username() {
        String UserName = "";
        boolean validUsername = false;

        while (!validUsername) {
            UserName = JOptionPane.showInputDialog("""
                    ***REGISTRATION***
                    Enter your UserName:
                    (It should contain an underscore '_' and not be more than 5 characters long)""");

            if (UserName == null) return false;

            if (!UserName.isBlank() && userNameCheck(UserName)) {
            JSONObject existingUser = getUserByUsername(UserName);

                if (existingUser != null) {
                String existingFirst = existingUser.getString("firstName");
                String existingLast = existingUser.getString("lastName");

                    if (existingFirst.equalsIgnoreCase(userFirstName) &&
                    existingLast.equalsIgnoreCase(userLastName)) {

                    String[] options = {"Try Another Username", "Go to Sign In"};
                    int choice = JOptionPane.showOptionDialog(
                            null,
                            "This username already belongs to you.\nWould you like to Sign In instead?",
                            "Username Already Registered",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            options[0]);

                        if (choice == 1) return false; 
                            } else {
                                JOptionPane.showMessageDialog(null, "Username is already taken by another user.\nPlease choose a different one.");
                            }
                        } else {
                            validUsername = true;
                }
            }
        }

        registeredUserName = UserName;
        return true;
    }
    
    public boolean userPhoneNumber() {
        String UserPhoneNumber = "";
        boolean validPhone = false;

        while (!validPhone) {
            UserPhoneNumber = JOptionPane.showInputDialog("""
                ***REGISTRATION***
                Enter your CellPhone Number:
                (It should start with '+27' or '0' and be 10 digits long)""");

            if (UserPhoneNumber == null) return false;

            if (!UserPhoneNumber.isBlank()) {
            validPhone = userPhoneNumCheck(UserPhoneNumber);
                if (!validPhone) {
                JOptionPane.showMessageDialog(null, "Invalid phone number format. Please try again. \n Your Phone Number should start with '+27' or '0' and be 10 digits long)\"");
                }  
            }
        }

        registeredPhoneNumber = UserPhoneNumber;
        return true;
    }
    
    public boolean userPassword() {
    String UserPassword = "";
    boolean validPassword = false;

    while (!validPassword) {
        UserPassword = JOptionPane.showInputDialog("""
            ***REGISTRATION***
            Enter your Password:
            (At least 8 characters, one capital letter, one number, and one special character)""");

        if (UserPassword == null) return false;

        if (!UserPassword.isBlank()) {
            validPassword = userPasswordCheck(UserPassword);
        }
        }

        registeredUserPassword = UserPassword;
        return true;
    }
    
    public boolean userSecurityDetails() {
        securityQuestion = JOptionPane.showInputDialog("Set your security question (e.g., What is your favorite color?):");
        if (securityQuestion == null || securityQuestion.isBlank()) return false;

            securityAnswer = JOptionPane.showInputDialog("Enter the answer to your security question:");
        if (securityAnswer == null || securityAnswer.isBlank()) return false;

        return true;
    } 
    
    // Declaring a shared password field at class level
private JPasswordField passwordField;

public void signIn() {
    String enteredUsername = promptUsername();
    if (enteredUsername == null) return;

    
    boolean userFound = loadLoginInfo(enteredUsername.trim());

    int choice = promptPasswordOptions();
    if (choice == 2 || choice == JOptionPane.CLOSED_OPTION) return;

    String enteredPassword = getEnteredPassword().trim();

//    // Debugging output
//    System.out.println("Entered username: [" + enteredUsername.trim() + "]");
//    System.out.println("Saved username:   [" + registeredUserName + "]");
//    System.out.println("Entered password: [" + enteredPassword + "]");
//    System.out.println("Saved password:   [" + registeredUserPassword + "]");

    if (choice == 1) {
        handleForgotPassword(userFound);
        return;
    }

    handleLogin(userFound, enteredPassword, enteredUsername);
}

public String promptUsername() {
    return JOptionPane.showInputDialog("***LOGIN***\nEnter your Username:");
}

public int promptPasswordOptions() {
    passwordField = new JPasswordField(); 
    Object[] message = { "Enter your Password:", passwordField };
    String[] options = {"Login", "Forgot Password", "Cancel"};

    return JOptionPane.showOptionDialog(null, message, "Password Required",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
}

public String getEnteredPassword() {
    return new String(passwordField.getPassword()); // read from same field
}


public void handleForgotPassword(boolean userFound) {
    if (userFound) {
        String answer = JOptionPane.showInputDialog("Security Check:\n" + securityQuestion);
        if (answer != null && answer.trim().equalsIgnoreCase(securityAnswer)) {
            JOptionPane.showMessageDialog(null, "Your saved password is: " + registeredUserPassword);
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect answer to the security question.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "No saved password found for this username.");
    }
}

      public void handleLogin(boolean userFound, String enteredPassword, String enteredUsername) {
    if (userFound) {
        if (enteredPassword.equals(registeredUserPassword)) {
            
            currentUser = enteredUsername;

            JOptionPane.showMessageDialog(null,
                "Welcome back " + currentUser +
                "\nYou're now signed in to QuickChat.\nLet's get started!");

            // Loading messages for this user
            loadAllMessages(currentUser);

            // Launching QuickChat
            launchQuickChat();
        } else {
            JOptionPane.showMessageDialog(null,
                "Login failed: Your password is incorrect.");
        }
    } else {
        JOptionPane.showMessageDialog(null,
            "Invalid Username, you must Sign Up before you can Sign In.");
    }
}

   public void logout() {
    int choice = JOptionPane.showConfirmDialog(
        null,
        "Do you want to save your login details for next time?",
        "Logout Options",
        JOptionPane.YES_NO_OPTION);

    if (choice == JOptionPane.NO_OPTION) {
        if (currentUser != null) {
            saveAllMessages(currentUser);
        }
        clearLoginFile();
        JOptionPane.showMessageDialog(null,
            "You have been logged out and your login details were removed.");
    } else {
        if (currentUser != null) {
            saveAllMessages(currentUser);
        }
        JOptionPane.showMessageDialog(null,
            "You have been logged out. Your login details are saved for next time.");
    }

    currentUser = null;
}


    public void clearLoginFile() {
        File file = new File("userlogin.json");
        
        saveAllMessages(currentUser);
        
        if (file.exists() && file.delete()) {
            JOptionPane.showMessageDialog(null, "You have been logged out and your login details were removed.");
        }
    }
    
    public void launchQuickChat() {
    boolean running = true;
    while (running) {
        int choice = showQuickChatMenu();
        switch (choice) {
            case 0 -> sendMessages(sentMessages);              // Send Messages
            case 1 -> showSentMessages(sentMessages);          // Show Sent Messages
            case 2 -> showDisregardedMessages(disregardedMessages); // Show Disregarded Messages
            case 3 -> showStoredMessages(storedMessages);      // Show Stored Messages
            case 4 -> showLongestSentMessage(sentMessages);    // Show Longest Sent Message
            case 5 -> searchByMessageID(sentMessages);         // Search by Message ID
            case 6 -> searchByRecipient(sentMessages);         // Search by Recipient
            case 7 -> deleteByHash(storedMessages);            // Delete by Hash
            case 8 -> displayReport(sentMessages);             // Display Report
            case 9 -> logout();                                // Logout
            case 10 -> {
                running = false;
                JOptionPane.showMessageDialog(null, "Goodbye!!!");
            }
            default -> JOptionPane.showMessageDialog(null, "Invalid option. Please choose again.");
        }
    }
}
    
    public int showQuickChatMenu() {
    String[] options = {
        "Send Messages",
        "Show Sent Messages",
        "Show Disregarded Messages",
        "Show Stored Messages",
        "Show Longest Sent Message",
        "Search by Message ID",
        "Search by Recipient",
        "Delete by Hash",
        "Display Report",
        "Logout",
        "Exit"
    };

    String choice = (String) JOptionPane.showInputDialog(
        null,
        "Choose an Option:",
        "QuickChat Menu",
        JOptionPane.PLAIN_MESSAGE,
        null,
        options,
        options[0]   
    );

    if (choice == null) return -1; 
    
    for (int i = 0; i < options.length; i++) {
        if (options[i].equals(choice)) return i;
    }
    return -1;
}
    
    public void sendMessages(ArrayList<Message> sentMessages1) {
    int numMessages = Integer.parseInt(
        JOptionPane.showInputDialog("How many messages would you like to send?")
    );

    for (int i = 0; i < numMessages; i++) {
        String recipient = captureRecipient();
        String messageText = captureMessageText();

        // Creating and storing the message
        Message msg = new Message(recipient, messageText, "Sent");

        sentMessages.add(msg);

        handleMessageAction(msg); 
    }

    JOptionPane.showMessageDialog(null,
        "✅ Total messages sent: " + Message.returnTotalMessages() +
        "\nMessages stored in memory: " + sentMessages.size());
}

    public String captureRecipient() {
        String recipient = "";
           boolean validRecipient = false;

        while (!validRecipient) {
            recipient = JOptionPane.showInputDialog("Enter recipient number:, It should begin with 0/+");
            Message temp = new Message(recipient, "", "Sent");
            if (temp.checkRecipientCellNum()) {
                validRecipient = true;
            } else {
                JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted. Please try again.");
            }
        }
        return recipient;
    }

    public String captureMessageText() {
        String messageText = "";
        boolean validMessage = false;

        while (!validMessage) {
            messageText = JOptionPane.showInputDialog("""
                Enter your message:
                NB: Please ensure your message doesn't exceed 250 characters in length.""");

            if (messageText.length() <= 250) {
                validMessage = true;
            } else {
                JOptionPane.showMessageDialog(null, "Error, Message exceeds 250 characters by " + (messageText.length() - 250));
            }
        }
        return messageText;
    }
    
    
    public void handleMessageAction(Message msg) {
    String action = JOptionPane.showInputDialog("Choose: Send / Store / Discard");

    if (action == null || action.isBlank()) {
        JOptionPane.showMessageDialog(null, "No action selected.");
        return;
    }

    String result = msg.sentMessage(action);
    JOptionPane.showMessageDialog(null, result);

    switch (action.toLowerCase()) {
        case "send" -> {
            msg.setFlag("Sent");
            if (!sentMessages.contains(msg)) {
                sentMessages.add(msg);
                messageIds.add(msg.getMessageID());
                messageHashes.add(msg.getMessageHash());
            }
            JOptionPane.showMessageDialog(null, msg.printMessage());
        }

        case "store" -> {
            msg.setFlag("Stored");
            if (!storedMessages.contains(msg)) {
                storedMessages.add(msg);
                messageIds.add(msg.getMessageID());
                messageHashes.add(msg.getMessageHash());
            }
        }

        case "discard" -> {
            msg.setFlag("Disregard");
            if (!disregardedMessages.contains(msg)) {
                disregardedMessages.add(msg);
                messageIds.add(msg.getMessageID());
                messageHashes.add(msg.getMessageHash());
            }
            JOptionPane.showMessageDialog(null, "Message disregarded.");
        }

        default -> JOptionPane.showMessageDialog(null, "Invalid choice. Please choose Send, Store, or Discard.");
    }
}


    public void showSentMessages(ArrayList<Message> sentMessages) {
            if (sentMessages.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No messages have been sent yet.");
            } else {
                StringBuilder allMessages = new StringBuilder("Sent Messages:\n");
            for (Message msg : sentMessages) {
                allMessages.append(msg.printMessage()).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, allMessages.toString());
        }
    }
    
    // Show disregarded messages
public void showDisregardedMessages(ArrayList<Message> disregardedMessages) {
    if (disregardedMessages.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No messages have been disregarded yet.");
    } else {
        StringBuilder sb = new StringBuilder("Disregarded Messages:\n");
        for (Message m : disregardedMessages) sb.append(m.printMessage()).append("\n\n");
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}

// Show stored messages
public void showStoredMessages(ArrayList<Message> storedMessages) {
    if (storedMessages.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No messages have been stored yet.");
    } else {
        StringBuilder sb = new StringBuilder("Stored Messages:\n");
        for (Message m : storedMessages) sb.append(m.printMessage()).append("\n\n");
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}

// Show longest sent message
public void showLongestSentMessage(ArrayList<Message> sentMessages) {
    if (sentMessages.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No sent messages yet.");
        return;
    }
    Message longest = sentMessages.stream()
        .max(Comparator.comparingInt(m -> m.getMessageText().length()))
        .orElse(null);
    if (longest != null) {
        JOptionPane.showMessageDialog(null, "Longest Sent Message:\n" + longest.getMessageText());
    }
}

// Search by Message ID
public void searchByMessageID(ArrayList<Message> allMessages) {
    String id = JOptionPane.showInputDialog("Enter Message ID to search:");
    for (Message m : allMessages) {
        if (m.getMessageID().equals(id)) {
            JOptionPane.showMessageDialog(null, "Recipient: " + m.getRecipientCellNum() +
                "\nMessage: " + m.getMessageText());
            return;
        }
    }
    JOptionPane.showMessageDialog(null, "Message ID not found.");
}

// Search by Recipient
public void searchByRecipient(ArrayList<Message> allMessages) {
    String recipient = JOptionPane.showInputDialog("Enter recipient number to search:");
    StringBuilder sb = new StringBuilder("Messages for " + recipient + ":\n");
    for (Message m : allMessages) {
        if (m.getRecipientCellNum().equals(recipient)) {
            sb.append(m.getMessageText()).append("\n");
        }
    }
    JOptionPane.showMessageDialog(null, sb.toString());
}

// Delete by Hash
public void deleteByHash(ArrayList<Message> storedMessages) {
    String hash = JOptionPane.showInputDialog("Enter Message Hash to delete:");
    Iterator<Message> it = storedMessages.iterator();
    while (it.hasNext()) {
        Message m = it.next();
        if (m.getMessageHash().equals(hash)) {
            it.remove();
            JOptionPane.showMessageDialog(null, "Message \"" + m.getMessageText() + "\" successfully deleted.");
            return;
        }
    }
    JOptionPane.showMessageDialog(null, "Message hash not found.");
}

// Display report

    public void displayReport(ArrayList<Message> sentMessages1) {
    // Build summary text
    String summary = """
                     QuickChat Report
                     
                     Total Messages: """ + Message.returnTotalMessages() + "\n" +
            "Sent: " + Message.returnSentMessages() + "\n" +
            "Stored: " + Message.returnStoredMessages() + "\n" +
            "Discarded: " + Message.returnDiscardedMessages() + "\n";

    // Sent messages
    JTextArea sentArea = new JTextArea();
    sentArea.setEditable(false);
    sentArea.setLineWrap(true);
    sentArea.setWrapStyleWord(true);
    for (Message m : sentMessages) {
        sentArea.append(m.printMessage() + "\n\n");
    }

    // Stored messages
    JTextArea storedArea = new JTextArea();
    storedArea.setEditable(false);
    storedArea.setLineWrap(true);
    storedArea.setWrapStyleWord(true);
    for (Message m : storedMessages) {
        storedArea.append(m.printMessage() + "\n\n");
    }

    // Discarded messages
    JTextArea discardedArea = new JTextArea();
    discardedArea.setEditable(false);
    discardedArea.setLineWrap(true);
    discardedArea.setWrapStyleWord(true);
    for (Message m : disregardedMessages) {
        discardedArea.append(m.printMessage() + "\n\n");
    }

    // Wrapping each section in scroll panes
    JScrollPane sentScroll = new JScrollPane(sentArea);
    JScrollPane storedScroll = new JScrollPane(storedArea);
    JScrollPane discardedScroll = new JScrollPane(discardedArea);

    // Tabbed pane for collapsible sections
    JTabbedPane tabs = new JTabbedPane();
    tabs.addTab("Sent Messages", sentScroll);
    tabs.addTab("Stored Messages", storedScroll);
    tabs.addTab("Discarded Messages", discardedScroll);

    // Putting summary + tabs together in a panel
    JTextArea summaryArea = new JTextArea(summary);
    summaryArea.setEditable(false);
    summaryArea.setLineWrap(true);
    summaryArea.setWrapStyleWord(true);

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new JScrollPane(summaryArea), BorderLayout.NORTH);
    panel.add(tabs, BorderLayout.CENTER);

    // Wrap whole panel in a scrollable dialog
    JScrollPane mainScroll = new JScrollPane(panel);
    mainScroll.setPreferredSize(new Dimension(600, 500)); // adjust size

    JOptionPane.showMessageDialog(
        null,
        mainScroll,
        "QuickChat Report",
        JOptionPane.INFORMATION_MESSAGE
    );
}
    
    public void saveAllMessages(String currentUser1) {
    JSONArray arr = new JSONArray();

    for (Message m : sentMessages) {
        JSONObject obj = new JSONObject();
        obj.put("messageID", m.getMessageID());
        obj.put("recipient", m.getRecipientCellNum());
        obj.put("message", m.getMessageText());
        obj.put("messageHash", m.getMessageHash());
        obj.put("flag", m.getFlag());
        arr.put(obj);
    }
    for (Message m : storedMessages) {
        JSONObject obj = new JSONObject();
        obj.put("messageID", m.getMessageID());
        obj.put("recipient", m.getRecipientCellNum());
        obj.put("message", m.getMessageText());
        obj.put("messageHash", m.getMessageHash());
        obj.put("flag", m.getFlag());
        arr.put(obj);
    }
    for (Message m : disregardedMessages) {
        JSONObject obj = new JSONObject();
        obj.put("messageID", m.getMessageID());
        obj.put("recipient", m.getRecipientCellNum());
        obj.put("message", m.getMessageText());
        obj.put("messageHash", m.getMessageHash());
        obj.put("flag", m.getFlag());
        arr.put(obj);
    }

    try (FileWriter writer = new FileWriter(currentUser1 + "_messages.json", false)) {
    writer.write(arr.toString(2));
    JOptionPane.showMessageDialog(null,"All messages saved successfully for " + currentUser1);
    }catch (IOException e) {
        JOptionPane.showMessageDialog(null,"Error saving messages: " + e.getMessage());
    }
}

    
    public void loadAllMessages(String username) {
    File file = new File(username + "_messages.json");

    if (!file.exists()) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("[]"); // empty JSON array
            JOptionPane.showMessageDialog(null,
                "🎉 Welcome " + username + "!\nThis is your first time using QuickChat.\nYour message history starts fresh.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "Error creating new file for " + username + ": " + e.getMessage());
        }
        return;
    }

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) json.append(line);

        JSONArray arr = new JSONArray(json.toString());

        sentMessages.clear();
        storedMessages.clear();
        disregardedMessages.clear();

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            Message m = new Message(
                obj.getString("recipient"),
                obj.getString("message"),
                obj.getString("flag")
            );
            m.setCustomMessageID(obj.getString("messageID"));
            m.setFlag(obj.getString("flag"));

            switch (m.getFlag()) {
                case "Sent" -> sentMessages.add(m);
                case "Stored" -> storedMessages.add(m);
                case "Disregard" -> disregardedMessages.add(m);
            }
        }

        JOptionPane.showMessageDialog(null,
            "Messages loaded successfully for " + username + ".");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null,
            "Error loading messages for " + username + ": " + e.getMessage());
        }
    }

}