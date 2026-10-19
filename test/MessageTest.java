import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {
    @Test
    public void testMessageLength() { assertTrue(new Message("+27831234567", "Hi Mike").getMessage().length() <= 250); }
    
    @Test
    public void testRecipientSuccess() { assertEquals("Cell phone number successfully captured.", new Message("+27831234567", "Test").checkRecipientCell()); }
    
    @Test
    public void testRecipientFailure() { assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", new Message("0857597588945", "Test").checkRecipientCell()); }
    
    @Test
    public void testMessageHash() { assertTrue(new Message("+27831234567", "Hi Mike, can you join us for dinner tonight?").getMessageHash().endsWith(":0:HITONIGHT")); }
}
