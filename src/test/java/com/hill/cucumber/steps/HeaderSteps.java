package com.hill.cucumber.steps;

import com.hill.cucumber.TestContext;
import com.hill.webui.pageobjects.blocks.Header;
import com.hill.webui.utilities.Web;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.testng.Assert;

public class HeaderSteps {
    Header header = new Header();
    private static String currentCity; //sharing data via static variable

    @When("the user change city to {string}")
    @Step("the user change city to {city}")
    public void theUserChangeCityTo(String city) {
        currentCity = header.city().change(city).current();
    }

    @Then("current city is {string}")
    @Step("check current if city is {city}")
    public void currentCityIs(String city) {
        Assert.assertEquals(currentCity, city, String.format("Current city name should be %s", city));
    }

    @And("the user open medical services header menu option")
    @Step("the user open medical services header menu option")
    public void theUserOpenMedicalServicesHeaderMenuOption() {
        TestContext.instance().putData("MedicalServicesPage", header.menu().medicalServices());
        Web.addScreenshot();
    }

    @And("the user click on analyzes result button")
    public void theUserClickOnAnalyzesResultButton() {
        TestContext.instance().putData("NoRegistrationResultsPage", header.analyzesResults());
        Web.addScreenshot();
    }

    @When("the user choose '{audLimited}' audience")
    @Step("the user choose '{audience}' audience")
    public void theUserChooseAudience(String audience) {
        header.audience().choose(audience);
        Web.addScreenshot();
    }

    @ParameterType("Пациентам|Врачам|Франчайзинг|Корпоративным клиентам|Прессе")
    public String audLimited(String audience){
        return audience;
    }
}
