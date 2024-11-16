import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class MainTest {

    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    //@Disabled("Test was disabled in testing with other tests. Run this manually")
    void mainShouldCompleteWithin22Seconds() throws Exception {
        Main.main(new String[]{});
    }
}