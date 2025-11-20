/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poe_part_one;

import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_Lab
 */
public class POE_PART_ONE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Registration registration = new Registration();
        ManagementMenu manager = new ManagementMenu();

        boolean running = true;

        while (running) {
            String[] options = {"Sign Up", "Sign In", "Manage Users", "Exit"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Welcome to QuickChat\nChoose an option:",
                    "Main Menu",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    
                    
                    null,
                    options,
                    options[0]);

            switch (choice) {
                case 0 -> {
                    boolean signedUp = registration.signUp();
                    if (!signedUp) {
                        JOptionPane.showMessageDialog(null, "Sign Up cancelled.");
                    }
                }

                case 1 -> registration.signIn();

                case 2 -> {
                    String adminUsername = JOptionPane.showInputDialog("Enter admin username to access management:");
                    if (adminUsername != null && !adminUsername.isBlank()) {
                        manager.showMenu(adminUsername);
                    }
                }

                case 3 -> {
                    running = false;
                    JOptionPane.showMessageDialog(null, "Thank you for using QuickChat. Goodbye!");
                }

                default -> JOptionPane.showMessageDialog(null, "Invalid option!!!. Please choose again.");
         }
      }
   }
}