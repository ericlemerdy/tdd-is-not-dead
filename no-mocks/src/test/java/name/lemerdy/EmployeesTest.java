package name.lemerdy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;
import static org.mockito.BDDMockito.given;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmployeesTest {

    @Mock
    Employee employeeFromValtech;
    @Mock
    Employee employee1FromXebia;
    @Mock
    Employee employee2FromXebia;

    @Test
    public void should_count_employees_grouped_by_providers() {
        Employees employees = new Employees(employeeFromValtech, employee1FromXebia, employee2FromXebia);
        given(employeeFromValtech.extractEmailProvider()).willReturn("valtech.fr");
        given(employee2FromXebia.extractEmailProvider()).willReturn("xebia.fr");
        given(employee1FromXebia.extractEmailProvider()).willReturn("xebia.fr");

        Map<String, Long> numberOfEmployeeByEmailProvider = employees.countByEmailProvider();

        assertThat(numberOfEmployeeByEmailProvider).containsExactly(//
                entry("valtech.fr", 1L),//
                entry("xebia.fr", 2L));
    }

}
