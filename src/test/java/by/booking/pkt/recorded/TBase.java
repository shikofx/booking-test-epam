package by.booking.pkt.recorded;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.Assertion;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

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
    wait = new WebDriverWait(webDriver, 5);
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

  public void logIn(){
    WebElement signInForm = null;
    boolean single_form = false;
    boolean double_form = false;
    By usernameLocator = By.name("username");
    By passwordLocator = By.name("password");
    if (isElementPresent(By.cssSelector("div#user_form li#current_account_create"))) {
      //coice 1: button and double or single signin form
      if (webDriver.findElement(By.cssSelector("li#current_account a")).getAttribute("data-command-params").contains("redirect_uri")) {
        single_form = true;
      } else {
        double_form = true;
      }
      webDriver.findElement(By.id("current_account")).click();
    } else if (isElementPresent(By.cssSelector("li#current_account a[role=button]"))) {
      //choice 2: light_box + double signin form
      for (int i = 0; i < 3; i++) {
        webDriver.findElement(By.id("current_account")).click();
        if(isElementPresent(By.cssSelector("[data_command=show_auth_lightbox]"))){
          double_form = true;
          webDriver.findElement(By.cssSelector("[data_command=show_auth_lightbox]")).click();
          break;
        }
      }
    }
    if (single_form) {
      wait.until(presenceOfElementLocated(By.cssSelector("body[dir*=ltr]")));
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm = webDriver.findElement(By.cssSelector("form.nw-signin"));
      signInForm.findElement(usernameLocator).click();
      signInForm.findElement(usernameLocator).clear();
      signInForm.findElement(usernameLocator).sendKeys("parhkatechno@gmail.com");
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
      wait.until(visibilityOfElementLocated(passwordLocator));
      signInForm = webDriver.findElement(By.cssSelector("form.nw-signin"));
      signInForm.findElement(passwordLocator).click();
      signInForm.findElement(passwordLocator).clear();
      signInForm.findElement(passwordLocator).sendKeys("rfr3t2fRFR");
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    } else if (double_form) {
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm = webDriver.findElement(By.cssSelector("form[target=log_tar]"));
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm.findElement(usernameLocator).click();
      signInForm.findElement(usernameLocator).clear();
      signInForm.findElement(usernameLocator).sendKeys("parhkatechno@gmail.com");
      signInForm.findElement(passwordLocator).click();
      signInForm.findElement(passwordLocator).clear();
      signInForm.findElement(passwordLocator).sendKeys("rfr3t2fRFR");
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    }
  }

  public void logOut() {
    callAccountMenu();
    webDriver.findElement(By.cssSelector("input[name=logout]+input")).click();
  }

  public void goToWishlists() {
    callAccountMenu();
    webDriver.findElement(By.cssSelector("div[class*=wishlists")).click();
  }


  private void callAccountMenu() {
    webDriver.findElement(By.cssSelector("#current_account span[class=user_name_block]")).click();
  }


  public boolean isElementPresent(By locator) {
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

  public void createNewWishlist(){
    wait.until(visibilityOfElementLocated(By.cssSelector("button[class*=js-listview-create-list] span")));
    System.out.println("СОЗДАТЬ СПИСОК");
    webDriver.findElement(By.cssSelector("button[class*=js-listview-create-list] span")).click();
    Alert alertCreateList = wait.until(alertIsPresent());
    alertCreateList.sendKeys("Go to British");
    alertCreateList.accept();
    //!!!Список должен увеличиться на 1. Должна быть выбрана созданная группа
    wait.until(textMatches(By.cssSelector("div[class*=bui-dropdown] span"), Pattern.compile("Go to British")));
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

