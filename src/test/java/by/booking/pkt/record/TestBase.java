package by.booking.pkt.record;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class TestBase {
  protected WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  protected void selectDateRange() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='чт, 21 февр. - вс, 24 февр. (3 ночей)'])[1]/following::span[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='-'])[3]/following::span[2]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='вс'])[2]/following::td[32]")).click();
  }

  protected void fillTextFieldCity() {
    driver.findElement(By.id("ss")).click();
    driver.findElement(By.id("ss")).clear();
    driver.findElement(By.id("ss")).sendKeys("Чехия");
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  protected void goToMainSearchPage() {
    driver.get("https://www.booking.com");
  }

  protected void selectStarsCount() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='+'])[3]/following::span[1]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='без звезд'])[1]/preceding::div[7]")).click();
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Массаж'])[1]/following::div[5]")).click();
  }

  protected void selectChildrenCount() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Детей'])[1]/following::span[1]")).click();
    driver.findElement(By.id("group_children")).clear();
    driver.findElement(By.id("group_children")).sendKeys("1");
  }

  protected void selectAdoultsCount() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Взрослых'])[1]/following::span[1]")).click();
    driver.findElement(By.id("group_adults")).clear();
    driver.findElement(By.id("group_adults")).sendKeys("2");
  }

  protected void selectRoomsCount() {
    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Номера'])[1]/following::span[1]")).click();
    driver.findElement(By.id("no_rooms")).clear();
    driver.findElement(By.id("no_rooms")).sendKeys("1");
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean areElementsPresent(By locator) {
    return driver.findElements(locator).size() > 0;
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

  protected void logIn() {
    WebElement signInForm = null;
    By username;
    By password= By.name("password");
    //проверка заголовка для logIn (старый - с кнопками, или новый
    //с появляющиейся формой при наведении или клике
    if (areElementsPresent(By.cssSelector("div#user_form li#current_account_create"))) {
      //вариант 1. Старая форма. Стабильно открывается в firefox. Периодически открывается в Chrome
      driver.findElement(By.id("current_account")).click();
    } else if (areElementsPresent(By.cssSelector("li#current_account a[role=button]"))) {
      //вариант 2. Новая форма. Основная форма в Chrome
      while (areElementsPresent(By.cssSelector("[data_command=show_auth_lightbox]"))) {
        //достаточно навести курсор
        driver.findElement(By.id("current_account")).click();
      }
      driver.findElement(By.cssSelector("[data_command=show_auth_lightbox]")).click();
    }

    //проверка вида формы для логина - есть с последовательным вводом логин-> пароль
    //есть форма для одновременнго ввода логина и пароля
    if(areElementsPresent(By.cssSelector("form.nw-signin"))) {
      signInForm = driver.findElement(By.cssSelector("form.nw-signin"));
      username = By.name("loginname");
      signInForm.findElement(username).click();
      signInForm.findElement(username).clear();
      signInForm.findElement(username).sendKeys("parhkatechno@gmail.com");
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    } else if(areElementsPresent(By.cssSelector("form[target=log_tar]"))) {
      signInForm = driver.findElement(By.cssSelector("form[target=log_tar]"));
      username = By.name("username");
      signInForm.findElement(username).click();
      signInForm.findElement(username).clear();
      signInForm.findElement(username).sendKeys("parhkatechno@gmail.com");
    }
    if
    signInForm.findElement(By.name("password")).click();
    signInForm.findElement(By.name("password")).clear();
    signInForm.findElement(By.name("password")).sendKeys("rfr3t2fRFR");
    signInForm.findElement(By.cssSelector("[type=submit]")).click();

    driver.findElement(By.cssSelector("[data_command=show_auth_lightbox]")).click();
    //вариант 1 Здесь элемент li#current_account невидим, т.к. закрывается всплывающей
    //открывается эта форма в Chrome непостоянно. Периодически открывается вторая форма

    //driver.findElement(By.cssSelector("[type=submit]")).click();
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("rfr3t2fRFR");
    WebElement log_form = driver.findElement(By.cssSelector("div.user_access_menu_tabs+div form[target=log_tar]"));
    log_form.findElement(By.cssSelector("input[type=submit]")).click();
  }
}
}
