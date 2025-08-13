package org.itscen;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class IncidentServer
{
    private HttpServer mServer = null;

    public IncidentServer(int portNumber) {
        try {
            mServer = HttpServer.create(new InetSocketAddress(portNumber), 0);
        } catch (IOException e) {
            System.err.println("Could not create HTTP Server: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        init();
    }

    //NOOP
    private void init() {

    }

    //NOOP
    public int getPortNumber()
    {
        return 0;
    }
}
