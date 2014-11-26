package name.lemerdy;

import static java.lang.System.out;

import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        Employee employeeValtech = new Employee("employee@valtech.fr");
        Employee employee1Xebia = new Employee("employee@xebia.fr");
        Employee employee2Xebia = new Employee("employee@xEbia.fr");
        Notifier notifier = new Notifier(employeeValtech, employee1Xebia, employee2Xebia);

        out.println("Notifying Xebia employees...");
        out.println();
        
        List<Employee> notifiedEmployees = notifier.notify("xebia.fr");

        out.println();
        out.println("Expected Xebia employee 1 AND 2 to be notified. Were they ;-) ?");
        out.println(notifiedEmployees);
    }

}
