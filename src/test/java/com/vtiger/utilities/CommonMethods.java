package com.vtiger.utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class CommonMethods {

    private WebDriver driver;
    public WebDriverWait wait;
    public CommonMethods(WebDriver driver)
    {
        this.driver = driver;
        wait  = new WebDriverWait(driver,Duration.ofSeconds(10));
    }


    public void type(WebElement elm, String value)
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.clear();
            elm.sendKeys(value);
           // ExtentReportManager.logPass("User enters "+value+" successfully.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            ExtentReportManager.logFail("User unable to entered text due to error: " + e.getMessage()+"<a href = "+takeScreenshot()+"><span class='label end-time'>Screenshot</span></a>");
        }
    }

    public void type(String elmstr, String value)
    {
        try
        {
            WebElement elm = driver.findElement(By.xpath(elmstr));
            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.clear();
            elm.sendKeys(value);
           // ExtentReportManager.logPass("User enters "+value+" successfully.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            ExtentReportManager.logFail("User unable to entered text due to error: " + e.getMessage());
        }
    }

    public void clickElement(WebElement elm)
    {
        try
        {
            wait.until(ExpectedConditions.elementToBeClickable(elm));
            elm.click();
           // ExtentReportManager.logPass("User clicked successfully.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            ExtentReportManager.logFail("Unable to click due to error: " + e.getMessage()+"<a href = "+takeScreenshot()+"><span class='label end-time'>Screenshot</span></a>");
        }
    }

    public void clickElement(String elmstr, String elementType)
    {
        WebElement elm=null;
        try
        {
            if(elementType.equalsIgnoreCase("link")) {
                elm = driver.findElement(By.linkText("elmstr"));
            }
            wait.until(ExpectedConditions.elementToBeClickable(elm));
            elm.click();
           // ExtentReportManager.logPass("User clicked successfully.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            ExtentReportManager.logFail("Unable to click due to error: " + e.getMessage());
        }
    }

    public void elementExist(WebElement elm)
    {
        try
        {
            wait.until(ExpectedConditions.visibilityOf(elm));
            elm.isDisplayed();
           // ExtentReportManager.logPass("Element displayed successfully.");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            ExtentReportManager.logFail("Element not found due to error: " + e.getMessage()+"<a href = "+takeScreenshot()+"><span class='label end-time'>Screenshot</span></a>");
        }
    }

    public String takeScreenshot() {
        Date d = new Date();
        DateFormat ft = new SimpleDateFormat("ddMMyyyy_hhmmss");
        String timrstmp = ft.format(d);
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destFile = System.getProperty("user.dir")+"/src/test/java/com/vtiger/reports/screenshots/"+timrstmp+".png";
        try {
            FileUtils.copyFile(srcFile, new File(destFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       return destFile;
        //  test.fail("Test failed", MediaEntityBuilder.createScreenCaptureFromPath(destFile).build());
    }


}
