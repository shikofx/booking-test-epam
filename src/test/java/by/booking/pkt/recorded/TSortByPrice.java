package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TSortByPrice extends TBase {
  @Test
  public void sortByPrice() {
    SoftAssert softAssert = new SoftAssert();
    goToMainSearchPage();
    //   logIn();
    selectCurrency();
    enterAccomodaition("Минск");
    selectDates("2019-03-24", "2019-04-03");
    selectRoomsCount(1);
    selectAdultsCount(2);
    selectChildrenCount(2);
    submitMainSearch();
    onlyAvailableSelect();
    sortByPriceSelect();
    List<WebElement> searchResults = getSearchResults();
    for (int i = 1; i < searchResults.size(); i++) {
      assertion.assertTrue(getTotalPrice(searchResults.get(i)) >= getTotalPrice(searchResults.get(i - 1)),
              "Unvalid sort by total price.\n" +
                      "The place '" + getHotelName(searchResults.get(i)) + "' with total price " + getTotalPrice(searchResults.get(i)) +
                      " must be higher than '" + getHotelName(searchResults.get(i - 1)) + "' with total price " + getTotalPrice(searchResults.get(i - 1)));
    }
  }


  private int getTotalPrice(WebElement item) {
    int totalPrice = 0;
    if (isElementInPresentNoWait(item, By.cssSelector("div.totalPrice"), 30)) {
      totalPrice = Integer.parseInt(getTextByPatternNoSpace("(?<=:).+\\d", item.findElement(By.cssSelector("div.totalPrice")).getText()));
    } else {
      totalPrice = Integer.parseInt(getTextByPatternNoSpace("[\\d\\s]+\\d", item.findElement(By.cssSelector("strong.price")).getText()));
    }

    return totalPrice;
  }
}
