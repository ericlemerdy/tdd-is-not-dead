package name.lemerdy;

import static java.lang.System.out;

import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        Employee employeeValtech = new Employee("employee@valtech.fr");
        Employee employee1Xebia = new Employee("employee@xebia.fr");
        Employee employee2Xebia = new Employee("employee@xEbia.fr");
        Notifier notifier = new Notifier(employeeValtech, employee1Xebia, employee2Xebia);

        List<Employee> notifiedEmployees = notifier.notify("xebia.fr");
        
        out.println("Xebia employees should be migrated.");
        out.println(notifiedEmployees);
    }

}
