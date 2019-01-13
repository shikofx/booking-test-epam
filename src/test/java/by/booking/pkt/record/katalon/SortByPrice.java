package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SortByPrice {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSortByPrice() throws Exception {
    driver.get("https://www.booking.com/searchresults.ru.html");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Ошибка:'])[1]/following::div[4]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Найти'])[1]/following::div[1]")).click();
    driver.findElement(By.id("ss")).click();
    driver.findElement(By.id("ss")).clear();
    driver.findElement(By.id("ss")).sendKeys("Vby");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Место/название объекта:'])[1]/following::span[1]")).click();
    driver.findElement(By.id("group_adults")).click();
    new Select(driver.findElement(By.id("group_adults"))).selectByVisibleText("3 взрослых");
    driver.findElement(By.id("group_adults")).click();
    driver.findElement(By.id("group_children")).click();
    new Select(driver.findElement(By.id("group_children"))).selectByVisibleText("1 ребенок");
    driver.findElement(By.id("no_rooms")).click();
    new Select(driver.findElement(By.id("no_rooms"))).selectByVisibleText("3 номера");
    driver.findElement(By.id("no_rooms")).click();
    driver.findElement(By.name("age")).click();
    driver.findElement(By.id("b2searchresultsPage")).click();
    driver.findElement(By.linkText("Цена (сначала самая низкая)")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Результаты поиска по направлению Минск'])[1]/following::div[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Остался 1 номер!'])[2]/following::div[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Остался 1 номер!'])[4]/following::div[1]")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
