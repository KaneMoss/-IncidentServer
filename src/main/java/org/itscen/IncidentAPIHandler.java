package org.itscen;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

/**
 * REST API for IT based scenarios. Provides GET and POST request handling
 * */
public class IncidentAPIHandler implements HttpHandler
{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        //NOOP
    }
}
