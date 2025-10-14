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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
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

    // Save login info 
    public void saveLoginInfo() {
    JSONArray users = new JSONArray();

    // Load existing users if file exists
    try (BufferedReader reader = new BufferedReader(new FileReader("userlogin.json"))) {
        StringBuilder json = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            json.append(line);
        }
        users = new JSONArray(json.toString());
    } catch (Exception ignored) {}

    // Create new user Details
    JSONObject newUser = new JSONObject();
    newUser.put("username", registeredUserName);
    newUser.put("password", registeredUserPassword);
    newUser.put("firstName", userFirstName);
    newUser.put("lastName", userLastName);
    newUser.put("securityQuestion", securityQuestion);
    newUser.put("securityAnswer", securityAnswer);

    users.put(newUser); // Add new user to the existing users list

    // Save updated list
    try (FileWriter writer = new FileWriter("userlogin.json")) {
        writer.write(users.toString(4));
    } catch (IOException e) {
        System.out.println("Error saving login info: " + e.getMessage());
    }
}


    // Load login info from file if it exists
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
            if (obj.getString("username").equals(enteredUsername)) {
                registeredUserName = obj.getString("username");
                registeredUserPassword = obj.getString("password");
                userFirstName = obj.getString("firstName");
                userLastName = obj.getString("lastName");
                securityQuestion = obj.getString("securityQuestion");
                securityAnswer = obj.getString("securityAnswer");
                return true;
            }
        }
    } catch (Exception e) {
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
            if (obj.getString("username").equalsIgnoreCase(username)) {
                return obj;
            }
        }
    } catch (Exception e) {
        
    }
    return null;
}



    // SignUp user
    public boolean signUp() {
    userFirstName = JOptionPane.showInputDialog("Enter your First Name:");
    if (userFirstName == null) return false;

    userLastName = JOptionPane.showInputDialog("Enter your Last Name:");
    if (userLastName == null) return false;
    
    //Check if username exists

    String UserName = "";
    boolean validUsername = false;

    while (!validUsername) {
            UserName = JOptionPane.showInputDialog("""
                        ***REGISTRATION***
                        Enter your UserName:
                        (It should contain an underscore '_' and not be more than 5 characters long)""");

    if (UserName == null) return false;

    if (!UserName.isBlank()) {
        if (userNameCheck(UserName)) {
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

                    if (choice == 1) return false; // Go to Sign In
                } else {
                    JOptionPane.showMessageDialog(null, "Username is already taken by another user.\nPlease choose a different one.");
                }
            } else {
                validUsername = true;
            }
        }
    }
}


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
                JOptionPane.showMessageDialog(null, "Invalid phone number format. Please try again.");
            }
        }
    }

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

    securityQuestion = JOptionPane.showInputDialog("Set your security question (e.g., What is your favorite color?):");
    if (securityQuestion == null || securityQuestion.isBlank()) return false;

    securityAnswer = JOptionPane.showInputDialog("Enter the answer to your security question:");
    if (securityAnswer == null || securityAnswer.isBlank()) return false;

    registeredUserName = UserName;
    registeredPhoneNumber = UserPhoneNumber;
    registeredUserPassword = UserPassword;

    JOptionPane.showMessageDialog(null, "SignUp successful for " + userFirstName + " " + userLastName);
    saveLoginInfo();

    int another = JOptionPane.showConfirmDialog(null, "Would you like to register another user?", "Register Again", JOptionPane.YES_NO_OPTION);
    if (another == JOptionPane.YES_OPTION) {
        return signUp();
    }

    return true;
}


    //  SignIn existing user
public void signIn() {
    String enteredUsername = JOptionPane.showInputDialog("***LOGIN***\nEnter your Username:");
    if (enteredUsername == null) return;

    boolean userFound = loadLoginInfo(enteredUsername);

    JPasswordField passwordField = new JPasswordField();
    Object[] message = { "Enter your Password:", passwordField };
    String[] options = {"Login", "Forgot Password", "Cancel"};

    int choice = JOptionPane.showOptionDialog(null, message, "Password Required",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

    if (choice == 2 || choice == JOptionPane.CLOSED_OPTION) return;

    String enteredPassword = new String(passwordField.getPassword());

    if (choice == 1) {
        if (userFound) {
            String answer = JOptionPane.showInputDialog("Security Check:\n" + securityQuestion);
            if (answer != null && answer.equalsIgnoreCase(securityAnswer)) {
                JOptionPane.showMessageDialog(null, "Your saved password is: " + registeredUserPassword);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrect answer to the security question.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No saved password found for this username.");
        }
        return;
    }

    if (userFound) {
        if (enteredPassword.equals(registeredUserPassword)) {
            JOptionPane.showMessageDialog(null, "Welcome back " + userFirstName + " " + userLastName +
                    "\nYou're now signed in to QuickChat.\nLet's get started!");
            launchQuickChat();
        } else {
            JOptionPane.showMessageDialog(null, "Login failed: Your password is incorrect.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Invalid Username, you must Sign Up before you can Sign In.");
    }
}


    
       public void logout() {
    int choice = JOptionPane.showConfirmDialog(
            null,
            "Do you want to save your login details for next time?",
            "Logout Options",
            JOptionPane.YES_NO_OPTION);

    if (choice == JOptionPane.NO_OPTION) {
        File file = new File("userlogin.json");
        if (file.exists() && file.delete()) {
            JOptionPane.showMessageDialog(null, "You have been logged out and your login details were removed.");
        
    } else {
        JOptionPane.showMessageDialog(null, "You have been logged out. Your login details are saved for next time.");
  }
    } 
}      
       
       //Part 2
       public void launchQuickChat() {
    boolean running = true;
    ArrayList<Message> sentMessages = new ArrayList<>();

    while (running) {
        String[] options = {"Send Messages", "Show Sent Messages", "Logout", "Exit"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an Option:",
                "QuickChat Menu",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]);

        switch (choice) {
            case 0 -> {
                int numMessages = Integer.parseInt(JOptionPane.showInputDialog("How many messages would you like to send?"));

                for (int i = 0; i < numMessages; i++) {
                    String recipient = "";
                    boolean validRecipient = false;

                    while (!validRecipient) {
                        recipient = JOptionPane.showInputDialog("Enter recipient number:, It should begin with 0/+");
                        Message temp = new Message(recipient, "");
                        if (temp.checkRecipientCellNum()) {
                            validRecipient = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Cell phone number is incorrectly formatted. Please try again.");
                        }
                    }

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

                    Message msg = new Message(recipient, messageText);
                    String action = JOptionPane.showInputDialog("Choose: Send / Store / Discard");
                    JOptionPane.showMessageDialog(null, msg.sentMessage(action));

                    if (action.equalsIgnoreCase("send")) {
                        sentMessages.add(msg);
                        JOptionPane.showMessageDialog(null, msg.printMessage());
                    } else if (action.equalsIgnoreCase("store")) {
                        msg.storeMessage();
                    } else if (action.equalsIgnoreCase("discard")) {
                        JOptionPane.showMessageDialog(null, "Message discarded.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please choose Send, Store, or Discard.");
                    }
                }

                JOptionPane.showMessageDialog(null, "Total messages sent: " + Message.returnTotalMessages());
            }

            case 1 -> {
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

            case 2 -> {
                logout(); //Calls your logout method
            }

            case 3 -> {
                running = false;
                JOptionPane.showMessageDialog(null, "Goodbye!!!");
            }

            default -> JOptionPane.showMessageDialog(null, "Invalid option. Please choose again.");
        }
    }
  }
}