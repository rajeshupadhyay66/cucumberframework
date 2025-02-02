package com.vtiger.pages;

import com.vtiger.utilities.CommonMethods;
import com.vtiger.utilities.ExtentReportManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends CommonMethods {

    private WebDriver driver;

    public LoginPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath="//input[@name='user_name']")
    public WebElement tb_username;

    @FindBy(name="user_password")
    public WebElement tb_password;

    @FindBy(name="Login")
    public WebElement btn_login;

    @FindBy(xpath="//*[contains(text(),'123You must specify a valid username and password.')]")
    public WebElement error_msg;

    @FindBy(xpath="//img[@src='include/images/vtiger-crm.gif']")
    WebElement img_logo;

    @FindBy(xpath="//font[text()='Key Modules']")
    WebElement lbl_KeyModule;





    //private String username = "//input[@name='user_name']";
   // WebElement username = driver.findElement(By.xpath("//input[@name='user_name']"));


    public void login(String userid, String pwd)
    {


            setUsername(userid);
            setPassword(pwd);

            clickLogin();
            ExtentReportManager.logPass("userid="+userid+" and password="+pwd+" has been entered and Login button clicked"+"<a href = "+takeScreenshot()+"><span class='label start-time'>Screenshot</span></a>");

    }

    public void setUsername(String uid)
    {
       type(tb_username,uid);
    }

    public void setPassword(String pwd)
    {
        type(tb_password,pwd);
    }

    public void clickLogin()
    {
       clickElement(btn_login);
    }

    public void logoExist()
    {
         elementExist(img_logo);
        ExtentReportManager.logPass("Logo displayed successfully on Login page"+"<a href = "+takeScreenshot()+"><span class='label start-time'>Screenshot</span></a>");
    }

    public void KeyModulesExist()
    {
        elementExist(lbl_KeyModule);
        ExtentReportManager.logPass("Key Modules displayed successfully on Login page");
    }

    public void UsernameTextboxExist()
    {
        elementExist(tb_username);
        ExtentReportManager.logPass("Username textbox displayed successfully on Login page "+"<a href = "+takeScreenshot()+"><span class='label start-time'>Screenshot</span></a>");
    }

    public void ErrorMsgExist()
    {
        elementExist(error_msg);
        ExtentReportManager.logPass("Error Message displayed successfully on Login page");

    }




}
