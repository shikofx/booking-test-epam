package by.booking.pkt.recorded;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.Assertion;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
    //webDriver.manage().window().maximize();
    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait = new WebDriverWait(webDriver, 15);
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

  public void logIn() throws NullPointerException{

    wait.until(presenceOfElementLocated(By.cssSelector("#current_account")));

    boolean isDropedMenu = false;
    try{
      isDropedMenu = webDriver.findElement(By.cssSelector("#current_account a")).getAttribute("role").contains("button");
    } catch (NullPointerException e) {
      isDropedMenu = false;
    }

    By logInButton;

    if(isDropedMenu) {
      activateDropdownMenu(By.cssSelector("#current_account a"), By.cssSelector("div.profile-menu"));
      logInButton = By.cssSelector("div.profile-menu-auth-item a.js-header-login-link");
    } else {
      logInButton = By.cssSelector("#current_account a");
    }

    boolean single_form = false;
    try{
      single_form = webDriver.findElement(By.cssSelector("li#current_account a")).getAttribute("data-command-params").contains("redirect_uri");
    } catch (NullPointerException e) {
      single_form = false;
    }
    System.out.println("single_form = " + single_form);
    webDriver.findElement(logInButton).click();
    WebElement signInForm = null;
    By usernameLocator = By.cssSelector("[name=username]");
    By passwordLocator = By.cssSelector("[name=password]");
    if (single_form) {
      wait.until(presenceOfElementLocated(By.cssSelector("body[dir*=ltr]")));
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm = webDriver.findElement(By.cssSelector("form.nw-signin"));
      signInForm.findElement(usernameLocator).click();
      signInForm.findElement(usernameLocator).clear();
      signInForm.findElement(usernameLocator).sendKeys("pkt.autotests@gmail.com");
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
      wait.until(visibilityOfElementLocated(passwordLocator));
      signInForm = webDriver.findElement(By.cssSelector("form.nw-signin"));
      signInForm.findElement(passwordLocator).click();
      signInForm.findElement(passwordLocator).clear();
      signInForm.findElement(passwordLocator).sendKeys("123456789");
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    } else {
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm = webDriver.findElement(By.cssSelector("form[target=log_tar]"));
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm.findElement(usernameLocator).click();
      signInForm.findElement(usernameLocator).clear();
      signInForm.findElement(usernameLocator).sendKeys("pkt.autotests@gmail.com");
      signInForm.findElement(passwordLocator).click();
      signInForm.findElement(passwordLocator).clear();
      signInForm.findElement(passwordLocator).sendKeys("123456789");
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    }
    wait.until(visibilityOfElementLocated(By.cssSelector("span.user_name_block")));
  }

  public void logOut() {
    activateDropdownMenu(By.cssSelector("#current_account span[class=user_name_block]"), By.cssSelector(".profile-menu"));
    webDriver.findElement(By.cssSelector("input[name=logout]+input")).click();
  }

  public void goToWishlists() {
    activateDropdownMenu(By.cssSelector("#current_account span[class=user_name_block]"), By.cssSelector(".profile-menu"));
    webDriver.findElement(By.cssSelector("div[class*=wishlists")).click();
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

  public void createNewWishlist() {

    wait.until(visibilityOfElementLocated(By.cssSelector("button[class*=js-listview-create-list] span")));
    webDriver.findElement(By.cssSelector("button[class*=js-listview-create-list] span")).click();


    Alert alertCreateList = wait.until(alertIsPresent());
    alertCreateList.sendKeys("Go to British");
    alertCreateList.accept();
    //!!!Список должен увеличиться на 1. Должна быть выбрана созданная группа
  }

  protected int listsCount() {
    System.out.println("Считаем количество списков");
    wait.until(presenceOfElementLocated(By.cssSelector(".js-listview-header-dropdown")));
    webDriver.findElement(By.cssSelector(".js-listview-header-dropdown")).click();
    wait.until(presenceOfElementLocated(By.cssSelector(".listview__lists")));
    int listsCount = webDriver.findElements(By.cssSelector(".listview__lists div")).size();
    webDriver.findElement(By.cssSelector(".js-listview-header-dropdown")).click();
    return listsCount;
  }

  protected String getAttributeWithWait() {
    wait.until(presenceOfElementLocated(By.cssSelector("header[class*=header]")));
    return webDriver.findElement(By.cssSelector("header[class*=header] a")).getAttribute("href");
  }

  protected String getTextWithWait() {
    wait.until(presenceOfElementLocated(By.cssSelector("div.wl-bui-header h1")));
    return webDriver.findElement(By.cssSelector("div.wl-bui-header h1")).getText();
  }

  protected void goToWishCreatesListFromItem(WebElement newWishlist) {
    if (newWishlist.findElement(By.cssSelector("input")).isSelected()) {
      newWishlist.findElement(By.cssSelector("input~span a")).click();

    }
  }

  protected WebElement addNewWishlistInItemPage() {
    List<WebElement> oldWishlists = webDriver.findElements(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"));
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).click();
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).clear();
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).sendKeys("Go to France");
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).sendKeys(Keys.ENTER);
    wait.until(numberOfElementsToBe(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"), oldWishlists.size() + 1));
    List<WebElement> newWishlists = webDriver.findElements(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"));
    unselectAllElementsInList(oldWishlists);
    newWishlists.removeAll(oldWishlists);
    return newWishlists.iterator().next();
  }

  protected void activateDropdownMenu(By whatClick, By dropDownMenu) {
    wait.until(presenceOfElementLocated(whatClick));
    webDriver.findElement(whatClick).click();
    WebElement dropDownMenuElement = wait.until(presenceOfElementLocated(dropDownMenu));
    for (int i = 0; i < 3 && !dropDownMenuElement.isDisplayed(); i++) {
      webDriver.findElement(whatClick).click();
    }
  }

  protected void goToItemFromSearchResults(int itemNumber) {
    WebElement item = webDriver.findElement(By.cssSelector("#hotellist_inner>.sr_item:nth-of-type(" + itemNumber + ")"));
    item.findElement(By.cssSelector(".sr-cta-button-row")).click();
  }

  protected String switchToNewWindow(Set<String> oldWindowSet, Set<String> newWindowSet) {
    String newWindow = null;
    if (newWindowSet.removeAll(oldWindowSet) && newWindowSet.size() == 1) {
      newWindow = newWindowSet.iterator().next();
      webDriver.switchTo().window(newWindow);
    }
    return newWindow;
  }

  private void unselectAllElementsInList(List<WebElement> list) {
    for (WebElement e : list) {
      if (e.findElement(By.cssSelector("input")).isSelected())
        e.findElement(By.cssSelector("input~span.bui-checkbox__label")).click();
    }
  }

  protected void submitMainSearch() {
    webDriver.findElement(By.cssSelector("button[data-sb-id=main][type=submit]")).click();
  }

  protected void selectChildrenCount(int childrenCount) {
    if (webDriver.findElement(By.cssSelector("label#xp__guests__toggle+div")).getAttribute("className").contains("hidden")) {
      webDriver.findElement(By.cssSelector("label#xp__guests__toggle")).click();
    }
    int actualChildrenCount = -1;
    while (childrenCount != actualChildrenCount) {
      actualChildrenCount = Integer.parseInt(webDriver.findElement(By.cssSelector("#group_children")).getAttribute("defaultValue"));
      if (actualChildrenCount > childrenCount && actualChildrenCount > 0) {
        webDriver.findElement(By.cssSelector("#group_children~button[class*=subtract-button]")).click();
      } else if (actualChildrenCount < childrenCount) {
        webDriver.findElement(By.cssSelector("#group_children~button[class*=add-button]")).click();
      }
    }
  }

  protected void selectAdultsCount(int adultsCount) {
    if (webDriver.findElement(By.cssSelector("label#xp__guests__toggle+div")).getAttribute("className").contains("hidden")) {
      webDriver.findElement(By.cssSelector("label#xp__guests__toggle")).click();
    }
    int actualAdultsCount = -1;
    while (adultsCount != actualAdultsCount) {
      actualAdultsCount = Integer.parseInt(webDriver.findElement(By.cssSelector("#group_adults")).getAttribute("defaultValue"));
      if (actualAdultsCount > adultsCount && actualAdultsCount > 0) {
        webDriver.findElement(By.cssSelector("#group_adults~button[class*=subtract-button]")).click();
      } else if (actualAdultsCount < adultsCount) {
        webDriver.findElement(By.cssSelector("#group_adults~button[class*=add-button]")).click();
      }
    }
  }

  protected void selectRoomsCount(int roomsCount) {
    if (webDriver.findElement(By.cssSelector("label#xp__guests__toggle+div")).getAttribute("className").contains("hidden")) {
      webDriver.findElement(By.cssSelector("label#xp__guests__toggle")).click();
    }
    int actualRooms = Integer.parseInt(webDriver.findElement(By.cssSelector("#no_rooms")).getAttribute("defaultValue"));
    int actualRoomsCount = -1;
    while (roomsCount != actualRoomsCount) {
      actualRoomsCount = Integer.parseInt(webDriver.findElement(By.cssSelector("#no_rooms")).getAttribute("defaultValue"));
      if (actualRoomsCount > roomsCount && actualRoomsCount > 0) {
        webDriver.findElement(By.cssSelector("#no_rooms~button[class*=subtract-button]")).click();
      } else if (actualRoomsCount < roomsCount) {
        webDriver.findElement(By.cssSelector("#no_rooms~button[class*=add-button]")).click();
      }
    }
  }

  protected void selectDates(String checkInDate, String checkOutDate) {
    String[] firstMonthArray = checkInDate.split("-");
    firstMonthArray[2] = "01";
    String firstMonth = firstMonthArray[0] + "-" + firstMonthArray[1] + "-" + firstMonthArray[2];
    webDriver.findElement(By.cssSelector("div.xp__dates.xp__group")).click();
    // String currentFirstMonth = ;

    while (!firstMonth.equals(webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date^='20']")).getAttribute("data-date"))) {
      webDriver.findElement(By.cssSelector("div[data-bui-ref=calendar-next]")).click();
      //  currentFirstMonth = webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date^='20']")).getAttribute("data-date");
    }
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='" + checkInDate + "']")).click();
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='" + checkOutDate + "']")).click();
    webDriver.findElement(By.cssSelector("div.xp__dates.xp__group")).click();
  }

  protected void enterAccomodaition(String place) {
    webDriver.findElement(By.cssSelector("#ss")).click();
    webDriver.findElement(By.cssSelector("#ss")).clear();
    webDriver.findElement(By.cssSelector("#ss")).sendKeys(place);
  }

  protected void selectCurrency() {
    activateDropdownMenu(By.cssSelector("[data-id=currency_selector]"), By.cssSelector("#current_currency"));
    wait.until(visibilityOfElementLocated(By.cssSelector("#current_currency_foldout")));
    webDriver.findElement(By.cssSelector("a[data-currency=RUB")).click();
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

