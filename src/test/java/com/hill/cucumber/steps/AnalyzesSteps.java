package com.hill.cucumber.steps;

import com.hill.cucumber.TestContext;
import com.hill.webui.pageobjects.AnalyzesPage;
import com.hill.webui.pageobjects.blocks.AnalysisItem;
import com.hill.webui.utilities.Web;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyzesSteps {
    AnalyzesPage analyzesPage = new AnalyzesPage();

    @When("the user add any analysis to cart")
    @Step("the user add any analysis to cart")
    public void theUserAddAnyAnalysisToCart() {
        List<AnalysisItem> anList = analyzesPage.getAccessibleAnalysis();
        int randomIndex = Web.randomIndex(anList);
        AnalysisItem ai = anList.get(randomIndex)
                .add();
        Map<String, String> addedItem = new HashMap<>();
        addedItem.put("title", ai.getTitle());
        addedItem.put("price", String.valueOf(ai.getPrice()));
        TestContext.instance().putData("theUserAddAnyAnalysisToCart.addedItem", addedItem);
        Web.addScreenshot();
    }

    @When("the user search for analyzes by key-words {string}")
    @Step("the user search for analyzes by key-words {req}")
    public void theUserSearchForAnalyzesByKeyWords(String req) {
        analyzesPage.header().searchFor(req);
        TestContext.instance().putData("theUserSearchForAnalyzesByKeyWords.req", req);
        Web.addScreenshot();
    }
}
