package com.hill.webui.pageobjects;

import com.hill.webui.pageobjects.blocks.CartItem;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CartPage extends BasePage {
    private static final String PROD_CONTAINER = "//div[@class = 'CartProductsPanel_productList__ZwxTD']";
    private static final String PRODS = PROD_CONTAINER + "//div[@class = 'CartProduct_product__euiRX']";
    private static final String SUM = "//div[@class = 'SummaryBlock_cartCostPositionCost__BYAgG']";
    private static final String PROD_SUM = "//div[@class = 'SummaryBlock_cartCostPosition__7GXLO']//span";

    public int totalSum() {
        return Integer.parseInt($$x(SUM).stream()
                .filter(e -> e.getCssValue("color").equals("rgb(0, 151, 179)"))
                .collect(Collectors.toList()).get(0).getText());
    }

    public int productSum() {
        return Integer.parseInt($x(PROD_SUM).getText().replaceAll("[^0-9]+", ""));
    }

    public List<CartItem> getItems() {
        return $$x(PRODS).stream().map(CartItem::new).collect(Collectors.toList());
    }

    public CartItem getItemByTitle(String title) {
        return getItems().stream().filter(ci -> ci.getTitle().equals(title))
                .findFirst().get();
    }

}
