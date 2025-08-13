import org.itscen.IncidentServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Base class for testing Incident Server. Provides set up of output stream, and starts server
 */
public class IncidentServerTests
{
    protected final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    protected static final PrintStream originalOut = System.out;
    protected static IncidentServer mTestServer;

    @BeforeEach
    public void setUpClass() throws InterruptedException {
        System.setOut(new PrintStream(outputStream));

        mTestServer = IncidentServer.getInstance();
        Thread.sleep(1000);
    }

    @AfterAll
    public static void restoreSystemOut() {
        System.setOut(originalOut);
        mTestServer.StopServer();
    }
}
