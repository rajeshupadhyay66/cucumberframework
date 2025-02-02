package com.vtiger.steps;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.vtiger.pages.HomePage;
import com.vtiger.pages.LoginPage;
import com.vtiger.utilities.ExtentReportManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


public class BaseSteps {
    public WebDriver driver;
    public Properties prop;
    public Map<String, Map<String,String>> dt;
    public LoginPage lp;
    public HomePage hp;

    public static String ScenarioName;



    public void initiation() throws FilloException {
        readExcel();
        if(prop==null) {
            readproperties();
        }
        if(driver==null) {
            launchApp();
        }
    }

    public void launchApp()
    {
        if(System.getProperty("browser")!=null)
        {
            if(System.getProperty("browser").equalsIgnoreCase("edge"))
            {
                driver = new EdgeDriver();
            }
            else if(System.getProperty("browser").equalsIgnoreCase("firefox"))
            {
                driver = new FirefoxDriver();
            }
            else if(System.getProperty("browser").equalsIgnoreCase("headless"))
            {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            }
            else
            {
                driver = new ChromeDriver();
            }
        }
        else
        {
            if(prop.getProperty("BrowserName").equalsIgnoreCase("edge"))
            {
                driver = new EdgeDriver();
            }
            else if(prop.getProperty("BrowserName").equalsIgnoreCase("firefox"))
            {
                driver = new FirefoxDriver();
            }
            else if(prop.getProperty("BrowserName").equalsIgnoreCase("headless"))
            {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver = new ChromeDriver(options);
            }
            else
            {
                driver = new ChromeDriver();
            }
        }


        driver.get(prop.getProperty("AppUrl"));
        driver.manage().window().maximize();
        ExtentReportManager.logInfo("Navigated to login page");
        lp = new LoginPage(driver);
        hp = new HomePage(driver);
    }

    public void closeApp()
    {
        if(driver!=null)
        driver.quit();
    }


    public void readproperties()
    {
         prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/Settings/config.properties");
            prop.load(fis);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void readExcel() throws FilloException {
        Fillo fillo=new Fillo();
        Connection connection = fillo.getConnection(System.getProperty("user.dir")+"/src/test/resources/TestData/data.xlsx");
        String strQuery="Select * from Sheet1";
        dt = new HashMap<>();
        Recordset recordset=connection.executeQuery(strQuery);
        List<String> lst = recordset.getFieldNames();

        while(recordset.next()){
            String ScenarioName = recordset.getField("ScenarioName");
            Map<String,String> m = new HashMap<>();
            for(int i = 1;i<lst.size();i++)
            {
                String colmName = lst.get(i);
                String colmValue = recordset.getField(colmName);
                m.put(colmName,colmValue);
            }
            dt.put(ScenarioName,m);
        }

        recordset.close();
        connection.close();
    }





}
