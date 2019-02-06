package by.booking.pkt.recorded.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SortTest extends TBase {
  @Test
  public void testSortByDistance() {

    app.getSearchHelper().fillSearchForm("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchHelper().initSearch();
    app.getFilterBoxHelper().selectOnlyAvailable();
    app.getSearchResultsHelper().initSortByDistance();
    Assertion assertion = new Assertion();
    List<WebElement> searchResults = app.getSearchResultsHelper().getAllResults();
    for (int i = 1; i < searchResults.size(); i++) {
      String currentHotelName = app.getSearchResultsHelper().getHotelName(searchResults.get(i));
      int currentDistance = app.getSearchResultsHelper().distanceToDest(searchResults.get(i));
      String previousHotelName = app.getSearchResultsHelper().getHotelName(searchResults.get(i - 1));
      int previousDistance = app.getSearchResultsHelper().distanceToDest(searchResults.get(i - 1));
      assertion.assertTrue(currentDistance > previousDistance,
                            "Unvalid sort by distance.\n" +
                                    "The place " + currentHotelName+ " with distance " + currentDistance +
                                    " must be higher than " + previousHotelName + " with distance " + previousDistance);
    }
  }

  @Test
  public void sortByPrice() {
    SoftAssert softAssert = new SoftAssert();
    app.getSearchHelper().fillSearchForm("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchHelper().initSearch();
    app.getFilterBoxHelper().selectOnlyAvailable();
    app.getSearchResultsHelper().initSortByPrice();
    List<WebElement> searchResults = app.getSearchResultsHelper().getAllResults();
    for (int i = 1; i < searchResults.size(); i++) {
      int currentHotel = app.getSearchResultsHelper().totalPrice(searchResults.get(i));
      int currentPrice = app.getSearchResultsHelper().totalPrice(searchResults.get(i));
      int previousHotel = app.getSearchResultsHelper().totalPrice(searchResults.get(i - 1));
      int previousPrice = app.getSearchResultsHelper().totalPrice(searchResults.get(i - 1));
      Assertion assertion = new Assertion();
      assertion.assertTrue(currentPrice > previousPrice,
              "Unvalid sort by total price.\n" +
                      "The place '" + currentHotel + "' with total price " + currentPrice +
                      " must be higher than '" + previousHotel + "' with total price " + previousPrice);
    }
  }
}
