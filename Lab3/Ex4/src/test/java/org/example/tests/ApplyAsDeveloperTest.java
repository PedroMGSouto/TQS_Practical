package org.example.tests;

import org.example.webpages.DeveloperApplyPage;
import org.example.webpages.HomePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class ApplyAsDeveloperTest {
    WebDriver driver;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "/home/pedromgsouto/Documents/Uni/sel-chrome/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void applyAsDeveloper() {
        //Create object of HomePage Class
        HomePage home = new HomePage(driver);
        home.clickOnDeveloperApplyButton();

        //Create object of DeveloperApplyPage
        DeveloperApplyPage applyPage =new DeveloperApplyPage(driver);

        //Check if page is opened
        Assert.assertTrue(applyPage.isPageOpened());

        //Fill up data
        applyPage.setDeveloper_email("dejan@toptal.com");
        applyPage.setDeveloper_full_name("Dejan Zivanovic Automated Test");
        applyPage.setDeveloper_password("password123");
        applyPage.setDeveloper_password_confirmation("password123");

        //Click on join
        //applyPage.clickOnJoin();
    }

    @After
    public void close(){
        driver.close();
    }
}
