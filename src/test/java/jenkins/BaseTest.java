package jenkins;

import attachAllure.Attach;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {
    @BeforeAll
    public static void beforeAll() {
        System.setProperty("selenoid.url", "http://localhost:4444/wd/hub");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        String platform = System.getProperty("platform");

        if (platform.equals("web")) {
            Configuration.browser = "chrome";
            Configuration.browserVersion = "87.0"; // 88.0 // 87.0

//            Configuration.browser = "opera";
//            Configuration.browserVersion = "81.0"; // 74.0 // 73.0
//
//            Configuration.browser = "firefox";
//            Configuration.browserVersion = "94.0"; // 86.0 // 85.0

//            Configuration.browser = "safari";
//            Configuration.browserVersion = "13.0";

//            capabilities.setCapability("browserName", "opera");
//            capabilities.setCapability("browserVersion", "73.0");

            DesiredCapabilities capabilities = new DesiredCapabilities();
            if (!System.getProperty("selenoid.url").equals("")) {
                Configuration.remote = System.getProperty("selenoid.url");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);
                capabilities.setCapability("enableLog", true);
            }
            Configuration.browserCapabilities = capabilities;
    }
        else if (platform.equals("selenoid_android")) {
//          Configuration.browser = SelenoidMobileDriver.class.getName();
            Configuration.startMaximized = false;
            Configuration.browserSize = null;
            Configuration.timeout = 10000;
        }
    }
    @AfterEach
    public void getAttach() {
        Attach.screenshotAs("Last screenshot");
        Attach.browserConsoleLogs();
        Attach.pageSource();
        Attach.addVideo();
    }
}

