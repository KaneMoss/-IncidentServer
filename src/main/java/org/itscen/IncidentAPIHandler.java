package org.itscen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.itscen.utils.EHTTPStatusCode;
import org.jetbrains.annotations.NotNull;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;

/**
 * REST API for IT based scenarios. Provides GET and POST request handling
 * */
public class IncidentAPIHandler implements HttpHandler
{
    private static final String GET = "GET";

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        //GET
        if (exchange.getRequestMethod().equalsIgnoreCase(GET)) {
            exchange.getResponseHeaders().set("content-type", "application/json");

            final String response = GetResponse(exchange.getRequestURI().getQuery());
            exchange.sendResponseHeaders(EHTTPStatusCode.OK.getCode(), response.length());

            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        }
    }

    //Process the query parameters of the GET request and return our response
    @NotNull
    private String GetResponse(String query) throws JsonProcessingException {
        if (query == null || query.isEmpty()) {
            return ConvertToJSONString(new HashMap<>());
        }
        else {
            final HashMap<String, String> parameters = parseQuery(query);
            return ConvertToJSONString(parameters);
        }
    }


    @NotNull
    private static HashMap<String, String> parseQuery(@NotNull String query) {
        final HashMap<String, String> queryParams = new HashMap<>();

        String[] params = query.split("&");
        for (String param : params) {
            String decodedStr = URLDecoder.decode(param);

            int i = param.indexOf("=");

            if (i > 0) {
                String key = decodedStr.substring(0, i);
                String value = decodedStr.substring(i + 1);
                queryParams.put(key, value);
            }
        }
        return queryParams;
    }

    //Uses Jackson to handle conversion of the query parameters map to a JSON string
    private static String ConvertToJSONString(@NotNull HashMap<String, String> map) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }
}
