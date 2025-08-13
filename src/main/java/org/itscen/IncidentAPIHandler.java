package org.itscen;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.itscen.utils.EHTTPStatusCode;

import java.io.IOException;
import java.io.OutputStream;

/**
 * REST API for IT based scenarios. Provides GET and POST request handling
 * */
public class IncidentAPIHandler implements HttpHandler
{
    private static final String GET = "GET";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase(GET)) {
            exchange.getResponseHeaders().set("content-type", "application/json");

            final String response = GetResponse();
            exchange.sendResponseHeaders(EHTTPStatusCode.OK.getCode(), response.length());

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    private String GetResponse() {
        return "Hello World!";
    }
}
