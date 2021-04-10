package org.example;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class Ex1_DemoTest {
    private WebDriver wdriver;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "/home/pedromgsouto/Documents/Uni/sel-chrome/chromedriver");
        wdriver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown(){
        wdriver.close();
    }

    @Test
    public void site_header_is_on_home_page() {
        wdriver.get("https://www.saucelabs.com");
        WebElement href = wdriver.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));
        assertTrue((href.isDisplayed()));

    }
}