package com.hill.webui.pageobjects;

import com.codeborne.selenide.SelenideElement;
import com.hill.webui.utilities.Timeouts;
import com.hill.webui.utilities.Web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class BasePage {
    private static final Logger log = LoggerFactory.getLogger(BasePage.class);

    public static void waitPageLoading() {
        Web.Wait.pageLoading();
    }

    public static SelenideElement getVisible(String xpath) {
        return $$x(xpath).stream()
                .filter(SelenideElement::isDisplayed)
                .findFirst().get();
    }

    public static String pageTitle() {
        String title = "//div[@id='titlePage']/h1";
        String res = "";
        if (Web.Wait.notStrict("can't find title",
                () -> $$x(title).stream().anyMatch(SelenideElement::isDisplayed), Timeouts.TITLE)) {
            log.info("Cookie dialog window found");
            res = $x(title).getText();
        } else {
            log.info("Cookie dialog window not found");
        }
        return res;
    }
}
