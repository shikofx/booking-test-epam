package by.booking.pkt.application;

import by.booking.pkt.model.TestsData;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageHelper extends HelperBase {
  public SearchPageHelper(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  @FindBy(id = "ss")
  public WebElement placeInput;

  @FindBy(css = "[data-id=currency_selector]")
  public WebElement currencySelectButton;

  @FindBy(css = "button[data-sb-id=main][type=submit]")
  public WebElement searchButton;

  @FindBy(css = "#current_currency_foldout")
  public WebElement currencyPanel;

  @FindBy(css = "[name=selected_currency]")
  public WebElement selectedCurrency;

  @FindBy(css = "div.xp__dates.xp__group")
  public WebElement initCalendar;

  @FindBy(css = "div.bui-calendar")
  public WebElement calendarPanel;

  @FindBy(css = "div.bui-calendar td[data-date^='20']")
  public WebElement firstDate;

  @FindBy(css = "div[data-bui-ref=calendar-next]")
  public WebElement nextMonth;

  @FindBy(css = "label#xp__guests__toggle")
  public WebElement initGuestsPanel;

  @FindBy(css = "div.xp__guests__inputs")
  public WebElement guestsPanel;

   public SearchPageHelper searchFor(TestsData testsData) {
      setCurrency(testsData.currency()).
              setPlace(testsData.placeTo()).
              setDates(testsData.inDate(), testsData.outDate()).
              setRooms(testsData.roomsCount()).
              setAdults(testsData.adultsCount()).
              setChildren(testsData.childrenCount()).
            initSearch();
    return this;
  }

  private void initGuestsPanel() {
    if (guestsPanel.getAttribute("className").contains("hidden")) {
      initGuestsPanel.click();
    }
  }

  private SearchPageHelper initSearch() {
    searchButton.click();
    return this;
  }

  private SearchPageHelper setCurrency(String currency) {
    if (!selectedCurrency.getAttribute("value").equals(currency)) {
      displayDropDown(currencySelectButton, currencyPanel, 5);
      webDriver.findElement(By.cssSelector("a[data-currency=" + currency + "]")).click();
    }
    return this;
  }

  private SearchPageHelper setPlace(String place) {
    inputText(placeInput, place);
    return this;
  }

  private SearchPageHelper setDates(String checkInDate, String checkOutDate) {
    String firstDayOfMonthMonth = firstDate(checkInDate);
    displayDropDown(initCalendar, calendarPanel,5);
    while (!firstDayOfMonthMonth.equals(firstDate.getAttribute("data-date"))) {
      nextMonth.click();
    }
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='" + checkInDate + "']")).click();
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='" + checkOutDate + "']")).click();
    initCalendar.click();
    return this;
  }

  @NotNull
  private String firstDate(String checkInDate) {
    String[] firstMonthArray = checkInDate.split("-");
    firstMonthArray[2] = "01";
    return firstMonthArray[0] + "-" + firstMonthArray[1] + "-" + firstMonthArray[2];
  }

  private SearchPageHelper setChildren(int count) {
    setCount("group_children", count);
    return this;
  }

  private SearchPageHelper setAdults(int count) {
    setCount("group_adults", count);
    return this;
  }

  private SearchPageHelper setRooms(int count) {
    setCount("no_rooms", count);
    return this;
  }

  private void setCount(String id, int count) {
    initGuestsPanel();
    int actualCount = -1;
    By minusBy = By.cssSelector("#" + id + "~button[class*=subtract-button]");
    By plusBy = By.cssSelector("#" + id + "~button[class*=add-button]");
    By currentValueBy = By.cssSelector("#" + id);
    while (count != actualCount) {
      actualCount = Integer.parseInt(webDriver.findElement(currentValueBy).getAttribute("defaultValue"));
      if (actualCount > count && actualCount > 0) {
        webDriver.findElement(minusBy).click();
      } else if (actualCount < count) {
        webDriver.findElement(plusBy).click();
      }
    }
  }
}

