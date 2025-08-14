package org.itscen;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;


/**
 * HTTPServer for providing a REST API for generating random IT-related scenarios based on user inputs.
 * Implemented as singleton to prevent accidental multiple servers being initialised.
 * */
public class IncidentServer
{
    private static final int PORT = 8080;

    private static IncidentServer instance = null;

    private HttpServer mServer = null;

    private IncidentAPIHandler mHandler = null;


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
        mHandler = new IncidentAPIHandler();
        mServer.createContext("/api", mHandler);
        System.out.println("Server has started");
    }

    public int getPortNumber() {
        return mServer.getAddress().getPort();
    }

    public void StopServer() {
        mServer.stop(0);
    }

    public void setSeed(int seed) {
        mHandler.setSeed(seed);
    }
}
