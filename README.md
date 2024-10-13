run: com.hill.cucumber.runner.RunTestNGCucumberTest  
set propertis: src/main/resources/webtest.properties  

stack:  
java8, maven,  
testNG, Cucumber,  
Selenide,  WebDriverManager,  
slf4j, allure

implemented:
UI tests, API tests,  
automatic driver management,  
automatic report generating and archiving,  
Gherkin: parametrized tests, parameter type limitation, passing values between steps  