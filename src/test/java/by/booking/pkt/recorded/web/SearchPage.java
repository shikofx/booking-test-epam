package by.booking.pkt.recorded.web;

import by.booking.pkt.recorded.model.SearchData;
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

  public void setChildrenCount(int childrenCount) {
    if (getAttribute(By.cssSelector("label#xp__guests__toggle+div"), ("className")).contains("hidden")) {
      clickOn(By.cssSelector("label#xp__guests__toggle"));
    }
    int actualChildrenCount = -1;
    while (childrenCount != actualChildrenCount) {
      actualChildrenCount = Integer.parseInt(getAttribute(By.cssSelector("#group_children"), "defaultValue"));
      if (actualChildrenCount > childrenCount && actualChildrenCount > 0) {
        clickOn(By.cssSelector("#group_children~button[class*=subtract-button]"));
      } else if (actualChildrenCount < childrenCount) {
        clickOn(By.cssSelector("#group_children~button[class*=add-button]"));
      }
    }
  }

  public void setAdultsCount(int adultsCount) {
    if (getAttribute(By.cssSelector("label#xp__guests__toggle+div"), ("className")).contains("hidden")) {
      clickOn(By.cssSelector("label#xp__guests__toggle"));
    }
    int actualAdultsCount = -1;
    while (adultsCount != actualAdultsCount) {
      actualAdultsCount = Integer.parseInt(getAttribute(By.cssSelector("#group_adults"), "defaultValue"));
      if (actualAdultsCount > adultsCount && actualAdultsCount > 0) {
        clickOn(By.cssSelector("#group_adults~button[class*=subtract-button]"));
      } else if (actualAdultsCount < adultsCount) {
        clickOn(By.cssSelector("#group_adults~button[class*=add-button]"));
      }
    }
  }

  public void setRoomsCount(int roomsCount) {
    if (getAttribute(By.cssSelector("label#xp__guests__toggle+div"), ("className")).contains("hidden")) {
      clickOn(By.cssSelector("label#xp__guests__toggle"));
    }
    int actualRoomsCount = -1;
    while (roomsCount != actualRoomsCount) {
      actualRoomsCount = Integer.parseInt(getAttribute(By.cssSelector("#no_rooms"), "defaultValue"));
      if (actualRoomsCount > roomsCount && actualRoomsCount > 0) {
        clickOn(By.cssSelector("#no_rooms~button[class*=subtract-button]"));
      } else if (actualRoomsCount < roomsCount) {
        clickOn(By.cssSelector("#no_rooms~button[class*=add-button]"));
      }
    }
  }

  public void setDatesRange(String checkInDate, String checkOutDate) {
    String firstDayOfMonthMonth = getFirstDayOfMonth(checkInDate);
    showDropdown(By.cssSelector("div.xp__dates.xp__group"), By.cssSelector("div.bui-calendar"), 5);
    while (!firstDayOfMonthMonth.equals(getAttribute(By.cssSelector("div.bui-calendar td[data-date^='20']"), "data-date"))) {
      clickOn(By.cssSelector("div[data-bui-ref=calendar-next]"));
    }
    clickOn(By.cssSelector("div.bui-calendar td[data-date='" + checkInDate + "']"));
    clickOn(By.cssSelector("div.bui-calendar td[data-date='" + checkOutDate + "']"));
    clickOn(By.cssSelector("div.xp__dates.xp__group"));
  }

  @NotNull
  private String getFirstDayOfMonth(String checkInDate) {
    String[] firstMonthArray = checkInDate.split("-");
    firstMonthArray[2] = "01";
    return firstMonthArray[0] + "-" + firstMonthArray[1] + "-" + firstMonthArray[2];
  }

  public void setPlace(String place) {
    typeText(By.cssSelector("#ss"), place);
  }

  public SearchPage fillForm(SearchData searchData) {
    selectCurrency(searchData.getCurrency());
    setPlace(searchData.withPlace());
    setDatesRange(searchData.getInDate(), searchData.getOutDate());
    setRoomsCount(searchData.getRoomsCount());
    setAdultsCount(searchData.getAdultsCount());
    setChildrenCount(searchData.getChildrenCount());
    return this;
  }

  public void selectCurrency(String currency) {
    showDropdown(By.cssSelector("[data-id=currency_selector]"), By.cssSelector("#current_currency_foldout"), 5);
    if (getAttribute(By.cssSelector("a[data-currency=" + currency + "]"), "aria-selected") == null)
      clickOn(By.cssSelector("a[data-currency=" + currency + "]"));
  }
}

