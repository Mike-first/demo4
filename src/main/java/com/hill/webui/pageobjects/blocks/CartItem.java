package com.hill.webui.pageobjects.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.hill.webui.pageobjects.BasePage;

public class CartItem extends BasePage {
    private static final String DELETE = ". //button[contains(@class, 'productDelete')]";
    private static final String PRICE = ". //div[contains(@class, 'productPrice')]/span";
    private static final String TITLE = ". //button[contains(@class, 'productName')]";

    private final SelenideElement e;

    public CartItem(SelenideElement e) {
        this.e = e;
    }

    public CartItem exclude(){
        e.$x(DELETE).shouldBe(Condition.visible).scrollTo().click();
        e.should(Condition.disappear);
        return this;
    }

    public boolean isPresent(){
        return !e.isDisplayed();
    }

    public String getTitle(){
        return e.$x(TITLE).shouldBe(Condition.visible).getText();
    }

    public int getPrice(){
        return Integer.parseInt(e.$x(PRICE).shouldBe(Condition.visible)
                .getText().replaceAll("[^0-9]+", ""));
    }
}
