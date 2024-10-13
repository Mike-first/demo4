package com.hill.webui.pageobjects;

import com.codeborne.selenide.SelenideElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

public class SearchResultPage extends BasePage {
    private static final String ITEMS = "//div[contains(@class, 'search_card')]";

    public List<SearchResultItem> getResults() {
        return $$x(ITEMS).stream().map(SearchResultItem::new).collect(Collectors.toList());
    }

    public static class SearchResultItem {
        private final SelenideElement e;
        private static final String NUMBER = ". //div[@class='analyzes-item__head--number']/span";
        private static final String PRICE = ". //div[@class='analyzes-item__total--sum']";
        private static final String TITLE = ". //div[contains(@class, 'analyzes-item__title')]/a";

        public SearchResultItem(SelenideElement e) {
            this.e = e;
        }

        public String getTitle() {
            return e.$x(TITLE).getText();
        }

        public String getNumber() {
            return e.$x(NUMBER).getText().replace("№ ", "");
        }

        public int getPrice() {
            return Integer.parseInt(e.$x(PRICE).getText().replaceAll("[^0-9]+", ""));
        }
    }
}
