import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Tests for the returned JSON data when a correct GET request with query params is made
 * */
public class IncidentServerJSONResponseTest extends IncidentServerTests
{
    @Test
    public void DoesGETReturnAnITScenarioTest() throws IOException, InterruptedException {
        final int seed = 1000;
        final String expectedChallenge = "Forgot password";
        final String expectedIncident = "Tighten the firewall";
        final String expectedSteps = "Bad actor got past firewall";
        final String requestParams = URLEncoder.encode("technology=Cloud Computing" + "&role=System Administrator"
                + "&environment=Enterprise Network");

        mTestServer.setSeed(seed);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "?q=" + requestParams))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        assertTrue(response.body().contains(expectedChallenge));
        assertTrue(response.body().contains(expectedIncident));
        assertTrue(response.body().contains(expectedSteps));
    }
}
