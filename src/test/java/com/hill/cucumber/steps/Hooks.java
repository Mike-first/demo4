package com.hill.cucumber.steps;


import com.codeborne.selenide.Selenide;
import com.hill.webui.core.DriverSetter;
import com.hill.webui.core.Pages;
import com.hill.webui.pageobjects.blocks.CookieDialog;
import com.hill.webui.pageobjects.blocks.SubscribeEmailWindow;
import com.hill.webui.utilities.Web;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.asserts.SoftAssert;
import com.hill.webui.utilities.FileUtils;
import com.hill.webui.utilities.PathStorage;

public class Hooks {
    private static volatile boolean onceCommon = false;
    private static volatile boolean onceUi = false;
    private static final Logger log = LoggerFactory.getLogger(Hooks.class);

    @Before
    public static void setOnceUi(Scenario scenario) {
        if (!onceCommon) {
            System.out.println("before onceCommon: cleaning");
            log.info(String.format("clean report dir: %s", FileUtils.cleanDir(PathStorage.ALLURE_RESULTS_DIR)));
            log.info(String.format("clean report dir: %s", FileUtils.cleanDir(PathStorage.ALLURE_REPORT_DIR_W)));
            onceCommon = true;
        }
        if (!onceUi && !scenario.getSourceTagNames().contains("@api")) {
            System.out.println("before onceUI: set driver, close cookie & subscribe");
            DriverSetter.set();
            Selenide.open(Pages.HOME.link());
            CookieDialog.waitForAndClose();
            SubscribeEmailWindow.waitForAndClose();
            onceUi = true;
        }
    }

    @Before
    public static void beforeScenario(Scenario scenario) {
        log.info(String.format("Scenario: %s", scenario.getName()));
        BaseStep.sAssert = new SoftAssert();
    }

    @After
    public static void afterScenario(Scenario scenario) {
        BaseStep.sAssert.assertAll();
        if (scenario.isFailed()) {
            log.info(String.format("Scenario: %s failed", scenario.getName()));
            Web.addScreenshot();
        } else {
            log.info(String.format("Scenario: %s passed", scenario.getName()));
        }
    }

}