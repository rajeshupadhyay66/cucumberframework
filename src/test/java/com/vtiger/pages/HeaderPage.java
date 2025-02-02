package com.vtiger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {

    private WebDriver driver;

    public HeaderPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(linkText="Home")
    public WebElement lnk_home;

    @FindBy(linkText="Logout")
    public WebElement lnk_logout;

    public boolean LinkHomexExist()
    {
        return lnk_home.isDisplayed();
    }

    public boolean LinkLogoutExist()
    {
        return lnk_logout.isDisplayed();
    }
}
