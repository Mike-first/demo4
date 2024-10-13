package com.hill.webui.pageobjects.blocks;

import com.codeborne.selenide.SelenideElement;
import com.hill.webui.pageobjects.BasePage;
import com.hill.webui.utilities.Web;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LeftMenuItem extends BasePage {
    private final SelenideElement item;

    private final String ITEMS = "//li[@class='side-bar-third__items']";
    private final String ITEM_BY_NAME = "//a[contains(@class, 'side-bar-third') and normalize-space(text())='%s']";
    private static final String ITEM_NAME = ". /a";

    public LeftMenuItem(SelenideElement item) {
        this.item = item;
    }

    public boolean isExpandable() {
        return Web.Is.hasPseudoElement(item, "::after");
    }

    public LeftMenuItem expand() {
        if (isExpandable()) {
            Web.Click.blind(item.scrollTo(), 0, item.getRect().getWidth() / 2 - 25);
        } else System.out.println("not expandable");
        return this;
    }

    public List<String> getSubItemsNames() {
        return $$x(ITEMS)
                .stream()
                .map(se -> se.$x(ITEM_NAME).getText())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public SelenideElement getSubItem(String name) {
        return $x(String.format(ITEM_BY_NAME,name)).scrollTo();
    }

    public SelenideElement getThisElement(){
        return item.scrollTo();
    }

}
