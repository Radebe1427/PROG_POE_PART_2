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
public class MessageNGTest {
    
    public MessageNGTest() {
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
     * Test of checkMessageID method, of class Message.
     */
    @Test
    public void testCheckMessageID() {
        System.out.println("checkMessageID");
        Message instance = null;
        boolean expResult = false;
        boolean result = instance.checkMessageID();
        assertEquals(result, expResult);;
    }

    /**
     * Test of checkRecipientCellNum method, of class Message.
     */
    @Test
    public void testCheckRecipientCellNum() {
        System.out.println("checkRecipientCellNum");
        Message instance = null;
        boolean expResult = false;
        boolean result = instance.checkRecipientCellNum();
        assertEquals(result, expResult);
    }

    /**
     * Test of createMessageID method, of class Message.
     */
    @Test
    public void testCreateMessageID() {
        System.out.println("createMessageID");
        Message instance = null;
        String expResult = "";
        String result = instance.createMessageID();
        assertEquals(result, expResult);
    }

    /**
     * Test of createMessageHash method, of class Message.
     */
    @Test
    public void testCreateMessageHash() {
        System.out.println("createMessageHash");
        Message instance = null;
        String expResult = "";
        String result = instance.createMessageHash();
        assertEquals(result, expResult);
    }

    /**
     * Test of sentMessage method, of class Message.
     */
    @Test
    public void testSentMessage() {
        System.out.println("sentMessage");
        String choice = "";
        Message instance = null;
        String expResult = "";
        String result = instance.sentMessage(choice);
        assertEquals(result, expResult);
    }

    /**
     * Test of printMessage method, of class Message.
     */
    @Test
    public void testPrintMessage() {
        System.out.println("printMessage");
        Message instance = null;
        String expResult = "";
        String result = instance.printMessage();
        assertEquals(result, expResult);
    }

    /**
     * Test of returnTotalMessages method, of class Message.
     */
    @Test
    public void testReturnTotalMessages() {
        System.out.println("returnTotalMessages");
        int expResult = 2;
        int result = Message.returnTotalMessages();
        assertEquals(result, expResult);
    }

    /**
     * Test of storeMessage method, of class Message.
     */
    @Test
    public void testStoreMessage() {
        System.out.println("storeMessage");
        Message instance = null;
        instance.storeMessage();
    }
    
}
