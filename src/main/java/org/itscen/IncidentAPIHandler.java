package org.itscen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.itscen.data.DataHandler;
import org.itscen.utils.EHTTPStatusCode;
import org.jetbrains.annotations.NotNull;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * REST API for IT based scenarios. Provides GET and POST request handling
 * */
public class IncidentAPIHandler implements HttpHandler
{
    private static final String GET = "GET";

    private final DataHandler mDataHandler;

    private int mseed = -1;

    public IncidentAPIHandler() {
        mDataHandler = new DataHandler();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String response = "";

        //GET
        if (exchange.getRequestMethod().equalsIgnoreCase(GET)) {
            exchange.getResponseHeaders().set("content-type", "application/json");

            response = GetResponse(exchange.getRequestURI().getQuery());
            exchange.sendResponseHeaders(EHTTPStatusCode.OK.getCode(), response.length());
        }

        else {
            //Else not implemented so return 405 error
            exchange.sendResponseHeaders(EHTTPStatusCode.METHOD_NOT_ALLOWED.getCode(), 0);
        }

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    //Process the query parameters of the GET request and return our response
    @NotNull
    private String GetResponse(String query) throws JsonProcessingException {
        if (query == null || query.isEmpty()) {
            return ConvertToJSONString(new HashMap<>());
        }
        else {
            final HashMap<String, String> jsonOutMap = parseQuery(query);
            jsonOutMap.put("scenario", ConvertToJSONString(getRandomScenario()));

            return ConvertToJSONString(jsonOutMap);
        }
    }

    @NotNull private Map<String, String> getRandomScenario()
    {
        final HashMap<String, String> scenarioMap = new HashMap<>();
        final Random random = new Random();

        if (mseed != -1) {
            random.setSeed(mseed);
        }

        String incident = mDataHandler.RetrieveIncident(random.nextInt(3));
        String step = mDataHandler.RetrieveTroubleStep(random.nextInt(3));
        String challenge = mDataHandler.RetrieveChallenge(random.nextInt(3));

        scenarioMap.put("incident", incident);
        scenarioMap.put("challenge", challenge);
        scenarioMap.put("troubleshooting", step);

        return scenarioMap;
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
    @NotNull
    private static String ConvertToJSONString(@NotNull Map<String, String> map) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(map);
    }

    public void setSeed(int seed) {
        mseed = seed;
    }
}
