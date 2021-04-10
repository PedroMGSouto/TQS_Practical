package org.example;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SeleniumJupiter.class)
public class Ex3c_SelJupBuyTicketTest {
  private Map<String, Object> vars;
  JavascriptExecutor js;

  @Test
  public void buyTicket(ChromeDriver driver) {
    driver.get("https://blazedemo.com/");
    driver.manage().window().setSize(new Dimension(1920, 1014));
    {
      WebElement dropdown = driver.findElement(By.name("fromPort"));
      dropdown.findElement(By.xpath("//option[. = 'Mexico City']")).click();
    }
    {
      WebElement element = driver.findElement(By.name("fromPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.name("fromPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.name("fromPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    {
      WebElement dropdown = driver.findElement(By.name("toPort"));
      dropdown.findElement(By.xpath("//option[. = 'New York']")).click();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).clickAndHold().perform();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).perform();
    }
    {
      WebElement element = driver.findElement(By.name("toPort"));
      Actions builder = new Actions(driver);
      builder.moveToElement(element).release().perform();
    }
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("Flights from Mexico City to New York:"));
    assertThat(driver.findElement(By.cssSelector("th:nth-child(4)")).getText(), is("Departs: Mexico City"));
    assertThat(driver.findElement(By.cssSelector("th:nth-child(5)")).getText(), is("Arrives: New York"));
    driver.findElement(By.cssSelector("tr:nth-child(1) .btn")).click();
    driver.findElement(By.id("inputName")).click();
    driver.findElement(By.id("inputName")).sendKeys("Pedro");
    driver.findElement(By.id("address")).sendKeys("Avenida 25");
    driver.findElement(By.id("city")).sendKeys("Aveiro");
    driver.findElement(By.id("state")).sendKeys("Aveiro");
    driver.findElement(By.id("zipCode")).sendKeys("13245");
    driver.findElement(By.id("creditCardNumber")).sendKeys("12345");
    driver.findElement(By.id("creditCardMonth")).sendKeys("12");
    driver.findElement(By.id("creditCardYear")).sendKeys("2021");
    driver.findElement(By.id("nameOnCard")).sendKeys("Pedro Souto");
    driver.findElement(By.cssSelector(".controls:nth-child(1)")).click();
    driver.findElement(By.cssSelector(".btn-primary")).click();
    assertThat(driver.getTitle(), is("BlazeDemo Confirmation"));
  }
}
