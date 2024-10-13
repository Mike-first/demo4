package com.hill.cucumber.steps;

import com.hill.cucumber.TestContext;
import com.hill.webui.pageobjects.CartPage;
import com.hill.webui.utilities.Web;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.Map;

public class CartSteps {
    CartPage cartPage = new CartPage();

    @Then("analysis price in cart equal to previously added analysis")
    @Step("analysis price in cart equal to previously added analysis")
    public void analysisPriceInCartEqualToPreviouslyAddedAnalysis() {
        Map<String, String> addedItem = (Map<String, String>) TestContext.instance()
                .getData("theUserAddAnyAnalysisToCart.addedItem");
        int itemPriceInCart = cartPage.getItemByTitle(addedItem.get("title")).getPrice();
        Web.addScreenshot();
        Assert.assertEquals(itemPriceInCart,
                Integer.parseInt(addedItem.get("price")),
                "Different price");

        Assert.assertNotEquals(itemPriceInCart, 10000, "Price should not be equal to 10_000");
        if (itemPriceInCart > 10000) System.out.println("Price more than 10_000");
        else System.out.println("Price less than 10_000");
    }

}
