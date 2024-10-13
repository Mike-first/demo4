package com.hill.webui.pageobjects.blocks;

import com.codeborne.selenide.SelenideElement;
import com.hill.webui.utilities.Timeouts;
import com.hill.webui.utilities.Web;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class AudiencePopup {
    private static final String BASE = "//div[@id='popupTargetSTATICSTRINGFORID']";
    private static final String OPTION_BY_NAME = BASE + "//span[text()='%s']//ancestor::a";
    private static final String CLOSE = BASE + "//button[@id='close']";
    private static final String CURRENT = BASE + "//a[contains(@class, 'active')]/span";

    public AudiencePopup() {
        Web.Wait.until("",
                () -> !$$x(BASE).isEmpty(),
                Timeouts.POPUP_TO_BE_SHOWN);
    }

    public void choose(String option) {
        if(current().equals(option)) close();
        $x(String.format(OPTION_BY_NAME, option)).click();
        Web.Wait.until("",
                () -> $$x(BASE).stream().noneMatch(SelenideElement::isDisplayed),
                Timeouts.ACCEPTED_DISAPPEAR);
    }

    public void close() {
        $x(CLOSE).click();
    }

    public String current() {
        return $x(CURRENT).getText();
    }
}
