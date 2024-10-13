package com.hill.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;


public class CityApiSteps {
    private Response response;

    @Given("I make a GET request to the API with CODE {string}")
    public void makeGetRequest(String code) {
        response = RestAssured.given()
                .queryParam("CODE", code)
                .when()
                .get("https://www.invitro.ru/local/ajax/current-city.php");
    }

    @Then("I should receive a response code of {int}")
    public void checkResponseCode(int expectedCode) {
        Assert.assertEquals(expectedCode, response.getStatusCode());
    }

    @And("the response body should contain {string}")
    public void checkResponseBody(String expectedCity) {
        Assert.assertTrue(response.getBody().asString().contains(expectedCity));
    }
}

