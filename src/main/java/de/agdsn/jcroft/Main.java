package de.agdsn.jcroft;

import de.agdsn.jcroft.servlet.WelcomeServlet;
import org.eclipse.jetty.server.Server;

public class Main {
    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new WelcomeServlet());

        server.start();
        server.join();
    }
}
