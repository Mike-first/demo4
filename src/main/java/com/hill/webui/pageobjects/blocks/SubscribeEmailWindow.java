package com.hill.webui.pageobjects.blocks;

import com.codeborne.selenide.SelenideElement;
import com.hill.webui.utilities.Timeouts;
import com.hill.webui.utilities.Web;
import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class SubscribeEmailWindow {
    private static final Logger log = LoggerFactory.getLogger(SubscribeEmailWindow.class);

    private static final String WINDOW = "//div[@class='popmechanic-title']//ancestor::div[@class='popmechanic-js-wrapper']";
    private static final String CLOSE = WINDOW + "//div[contains(@class, 'close')]";

    @Step("wait for subscribe e-mail and close")
    public static void waitForAndClose() {
        if (Web.Wait.notStrict("Subscribe window was not shown",
                () -> !$$x(WINDOW).isEmpty(), Timeouts.SUBSCRIBE_WINDOW)) {
            log.info("SubscribeEmail dialog window found");
            Web.addScreenshot();
            Web.Wait.forMillis(200);
            $x(CLOSE).click();
            Web.Wait.until("Subscribe window was not closed",
                    () -> $$x(WINDOW).stream().noneMatch(SelenideElement::isDisplayed),
                    Timeouts.ACCEPTED_DISAPPEAR);
            log.info("SubscribeEmail dialog window closed");
        }else {
            log.info("SubscribeEmail dialog window not found");
        }
    }
}
