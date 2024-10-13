package com.hill.webui.pageobjects.blocks;

import com.codeborne.selenide.SelenideElement;
import com.hill.webui.utilities.Timeouts;
import com.hill.webui.utilities.Web;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CookieDialog {
    private static final Logger log = LoggerFactory.getLogger(CookieDialog.class);
    private static final String WINDOW = "//div[contains(@class, 'cookie--popup')]";
    private static final String CLOSE = WINDOW + "//a[contains(@class, 'close-button')]";

    @Step("wait for cookie and close")
    public static void waitForAndClose() {
        if (Web.Wait.notStrict("Cookie window was not shown",
                () -> $$x(WINDOW).stream().anyMatch(SelenideElement::isDisplayed), Timeouts.COOKIE_ACCEPT)) {
            log.info("Cookie dialog window found");
            Web.addScreenshot();
            Web.Wait.forMillis(200);
            $x(CLOSE).click();
            Web.Wait.until("Subscribe window was not closed",
                    () -> $$x(WINDOW).stream().noneMatch(SelenideElement::isDisplayed),
                    Timeouts.ACCEPTED_DISAPPEAR);
            log.info("Cookie dialog window closed");
        } else {
            log.info("Cookie dialog window not found");
        }
    }
}
