package com.seleniums.tests.selenium;

import com.seleniums.tests.selenium.ExcelReader;

import io.qameta.allure.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Epic("ECAP Automation")
@Feature("Login Feature")
public class EcapSearchTest {

    WebDriver driver;

    @BeforeMethod
    @Step("Setup Chrome browser and initialize WebDriver")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    //@DataProvider(name = "logindata")
    //public Object[][] loginDataProvider() {
    //    String path = System.getProperty("user.dir") + "/src/test/resources/LoginData.xlsx";
    //    return ExcelReader.readExcelData(path, "LoginData");
    //}

    @Test(description = "Login to ECAP portal with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Valid login into ECAP")
    @Description("Test Description: Navigate to ECAP login page and verify successful login with valid credentials")
    public void testEcap() throws InterruptedException {

        openECAPPage();

        enterCredentials("22a81a05i7", "prasannakumar65");

        clickLogin();
    }

    @Step("Navigating to ECAP URL")
    public void openECAPPage() {
        driver.get("http://sves.org.in/ecap/Default.aspx?ReturnUrl=%2fecap%2f");
        System.out.println("Title: " + driver.getTitle());
    }

    @Step("Entering credentials - Username: {0}")
    public void enterCredentials(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement uname = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtId2")));
        uname.sendKeys(username);

        WebElement pwd = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtPwd2")));
        pwd.sendKeys(password);
    }

    @Step("Clicking login button")
    public void clickLogin() {
        WebElement login = driver.findElement(By.name("imgBtn2"));
        login.click();
    }
}
