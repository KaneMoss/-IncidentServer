import org.itscen.utils.EHTTPStatusCode;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the Server response to GET requests
 */
public class IncidentServerResponseTest extends IncidentServerTests
{

    @Test
    public void DoesUnmappedURLReturn404ResponseCodeTest() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/nonexistentpage"))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(EHTTPStatusCode.NOT_FOUND.getCode(), response.statusCode());
    }

    @Test
    public void DoesNonImplementedRequestReturn405ResponseCodeTest() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .PUT(HttpRequest.BodyPublishers.ofString(""))
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(EHTTPStatusCode.METHOD_NOT_ALLOWED.getCode(), response.statusCode());
    }

    @Test
    public void DoesGETRequestReturnExpectedResponseTest() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(EHTTPStatusCode.OK.getCode(), response.statusCode());
    }

    @Test
    public void DoesGETRequestWithParametersReturnSameParameters() throws IOException, InterruptedException {
        final String requestParams = "technology=Cloud Computing" + "&role=System Administrator"
                + "&environment=Enterprise Network";
        String requestParams2 = URLEncoder.encode(requestParams);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?q=" + requestParams2))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        assertTrue(response.body().contains("Cloud Computing"));
        assertTrue(response.body().contains("System Administrator"));
        assertTrue(response.body().contains("Enterprise Network"));
    }

    @Test
    public void DoesGETRequestWithNoParamsReturnEmpty() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals("{}", response.body());
    }
}
