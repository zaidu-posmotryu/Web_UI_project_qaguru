package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attachments;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.Map;

@ExtendWith({AllureJunit5.class})
public class TestBase {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("browserVersion", "100.0");
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));
        System.setProperty("chromeoptions.prefs", "intl.accept_languages=ru");
        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://www.open.ru";
        Configuration.pageLoadTimeout = 200000;
        Configuration.browserSize = "1600x1200";
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @AfterEach
    void addAttachments() {

        Attachments.screenshotAs("Скриншот");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
        //Selenide.closeWebDriver();

    }
}

