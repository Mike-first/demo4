package com.hill.cucumber.steps;

import com.codeborne.selenide.SelenideElement;
import com.hill.cucumber.TestContext;
import com.hill.webui.pageobjects.BasePage;
import com.hill.webui.pageobjects.RadiologyPage;
import com.hill.webui.pageobjects.blocks.LeftMenu;
import com.hill.webui.pageobjects.blocks.LeftMenuItem;
import com.hill.webui.utilities.StringUtils;
import com.hill.webui.utilities.Web;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.*;

public class MenuSteps {

    @Before
    public void beforeScenario(Scenario scenario) {
        System.out.println(scenario.getSourceTagNames());
    }

    @When("the user click each menu option")
    @Step("the user click each menu option")
    public void theUserClickEachMenuOption() {
        Map<String, List<String>> itemsInfo = traversMenu(new RadiologyPage().leftMenu());
        Web.addText("menu_items", StringUtils.mapStrListStrToString(itemsInfo));
        TestContext.instance().putData("traversLeftMenuResult", itemsInfo);
    }

    @Then("each option is clickable")
    @Step("check if all options are clickable")
    public void eachOptionIsClickable() {
        Map<String, List<String>> itemsInfo = (Map<String, List<String>>)
                TestContext.instance().getData("traversLeftMenuResult");

        List<String> titles = new ArrayList<>();
        Set<String> keys = itemsInfo.keySet();
        for (String key : keys) {
//            String k = (key.startsWith("-")) ? "subItem: " + key.substring(1, key.length()) : "Item: " + key;
//            String v = String.format(" title '%s', link '%s'", itemsInfo.get(key).get(0), itemsInfo.get(key).get(1));
//            System.out.println(String.format("%s %s", k, v));
            titles.add(itemsInfo.get(key).get(0));
        }
        Assert.assertEquals(titles.size(), new HashSet<>(titles).size(), "Titles should be unique");

    }

    private static Map<String, List<String>> traversMenu(LeftMenu menu) {
        Map<String, List<String>> itemsInfo = new HashMap<>();
        List<String> menuItems = menu.getItemsNames();
        System.out.printf("count: %d, items are: %s%n", menuItems.size(), String.join(", ", menuItems));

        for (String menuItem : menuItems) {
            LeftMenuItem mi = menu.getItem(menuItem);
            Web.Wait.pageLoading();
            waitJustInCase();

            if (mi.isExpandable()) {
                List<String> menuSubItems = mi.expand().getSubItemsNames();
                for (String subItem : menuSubItems) {
                    SelenideElement subItemElement = mi.getSubItem(subItem);
                    waitJustInCase();
                    subItemElement.click();
                    Web.Wait.pageLoading();
                    waitJustInCase();
                    itemsInfo.put("-" + subItem, Arrays.asList(BasePage.pageTitle(), Web.Get.currentUrl()));
                    Web.addScreenshot();
                }
                waitJustInCase();
                mi.expand();
            } else {
                mi.getThisElement().click();
                Web.Wait.pageLoading();
                waitJustInCase();
                //Selenide.title() возвращал одинаковое на инъекциях
                itemsInfo.put(menuItem, Arrays.asList(BasePage.pageTitle(), Web.Get.currentUrl()));
                Web.addScreenshot();
            }
        }
        return itemsInfo;
    }

    private static void waitJustInCase() {
        Web.Wait.forMillis(500);
    }

}
