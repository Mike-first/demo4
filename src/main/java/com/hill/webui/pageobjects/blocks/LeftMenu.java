package com.hill.webui.pageobjects.blocks;

import com.codeborne.selenide.SelenideElement;
import com.hill.webui.pageobjects.BasePage;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class LeftMenu extends BasePage {
    private static final String BASE = "//div[contains(@class,'side-bar__box')]";
    private static final String ITEMS = BASE + "//li[@class='side-bar-second__items']";
    private static final String ITEM_BY_NAME = BASE + "//a[contains(@class, 'side-bar-second') and text()='%s']";
    private static final String ITEM_NAME = ". /a";

    public List<String> getItemsNames() {
        return $$x(ITEMS)
                .stream()
                .map(se -> se.$x(ITEM_NAME).getText())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public LeftMenuItem getItem(String name) {
        SelenideElement e = $x(String.format(ITEM_BY_NAME, name));
        e.scrollTo();
        return new LeftMenuItem(e);
    }

    public SelenideElement getElement() {
        return $x(BASE);
    }

//    public List<LeftMenuItem> getItems() {
//        return $$x(ITEMS)
//                .stream()
//                .filter(se -> !se.$x(ITEM_NAME).getText().isEmpty())
//                .map(LeftMenuItem::new)
//                .collect(Collectors.toList());
//    }
}
