package name.lemerdy;

import net.codestory.http.constants.Headers;
import net.codestory.http.payload.Payload;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static net.codestory.http.constants.HttpStatus.FOUND;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeesResourceTest {
    @Test
    public void should_create_new_employee() {
        EmployeesResource employees = new EmployeesResource();
        Employee employee = new Employee(UUID.randomUUID().toString(), "name", "email");

        Payload result = employees.create(employee);
        
        assertThat((List<Employee>) employees.employees().get("employees")).contains(employee);
        assertThat(result.code()).isEqualTo(FOUND);
        assertThat(result.headers().get(Headers.LOCATION)).isEqualTo("/employees/" + employee.id);
    }
}