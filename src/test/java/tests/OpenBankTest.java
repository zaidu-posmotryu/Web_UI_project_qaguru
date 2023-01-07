package tests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pages.OpenBankIntBankPage;
import pages.OpenBankMainPage;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class OpenBankTest extends TestBase {
    OpenBankMainPage openBankMainPage = new OpenBankMainPage();
    OpenBankIntBankPage openBankIntBankPage = new OpenBankIntBankPage();

    @Test
    @Description("Проверка заголовка первого раздела сайта")
    @DisplayName("Проверка заголовка первого раздела сайта")
    void checkTitle() {
        step("Открыть сайт", () -> open(baseUrl));
        step("Подтвердить, что заголовок соответствует требуемому", () -> openBankMainPage.siteTitleCheck("Лучшее от банка"));
    }

    @ValueSource(strings = {"RETAIL BANKING", "SME`S", "CORPORATE CLIENTS", "FINANCIAL INSTITUTIONS", "PRIVATE BANKING"})
    @ParameterizedTest(name = "Проверка заголовков на английской версии сайта {0}")
    @Description("Проверка заголовков на английской версии сайта")
    //@DisplayName("Проверка заголовков на английской версии сайта")
    void checkEnChapters(String testData) {
        step("Открыть сайт", () -> open(baseUrl));
        step("Перейти на английскую версию", () -> openBankMainPage.goToEnVersion("EN"));
        step("Подтвердить, что название раздела отображается корректно", () -> openBankMainPage.engChaptersCheck(testData));
    }

    @Test
    @Description("Проверка поиска по сайту")
    @DisplayName("Проверка поиска по сайту")
    void checkSiteSearch() {
        step("Открыть сайт", () -> open(baseUrl));
        step("Активировать поле поиска и ввести значение", () -> openBankMainPage.siteSearchStart("карта"));
        step("Подтвердить, что во всех результатах поиска присутствует искомое значение", () -> openBankMainPage.siteSearchControl("карта"));
    }

    @CsvSource(value = {"50 000, 10 млн"})
    @ParameterizedTest(name = "Проверка мин/макс значений сумм в онлайн-калькуляторе")
    @Description("Проверка мин/макс значений сумм в онлайн-калькуляторе")
    //@DisplayName("Проверка мин/макс значений сумм в онлайн-калькуляторе")
    void checkMinMaxOnlineCalculator(String min, String max) {
        step("Открыть сайт", () -> open(baseUrl));
        step("Подтвердить, что минимальная и максимальная суммы вклада соответствуют требуемым", () -> openBankMainPage.limitSumCalculator(min, max));
    }

    @Test
    @Description("Проверка курсов покупки и продажи USD в отделениях")
    @DisplayName("Проверка курсов покупки и продажи USD в отделениях")
    void checkExchangeRates() {
        step("Открыть сайт", () -> open(baseUrl));
        step("Сравнить отображаемые курсы и подтвердить, что курс продажи превышает курс покупки", () -> openBankMainPage.exchangeRates());
    }

    @Test
    @Description("Проверка URL Интернет-банка для частных лиц")
    @DisplayName("Проверка URL Интернет-банка для частных лиц")
    void checkInternetBankUrl() {
        step("Открыть главную страницу сайта", () -> open(baseUrl));
        step("Перейти на страницу ИБ для частных лиц по кнопке", () -> openBankIntBankPage.goToInternetBankPage());
        step("Подтвердить, что в адресной строке указан правильный URL", () -> openBankIntBankPage.urlCheckInternetBank());
    }

    @Test
    @Description("Проверка входа в Интернет-банк с некорректными авторизационными данными")
    @DisplayName("Проверка входа в Интернет-банк с некорректными авторизационными данными")
    void checkInternetBankAuthFail() {
        step("Открыть главную страницу сайта", () -> open(baseUrl));
        step("Перейти на страницу ИБ для частных лиц по кнопке", () -> openBankIntBankPage.goToInternetBankPage());
        step("Ввести логин и пароль в форму авторизации - один или оба содержат пробелы", () -> openBankIntBankPage.internetBankAuthSubmit());
        step("Получить сообщение о неверных логине и пароле", () -> openBankIntBankPage.internetBankAuthFail("Неправильно указаны логин или пароль"));
    }
}

