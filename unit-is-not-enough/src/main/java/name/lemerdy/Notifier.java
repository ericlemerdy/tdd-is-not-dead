package name.lemerdy;

import static java.lang.System.out;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

import java.util.List;

public class Notifier {
    private List<Employee> employees;

    public Notifier(Employee... employees) {
        this.employees = asList(employees);
    }

    public List<Employee> notify(final String provider) {
        return employees.stream()
                .filter(employee -> provider.equals(employee.extractEmailProvider()))
                .peek(employee -> out.format("Employee %s notified !%n", employee))
                .collect(toList());
    }
}
