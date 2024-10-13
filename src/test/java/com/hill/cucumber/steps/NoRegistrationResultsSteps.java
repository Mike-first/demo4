package com.hill.cucumber.steps;

import com.hill.cucumber.TestContext;
import com.hill.webui.pageobjects.NoRegistrationResultsPage;
import com.hill.webui.pageobjects.blocks.Localization;
import com.hill.webui.utilities.Web;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class NoRegistrationResultsSteps {
    private NoRegistrationResultsPage resultsPage;
    protected static SoftAssert sAssert = BaseStep.sAssert;

    @Then("title {string} is shown")
    @Step("title {tittleExpected} is shown")
    public void titleIsShown(String tittleExpected) {
//        Localization.ru();
        Web.addScreenshot();
        resultsPage = (NoRegistrationResultsPage) TestContext.instance().getData("NoRegistrationResultsPage");
        String tittleActual = resultsPage.getTitle();
        Web.addText("tittle_actual", tittleActual);
        Assert.assertEquals(tittleActual,
                tittleExpected,
                "Title does not correspond to expected one");
    }

    @When("the user search for results")
    @Step("the user search for results")
    public void theUserSearchForResults() {
        resultsPage = (NoRegistrationResultsPage) TestContext.instance().getData("NoRegistrationResultsPage");
        resultsPage.getResults();
        Web.addScreenshot();
    }

    @Then("error message {string} is shown")
    @Step("check if error message {msg} is shown")
    public void errorMessageIsShown(String msg) {
        Web.addScreenshot();
        sAssert.assertEquals(resultsPage.getErrorMessage(), msg,
                "Error message does not correspond to expected one");
    }

    @And("fields are highlighted red")
    @Step("check if fields are highlighted red")
    public void fieldsAreHighlightedRed() {
        Web.addScreenshot();
        sAssert.assertTrue(resultsPage.isInputsError(), "Fields should be highlighted red");
    }

    @When("the user fill {string} field with {string}")
    @Step("the user fill {field} field with {value}")
    public void theUserFillCodeFieldWith(String field, String value) {
        resultsPage = (NoRegistrationResultsPage) TestContext.instance().getData("NoRegistrationResultsPage");
        switch (field) {
            case "Код ИНЗ": {
                resultsPage.fillCode(value);
                TestContext.instance().putData("theUserFillCodeFieldWith.fillCode", value);
                break;
            }
            case "Дата рождения": {
                resultsPage.fillBirthday(value);
                TestContext.instance().putData("theUserFillCodeFieldWith.fillBirthday", value);
                break;
            }
            case "Фамилия": {
                resultsPage.fillSurname(value);
                TestContext.instance().putData("theUserFillCodeFieldWith.fillSurname", value);
                break;
            }
            default:
                throw new IllegalArgumentException(String.format("No such field '%s'", field));
        }
        Web.addScreenshot();
    }

    @Then("fields are filled with correct data")
    @Step("check if fields are filled with correct data")
    public void fieldsAreFilledWithCorrectData() {
        resultsPage = (NoRegistrationResultsPage) TestContext.instance().getData("NoRegistrationResultsPage");
        Web.addScreenshot();
        sAssert.assertEquals(TestContext.instance().getData("theUserFillCodeFieldWith.fillCode"),
                resultsPage.getCode() + "1", "Incorrect code field value");
        sAssert.assertEquals(TestContext.instance().getData("theUserFillCodeFieldWith.fillBirthday"),
                resultsPage.getBirthday(), "Incorrect birthday field value");
        sAssert.assertEquals(TestContext.instance().getData("theUserFillCodeFieldWith.fillSurname"),
                resultsPage.getSurname(), "Incorrect surname field value");
    }
}
