package com.hill.webui.pageobjects.blocks;

import com.hill.webui.pageobjects.AnalyzesPage;
import com.hill.webui.pageobjects.BasePage;
import com.hill.webui.pageobjects.MedicalServicesPage;

import static com.codeborne.selenide.Selenide.$x;

public class HeaderMenu extends BasePage {
    private static final String BASE = "//nav[@id='headerMainMenu']";
    private static final String OPTION_BY_LINK = BASE + "//li[@class='invitro_header-menu_main-item']//a[contains(@href,'%s')]";

    public AnalyzesPage analyzes(){
        $x(String.format(OPTION_BY_LINK, "analizes")).click();
        waitPageLoading();
        return new AnalyzesPage();
    }

    public MedicalServicesPage medicalServices() {
        $x(String.format(OPTION_BY_LINK, "radiology")).click();
        waitPageLoading();
        return new MedicalServicesPage();
    }
}
