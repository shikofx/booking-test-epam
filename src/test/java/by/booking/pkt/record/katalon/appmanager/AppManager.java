package by.booking.pkt.record.katalon.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class AppManager {
  public static WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  public void init() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  public void stop() {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  public void selectRoomsCount(String roomsCount) {
    driver.findElement(By.id("no_rooms")).click();
    new Select(driver.findElement(By.id("no_rooms"))).selectByVisibleText(roomsCount);
    driver.findElement(By.id("no_rooms")).click();
  }

  public void selectChildrenCount(String childrenCount) {
    driver.findElement(By.id("group_children")).click();
    new Select(driver.findElement(By.id("group_children"))).selectByVisibleText(childrenCount);
    driver.findElement(By.id("group_children")).click();
  }

  public void selectAdultsCount(String adultsCount) {
    driver.findElement(By.id("group_adults")).click();
    new Select(driver.findElement(By.id("group_adults"))).selectByVisibleText(adultsCount);
    driver.findElement(By.id("group_adults")).click();
  }

  public void setChildrenCount() {
    //проверить какая цифра установлена
    //нажатием + или - установить нужную цифру
//    driver.findElement(By.id("group_children")).clear();
//    driver.findElement(By.id("group_children")).sendKeys("1");
  }

  public void setAdultsCount() {
    //проверить какая цифра установлена
    //нажатием + или - установить нужную цифру
//    driver.findElement(By.id("group_adults")).clear();
//    driver.findElement(By.id("group_adults")).sendKeys("3");
  }

  public void setRoomsCount() {
    //проверить какая цифра установлена
    //нажатием + или - установить нужную цифру
//    driver.findElement(By.id("no_rooms")).clear();
//    driver.findElement(By.id("no_rooms")).sendKeys("2");
  }

  public void fillCityForm(String text) {
    driver.findElement(By.id("ss")).click();
    driver.findElement(By.id("ss")).clear();
    driver.findElement(By.id("ss")).sendKeys(text);
  }

  public boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public String closeAlertAndGetItsText() {
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

  public void selectStarsCount() {
    //нехороший локатор для чекбоксов количества звезд
    //Нужно искоть div в котором есть p с подстрокой "звезд"
    //После него идет div в котором есть a с data-id=class-1 (где 1 - это количество звезд) у которого значение data-value=1 - это количество звезд
    //data-count=6 - количество найденных записей
    //внутри находится input - это и есть чекбокс
  }

  public void goToSearchPage() {
    driver.get("https://www.booking.com");
  }

  public void goToSeachResultPage() {
    driver.get("https://www.booking.com/searchresults.ru.html");
  }
}
