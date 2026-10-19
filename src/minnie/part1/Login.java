package minnie.part1;

public class Login {
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    
    private String enteredUsername;
    private String enteredPassword;

    public Login(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String checkUserName() {
        if (username.contains("_") && username.length() <= 5) {
            return "Username successfully captured.";
        } else {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
    }

    public String checkPasswordComplexity() {
        boolean hasLength = password.length() >= 8;
        boolean hasCapital = password.matches(".*[A-Z].*");
        boolean hasNumber = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        if (hasLength && hasCapital && hasNumber && hasSpecial) {
            return "Password successfully captured.";
        } else {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
    }

    public String checkCellPhoneNumber() {
        if (cellPhoneNumber.matches("^\\+27[0-9]{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public void setEnteredUsername(String enteredUsername) { this.enteredUsername = enteredUsername; }
    public void setEnteredPassword(String enteredPassword) { this.enteredPassword = enteredPassword; }

    public boolean loginUser() {
        return enteredUsername != null && enteredPassword != null 
            && enteredUsername.equals(username) && enteredPassword.equals(password);
    }

    public String returnLoginStatus() {
        if (loginUser()) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
