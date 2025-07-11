package com.seleniums.tests.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.qameta.allure.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SauceDemoTest {

    WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(@Optional("chrome") String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser value: " + browser);
        }

        driver.manage().window().maximize();
    }

  
    @Epic("cross browser testing")
    @Feature("Open a sauce demo in various broswers")
    @Story("Open site and verify")
    @Severity(SeverityLevel.CRITICAL)
    @Test(description="verify title in multiple browsers ")
    public void openSauceDemo() {
        driver.get("https://www.saucedemo.com/");
        System.out.println("Title: " + driver.getTitle());
        
        WebElement uname= driver.findElement(By.id("user-name"));
        uname.sendKeys("standard_user");
        
        WebElement pwd=driver.findElement(By.id("password"));
        pwd.sendKeys("secret_sauce");
        
        WebElement lgn= driver.findElement(By.id("login-button"));
        lgn.click();
        
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
