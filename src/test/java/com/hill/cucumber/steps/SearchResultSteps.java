package com.hill.cucumber.steps;

import com.hill.cucumber.TestContext;
import com.hill.webui.pageobjects.SearchResultPage;
import com.hill.webui.utilities.Web;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.util.stream.Collectors;

public class SearchResultSteps {
    SearchResultPage srp = new SearchResultPage();
    @Then("only relevant analyzes are shown")
    @Step("check if only relevant analyzes are shown")
    public void onlyRelevantAnalyzesAreShown() {
        String keyWord = (String) TestContext.instance().getData("theUserSearchForAnalyzesByKeyWords.req");
        Web.addScreenshot();
        Web.addText("key_word", keyWord);
        Assert.assertEquals(srp.getResults().size(),
                srp.getResults().stream()
                        .map(SearchResultPage.SearchResultItem::getNumber)
                        .filter(s -> s.contains(keyWord)).collect(Collectors.toList()).size(),
                "Bad search result");
    }
}
