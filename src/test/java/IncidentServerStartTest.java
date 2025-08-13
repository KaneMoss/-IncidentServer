import org.itscen.IncidentServer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncidentServerStartTest
{
    @Test
    public void DoesTheServerStartSuccessfullyTest()
    {
        final String expectedOutput = "Server has started" + System.lineSeparator();
        IncidentServer server = new IncidentServer(8080);
        assertEquals("", expectedOutput, "Server ran without expected output message");
    }

    @Test
    public void ServerIsRanOnHTTPPortNumberTest()
    {
        final int portnumber = 8080;
        IncidentServer server = new IncidentServer(portnumber);
        assertEquals(portnumber, server.getPortNumber(), "Server has the incorrect port open");
    }
}
