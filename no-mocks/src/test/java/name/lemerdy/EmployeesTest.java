package name.lemerdy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;
import static org.mockito.BDDMockito.given;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

public class EmployeesTest {

    @Test
    public void should_count_employees_grouped_by_providers() {
        Employees employees = new Employees(
                new Employee("employee@valtech.fr"),
                new Employee("employee1@xebia.fr"),
                new Employee("employee2@xebia.fr"));

        Map<String, Long> numberOfEmployeeByEmailProvider = employees.countByEmailProvider();

        assertThat(numberOfEmployeeByEmailProvider).containsExactly(//
                entry("valtech.fr", 1L),//
                entry("xebia.fr", 2L));
    }

}
