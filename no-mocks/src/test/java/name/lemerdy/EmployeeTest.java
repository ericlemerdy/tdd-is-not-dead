package name.lemerdy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class EmployeeTest {
    @Test
    public void should_extract_email_provider_from_employee() {
        Employee guillaume = new Employee("guillaume.lours@exemple.com");
        
        String actualEmailProvider = guillaume.extractEmailProvider();
        
        assertThat(actualEmailProvider).isEqualTo("exemple.com");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void unable_to_create_employee_with_invalid_email() {
        new Employee("guillaume.lours_at_exemple.com");
    }
}
