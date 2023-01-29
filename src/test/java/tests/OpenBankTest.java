package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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

    @Feature("Заголовки разделов сайта")
    @Story("Первый раздел на главной странице")
    @Test
    @DisplayName("Проверить, что заголовок корректен")
    void checkTitle() {
        step("Открыть сайт", () -> open(baseUrl));
        step("Подтвердить, что заголовок соответствует требуемому", () -> openBankMainPage.siteTitleCheck("Лучшее от банка"));
    }

    @Feature("Заголовки разделов сайта")
    @Story("Разделы на английской версии сайта")
    @ValueSource(strings = {"RETAIL BANKING", "SME`S", "CORPORATE CLIENTS", "FINANCIAL INSTITUTIONS", "PRIVATE BANKING"})
    @ParameterizedTest(name = "Проверить, что заголовки на английской версии корректны {0}")
        //@DisplayName("Проверка заголовков на английской версии сайта")
    void checkEnChapters(String testData) {
        step("Открыть сайт", () -> open(baseUrl));
        step("Перейти на английскую версию", () -> openBankMainPage.goToEnVersion("EN"));
        step("Подтвердить, что название раздела отображается корректно", () -> openBankMainPage.engChaptersCheck(testData));
    }

    @Feature("Поиск по сайту")
    @Story("Работа строки поиска")
    @Test
    @DisplayName("Ввести значение и проверить, что в результатах поиска оно есть")
    void checkSiteSearch() {
        step("Открыть сайт", () -> open(baseUrl));
        step("Активировать поле поиска и ввести слово", () -> openBankMainPage.siteSearchStart("карта"));
        step("Подтвердить, что во всех результатах поиска присутствует искомое значение", () -> openBankMainPage.siteSearchControl("карта"));
    }

    @Feature("Предельные суммы вкладов в онлайн-калькуляторе")
    @Story("Мин/макс суммы корректны")
    @CsvSource(value = {"50 000, 10 млн"})
    @ParameterizedTest(name = "Проверить мин/макс значения сумм в онлайн-калькуляторе")
        //@DisplayName("Проверка мин/макс значений сумм в онлайн-калькуляторе")
    void checkMinMaxOnlineCalculator(String min, String max) {
        step("Открыть сайт", () -> open(baseUrl));
        step("Подтвердить, что минимальная и максимальная суммы вклада соответствуют требуемым", () -> openBankMainPage.limitSumCalculator(min, max));
    }

    @Feature("Отображение курсов валют в отделениях")
    @Story("Сравнить курсы покупки и продажи в отделениях ")
    @Test
    @DisplayName("Курс продажи USD превышает курс покупки")
    void checkExchangeRates() {
        step("Открыть сайт", () -> open(baseUrl));
        step("Сравнить отображаемые курсы и подтвердить, что курс продажи превышает курс покупки", () -> openBankMainPage.exchangeRates());
    }

    @Feature("Интернет-банк для частных лиц")
    @Story("URL страницы ИБ для частных лиц правильный")
    @Test
    @DisplayName("Проверить, что URL в адресной строке правильный")
    void checkInternetBankUrl() {
        step("Открыть главную страницу сайта", () -> open(baseUrl));
        step("Перейти на страницу ИБ для частных лиц по кнопке", () -> openBankIntBankPage.goToInternetBankPage());
        step("Подтвердить, что в адресной строке указан правильный URL", () -> openBankIntBankPage.urlCheckInternetBank());
    }

    @Feature("Интернет-банк для частных лиц")
    @Story("Попытка авторизации с некорректными данными")
    @Test
    @DisplayName("Проверить, что вход в ИБ невозможен")
    void checkInternetBankAuthFail() {
        step("Открыть главную страницу сайта", () -> open(baseUrl));
        step("Перейти на страницу ИБ для частных лиц по кнопке", () -> openBankIntBankPage.goToInternetBankPage());
        step("Ввести логин и пароль в форму авторизации - один или оба содержат пробелы", () -> openBankIntBankPage.internetBankAuthSubmit());
        step("Получить сообщение о неверных логине и пароле", () -> openBankIntBankPage.internetBankAuthFail("Неправильно указаны логин или пароль"));
    }
}

