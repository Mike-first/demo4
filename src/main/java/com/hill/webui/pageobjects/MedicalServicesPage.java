package com.hill.webui.pageobjects;

import com.hill.webui.pageobjects.blocks.LeftMenu;

public class MedicalServicesPage extends BasePage {

    public LeftMenu leftMenu() {
        waitPageLoading();
        LeftMenu menu = new LeftMenu();
        menu.getElement().scrollTo();
        return menu;
    }
}
