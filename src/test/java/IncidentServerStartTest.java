import org.itscen.IncidentServer;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncidentServerStartTest
{
    protected final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;
    protected static IncidentServer mTestServer;

    @BeforeEach
    public void setUpClass() throws InterruptedException {
        System.setOut(new PrintStream(outputStream));

        mTestServer = IncidentServer.getInstance();
        Thread.sleep(1000);
    }

    @AfterEach
    public void restoreSystemOut() {
        System.setOut(originalOut);
    }

    @Test
    public void DoesTheServerStartSuccessfullyTest()
    {
        final String expectedOutput = "Server has started" + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString(), "Server ran without expected output message");
    }

    @Test
    public void ServerIsRanOnHTTPPortNumberTest()
    {
        final int portnumber = 8080;
        assertEquals(portnumber, mTestServer.getPortNumber(), "Server has the incorrect port open");
    }

    @AfterAll
    public static void tearDown() {
        mTestServer.StopServer();
    }
}
