package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class PropertiesFoundCount {
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
  public void testPropertiesFoundCount() throws Exception {
    driver.get("https://www.booking.com/searchresults.ru.html");
    driver.findElement(By.id("ss")).click();
    driver.findElement(By.id("ss")).clear();
    driver.findElement(By.id("ss")).sendKeys("Минск");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Место/название объекта:'])[1]/following::li[1]")).click();
    driver.findElement(By.id("group_adults")).click();
    new Select(driver.findElement(By.id("group_adults"))).selectByVisibleText("3 взрослых");
    driver.findElement(By.id("group_adults")).click();
    driver.findElement(By.id("group_children")).click();
    new Select(driver.findElement(By.id("group_children"))).selectByVisibleText("3 детей");
    driver.findElement(By.id("group_children")).click();
    driver.findElement(By.id("no_rooms")).click();
    new Select(driver.findElement(By.id("no_rooms"))).selectByVisibleText("3 номера");
    driver.findElement(By.id("no_rooms")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='вс, 27 янв. 2019'])[2]/following::span[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Свернуть'])[1]/following::h1[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Показать только доступные'])[1]/following::a[7]")).click();
    driver.findElement(By.linkText("5")).click();
    driver.findElement(By.cssSelector("svg.bk-icon.-iconset-navarrow_right.bui-pagination__icon > path")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Результаты поиска по направлению Минск'])[1]/following::div[2]")).click();
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
