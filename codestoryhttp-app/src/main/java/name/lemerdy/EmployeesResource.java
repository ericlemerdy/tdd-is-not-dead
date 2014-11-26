package name.lemerdy;

import net.codestory.http.Context;
import net.codestory.http.annotations.Get;
import net.codestory.http.annotations.Post;
import net.codestory.http.payload.Payload;
import net.codestory.http.templating.Model;
import net.codestory.http.templating.ModelAndView;

import java.util.*;

import static net.codestory.http.constants.Headers.LOCATION;
import static net.codestory.http.constants.HttpStatus.FOUND;

public class EmployeesResource {
    private List<Employee> employees = new ArrayList<>();

    @Post("/employees")
    public Payload createEmployee(Employee newEmployee) {
        employees.add(newEmployee);
        return new Payload(FOUND).withHeader(LOCATION, "/employees/" + newEmployee.id);
    }

    @Get("/employees/:id")
    public ModelAndView employee(String id, Context context) {
        if (requestToNewEmployeeAccidentlyMatch(id)) {
            return newEmployee();
        }

        String notice = "";
        if (context.header("referer").endsWith("employees/new")) {
            notice = "Employee was successfully created.";
        } else if (context.header("referer").endsWith("employees/" + id + "/edit")) {
            notice = "Employee was successfully updated.";
        }
        
        return ModelAndView.of("employee",
                "employee", findById(id),
                "notice", notice);
    }

    @Post("/employees/edited")
    public Payload employeeEdited(Employee editedEmployee) {
        findById(editedEmployee.id).updateWith(editedEmployee);
        return new Payload(FOUND).withHeader(LOCATION, "/employees/" + editedEmployee.id);
    }

    @Get("/employees/:id/delete")
    public Payload deleteEmployee(String id) {
        employees.remove(findById(id));
        return new Payload(FOUND).withHeader(LOCATION, "/employees");
    }

    @Get("/employees")
    public Model employees() {
        return Model.of("employees", employees);
    }

    @Get("/employees/new")
    public ModelAndView newEmployee() {
        return ModelAndView.of("employee_new");
    }

    @Get("/employees/:id/edit")
    public ModelAndView editEmployee(String id) {
        return ModelAndView.of("employee_edit", "employee", findById(id));
    }

    private boolean requestToNewEmployeeAccidentlyMatch(String id) {
        return "new".equals(id);
    }
    
    private Employee findById(String id) {
        return employees.stream()
                    .filter(employee -> Objects.equals(employee.id, id))
                    .findFirst().orElseThrow(() -> new RuntimeException(""));
    }
}
