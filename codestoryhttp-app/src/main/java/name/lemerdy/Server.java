package name.lemerdy;

import net.codestory.http.WebServer;

public class Server {
    private final WebServer webServer;

    public Server() {
        webServer = new WebServer().configure(routes -> routes.add(EmployeesResource.class));
    }

    public void start() {
        webServer.start();
    }
    
    public void startOnRandomPort() {
        webServer.startOnRandomPort();
    }

    public void stop() {
        webServer.stop();
    }

    public int port() {
        return webServer.port();
    }

    public static void main(String[] args) {
        new Server().start();
    }
}
