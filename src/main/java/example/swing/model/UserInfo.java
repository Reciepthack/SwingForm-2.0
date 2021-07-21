package main.java.example.swing.model;


public class UserInfo {
    private String firstName;
    private String lastName;
    private String email;


    public UserInfo(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return "First name = " + firstName + "\n" +
                "Last name = " + lastName + "\n" +
                "Email = " + email;
    }
}