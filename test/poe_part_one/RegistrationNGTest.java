/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package poe_part_one;

import java.util.ArrayList;
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

    @Test
    public void testUserPersonalDetails() {
        System.out.println("userPersonalDetails");
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.userPersonalDetails();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUsername() {
        System.out.println("Username");
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.Username();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUserPhoneNumber() {
        System.out.println("userPhoneNumber");
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.userPhoneNumber();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUserPassword() {
        System.out.println("userPassword");
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.userPassword();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testUserSecurityDetails() {
        System.out.println("userSecurityDetails");
        Registration instance = new Registration();
        boolean expResult = false;
        boolean result = instance.userSecurityDetails();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPromptUsername() {
        System.out.println("promptUsername");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.promptUsername();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testPromptPasswordOptions() {
        System.out.println("promptPasswordOptions");
        Registration instance = new Registration();
        int expResult = 0;
        int result = instance.promptPasswordOptions();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetEnteredPassword() {
        System.out.println("getEnteredPassword");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.getEnteredPassword();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testHandleForgotPassword() {
        System.out.println("handleForgotPassword");
        boolean userFound = false;
        Registration instance = new Registration();
        instance.handleForgotPassword(userFound);
        fail("The test case is a prototype.");
    }

    @Test
    public void testHandleLogin() {
        System.out.println("handleLogin");
        boolean userFound = false;
        String enteredPassword = "";
        String enteredUsername = "";
        Registration instance = new Registration();
        instance.handleLogin(userFound, enteredPassword, enteredUsername);
        fail("The test case is a prototype.");
    }

    @Test
    public void testClearLoginFile() {
        System.out.println("clearLoginFile");
        Registration instance = new Registration();
        instance.clearLoginFile();
        fail("The test case is a prototype.");
    }

    @Test
    public void testShowQuickChatMenu() {
        System.out.println("showQuickChatMenu");
        Registration instance = new Registration();
        int expResult = 0;
        int result = instance.showQuickChatMenu();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSendMessages() {
        System.out.println("sendMessages");
        ArrayList<Message> sentMessages1 = null;
        Registration instance = new Registration();
        instance.sendMessages(sentMessages1);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCaptureRecipient() {
        System.out.println("captureRecipient");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.captureRecipient();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testCaptureMessageText() {
        System.out.println("captureMessageText");
        Registration instance = new Registration();
        String expResult = "";
        String result = instance.captureMessageText();
        assertEquals(result, expResult);
        fail("The test case is a prototype.");
    }

    @Test
    public void testHandleMessageAction() {
        System.out.println("handleMessageAction");
        Message msg = null;
        Registration instance = new Registration();
        instance.handleMessageAction(msg);
        fail("The test case is a prototype.");
    }

    @Test
    public void testShowSentMessages() {
        System.out.println("showSentMessages");
        ArrayList<Message> sentMessages = null;
        Registration instance = new Registration();
        instance.showSentMessages(sentMessages);
        fail("The test case is a prototype.");
    }

    @Test
    public void testShowDisregardedMessages() {
        System.out.println("showDisregardedMessages");
        ArrayList<Message> disregardedMessages = null;
        Registration instance = new Registration();
        instance.showDisregardedMessages(disregardedMessages);
        fail("The test case is a prototype.");
    }

    @Test
    public void testShowStoredMessages() {
        System.out.println("showStoredMessages");
        ArrayList<Message> storedMessages = null;
        Registration instance = new Registration();
        instance.showStoredMessages(storedMessages);
        fail("The test case is a prototype.");
    }

    @Test
    public void testShowLongestSentMessage() {
        System.out.println("showLongestSentMessage");
        ArrayList<Message> sentMessages = null;
        Registration instance = new Registration();
        instance.showLongestSentMessage(sentMessages);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSearchByMessageID() {
        System.out.println("searchByMessageID");
        ArrayList<Message> allMessages = null;
        Registration instance = new Registration();
        instance.searchByMessageID(allMessages);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSearchByRecipient() {
        System.out.println("searchByRecipient");
        ArrayList<Message> allMessages = null;
        Registration instance = new Registration();
        instance.searchByRecipient(allMessages);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDeleteByHash() {
        System.out.println("deleteByHash");
        ArrayList<Message> storedMessages = null;
        Registration instance = new Registration();
        instance.deleteByHash(storedMessages);
        fail("The test case is a prototype.");
    }

    @Test
    public void testDisplayReport() {
        System.out.println("displayReport");
        ArrayList<Message> sentMessages1 = null;
        Registration instance = new Registration();
        instance.displayReport(sentMessages1);
        fail("The test case is a prototype.");
    }

    @Test
    public void testSaveAllMessages() {
        System.out.println("saveAllMessages");
        String currentUser1 = "";
        Registration instance = new Registration();
        instance.saveAllMessages(currentUser1);
        fail("The test case is a prototype.");
    }

    @Test
    public void testLoadAllMessages() {
        System.out.println("loadAllMessages");
        String username = "";
        Registration instance = new Registration();
        instance.loadAllMessages(username);
        fail("The test case is a prototype.");
    }
    
}
