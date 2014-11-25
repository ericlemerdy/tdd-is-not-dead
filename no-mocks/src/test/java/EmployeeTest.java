import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;


public class EmployeeTest {
    
    @Test
    public void should_extract_email_provider_from_employee() {
        Employee guillaume = new Employee("guillaume.lours@exemple.com");
        
        String actualEmailProvider = guillaume.extractEmailProvider();
        
        assertThat(actualEmailProvider).isEqualTo("exemple.com");
    }

}
