package name.lemerdy;

import java.util.UUID;

public class Employee {
    final String id;
    
    private String name;
    private String email;

    public Employee(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    
    public Employee(String id) {
        this(id, "", "");
    }

    public Employee() {
        this(UUID.randomUUID().toString());
    }

    public Employee updateWith(Employee newEmployee) {
        name = newEmployee.name;
        email = newEmployee.email;
        return this;
    }

    @Override
    public String toString() {
        return name + " <" + email + ">";
    }
}
