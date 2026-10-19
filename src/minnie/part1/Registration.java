package minnie.part1;

import java.util.Scanner;

public class Registration {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("--- User Registration ---");
        
        System.out.print("Enter first name: ");
        String firstName = input.nextLine();
        
        System.out.print("Enter last name: ");
        String lastName = input.nextLine();
        
        System.out.print("Enter username: ");
        String username = input.nextLine();
        
        System.out.print("Enter password: ");
        String password = input.nextLine();
        
        System.out.print("Enter cell phone number (+27...): ");
        String cellPhone = input.nextLine();
        
        // Create a new Login object with the entered details
        Login newUser = new Login(username, password, cellPhone, firstName, lastName);
        
        // Run the validation checks
        String userCheck = newUser.checkUserName();
        String passCheck = newUser.checkPasswordComplexity();
        String cellCheck = newUser.checkCellPhoneNumber();
        
        // Print the results
        System.out.println(userCheck);
        System.out.println(passCheck);
        System.out.println(cellCheck);
        
        // Only print "Registration successful" if ALL checks passed
        if (userCheck.equals("Username successfully captured.") &&
            passCheck.equals("Password successfully captured.") &&
            cellCheck.equals("Cell phone number successfully captured.")) {
            
            System.out.println("\nRegistration successful!");
            
        } else {
            System.out.println("\nRegistration failed. Please fix the errors above and try again.");
        }
        
        input.close();
    }
}