package name.lemerdy;

import net.codestory.http.*;

public class Server {
    public static void main(String[] args) {
        new WebServer().configure(routes -> routes.add(EmployeesResource.class)).start();
    }
}
