package com.vtiger.utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static ExtentTest test;

    // Initialize ExtentReports
    public static ExtentReports getInstance() {
        if (extent == null) {
            //ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("target/extent-reports/extent-report.html");
            Date d = new Date();
            DateFormat ft = new SimpleDateFormat("ddMMyyyy_hhmmss");
            String timrstmp = ft.format(d);
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/src/test/java/com/vtiger/reports/extent-report_"+timrstmp+".html");
            htmlReporter.config().setDocumentTitle("Cucumber Test Automation Report");
            htmlReporter.config().setReportName("Test Execution Results");

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
        }
        return extent;
    }

    // Start a new test case
    public static ExtentTest startTest(String testName) {
        test = extent.createTest(testName);
        return test;
    }

    // End the test case
    public static void endTest() {
        extent.flush(); // Write all tests into the report
    }

    // Log test information
    public static void logInfo(String message) {
        if (test != null) {
            test.info(message);
        }
    }

    // Log test pass
    public static void logPass(String message) {
        if (test != null) {
            test.pass(message);
        }
    }

    // Log test failure
    public static void logFail(String message) {
        if (test != null) {
            test.fail(message);
        }
    }
}
