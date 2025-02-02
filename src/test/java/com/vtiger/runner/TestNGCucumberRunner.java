package com.vtiger.runner;

import com.vtiger.utilities.ExtentReportManager;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


@CucumberOptions(

        features = "src/test/resources/Features/",
        glue = "com.vtiger.steps",
        dryRun = false,
        tags = "@bhumi",
        plugin = {"html:target/cucumber-reports.html", "json:target/cucumber.json"}




)
public class TestNGCucumberRunner extends AbstractTestNGCucumberTests {

}
