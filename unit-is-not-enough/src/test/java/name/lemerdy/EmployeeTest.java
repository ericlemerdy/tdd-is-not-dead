package name.lemerdy;

import org.junit.Test;

public class EmployeeTest {
    @Test(expected = IllegalArgumentException.class)
    public void unable_to_create_employee_with_invalid_email() {
        new Employee("guillaume.lours_at_exemple.com");
    }
}
