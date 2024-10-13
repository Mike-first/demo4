package com.hill.webui.pageobjects.blocks;

import com.hill.webui.pageobjects.BasePage;
import com.hill.webui.pageobjects.CartPage;
import com.hill.webui.pageobjects.NoRegistrationResultsPage;
import com.hill.webui.pageobjects.SearchResultPage;
import com.hill.webui.utilities.Timeouts;
import com.hill.webui.utilities.Web;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

public class Header extends BasePage {
    private static final String HEADER = "//header";
    private static final String ANALYSES_RES = HEADER + "//a[contains(@class, 'get_result')]";
    private static final String CART = "//div[@id = 'headerCartDynamicFrame']";
    private static final String SEARCH = "//input[@name='q']";
    private static final String AUDIENCE = "//div[@id='buttonOpenPopupTargetSTATICSTRINGFORID']";

    public NoRegistrationResultsPage analyzesResults() {
        $x(ANALYSES_RES).click();
        waitPageLoading();
        return new NoRegistrationResultsPage();
    }


    public City city() {
        return new City();
    }

    public HeaderMenu menu() {

        return new HeaderMenu();
    }

    public SearchResultPage searchFor(String req) {
        $x(SEARCH).scrollTo().sendKeys(req); //после .shouldBe(Condition.visible) скролл тупит. вот тебе и селенид.
        $x(SEARCH).sendKeys(Keys.ENTER);
        Web.Wait.until("Value was not sent to search",
                () -> $x(SEARCH).getAttribute("value").equals(req),
                Timeouts.TEXT_TYPE);
        waitPageLoading();
        return new SearchResultPage();
    }

    public static class City {
        private static final String CITY = "//div[@id='city']";
        private static final String CURRENT_CITY = CITY + "//span[contains(@class,'city__name--label')]";
        private static final String INPUT = "//input[@class='change-city-search-input']";
        private static final String RESULTS = "//div[@id='eac-container-select-basket-city-input']";
        private static final String RESULT_BY_NAME = "//div[@class='eac-item']/b[text()='%s']";

        public City openSearch() {
            $x(CITY).scrollTo().click();
            Web.Wait.forMillis(100);
            return this;
        }

        public City search(String city) {
            $x(INPUT).scrollTo().sendKeys(city);
            return this;
        }

        public City choose(String city) {
            $x(String.format(RESULT_BY_NAME, city)).scrollTo().click();
            return this;
        }

        public City change(String city) {
            openSearch();
            ChangeCityDialog.change();
            search(city).choose(city);
            return this;
        }

        public String current() {
            return $x(CURRENT_CITY).getText();
        }
    }

    public CartPage toCart() {
        $x(CART).scrollTo().click();
        waitPageLoading();
        return new CartPage();
    }

    public AudiencePopup audience() {
        $x(AUDIENCE).scrollTo().click();
//        waitPageLoading();
        return new AudiencePopup();
    }
}
