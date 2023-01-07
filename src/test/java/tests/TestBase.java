package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Project;
import helpers.AllureAttachments;
import helpers.DriverSettings;
import helpers.DriverUtils;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;

@ExtendWith({AllureJunit5.class})
public class TestBase {

       @BeforeAll
        static void setUp() {
            SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
            DriverSettings.configure();
            Configuration.baseUrl = "https://www.open.ru";
            Configuration.pageLoadTimeout = 50000;
            Configuration.browserSize = "1600x1200";
        }

      /*  @AfterEach
        public void addAttachments() {
            String sessionId = DriverUtils.getSessionId();

            AllureAttachments.addScreenshotAs("Last screenshot");
            AllureAttachments.addPageSource();
            AllureAttachments.addBrowserConsoleLogs();
            Selenide.closeWebDriver();

           /* if (Project.isVideoOn()) {
                AllureAttachments.addVideo(sessionId);
            }
        }*/
    }
