package com.vtiger.steps;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.codoid.products.exception.FilloException;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LoginPage;
import com.vtiger.utilities.ExtentReportManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Map;

public class loginsteps extends BaseSteps {
    private static ExtentReports extent;
    private static ExtentTest test;

    @Before
    public void getScenarioName(Scenario scenario)
    {
        ExtentReportManager.getInstance();
        ScenarioName = scenario.getName();
        ExtentReportManager.startTest(ScenarioName);
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("target/extent-reports/extent-report.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        test = extent.createTest(scenario.getName());
    }
    @After
    public void tierdown()
    {
        ExtentReportManager.endTest();
        closeApp();
    }


    @And("user should be on login page")
    public void user_should_be_on_login_page() throws FilloException {
        initiation();

    }
    @When("user enter the valid credentials")
    public void user_enter_the_valid_credentials() {
        lp.setUsername(dt.get(ScenarioName).get("Userid"));
        lp.setPassword(dt.get(ScenarioName).get("Password"));
    }

    @Given("user enter the invalid credentials")
    public void user_enter_the_inasavalid_credentials() {
        lp.setUsername(dt.get(ScenarioName).get("Userid"));
        lp.setPassword(dt.get(ScenarioName).get("Password"));
    }

    @When("user enter the invalid credentials {string} ,{string} and click on login button")
    public void user_enter_the_invalid_credentials_and_click_on_login_button(String string, String string2, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> lst = dataTable.asMaps();
       for(Map<String, String> map : lst) {
           lp.setUsername(map.get("uid"));
           lp.setPassword(map.get("pwd"));
       }
    }

    @When("user enter the invalid credentials as username {string} and password {string}")
    public void user_enter_the_invalid_credentials_as_username_and_password(String uid, String pwd) {
        lp.setUsername(uid);
        lp.setPassword(pwd);
        lp.clickLogin();
    }

    @When("click on login button")
    public void click_on_login_button() {
       lp.clickLogin();
        ExtentReportManager.logInfo("Entered valid credentials and clicked login");
    }
    @Then("user should navigated to home page")
    public void user_should_navigated_to_home_page() {

         hp.LinkHomexExist();
    }
    @Then("use can see the logout link")
    public void use_can_see_the_logout_link() {

        hp.LinkLogoutExist();
    }

    @Then("user should navigated to login page")
    public void user_should_navigated_to_login_page() {
        lp.UsernameTextboxExist();
    }

    @Then("use can see the error message")
    public void error_measge() {
        lp.ErrorMsgExist();
    }



}
