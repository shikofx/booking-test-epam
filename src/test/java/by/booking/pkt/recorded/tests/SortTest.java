package by.booking.pkt.recorded.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SortTest extends TBase {
  @Test
  public void testSortByDistance() {

    app.getSearchHelper().ms_fillFields("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchHelper().ms_initSearch();
    app.getFilterBoxHelper().fb_selectOnlyAvailableON();
    By sortByDistance = By.cssSelector("li.sort_distance_from_search a");
    app.getSearchResultsHelper().app_activateDropdownMenu(By.cssSelector("#sortbar_dropdown_button"), By.cssSelector("#sortbar_dropdown_options"));
    app.getSearchResultsHelper().sr_initSortByDistance(sortByDistance);
    Assertion assertion = new Assertion();
    List<WebElement> searchResults = app.getSearchResultsHelper().sr_getAll();
    for (int i = 1; i < searchResults.size(); i++) {
      String currentHotelName = app.getSearchResultsHelper().hotel_getHotelName(searchResults.get(i));
      int currentDistance = app.getSearchResultsHelper().hotel_distanceToDest(searchResults.get(i));
      String previousHotelName = app.getSearchResultsHelper().hotel_getHotelName(searchResults.get(i-1));
      int previousDistance = app.getSearchResultsHelper().hotel_distanceToDest(searchResults.get(i-1));
      assertion.assertTrue(currentDistance >= previousDistance,
                            "Unvalid sort by distance.\n" +
                                    "The place " + currentHotelName+ " with distance " + currentDistance +
                                    " must be higher than " + previousHotelName + " with distance " + previousDistance);
    }
  }

  @Test
  public void sortByPrice() {
    SoftAssert softAssert = new SoftAssert();
    app.getSearchHelper().ms_fillFields("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchHelper().ms_initSearch();
    app.getFilterBoxHelper().fb_selectOnlyAvailableON();
    app.getSearchResultsHelper().sr_initSortByPrice();
    List<WebElement> searchResults = app.getSearchResultsHelper().sr_getAll();
    for (int i = 1; i < searchResults.size(); i++) {
      app.getSearchResultsHelper().hotel_totalPrice(searchResults.get(i));
      Assertion assertion = new Assertion();
      assertion.assertTrue() >= app.hotel_totalPrice(searchResults.get(i - 1)),
              "Unvalid sort by total price.\n" +
                      "The place '" + app.hotel_getHotelName(searchResults.get(i)) + "' with total price " + app.hotel_totalPrice(searchResults.get(i)) +
                      " must be higher than '" + app.hotel_getHotelName(searchResults.get(i - 1)) + "' with total price " + app.hotel_totalPrice(searchResults.get(i - 1)));
    }
  }
}
