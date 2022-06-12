import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestMode {

    @BeforeEach
    private void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void shouldTestHappyPath() {
        open("http://localhost:9999/");
    }
}
