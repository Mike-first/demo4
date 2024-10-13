package com.hill.webui.pageobjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class NoRegistrationResultsPage extends BasePage {

    private static final String CONTAINER = "//div[contains(@class, 'bodyContainer')]";
    private static final String TITLE = CONTAINER + "/h2";
    private static final String ERROR = CONTAINER + "//div[contains(@class, 'error')]";
    private static final String GET_RES_BTN = CONTAINER + "//button[contains(@class, 'xwbHQ')]";
    private static final String ORDER_INPUT = "//input[@name = 'orderNumber']";
    private static final String BD_INPUT = "//input[@name = 'birthday']";
    private static final String SURNAME_INPUT = "//input[@name = 'lastName']";

    public String getTitle() {
        return $x(TITLE).getText();
    }

    public boolean isShown() {
        return !$$x(CONTAINER).isEmpty();
    }

    public NoRegistrationResultsPage getResults() {
        //button[contains(text(), 'Найти результаты')] - не вариант потому что локализация?
        getVisible(GET_RES_BTN).click();
        waitPageLoading();
        return this;
    }

    public boolean isErrorShown() {
        return !$$x(ERROR).isEmpty();
    }

    public String getErrorMessage() {
        return $x(ERROR).text();
    }

    public boolean isInputsError() {
        return getInputBorder($x(ORDER_INPUT)).equals("rgb(255, 0, 0)") //to constants
                && getInputBorder($x(BD_INPUT)).equals("rgb(255, 0, 0)")
                && getInputBorder($x(SURNAME_INPUT)).equals("rgb(255, 0, 0)");
    }

    private String getInputBorder(SelenideElement e) {
        return e.getCssValue("border-color");
    }

    public NoRegistrationResultsPage fillCode(String arg) {
        $x(ORDER_INPUT).sendKeys(arg);
        return this;
    }

    public NoRegistrationResultsPage fillBirthday(String arg) {
        $x(BD_INPUT).sendKeys(arg);
        $x(BD_INPUT).sendKeys(Keys.ENTER);
        return this;
    }

    public NoRegistrationResultsPage fillSurname(String arg) {
        $x(SURNAME_INPUT).sendKeys(arg);
        return this;
    }

    public String getCode() {
        return $x(ORDER_INPUT).getAttribute("value");
    }

    public String getBirthday() {
        return $x(BD_INPUT).getAttribute("value");
    }

    public String getSurname() {
        return $x(SURNAME_INPUT).getAttribute("value");
    }
}
