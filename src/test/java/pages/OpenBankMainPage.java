package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

public class OpenBankMainPage {
    SelenideElement
            siteTitle = $(".ant-typography.open-ui-title.open-ui-title-theme-default.blockTitle.offers__header-title"),
            engSite = $(".ant-typography.open-ui-text.open-ui-text-theme-default.smallText.main-page-header__main-nav-locale-text"),
            engChapters = $(".sections-menu-items"),
            siteSearch = $(".open-ui-icon.search-open"),
            searchInput = $("input[type='search']"),
            selectorFrame = $(By.cssSelector(".iframe-deposits iframe"));
    ElementsCollection
            results = $$(".card"),
            rates = $$(".main-page-exchange__rate"),
            depositSum = $$(".ant-typography.open-ui-text.open-ui-text-theme-default.regularText.legend-text");

    public OpenBankMainPage siteTitleCheck(String value) {
        siteTitle.shouldHave(text(value));
        return this;
    }

    public OpenBankMainPage goToEnVersion(String value) {
        engSite.shouldHave(text(value)).click();
        return this;
    }

    public OpenBankMainPage engChaptersCheck(String value) {
        engChapters.shouldHave(text(value));
        return this;
    }

    public OpenBankMainPage siteSearchStart(String value) {
        siteSearch.click();
        searchInput.setValue(value).pressEnter();
        return this;
    }

    public OpenBankMainPage siteSearchControl(String value) {
        for (SelenideElement element : results) {
            element.shouldHave(text(value));
        }
        return this;
    }

    public OpenBankMainPage limitSumCalculator(String min, String max) {
        switchTo().frame(selectorFrame);
        depositSum.get(0).shouldHave(text(String.valueOf(min)));
        depositSum.get(1).shouldHave(text(String.valueOf(max)));
        return this;
    }

    public OpenBankMainPage exchangeRates() {
        String buy = rates.get(0).getText();
        double buyrate = Double.parseDouble(buy.replace(",", "."));
        String sell = rates.get(1).getText();
        double sellrate = Double.parseDouble(sell.replace(",", "."));
        assertThat(sellrate).isGreaterThan(buyrate);
        return this;
    }
}

