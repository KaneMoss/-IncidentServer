package org.itscen;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

public class IncidentServer
{
    private static final int PORT = 8080;

    private static IncidentServer instance = null;

    private HttpServer mServer = null;


    public static IncidentServer getInstance() {
        if (instance == null) {
            instance = new IncidentServer(PORT);
        }
        return instance;
    }


    private IncidentServer(int portNumber) {
        try {
            mServer = HttpServer.create(new InetSocketAddress(portNumber), 0);
        }
        catch (IOException e) {
            System.err.println("Could not create HTTP Server: ");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        init();
    }


    private void init() {
        mServer.start();
        System.out.println("Server has started");
    }


    public int getPortNumber() {
        return mServer.getAddress().getPort();
    }

    public void StopServer() {
        mServer.stop(0);
    }
}
