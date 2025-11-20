/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poe_part_one;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_Lab
 */
public class ManagementMenuNGTest {
    
    public ManagementMenuNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of isAdmin method, of class ManagementMenu.
     */
    @Test
    public void testIsAdmin() {
        System.out.println("isAdmin");
        String username = "";
        ManagementMenu instance = new ManagementMenu();
        boolean expResult = false;
        boolean result = instance.isAdmin(username);
        assertEquals(result, expResult);
    }

    /**
     * Test of showMenu method, of class ManagementMenu.
     */
    @Test
    public void testShowMenu() {
        System.out.println("showMenu");
        String currentUsername = "";
        ManagementMenu instance = new ManagementMenu();
        instance.showMenu(currentUsername);
    }

    /**
     * Test of viewAllUsers method, of class ManagementMenu.
     */
    @Test
    public void testViewAllUsers() {
        System.out.println("viewAllUsers");
        ManagementMenu instance = new ManagementMenu();
        instance.viewAllUsers();
    }

    /**
     * Test of deleteUser method, of class ManagementMenu.
     */
    @Test
    public void testDeleteUser() {
        System.out.println("deleteUser");
        ManagementMenu instance = new ManagementMenu();
        instance.deleteUser();
    }

    /**
     * Test of updateUserPassword method, of class ManagementMenu.
     */
    @Test
    public void testUpdateUserPassword() {
        System.out.println("updateUserPassword");
        ManagementMenu instance = new ManagementMenu();
        instance.updateUserPassword();
    }

    /**
     * Test of exportUserList method, of class ManagementMenu.
     */
    @Test
    public void testExportUserList() {
        System.out.println("exportUserList");
        ManagementMenu instance = new ManagementMenu();
        instance.exportUserList();
    }
    
}
