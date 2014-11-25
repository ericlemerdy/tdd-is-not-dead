package name.lemerdy.e2e.tests;

import name.lemerdy.Server;
import org.junit.rules.ExternalResource;

public class ServerRule extends ExternalResource {
    
    Server server;

    @Override
    protected void before() {
        server = new Server();
        server.startOnRandomPort();
    }

    @Override
    protected void after() {
        server.stop();
    }

    public int port() {
        return server.port();
    }
}
