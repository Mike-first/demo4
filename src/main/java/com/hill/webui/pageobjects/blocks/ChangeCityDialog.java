package com.hill.webui.pageobjects.blocks;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ChangeCityDialog {
    private static final String WINDOW = "//div[@class='city__change']";
    private static final String CONFIRM = "//a[contains(@class,'confirm-btn')]";
    private static final String CHANGE = "//a[contains(@class,'change-btn')]";
    private static final String YOUR_CITY = "//span[contains(@class,'city__name--main')]";

    public static void confirm() {
        $x(CONFIRM).click();
    }

    public static void change() {
        $x(CHANGE).click();
    }

    public static boolean isShown() {
        return !$$x(WINDOW).isEmpty();
    }

    public static String yourCity() {
        return $x(YOUR_CITY).getText();
    }
}
