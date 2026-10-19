import java.util.Scanner;
import minnie.part1.Login;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("--- QuickChat Login ---");
        System.out.print("Enter username: ");
        String username = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        Login user = new Login("bol_1", "Bolen$23", "+27761128209", "Boleng", "Kubyane");
        user.setEnteredUsername(username);
        user.setEnteredPassword(password);

        if (!user.loginUser()) {
            System.out.println(user.returnLoginStatus());
            return;
        }
        System.out.println("\n" + user.returnLoginStatus());

        boolean quit = false;
        while (!quit) {
            System.out.println("\n--- Main Menu ---\n1) Send Messages\n2) Show recently sent messages\n3) Quit");
            System.out.print("Choose an option: ");
            String menuChoice = input.nextLine();

            switch (menuChoice) {
                case "1":
                    System.out.print("How many messages? ");
                    int numMessages = Integer.parseInt(input.nextLine());
                    for (int i = 0; i < numMessages; i++) {
                        System.out.print("Enter recipient (+27...): ");
                        String recipient = input.nextLine();
                        System.out.print("Enter message: ");
                        String messageText = input.nextLine();
                        
                        Message msg = new Message(recipient, messageText);
                        System.out.println(msg.sentMessage());
                        if (msg.getStatus().equals("Sent")) {
                            System.out.println("\nID: " + msg.getMessageID() + "\nHash: " + msg.getMessageHash() + "\nTo: " + msg.getRecipient() + "\nMsg: " + msg.getMessage());
                        }
                    }
                    System.out.println("\nTotal messages sent: " + Message.returnTotalMessages());
                    break;
                case "2":
                    System.out.println("Coming Soon."); break;
                case "3":
                    quit = true; break;
            }
        }
    }
}

