/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poe_part_one;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author RC_Student_Lab
 */
class ManagementMenu {
    // Admin access check
    public boolean isAdmin(String username) {
        return username.equalsIgnoreCase("admin");
    }

    // Entry point for admin menu
    public void showMenu(String currentUsername) {
        if (!isAdmin(currentUsername)) {
            JOptionPane.showMessageDialog(null, "Access denied. Only admin can manage users.");
            return;
        }

        boolean managing = true;

        while (managing) {
            String[] options = {
                "View All Users",
                "Delete User",
                "Update Password",
                "Export User List",
                "Back to Main Menu"
            };

            int choice = JOptionPane.showOptionDialog(
                    null,
                    "User Management Menu",
                    "Manage Users",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]);

            switch (choice) {
                case 0 -> viewAllUsers();
                case 1 -> deleteUser();
                case 2 -> updateUserPassword();
                case 3 -> exportUserList();
                case 4 -> managing = false;
                default -> JOptionPane.showMessageDialog(null, "Invalid option. Please choose again.");
            }
        }
    }

    public void viewAllUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("userlogin.json"))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            JSONArray users = new JSONArray(json.toString());
            StringBuilder output = new StringBuilder("Registered Users:\n");

            for (int i = 0; i < users.length(); i++) {
                JSONObject obj = users.getJSONObject(i);
                output.append("- ").append(obj.getString("username"))
                      .append(" (").append(obj.getString("firstName")).append(" ")
                      .append(obj.getString("lastName")).append(")\n");
            }

            JOptionPane.showMessageDialog(null, output.toString());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error reading user list.");
        }
    }

    public void deleteUser() {
        String usernameToDelete = JOptionPane.showInputDialog("Enter the username to delete:");
        if (usernameToDelete == null || usernameToDelete.isBlank()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader("userlogin.json"))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            JSONArray users = new JSONArray(json.toString());
            JSONArray updatedUsers = new JSONArray();
            boolean found = false;

            for (int i = 0; i < users.length(); i++) {
                JSONObject obj = users.getJSONObject(i);
                if (!obj.getString("username").equals(usernameToDelete)) {
                    updatedUsers.put(obj);
                } else {
                    found = true;
                }
            }

            if (found) {
                try (FileWriter writer = new FileWriter("userlogin.json")) {
                    writer.write(updatedUsers.toString(4));
                }
                JOptionPane.showMessageDialog(null, "User '" + usernameToDelete + "' has been deleted.");
            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error deleting user.");
        }
    }

    public void updateUserPassword() {
        String usernameToUpdate = JOptionPane.showInputDialog("Enter the username to update password:");
        if (usernameToUpdate == null || usernameToUpdate.isBlank()) return;

        String newPassword = JOptionPane.showInputDialog("Enter the new password:");
        if (newPassword == null || newPassword.isBlank()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader("userlogin.json"))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            JSONArray users = new JSONArray(json.toString());
            boolean updated = false;

            for (int i = 0; i < users.length(); i++) {
                JSONObject obj = users.getJSONObject(i);
                if (obj.getString("username").equals(usernameToUpdate)) {
                    obj.put("password", newPassword);
                    updated = true;
                }
            }

            if (updated) {
                try (FileWriter writer = new FileWriter("userlogin.json")) {
                    writer.write(users.toString(4));
                }
                JOptionPane.showMessageDialog(null, "Password updated for user '" + usernameToUpdate + "'.");
            } else {
                JOptionPane.showMessageDialog(null, "User not found.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error updating password.");
        }
    }

    public void exportUserList() {
        try (BufferedReader reader = new BufferedReader(new FileReader("userlogin.json"));
             FileWriter writer = new FileWriter("userlist.txt")) {

            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }

            JSONArray users = new JSONArray(json.toString());

            for (int i = 0; i < users.length(); i++) {
                JSONObject obj = users.getJSONObject(i);
                writer.write(obj.getString("username") + " - " +
                             obj.getString("firstName") + " " +
                             obj.getString("lastName") + "\n");
            }

            JOptionPane.showMessageDialog(null, "User list exported to 'userlist.txt'.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error exporting user list.");
     }
   }
}

