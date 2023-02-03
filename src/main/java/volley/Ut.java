package volley;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Ut {
    public static final Duration BLOCK_WAIT_TIME = Duration.of(1000, ChronoUnit.MILLIS);

    public static final String ERR_MISSING_PARAMS = "Command is missing parameters. Run a help command for the specs.";

    public static boolean isValidSearchQuery(String search, String toMatch) {
        return search.equalsIgnoreCase(toMatch); // TODO: implement
    }
}
