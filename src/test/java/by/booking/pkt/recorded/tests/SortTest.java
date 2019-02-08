package by.booking.pkt.recorded.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SortTest extends TBase {
  @Test
  public void testSortByDistance() {
    app.getSearchManager().fillSearchForm("RUB","Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchManager().initSearch();
    //app.getFilterManager().selectOnlyAvailable();
    app.getResultsManager().initSortByDistance();
    Assertion assertion = new Assertion();
    List<WebElement> searchResults = app.getResultsManager().getAllResults();
    List<WebElement> availabelResults = app.getResultsManager().getOnlyAvailable(searchResults);
    for (int i = 1; i < availabelResults.size(); i++) {
      String currentHotelName = app.getResultsManager().getHotelName(availabelResults.get(i));
      int currentDistance = app.getResultsManager().distanceToDest(availabelResults.get(i));
      String previousHotelName = app.getResultsManager().getHotelName(availabelResults.get(i - 1));
      int previousDistance = app.getResultsManager().distanceToDest(availabelResults.get(i - 1));
      assertion.assertTrue(currentDistance >= previousDistance,
                            "Unvalid sort by distance.\n" +
                                    "The place " + currentHotelName+ " with distance " + currentDistance +
                                    " must be higher than " + previousHotelName + " with distance " + previousDistance);
    }
  }

  @Test
  public void sortByPrice() {
    SoftAssert softAssert = new SoftAssert();
    app.getSearchManager().fillSearchForm("RUB","Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchManager().initSearch();
    // app.getFilterManager().selectOnlyAvailable();
    app.getResultsManager().initSortByPrice();
    List<WebElement> searchResults = app.getResultsManager().getAllResults();
    List<WebElement> availabelResults = app.getResultsManager().getOnlyAvailable(searchResults);
    for (int i = 1; i < availabelResults.size(); i++) {
      String currentHotel = app.getResultsManager().getHotelName(availabelResults.get(i));
      int currentPrice = app.getResultsManager().totalPrice(availabelResults.get(i));
      String previousHotel = app.getResultsManager().getHotelName(availabelResults.get(i - 1));
      int previousPrice = app.getResultsManager().totalPrice(availabelResults.get(i - 1));
      Assertion assertion = new Assertion();
      assertion.assertTrue(currentPrice >= previousPrice,
              "Unvalid sort by total price.\n" +
                      "The place '" + currentHotel + "' with total price " + currentPrice +
                      " must be higher than '" + previousHotel + "' with total price " + previousPrice);
    }
  }
}
