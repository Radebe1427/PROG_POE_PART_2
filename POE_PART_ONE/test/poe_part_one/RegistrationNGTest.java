/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poe_part_one;

import org.json.JSONObject;
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
public class RegistrationNGTest {
    
    public RegistrationNGTest() {
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
     * Test of userNameCheck method correctly formatted, of class Registration.
     */
    @Test
    public void testUserNameCheck() {
        System.out.println("userNameCheck");
        String Username = "kyl_1";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.userNameCheck(Username);
        assertEquals(result, expResult);
    }
    
    /**
     * Test of usernameCheck method Incorrectly formatted, of class Registration.
     */
    
    @Test
    public void testIncorrectUserNameCheck() {
        System.out.println("userINameCheck");
        String Username = "kyle!!!!!!!";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.userNameCheck(Username);
        assertEquals(result, expResult);
    }

    /**
     * Test of userPhoneNumCheck method correctly Formatted, of class Registration.
     */
    @Test
    public void testUserPhoneNumCheck() {
        System.out.println("userPhoneNumCheck");
        String cellphoneNumber = "+27838968976";
        Registration instance = new Registration();
        boolean expResult = true;
        boolean result = instance.userPhoneNumCheck(cellphoneNumber);
        assertEquals(result, expResult);
    }
    
    
    /**
     * Test of userPhoneNumCheck method incorrectly formatted, of class Registration.
     */
    @Test
    public void testIncorrectUserPhoneNumCheck() {
        System.out.println("userPhoneNumCheck");
        String cellphoneNumber = "08966553";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.userPhoneNumCheck(cellphoneNumber);
        assertEquals(result, expResult);
    }

    /**
     * Test of userPasswordCheck method correctly formatted, of class Registration.
     */
    @Test
    public void testUserPasswordCheck() {
        System.out.println("userPasswordCheck");
        String Password = "Ch&&sec@ke99!";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.userPasswordCheck(Password);
        assertEquals(result, expResult);
    }
    
    
    /**
     * Test of userPasswordCheck method incorrectly formatted, of class Registration.
     */
    @Test
    public void testIncorrectUserPasswordCheck() {
        System.out.println("userPasswordCheck");
        String Password = "";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.userPasswordCheck(Password);
        assertEquals(result, expResult);
    }

    /**
     * Test of saveLoginInfo method, of class Registration.
     */
    @Test
    public void testSaveLoginInfo() {
        System.out.println("saveLoginInfo");
        Registration instance = new Registration();
        instance.saveLoginInfo();
    }

    /**
     * Test of loadLoginInfo method, of class Registration.
     */
    @Test
    public void testLoadLoginInfo() {
        System.out.println("loadLoginInfo");
        String enteredUsername = "";
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.loadLoginInfo(enteredUsername);
        assertEquals(result, expResult);
    }

    /**
     * Test of getUserByUsername method, of class Registration.
     */
    @Test
    public void testGetUserByUsername() {
        System.out.println("getUserByUsername");
        String username = "";
        Registration instance = new Registration();
        JSONObject expResult = null;
        JSONObject result = instance.getUserByUsername(username);
        assertEquals(result, expResult);
    }

    /**
     * Test of signUp method, of class Registration.
     */
    @Test
    public void testSignUp() {
        System.out.println("signUp");
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.signUp();
        assertEquals(result, expResult);
    }

    /**
     * Test of signIn method, of class Registration.
     */
    @Test
    public void testSignIn() {
        System.out.println("signIn");
        Registration instance = new Registration();
        instance.signIn();
    }

    /**
     * Test of logout method, of class Registration.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        Registration instance = new Registration();
        instance.logout();
    }

    /**
     * Test of launchQuickChat method, of class Registration.
     */
    @Test
    public void testLaunchQuickChat() {
        System.out.println("launchQuickChat");
        Registration instance = new Registration();
        instance.launchQuickChat();
    }
    
}
