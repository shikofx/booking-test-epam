package by.booking.pkt.application;

import by.booking.pkt.model.SearchData;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SearchPageHelper extends HelperBase {
  public SearchPageHelper(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
    PageFactory.initElements(webDriver, this);
  }

  @FindBy(id = "ss")
  private WebElement placeInput;

  @FindBy(css = "[data-id=currency_selector]")
  private WebElement currencySelectButton;

  @FindBy(css = "button[data-sb-id=main][type=submit]")
  private WebElement searchButton;

  @FindBy(css = "#current_currency_foldout")
  private WebElement currencyPanel;

  @FindBy(css = "[name=selected_currency]")
  private WebElement selectedCurrency;

  @FindBy(css = "div.xp__dates.xp__group")
  private WebElement initCalendar;

  @FindBy(css = "div.bui-calendar")
  private WebElement calendarPanel;

  @FindBy(css = "div.bui-calendar td[data-date^='20']")
  private WebElement firstDate;

  @FindBy(css = "div[data-bui-ref=calendar-next]")
  private WebElement nextMonth;

  @FindBy(css = "label#xp__guests__toggle")
  private WebElement initGuestsPanel;

  @FindBy(css = "div.focussable")
  private WebElement guestsPanel;

  public SearchPageHelper searchFor(SearchData searchData) {
    setCurrency(searchData.currency()).
            setPlace(searchData.whereGo()).
            setDates(searchData.inDate(), searchData.outDate()).
            setRooms(searchData.roomsCount()).
            setAdults(searchData.adultsCount()).
            setChildren(searchData.childrenCount()).
            initSearch();
    return this;
  }

  private SearchPageHelper initSearch() {
    searchButton.click();
    return this;
  }

  private SearchPageHelper setCurrency(String currency) {
    if (!selectedCurrency.getAttribute("value").equals(currency)) {
   //   wait.until((WebDriver d)->{return currencyPanel.isEnabled();});
     // displayDropDown(By.cssSelector("[data-id=currency_selector]"), By.cssSelector("#current_currency_foldout"), 15);
      displayDropDown(currencySelectButton, currencyPanel, 15);
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
    displayDropDown(initCalendar, calendarPanel, 5);
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
    displayDropDown(initGuestsPanel, guestsPanel,5);
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

