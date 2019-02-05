package by.booking.pkt.recorded;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.Assertion;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
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
    //webDriver.manage().window().maximize();
    webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wait = new WebDriverWait(webDriver, 15);
    goToSearchPage();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
   /* webDriver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }*/
  }
  @Nullable
  public String app_getTextByPatternNoSpace(String regex, String inString){
    return app_getTextByPattern(regex, inString).replaceAll("\\s", "");
  }

  @Nullable
  public String app_getTextByPattern(String regex, String string) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(string);
    String result= null;
    while(matcher.find()) {
      result = string.substring(matcher.start(), matcher.end());
    }
    return result;
  }

  protected void goToSearchPage() {
    webDriver.get("https://www.booking.com");
  }

  public void hdr_login(String username, String password) throws NullPointerException{
    By logInButton = hdr_findLoginButton();
    boolean single_form = hdr_isSingleForm();
    hdr_openLoginForm(logInButton);
    WebElement signInForm = null;
    By usernameLocator = By.cssSelector("[name=username]");
    By passwordLocator = By.cssSelector("[name=password]");
    if (single_form) {
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm = webDriver.findElement(By.cssSelector("form.nw-signin"));
      signInForm.findElement(usernameLocator).click();
      signInForm.findElement(usernameLocator).clear();
      signInForm.findElement(usernameLocator).sendKeys(username);
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
      wait.until(visibilityOfElementLocated(passwordLocator));
      signInForm = webDriver.findElement(By.cssSelector("form.nw-signin"));
      signInForm.findElement(passwordLocator).click();
      signInForm.findElement(passwordLocator).clear();
      signInForm.findElement(passwordLocator).sendKeys(password);
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    } else {
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm = webDriver.findElement(By.cssSelector("form[target=log_tar]"));
      wait.until(visibilityOfElementLocated(usernameLocator));
      signInForm.findElement(usernameLocator).click();
      signInForm.findElement(usernameLocator).clear();
      signInForm.findElement(usernameLocator).sendKeys(username);

      signInForm.findElement(passwordLocator).click();
      signInForm.findElement(passwordLocator).clear();
      signInForm.findElement(passwordLocator).sendKeys(password);
      signInForm.findElement(By.cssSelector("[type=submit]")).click();
    }
  }

  private boolean hdr_isSingleForm() {
    boolean single_form = false;
    try{
      single_form = webDriver.findElement(By.cssSelector("li#current_account a")).getAttribute("data-command-params").contains("redirect_uri");
    } catch (NullPointerException e) {
      single_form = false;
    }
    return single_form;
  }

  private void hdr_openLoginForm(By logInButton) {
    webDriver.findElement(logInButton).click();
  }

  @NotNull
  private By hdr_findLoginButton() {
    wait.until(presenceOfElementLocated(By.cssSelector("#current_account a")));
    boolean isDropedMenu = false;
    try{
      isDropedMenu = webDriver.findElement(By.cssSelector("#current_account a")).getAttribute("role").contains("button");
    } catch (NullPointerException e) {
      isDropedMenu = false;
    }

    By logInButton;

    if(isDropedMenu) {
      app_activateDropdownMenu(By.cssSelector("#current_account a"), By.cssSelector("div.profile-menu"));
      logInButton = By.cssSelector("div.profile-menu-auth-item a.js-header-hdr_login-link");
    } else {
      logInButton = By.cssSelector("#current_account a");
    }
    return logInButton;
  }

  public void hdr_logOut() {
    app_activateDropdownMenu(By.cssSelector("#current_account span[class=user_name_block]"), By.cssSelector(".profile-menu"));
    webDriver.findElement(By.cssSelector("input[name=logout]+input")).click();
  }

  public void hfr_goToWishlistsPage() {
    app_activateDropdownMenu(By.cssSelector("#current_account span[class=user_name_block]"), By.cssSelector(".profile-menu"));
    webDriver.findElement(By.cssSelector("div[class*=wishlists")).click();
  }

  public boolean app_isElementPresent(By locator) {
    return webDriver.findElements(locator).size() > 0;
  }
  public boolean app_isElementPresent(WebElement webElement, By locator) {
    return webElement.findElements(locator).size() > 0;
  }

  public boolean app_isElementInPresentNoWait(By locator, int oldWait) {
    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    boolean isElementPresent = webDriver.findElements(locator).size() > 0;
    webDriver.manage().timeouts().implicitlyWait(oldWait, TimeUnit.SECONDS);
    return isElementPresent;

  }

  public boolean app_isElementInPresentNoWait(WebElement webElement, By locator, int oldWait) {
    webDriver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    boolean isElementPresent = webElement.findElements(locator).size() > 0;
    webDriver.manage().timeouts().implicitlyWait(oldWait, TimeUnit.SECONDS);
    return isElementPresent;
  }
  private boolean app_isAlertPresent() {
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

  public void wl_createNewWishlist(String listName) {
    wait.until(visibilityOfElementLocated(By.cssSelector("button[class*=js-listview-create-list] span")));
    webDriver.findElement(By.cssSelector("button[class*=js-listview-create-list] span")).click();
    Alert alertCreateList = wait.until(alertIsPresent());
    alertCreateList.sendKeys(listName);
    alertCreateList.accept();
    webDriver.navigate().refresh();
  }

  protected int wl_getCount() {
    wait.until(presenceOfElementLocated(By.cssSelector(".js-listview-header-dropdown")));
    webDriver.findElement(By.cssSelector(".js-listview-header-dropdown")).click();
    wait.until(presenceOfElementLocated(By.cssSelector(".listview__lists")));
    int count = webDriver.findElements(By.cssSelector(".listview__lists div")).size();
    webDriver.findElement(By.cssSelector(".js-listview-header-dropdown")).click();
    return count;
  }

  protected String app_getAttributeWithWait(By locatorForWait, String attributeName) {
    wait.until(presenceOfElementLocated(locatorForWait));
    return webDriver.findElement(locatorForWait).getAttribute(attributeName);
  }

  protected String app_getTextWithWait(By locator) {
    wait.until(presenceOfElementLocated(locator));
    return webDriver.findElement(locator).getText();
  }

  protected void hotel_goToCreatedWishlist(WebElement newWishlist) {
    if (newWishlist.findElement(By.cssSelector("input")).isSelected()) {
      newWishlist.findElement(By.cssSelector("input~span a")).click();
    } else{
      throw new NoSuchElementException("Element isn't selected!");
    }
  }

  protected WebElement hotel_addWishlistFromSearchResultPage(String listName) {
    List<WebElement> oldWishlists = webDriver.findElements(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"));
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).click();
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).clear();
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).sendKeys(listName);
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).sendKeys(Keys.ENTER);
    wait.until(numberOfElementsToBe(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"), oldWishlists.size() + 1));
    List<WebElement> newWishlists = webDriver.findElements(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"));
    unselectAllElementsInList(oldWishlists);
    newWishlists.removeAll(oldWishlists);
    return newWishlists.iterator().next();
  }

  protected void app_activateDropdownMenu(By whatClick, By dropDownMenu) {
    wait.until(presenceOfElementLocated(whatClick));
    webDriver.findElement(whatClick).click();
    WebElement dropDownMenuElement = wait.until(presenceOfElementLocated(dropDownMenu));
    for (int i = 0; i < 3 && !dropDownMenuElement.isDisplayed(); i++) {
      webDriver.findElement(whatClick).click();
    }
    wait.until(visibilityOfElementLocated(dropDownMenu));
  }

  protected void sr_goToSearchResult(int itemNumber) {
    WebElement item = webDriver.findElement(By.cssSelector("#hotellist_inner>.sr_item:nth-of-type(" + itemNumber + ")"));
    item.findElement(By.cssSelector(".sr-cta-button-row")).click();
  }

  protected String app_switchToNewWindow(Set<String> oldWindowSet) {
    Set<String> newWindowSet = webDriver.getWindowHandles();
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

  protected void ms_initSearch() {
    webDriver.findElement(By.cssSelector("button[data-sb-id=main][type=submit]")).click();
  }

  protected void ms_setChildrenCount(int childrenCount) {
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

  protected void ms_setAdultsCount(int adultsCount) {
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

  protected void ms_setRoomsCount(int roomsCount) {
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

  protected void ms_setDatesRange(String checkInDate, String checkOutDate) {
    String[] firstMonthArray = checkInDate.split("-");
    firstMonthArray[2] = "01";
    String firstMonth = firstMonthArray[0] + "-" + firstMonthArray[1] + "-" + firstMonthArray[2];
    webDriver.findElement(By.cssSelector("div.xp__dates.xp__group")).click();
    while (!firstMonth.equals(webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date^='20']")).getAttribute("data-date"))) {
      webDriver.findElement(By.cssSelector("div[data-bui-ref=calendar-next]")).click();
    }
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='" + checkInDate + "']")).click();
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='" + checkOutDate + "']")).click();
    webDriver.findElement(By.cssSelector("div.xp__dates.xp__group")).click();
  }

  protected void ms_setPlace(String place) {
    webDriver.findElement(By.cssSelector("#ss")).click();
    webDriver.findElement(By.cssSelector("#ss")).clear();
    webDriver.findElement(By.cssSelector("#ss")).sendKeys(place);
  }

  protected void hdr_selectCurrency(final String currency) {
    app_activateDropdownMenu(By.cssSelector("[data-id=currency_selector]"), By.cssSelector("#current_currency"));
    wait.until(visibilityOfElementLocated(By.cssSelector("#current_currency_foldout")));
    webDriver.findElement(By.cssSelector("a[data-currency=" + currency)).click();
  }

  protected String hotel_getHotelName(WebElement item) {
    wait.until(presenceOfNestedElementLocatedBy(item, By.cssSelector("span.sr-hotel__name")));
    return (item.findElement(By.cssSelector("span.sr-hotel__name")).getText());
  }

  protected int sr_totalPrice(WebElement item) {
    wait.until(presenceOfNestedElementLocatedBy(item, By.cssSelector("div.totalPrice")));
    return Integer.parseInt(app_getTextByPatternNoSpace("(?<=:).+\\d", item.findElement(By.cssSelector("div.totalPrice")).getText()));
  }

  protected List<WebElement> fb_getItemsOfFilterByBudget() {
    return webDriver.findElements(By.cssSelector("a[data-id^=pri]"));
  }


  protected void fb_setOnlyAvailableON() {
    webDriver.findElement(By.cssSelector("[data-name=oos] div")).click();
    webDriver.navigate().refresh();
  }

  protected int fb_getNigtsCount() {
    return Integer.parseInt(app_getTextByPatternNoSpace("\\d+", webDriver.findElement(By.cssSelector("div.sb-dates__los")).getText()));
  }

  protected List<WebElement> sr_getAll() {
    List<WebElement> searchResultItems;
    //Найти все отели до записи: есть еще отели вне определенной локации
    if(app_isElementPresent(By.cssSelector("div.sr_separator"))) {
      searchResultItems = webDriver.findElements(By.xpath("//*/div[contains(@class,'sr_separator')]/preceding-sibling::div[contains(@class,'sr_item')]"));
    } else {
      searchResultItems = webDriver.findElements(By.cssSelector("div.sr_item"));
    }
    return searchResultItems;
  }

  protected void fb_setStarsCount(int stars) {
    webDriver.findElement(By.cssSelector("a[data-id^=class][data-value='"+stars+"']")).click();
  }

  @Nullable
  protected int fb_setBudget(int budget) {
    List<WebElement> filterItems = fb_getItemsOfFilterByBudget();
    WebElement filterElement = null;
    for(int i = 0; i<filterItems.size(); i++){
      filterElement = filterItems.get(i);
      if (budget < Integer.parseInt(filterElement.getAttribute("data-value"))) {
        break;
      }
    }
    filterElement.click();
    int totalBudget = fb_getNigtsCount()*Integer.parseInt(filterElement.getAttribute("data-value"));
    if(budget* fb_getNigtsCount()>totalBudget)
      totalBudget = Integer.MAX_VALUE;
    return totalBudget;
  }

  protected String wl_getUrlToSend() {
    app_activateDropdownMenu(By.cssSelector("div[class=bui-dropdown]+button span"), By.cssSelector("div.listview-share"));
    wait.until(visibilityOfElementLocated(By.cssSelector("p[class*=content] input")));
    String url = webDriver.findElement(By.cssSelector("p[class*=content] input")).getAttribute("defaultValue");
    return url;
  }

  protected void sr_initSortByPrice() {
    wait.until(visibilityOfElementLocated(By.cssSelector("li.sort_price")));
    webDriver.findElement(By.cssSelector("li.sort_price")).click();
  }

  protected int hotel_distanceToDest(WebElement item) {
    int distance = 0;
    distance = Integer.parseInt(app_getTextByPatternNoSpace("[\\,\\d]+", item.findElement(By.cssSelector("span.distfromdest")).getText().replace(',', '.')));
    String meterField = app_getTextByPatternNoSpace("\\s.*?\\s", item.findElement(By.cssSelector("span.distfromdest")).getText());
    if (meterField.length() > 1) {
      distance = 1000 * distance;
    }
    return distance;
  }

  protected WebElement wl_getDefaultWishlist() {
    return webDriver.findElement(By.cssSelector("div[class*=bui-dropdown] span"));
  }

  protected void ms_fillFields(String place, String checkInDate, String checkOutDate, int roomsCount, int adultsCount, int childrenCount) {
    ms_setPlace(place);
    ms_setDatesRange(checkInDate, checkOutDate);
    ms_setRoomsCount(roomsCount);
    ms_setAdultsCount(adultsCount);
    ms_setChildrenCount(childrenCount);
  }

  protected int hotel_getStarsCount(WebElement item) {
    int actualStars;
    if (app_isElementInPresentNoWait(item, By.cssSelector("svg[class*=stars]"), 30)) {
      actualStars = Integer.parseInt(app_getTextByPatternNoSpace("\\d", item.findElement(By.cssSelector("svg[class*=stars]")).getAttribute("class")));
    } else {
      actualStars = 0;
    }
    return actualStars;
  }

  protected String wl_getHeader() {
    return app_getTextWithWait(By.cssSelector("div.wl-bui-header h1"));
  }

  protected String app_getUrlHead() {
    return app_getTextByPattern(webDriver.getCurrentUrl(), ".+/[A-Za-z0-9_-]*");
  }

  protected void sr_initSortByDistance(By sortByDistance) {
    wait.until(visibilityOfElementLocated(sortByDistance));
    webDriver.findElement(sortByDistance).click();
  }

  protected int hotel_totalPrice(WebElement item) {
    int totalPrice = 0;
    if (app_isElementInPresentNoWait(item, By.cssSelector("div.totalPrice"), 30)) {
      totalPrice = Integer.parseInt(app_getTextByPatternNoSpace("(?<=:).+\\d", item.findElement(By.cssSelector("div.totalPrice")).getText()));
    } else {
      totalPrice = Integer.parseInt(app_getTextByPatternNoSpace("[\\d\\s]+\\d", item.findElement(By.cssSelector("strong.price")).getText()));
    }
    return totalPrice;

  }
}

