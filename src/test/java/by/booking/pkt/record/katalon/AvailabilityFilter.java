package by.booking.pkt.record.katalon;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class AvailabilityFilter {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("https://www.booking.com/searchresults.ru.html");
  }

  @Test
  public void testAvailabilityFilter() throws Exception {
    fillTextForm("Минск");
    driver.findElement(By.cssSelector("label[for=ss")).click();
    selectAdultsCount();
    selectChildrenCount();
    selectRomsCount("3 номера");
  }

  private void selectRomsCount(String adults) {
    driver.findElement(By.id("no_rooms")).click();
    new Select(driver.findElement(By.id("no_rooms"))).selectByVisibleText(adults);
    driver.findElement(By.id("no_rooms")).click();
  }

  private void selectChildrenCount() {
    driver.findElement(By.id("group_children")).click();
    new Select(driver.findElement(By.id("group_children"))).selectByVisibleText("3 детей");
    driver.findElement(By.id("group_children")).click();
  }

  private void selectAdultsCount() {
    driver.findElement(By.id("group_adults")).click();
    driver.findElement(By.cssSelector("select[name=group_adults]("+1+")")).click();
    //new Select(driver.findElement(By.id("group_adults"))).selectByVisibleText("2 взрослых");
    driver.findElement(By.id("group_adults")).click();
  }

  private void fillTextForm(String text) {
    driver.findElement(By.id("ss")).click();
    driver.findElement(By.id("ss")).clear();
    driver.findElement(By.id("ss")).sendKeys(text);
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    /*driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }*/
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
