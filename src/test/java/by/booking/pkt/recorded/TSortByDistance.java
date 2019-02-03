package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TSortByDistance extends TBase{
  @Test
  public void testSortByDistance() {
    Assertion assertion = new Assertion();
    goToMainSearchPage();
 //   logIn();
    selectCurrency();
    enterAccomodaition("Минск");
    selectDates("2019-03-24", "2019-04-03");
    selectRoomsCount(1);
    selectAdultsCount(2);
    selectChildrenCount(0);
    submitMainSearch();
    onlyAvailableSelect();
    By sortByDistance = By.cssSelector("li.sort_distance_from_search a");

    activateDropdownMenu(By.cssSelector("#sortbar_dropdown_button"), By.cssSelector("#sortbar_dropdown_options"));
    wait.until(visibilityOfElementLocated(sortByDistance));
    webDriver.findElement(sortByDistance).click();
    List<WebElement> searchResults = getSearchResults();
    for (int i = 1; i < searchResults.size(); i++) {
      assertion.assertTrue(getDistance(searchResults.get(i)) >= getDistance(searchResults.get(i - 1)),
              "Unvalid sort by distance.\n" +
                      "The place " + getHotelName(searchResults.get(i)) + " with distance " + getDistance(searchResults.get(i)) +
                      " must be higher than " + getHotelName(searchResults.get(i - 1)) + " with distance " + getDistance(searchResults.get(i - 1)));
    }
  }

}
