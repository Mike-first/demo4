package com.hill.webui.pageobjects;

import com.hill.webui.pageobjects.blocks.Header;
import com.hill.webui.pageobjects.blocks.LeftMenu;

public class RadiologyPage extends BasePage {

    public LeftMenu leftMenu() {
        waitPageLoading();
        LeftMenu menu = new LeftMenu();
        menu.getElement().scrollTo();
        return menu;
    }

    public Header header(){
        waitPageLoading();
        return new Header();
    }
}
