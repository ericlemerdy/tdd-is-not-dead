package name.lemerdy;

public class Employee {
    private final String email;

    public Employee(String emailString) {
        if (!emailString.contains("@")) {
            throw new IllegalArgumentException();
        }
        email = emailString;
    }

    public String extractEmailProvider() {
        return email.split("@")[1];
    }
}
