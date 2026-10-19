import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private String status;

    private static int totalMessagesSent = 0;
    private static int messageNumber = 0;
    private static final List<Message> sentMessages = new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);

    public Message(String recipient, String message) {
        Random random = new Random();
        this.messageID = String.format("%010d", Math.abs(random.nextLong()) % 10000000000L);
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
        this.status = "Pending";
    }

    public String checkRecipientCell() {
        if (recipient == null) return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        if (recipient.matches("(\\+27\\d{9}|0\\d{9})")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String createMessageHash() {
        if (message == null || message.trim().isEmpty()) return "";
        String cleanMessage = message.replaceAll("[^a-zA-Z ]", "").trim();
        String[] words = cleanMessage.split("\\s+");
        String firstWord = words.length > 0 ? words[0].toUpperCase() : "";
        String lastWord = words.length > 1 ? words[words.length - 1].toUpperCase() : firstWord;
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    public String sentMessage() {
        if (message == null || message.trim().isEmpty()) return "Please enter a message.";
        if (message.length() > 250) return "Message exceeds 250 characters by " + (message.length() - 250) + ", please reduce the size.";
        if (checkRecipientCell().contains("incorrectly formatted")) return checkRecipientCell();

        System.out.println("\nChoose an option:");
        System.out.println("1. Send Message\n2. Disregard Message\n3. Store Message");
        String choice = input.nextLine();

        switch (choice) {
            case "1":
                status = "Sent"; totalMessagesSent++; sentMessages.add(this); messageNumber++;
                return "Message successfully sent.";
            case "2":
                status = "Disregarded"; return "Press 0 to delete the message.";
            case "3":
                status = "Stored"; storeMessage(); return "Message successfully stored.";
            default:
                return "Invalid option. Message not sent.";
        }
    }

    public void storeMessage() {
        String json = "{\n  \"messageID\": \"" + this.messageID + "\",\n  \"recipient\": \"" + this.recipient + "\",\n  \"message\": \"" + this.message + "\",\n  \"hash\": \"" + this.messageHash + "\"\n}\n";
        try (PrintWriter out = new PrintWriter(new FileWriter("stored_messages.json", true))) {
            out.println(json);
        } catch (IOException e) {}
    }

    public static int returnTotalMessages() { return totalMessagesSent; }
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getStatus() { return status; }
}