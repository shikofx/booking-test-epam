package by.booking.pkt.record.katalon;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class PageTransfer {
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
  public void testPageTransfer() throws Exception {
    driver.get("https://www.booking.com/index.ru.html?label=gen173nr-1DCAEoggI46AdIM1gEaCWIAQGYASG4AQbIAQzYAQPoAQGIAgGoAgM;sid=4cc42b5384fa0763b472b501c520af8e;keep_landing=1&sb_price_type=total&");
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Проживание'])[1]/following::span[1]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_1 | ]]
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Авиабилеты'])[1]/following::span[1]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_2 | ]]
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Аренда машин'])[1]/following::span[1]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_3 | ]]
    driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
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
