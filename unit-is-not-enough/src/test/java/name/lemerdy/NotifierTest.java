package name.lemerdy;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NotifierTest {
    @Test
    public void should_notify_only_employees_with_given_provider() {
        Employee employeeValtech = new Employee("employee@valtech.fr");
        Employee employeeXebia = new Employee("employee@xebia.fr");
        Notifier notifier = new Notifier(employeeValtech, employeeXebia);

        List<Employee> notifiedEmployees = notifier.notify("valtech.fr");
        
        assertThat(notifiedEmployees).containsOnly(employeeValtech);
    }
}
