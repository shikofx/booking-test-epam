package by.booking.pkt.tests;

import by.booking.pkt.model.SearchData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class SortResultsTests extends TBase {
  @Test
  public void testSortByDistance() {
    SearchData searchData = new SearchData().
            withUsername("pkt.autotests@gmail.com").withPassword("123456789").
            withCurrency("RUB").withPlace("Минск").
            withInDate("2019-03-24").withOutDate("2019-04-03").
            withRooms(5).withAdults(2).withChildren(4).withMaxBudget(13000);

    app.account().loginAs(searchData.userName(), searchData.userPassword());

    app.forSearch().fillForm(searchData).initSearch();

    app.results().initSortByDistance();
    Assertion assertion = new Assertion();
    List<WebElement> availabelResults = app.results().getOnlyAvailable();
    for (int i = 1; i < availabelResults.size(); i++) {
      String currentHotelName = app.results().getHotelName(availabelResults.get(i));
      int currentDistance = app.results().distanceToDest(availabelResults.get(i));
      String previousHotelName = app.results().getHotelName(availabelResults.get(i - 1));
      int previousDistance = app.results().distanceToDest(availabelResults.get(i - 1));
      assertion.assertTrue(currentDistance >= previousDistance,
              "Unvalid sort by distance.\n" +
                      "The place " + currentHotelName + " with distance " + currentDistance +
                      " must be higher than " + previousHotelName + " with distance " + previousDistance);
    }
  }

  @Test
  public void sortByPrice() {
    SoftAssert softAssert = new SoftAssert();
    app.forSearch().fillForm(new SearchData().withCurrency("RUB").withPlace("Минск").
            withInDate("2019-03-24").withOutDate("2019-04-03").
            withRooms(5).withAdults(2).withChildren(4));
    app.forSearch().initSearch();
    // app.getFilter().selectOnlyAvailable();
    app.results().initSortByPrice();
    List<WebElement> availabelResults = app.results().getOnlyAvailable();
    for (int i = 1; i < availabelResults.size(); i++) {
      String currentHotel = app.results().getHotelName(availabelResults.get(i));
      int currentPrice = app.results().totalPrice(availabelResults.get(i));
      String previousHotel = app.results().getHotelName(availabelResults.get(i - 1));
      int previousPrice = app.results().totalPrice(availabelResults.get(i - 1));
      Assertion assertion = new Assertion();
      assertion.assertTrue(currentPrice >= previousPrice,
              "Unvalid sort by total price.\n" +
                      "The place '" + currentHotel + "' with total price " + currentPrice +
                      " must be higher than '" + previousHotel + "' with total price " + previousPrice);
    }
  }
}
