package by.booking.pkt.recorded;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.Assertion;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TBase {
  protected WebDriver webDriver;
  protected WebDriverWait wait;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  Assertion assertion = new Assertion();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    webDriver = new ChromeDriver();
    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait  = new WebDriverWait(webDriver, 5);
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
   /* webDriver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }*/
  }

  protected void goToMainSearchPage() {
    webDriver.get("https://www.booking.com");
  }
  public void logIn() {
    WebElement signInForm = null;
    By usernameBy;
    By passwordBy = By.name("password");

    //проверка заголовка для logIn (старый - с кнопками, или новый
    //с появляющиейся формой при наведении или клике
    if (isElementPresent(By.cssSelector("div#user_form li#current_account_create"))) {
      //вариант 1. Старая форма. Стабильно открывается в firefox. Периодически открывается в Chrome
      webDriver.findElement(By.id("current_account")).click();
    } else if (isElementPresent(By.cssSelector("li#current_account a[role=button]"))) {
      //вариант 2. Новая форма. Основная форма в Chrome
      while (!isElementPresent(By.cssSelector("[data_command=show_auth_lightbox]"))) {
        //достаточно навести курсор
        webDriver.findElement(By.id("current_account")).click();
      }
      webDriver.findElement(By.cssSelector("[data_command=show_auth_lightbox]")).click();
    }
    System.out.println("ПРИВЕТ");
    //wait.until();
    //проверка вида формы для логина - есть с последовательным вводом логин-> пароль
    //есть форма для одновременнго ввода логина и пароля

    if (webDriver.findElement(By.tagName("body")).getAttribute("dir").contains("ltr")){
      System.out.println("SINGLE FORM");
      usernameBy = By.name("username");
      wait.until(visibilityOfElementLocated(usernameBy));
      webDriver.findElement(usernameBy).click();
      webDriver.findElement(usernameBy).clear();
      webDriver.findElement(usernameBy).sendKeys("parhkatechno@gmail.com");
      webDriver.findElement(By.cssSelector("[type=submit]")).click();
      wait.until(visibilityOfElementLocated(passwordBy));
      webDriver.findElement(passwordBy).click();
      webDriver.findElement(passwordBy).clear();
      webDriver.findElement(passwordBy).sendKeys("rfr3t2fRFR");
      webDriver.findElement(By.cssSelector("[type=submit]")).click();
    } else if (isElementPresent(By.cssSelector("form[target=log_tar]"))) {
      System.out.println("DOUBLE FORM");
      signInForm = webDriver.findElement(By.cssSelector("form[target=log_tar]"));
      usernameBy = By.name("username");
      wait.until(visibilityOfElementLocated(usernameBy));
      signInForm.findElement(usernameBy).click();
      signInForm.findElement(usernameBy).clear();
      signInForm.findElement(usernameBy).sendKeys("parhkatechno@gmail.com");
      signInForm.findElement(passwordBy).click();
      signInForm.findElement(passwordBy).clear();
      signInForm.findElement(passwordBy).sendKeys("rfr3t2fRFR");
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    }
  }

  public void logOut(){
    callAccountMenu();
    webDriver.findElement(By.cssSelector("input[name=logout]+input")).click();
  }

  public void goToWishlists(){
    callAccountMenu();
    webDriver.findElement(By.cssSelector("div[class*=wishlists")).click();
  }


  private void callAccountMenu() {
    webDriver.findElement(By.cssSelector("#current_account span[class=user_name_block]")).click();
  }



  private boolean isElementPresent(By locator) {
    return webDriver.findElements(locator).size() > 0;
  }

  private boolean isAlertPresent() {
    try {
      webDriver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = webDriver.switchTo().alert();
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

//  protected void selectDateRange() {
//    webDriver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='чт, 21 февр. - вс, 24 февр. (3 ночей)'])[1]/following::span[2]")).click();
//    webDriver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='-'])[3]/following::span[2]")).click();
//    webDriver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='вс'])[2]/following::td[32]")).click();
//  }
//
//  protected void fillTextFieldCity() {
//    webDriver.findElement(By.id("ss")).click();
//    webDriver.findElement(By.id("ss")).clear();
//    webDriver.findElement(By.id("ss")).sendKeys("Чехия");
//  }
//
//
//

//
//  protected void selectStarsCount() {
//    webDriver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='+'])[3]/following::span[1]")).click();
//    webDriver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='без звезд'])[1]/preceding::div[7]")).click();
//    webDriver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Массаж'])[1]/following::div[5]")).click();
//  }
//
//  protected void selectChildrenCount() {
//    webDriver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Детей'])[1]/following::span[1]")).click();
//    webDriver.findElement(By.id("group_children")).clear();
//    webDriver.findElement(By.id("group_children")).sendKeys("1");
//  }
//
//  protected void selectAdoultsCount() {
//    webDriver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Взрослых'])[1]/following::span[1]")).click();
//    webDriver.findElement(By.id("group_adults")).clear();
//    webDriver.findElement(By.id("group_adults")).sendKeys("2");
//  }
//
//  protected void selectRoomsCount() {
//    webDriver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Номера'])[1]/following::span[1]")).click();
//    webDriver.findElement(By.id("no_rooms")).clear();
//    webDriver.findElement(By.id("no_rooms")).sendKeys("1");
//  }
//


//

}

