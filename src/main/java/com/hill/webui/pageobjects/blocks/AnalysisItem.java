package com.hill.webui.pageobjects.blocks;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.hill.webui.pageobjects.BasePage;
import com.hill.webui.utilities.Web;

public class AnalysisItem extends BasePage {

    private static final String TITLE = ". //div[@class = 'analyzes-item__title']//a";
    private static final String PRICE = ". //div[@class = 'analyzes-item__total--sum']";
    private static final String ADD = ". //span[@class = 'ds_b_icon ds_b_3x ds_b_inline']";
    private static final String ADDED = ". //a[contains(@class,'active ds_b_inline')]";

    private final SelenideElement e;

    public AnalysisItem(SelenideElement e) {
        this.e = e;
    }

    public int getPrice() {
        return Integer.parseInt(e.$x(PRICE).shouldBe(Condition.visible)
                .getText().replaceAll("[^0-9]+", ""));
    }

    public String getTitle() {
        return e.$x(TITLE).shouldBe(Condition.visible).getText();
    }

    public AnalysisItem add() {
        e.$x(ADD).scrollTo().click();
        Web.Wait.until("", this::isAdded, 3);
        return this;
    }

    public boolean isAdded() {
        return !e.$$x(ADDED).isEmpty();
    }


}
