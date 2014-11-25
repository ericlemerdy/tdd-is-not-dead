package name.lemerdy;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class Employees {
    private final List<Employee> employees;

    public Employees(Employee... employees) {
        this.employees = asList(employees);
    }

    public Map<String, Long> countByEmailProvider() {
        return employees.stream().collect(groupingBy(Employee::extractEmailProvider, counting()));
    }
}
