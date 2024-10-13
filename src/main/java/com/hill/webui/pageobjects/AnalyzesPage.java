package com.hill.webui.pageobjects;

import com.hill.webui.pageobjects.blocks.AnalysisItem;
import com.hill.webui.pageobjects.blocks.Header;
import com.hill.webui.pageobjects.blocks.LeftMenu;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

public class AnalyzesPage extends BasePage {
    private static final String AN_CONTAINER = "//div[@class = 'pagination-items']";
    private static final String AN_ITEMS = AN_CONTAINER + "//div[@class = 'analyzes-list']";
    private static final String SUB_TITLE = ". /a[@class='result-list__subtitle']";

    public List<AnalysisItem> getAccessibleAnalysis() {
        return $$x(AN_ITEMS).stream()
                .filter(i -> i.$$x(SUB_TITLE).isEmpty())
                .map(AnalysisItem::new)
                .collect(Collectors.toList());
    }

    public Header header(){
        waitPageLoading();
        return new Header();
    }

    public LeftMenu leftMenu() {
        waitPageLoading();
        LeftMenu menu = new LeftMenu();
        menu.getElement().scrollTo();
        return menu;
    }
}
