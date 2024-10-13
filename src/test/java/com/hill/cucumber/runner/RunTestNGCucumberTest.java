package com.hill.cucumber.runner;

import com.hill.webui.utilities.scripts.ScriptRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;

@CucumberOptions(
        plugin = {"json:target/cucumber-results/cucumber-report.json", "html:target/cucumber-results"},
        features = {"src/test/resources/features"},
        glue = {"com/hill/cucumber"},
        tags = {"@cart"},//"@api" "@analyzesResult" "@cart" "@debug"
        strict = true,
        snippets = SnippetType.CAMELCASE
)
public class RunTestNGCucumberTest extends AbstractTestNGCucumberTests {
    private static final Logger log = LoggerFactory.getLogger(RunTestNGCucumberTest.class);

    @AfterClass
    public static void afterAll() {
        System.out.println("afterAll: generate & archive report");
        log.info(ScriptRunner.generateAllureReport());
        log.info(ScriptRunner.archiveReport());
    }
}