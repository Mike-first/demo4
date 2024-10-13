package com.hill.webui.pageobjects.blocks;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.hill.webui.pageobjects.BasePage;

import static com.codeborne.selenide.Selenide.$x;

/**
 * for steps debugging
 */
public class Localization extends BasePage {
    private static final String SELECTOR = "//div[contains(@class,'LanguageSelector')]";
    private static final String CURRENT = SELECTOR + "//div[contains(@class,'singleValue')]";
    private static final String CHANGER = SELECTOR + "//following-sibling::div[contains(@class, 'language-select__menu')]";
    private static final String CHANGER_BY_VALUE = CHANGER + "//*[text()='%s']";


    public static void ru() {
        if (current().equalsIgnoreCase("en")) toggle("ru");
    }

    public static void en() {
        if (current().equalsIgnoreCase("ru")) toggle("en");
    }

    public static String current() {
        return $x(CURRENT).getText();
    }

    private static void toggle(String lang) {
        $x(SELECTOR).scrollTo().click();
        Selenide.Wait().until(d -> $x(CHANGER).isDisplayed());
        SelenideElement change = $x(String.format(CHANGER_BY_VALUE, lang));
        Selenide.Wait().until(d -> change.isDisplayed());
        change.click();
    }
}
