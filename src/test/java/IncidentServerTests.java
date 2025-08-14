import org.itscen.IncidentServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.http.HttpClient;

/**
 * Base class for testing Incident Server. Provides set up of output stream, and starts server
 */
public class IncidentServerTests
{
    protected static final String BASE_URL = "http://localhost:8080/api";
    protected static final PrintStream OUT = System.out;
    protected static final HttpClient CLIENT = HttpClient.newHttpClient();

    protected final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    protected static IncidentServer mTestServer;

    @BeforeEach
    public void setUpClass() throws InterruptedException {
        System.setOut(new PrintStream(outputStream));

        mTestServer = IncidentServer.getInstance();
        Thread.sleep(1000);
    }

    @AfterAll
    public static void restoreSystemOut() {
        System.setOut(OUT);
        mTestServer.StopServer();
    }
}
