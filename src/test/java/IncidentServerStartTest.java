import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncidentServerStartTest extends IncidentServerTests
{
    @Test
    public void DoesTheServerStartSuccessfullyTest() {
        final String expectedOutput = "Server has started" + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString(), "Server ran without expected output message");
    }

    @Test
    public void ServerIsRanOnHTTPPortNumberTest() {
        final int expectedPortNumber = 8080;
        assertEquals(expectedPortNumber, mTestServer.getPortNumber(), "Server has the incorrect port open");
    }
}
