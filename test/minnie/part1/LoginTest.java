package minnie.part1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {
    private Login validUser;

    @Before
    public void setUp() {
        validUser = new Login("bol_1", "Bolen$23", "+27761128209", "Boleng", "Kubyane");
    }

    // --- Username Tests ---
    @Test
    public void testUsernameValid() { 
        assertEquals("Username successfully captured.", validUser.checkUserName()); 
    }
    
    @Test
    public void testUsernameNoUnderscore() { 
        Login user = new Login("boli", "Bolen$23", "+27761128209", "B", "K");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.", user.checkUserName()); 
    }
    
    @Test
    public void testUsernameTooLong() { 
        Login user = new Login("boli_1", "Bolen$23", "+27761128209", "B", "K");
        assertEquals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.", user.checkUserName()); 
    }

    // --- Password Tests ---
    @Test
    public void testPasswordValid() { 
        assertEquals("Password successfully captured.", validUser.checkPasswordComplexity()); 
    }
    
    @Test
    public void testPasswordTooShort() { 
        Login user = new Login("bol_1", "Bo$1", "+27761128209", "B", "K");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", user.checkPasswordComplexity()); 
    }
    
    @Test
    public void testPasswordNoCapital() { 
        Login user = new Login("bol_1", "bolen$23", "+27761128209", "B", "K");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", user.checkPasswordComplexity()); 
    }

    // --- Cell Phone Tests ---
    @Test
    public void testCellValid() { 
        assertEquals("Cell phone number successfully captured.", validUser.checkCellPhoneNumber()); 
    }
    
    @Test
    public void testCellInvalid() { 
        Login user = new Login("bol_1", "Bolen$23", "0761128209", "B", "K");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", user.checkCellPhoneNumber()); 
    }

    // --- Login Verification Tests ---
    @Test
    public void testLoginSuccess() {
        validUser.setEnteredUsername("bol_1");
        validUser.setEnteredPassword("Bolen$23");
        assertTrue(validUser.loginUser());
        assertEquals("Welcome Boleng, Kubyane it is great to see you again.", validUser.returnLoginStatus());
    }

    @Test
    public void testLoginWrongPassword() {
        validUser.setEnteredUsername("bol_1");
        validUser.setEnteredPassword("WrongPass!");
        assertFalse(validUser.loginUser());
        assertEquals("Username or password incorrect, please try again.", validUser.returnLoginStatus());
    }
}
