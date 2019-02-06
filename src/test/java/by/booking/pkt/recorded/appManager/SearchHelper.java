package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SearchHelper extends HelperBase {
  public SearchHelper(WebDriver webDriver, WebDriverWait wait) {
    super(webDriver, wait);
  }

  public void ms_initSearch() {
    webDriver.findElement(By.cssSelector("button[data-sb-id=main][type=submit]")).click();
  }

  public void ms_setChildrenCount(int childrenCount) {
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

  public void ms_setAdultsCount(int adultsCount) {
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

  public void ms_setRoomsCount(int roomsCount) {
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

  public void ms_setDatesRange(String checkInDate, String checkOutDate) {
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

  public void ms_setPlace(String place) {
    webDriver.findElement(By.cssSelector("#ss")).click();
    webDriver.findElement(By.cssSelector("#ss")).clear();
    webDriver.findElement(By.cssSelector("#ss")).sendKeys(place);
  }

  public void hdr_selectCurrency(final String currency) {
    app_activateDropdownMenu(By.cssSelector("[data-id=currency_selector]"), By.cssSelector("#current_currency"));
    wait.until(visibilityOfElementLocated(By.cssSelector("#current_currency_foldout")));
    webDriver.findElement(By.cssSelector("a[data-currency=" + currency)).click();
  }

  public void ms_fillFields(String place, String checkInDate, String checkOutDate, int roomsCount, int adultsCount, int childrenCount) {
    ms_setPlace(place);
    ms_setDatesRange(checkInDate, checkOutDate);
    ms_setRoomsCount(roomsCount);
    ms_setAdultsCount(adultsCount);
    ms_setChildrenCount(childrenCount);
  }
}
