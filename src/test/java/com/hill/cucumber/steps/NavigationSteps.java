package com.hill.cucumber.steps;

import com.hill.webui.core.Pages;
import com.hill.webui.pageobjects.blocks.Header;
import com.hill.webui.utilities.Web;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class NavigationSteps {

    @Given("the user navigated to the {string} page")
    @Step("the user navigated to the {page} page")
    public void theUserNavigatedToThePage(String page) {
        open(Pages.valueOf(page.toUpperCase().replace(" ", "")).link());
        Web.Wait.pageLoading();
        Web.addScreenshot();
    }

    @And("the user open cart")
    @Step("the user open cart")
    public void theUserOpenCart() {
        new Header().toCart();
        Web.addScreenshot();
    }
}
