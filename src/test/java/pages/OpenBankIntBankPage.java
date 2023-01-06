package pages;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class OpenBankIntBankPage {
    SelenideElement
            buttonIntBank = $(".ant-btn.ant-btn-round.ant-btn-sm.main-page-header__sub-nav-internet-bank-button"),
            listIntBank = $("a[href='https://ib.open.ru/login']"),
            loginField = $(".wb-editable__input"),
            passwordField = $(".wb-editable__input.wb-password-toggle-input"),
            submitAuth = $("button[type='submit']"),
            errorMessage = $(".wb-auth-error-message");

    private static final Faker faker = new Faker();
    private static String
            login = faker.harryPotter().character() + " " + faker.harryPotter().house(),
            password = faker.harryPotter().spell();

    public static String
            internetBankUrl = "https://ib.open.ru/webbank/#/login";

    public OpenBankIntBankPage goToInternetBankPage() {
        buttonIntBank.click();
        listIntBank.click();
        return this;
    }

    public OpenBankIntBankPage urlCheckInternetBank() {
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(internetBankUrl, currentUrl);
        return this;
    }

    public OpenBankIntBankPage internetBankAuthSubmit() {
        loginField.click();
        loginField.setValue(login);
        passwordField.click();
        passwordField.setValue(password);
        submitAuth.click();
        return this;
    }

    public OpenBankIntBankPage internetBankAuthFail(String value) {
        errorMessage.shouldHave(text(value));
        return this;
    }
}

