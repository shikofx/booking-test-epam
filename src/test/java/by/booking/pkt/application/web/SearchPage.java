package by.booking.pkt.application.web;

import by.booking.pkt.model.SearchData;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends HelperBase {
  public SearchPage(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public SearchPage initSearch() {
    clickOn(By.cssSelector("button[data-sb-id=main][type=submit]"));
    return this;
  }

  public SearchPage fillForm(SearchData searchData) {
    setCurrency(searchData.getCurrency());
    setPlace(searchData.withPlace());
    setDates(searchData.getInDate(), searchData.getOutDate());
    setRooms(searchData.getRoomsCount());
    setAdults(searchData.getAdultsCount());
    setChildren(searchData.getChildrenCount());
    return this;
  }

  public SearchPage setCurrency(String currency) {
    if (!(getAttribute(By.cssSelector("[name=selected_currency]"), "value").equals(currency))) {
      showDropdown(By.cssSelector("[data-id=currency_selector]"), By.cssSelector("#current_currency_foldout"), 5);
      clickOn(By.cssSelector("a[data-currency=" + currency + "]"));
    }
    return this;
  }

  public SearchPage setPlace(String place) {
    typeText(By.cssSelector("#ss"), place);
    return this;
  }

  public SearchPage setDates(String checkInDate, String checkOutDate) {
    String firstDayOfMonthMonth = getFirstDayOfMonth(checkInDate);
    showDropdown(By.cssSelector("div.xp__dates.xp__group"), By.cssSelector("div.bui-calendar"), 5);
    while (!firstDayOfMonthMonth.equals(getAttribute(By.cssSelector("div.bui-calendar td[data-date^='20']"), "data-date"))) {
      clickOn(By.cssSelector("div[data-bui-ref=calendar-next]"));
    }
    clickOn(By.cssSelector("div.bui-calendar td[data-date='" + checkInDate + "']"));
    clickOn(By.cssSelector("div.bui-calendar td[data-date='" + checkOutDate + "']"));
    clickOn(By.cssSelector("div.xp__dates.xp__group"));
    return this;
  }

  public SearchPage setChildren(int count) {
    setCount("group_children", count);
    return this;
  }

  public SearchPage setAdults(int count) {
    setCount("group_adults", count);
    return this;
  }

  public SearchPage setRooms(int count) {
    setCount("no_rooms", count);
    return this;
  }

  public void setCount(String id, int count) {
    openGuestsParameters();
    int actualCount = -1;
    while (count != actualCount) {
      actualCount = Integer.parseInt(getAttribute(By.cssSelector("#" + id), "defaultValue"));
      if (actualCount > count && actualCount > 0) {
        clickOn(By.cssSelector("#" + id + "~button[class*=subtract-button]"));
      } else if (actualCount < count) {
        clickOn(By.cssSelector("#" + id + "~button[class*=add-button]"));
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
    if (getAttribute(By.cssSelector("label#xp__guests__toggle+div"), ("className")).contains("hidden")) {
      clickOn(By.cssSelector("label#xp__guests__toggle"));
    }
  }

}

