package com.hill.cucumber.steps;

import io.cucumber.java.en.Then;

public class MockSteps {

    @Then("noting")
    public void noting() {
        System.out.println("Nothing happen");
    }
}
