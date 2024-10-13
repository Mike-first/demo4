package com.hill.webui.pageobjects.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.hill.webui.pageobjects.BasePage;

import static com.codeborne.selenide.Selenide.$x;

public class Localization2 extends BasePage {
    private static final String SELECTOR = "//div[@class='invitro_header-switch_lang']";
    private static final String CHANGER = "//div[@class='invitro_header-switch_lang-more']";

    public static void ru() {
        if (current().contains("en")) toggle();
    }

    public static void en() {
        if (current().contains("ru")) toggle();
    }

    public static String current() {
        return getVisible(SELECTOR)
                .getAttribute("innerText").toLowerCase()
                .replace(",", "");
    }

    private static void toggle() {
        getVisible(SELECTOR).scrollTo().click();
        Selenide.Wait().until(d -> $x(CHANGER).is(Condition.visible));
        Selenide.Wait().until(d -> $x(CHANGER).isDisplayed());
        getVisible(CHANGER).click();
    }
}
