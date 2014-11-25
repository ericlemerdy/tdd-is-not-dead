public class Employee {
    private final String email;

    public Employee(String emailString) {
        email = emailString;
    }

    public String extractEmailProvider() {
        return email.split("@")[1];
    }
}
