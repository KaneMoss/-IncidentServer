import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the Server response to GET requests
 */
public class IncidentServerGETTest extends IncidentServerTests
{
    private static final HttpClient client = HttpClient.newHttpClient();

    @Test
    public void DoesGETRequestReturnExpectedResponseTest() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080" + "/api"))
                                                      .GET()
                                                      .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }

    @Test
    public void DoesGETRequestWithParametersReturnSameParameters() throws IOException, InterruptedException {
        final String requestParams = "technology=Cloud Computing" + "&role=System Administrator"
                + "&environment=Enterprise Network";
        String requestParams2 = URLEncoder.encode(requestParams);

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080" + "/api" + "?q=" + requestParams2))
                                                      .GET()
                                                      .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String res =response.body();
        assertTrue(response.body().contains("Cloud Computing"));
        assertTrue(response.body().contains("System Administrator"));
        assertTrue(response.body().contains("Enterprise Network"));
    }

    @Test
    public void DoesGETRequestWithNoParamsReturnEmpty() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080" + "/api"))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals("{}", response.body());
    }
}
