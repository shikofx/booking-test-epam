package by.booking.pkt.application;

import by.booking.pkt.model.SearchData;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageHelper extends HelperBase {
  public SearchPageHelper(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
    PageFactory.initElements(webDriver, this);
  }

  @FindBy(id = "ss")
  private WebElement placeInput;

  public SearchPageHelper initSearch() {
    webDriver.findElement(By.cssSelector("button[data-sb-id=main][type=submit]")).click();
    return this;
  }

  public SearchPageHelper fillForm(SearchData searchData) {
    setCurrency(searchData.currency());
    setPlace(searchData.whereGo());
    setDates(searchData.inDate(), searchData.outDate());
    setRooms(searchData.roomsCount());
    setAdults(searchData.adultsCount());
    setChildren(searchData.childrenCount());
    return this;
  }

  public SearchPageHelper setCurrency(String currency) {
    if (!(webDriver.findElement(By.cssSelector("[name=selected_currency]")).getAttribute("value").equals(currency))) {
      displayDropDown(By.cssSelector("[data-id=currency_selector]"), By.cssSelector("#current_currency_foldout"), 5);
      webDriver.findElement(By.cssSelector("a[data-currency=" + currency + "]")).click();
    }
    return this;
  }

  public SearchPageHelper setPlace(String place) {
    inputText(placeInput, place);
    return this;
  }

  public SearchPageHelper setDates(String checkInDate, String checkOutDate) {
    String firstDayOfMonthMonth = getFirstDayOfMonth(checkInDate);
    displayDropDown(By.cssSelector("div.xp__dates.xp__group"), By.cssSelector("div.bui-calendar"), 5);
    while (!firstDayOfMonthMonth.equals(webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date^='20']")).getAttribute("data-date"))) {
      webDriver.findElement(By.cssSelector("div[data-bui-ref=calendar-next]")).click();
    }
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='" + checkInDate + "']")).click();
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='" + checkOutDate + "']")).click();
    webDriver.findElement(By.cssSelector("div.xp__dates.xp__group")).click();
    return this;
  }

  public SearchPageHelper setChildren(int count) {
    setCount("group_children", count);
    return this;
  }

  public SearchPageHelper setAdults(int count) {
    setCount("group_adults", count);
    return this;
  }

  public SearchPageHelper setRooms(int count) {
    setCount("no_rooms", count);
    return this;
  }

  public void setCount(String id, int count) {
    openGuestsParameters();
    int actualCount = -1;
    while (count != actualCount) {
      actualCount = Integer.parseInt(webDriver.findElement(By.cssSelector("#" + id)).getAttribute("defaultValue"));
      if (actualCount > count && actualCount > 0) {
        webDriver.findElement(By.cssSelector("#" + id + "~button[class*=subtract-button]")).click();
      } else if (actualCount < count) {
        webDriver.findElement(By.cssSelector("#" + id + "~button[class*=add-button]")).click();
      }
    }
  }

  @NotNull
  private String getFirstDayOfMonth(String checkInDate) {
    String[] firstMonthArray = checkInDate.split("-");
    firstMonthArray[2] = "01";
    return firstMonthArray[0] + "-" + firstMonthArray[1] + "-" + firstMonthArray[2];
  }

  private void openGuestsParameters() {
    if (webDriver.findElement(By.cssSelector("label#xp__guests__toggle+div")).getAttribute("className").contains("hidden")) {
      webDriver.findElement(By.cssSelector("label#xp__guests__toggle")).click();
    }
  }

}

