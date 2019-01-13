package by.booking.pkt.record.katalon;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchAccomodationPositive {
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
  public void testSearchAccomodationPositive() throws Exception {
    driver.get("https://www.booking.com/index.ru.html?label=gen173nr-1DCAEoggI46AdIM1gEaCWIAQGYASG4AQbIAQzYAQPoAQGIAgGoAgM;sid=4cc42b5384fa0763b472b501c520af8e;keep_landing=1&sb_price_type=total&");
    driver.findElement(By.id("ss")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='вс, 13 янв. - вс, 27 янв. (14 ночей)'])[1]/following::span[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Номера'])[1]/following::span[1]")).click();
    driver.findElement(By.id("no_rooms")).clear();
    driver.findElement(By.id("no_rooms")).sendKeys("1");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='-'])[1]/following::span[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='-'])[1]/following::span[2]")).click();
    driver.findElement(By.id("no_rooms")).clear();
    driver.findElement(By.id("no_rooms")).sendKeys("2");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Взрослых'])[1]/following::span[1]")).click();
    driver.findElement(By.id("group_adults")).clear();
    driver.findElement(By.id("group_adults")).sendKeys("1");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='-'])[2]/following::span[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='-'])[2]/following::span[2]")).click();
    driver.findElement(By.id("group_adults")).clear();
    driver.findElement(By.id("group_adults")).sendKeys("2");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Детей'])[1]/following::span[1]")).click();
    driver.findElement(By.id("group_children")).clear();
    driver.findElement(By.id("group_children")).sendKeys("0");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='-'])[3]/following::span[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='-'])[3]/following::span[2]")).click();
    driver.findElement(By.id("group_children")).clear();
    driver.findElement(By.id("group_children")).sendKeys("1");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='-'])[3]/following::span[2]")).click();
    driver.findElement(By.id("group_children")).clear();
    driver.findElement(By.id("group_children")).sendKeys("2");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='-'])[3]/following::span[2]")).click();
    driver.findElement(By.id("group_children")).clear();
    driver.findElement(By.id("group_children")).sendKeys("3");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Укажите возраст детей'])[1]/following::span[1]")).click();
    driver.findElement(By.id("ss")).click();
    driver.findElement(By.id("group_adults")).click();
    driver.findElement(By.id("group_adults")).click();
    driver.findElement(By.id("group_children")).click();
    driver.findElement(By.id("no_rooms")).click();
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
