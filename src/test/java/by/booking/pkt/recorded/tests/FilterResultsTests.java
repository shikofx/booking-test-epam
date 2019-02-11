package by.booking.pkt.recorded.tests;

import by.booking.pkt.recorded.model.Filter;
import by.booking.pkt.recorded.model.SearchData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class FilterResultsTests extends TBase {
  @Test
  public void testFilterByBudget() {
    app.forSearch().fillForm(new SearchData().withCurrency("RUB").withPlace("Минск").
            withInDate("2019-03-24").withOutDate("2019-04-03").
            withRooms(5).withAdults(2).withChildren(4));
    app.forSearch().initSearch();
    //app.getFilter().selectOnlyAvailable();
    Filter filter = new Filter(13000, 0);
    WebElement budgetElement = app.results().selectBudget(filter);
    int maxBudget = app.results().getBudget(budgetElement, filter);
    Assertion assertion = new Assertion();
    assertion.assertNotEquals(maxBudget, 0, "Filter by budget has not selected!");
    SoftAssert softAssert = new SoftAssert();
    for (WebElement item : app.results().getAll()) {
      int totalPrice = app.results().totalPrice(item);
      String hotelName = app.results().getHotelName(item);
      if (totalPrice != 0)
        softAssert.assertTrue(totalPrice <= maxBudget, "Budget " + maxBudget + " less than total price " + totalPrice + " for " + hotelName);
    }
    softAssert.assertAll();
  }

  @Test
  public void testFilterByStars() {
    Filter filter = new Filter(0, 3);
    SoftAssert softAssert = new SoftAssert();
    app.forSearch().fillForm(new SearchData().withCurrency("RUB").withPlace("Минск").
            withInDate("2019-03-24").withOutDate("2019-04-03").
            withRooms(5).withAdults(2).withChildren(4));
    app.forSearch().initSearch();
    //app.getFilter().selectOnlyAvailable();
    Assertion assertion = new Assertion();
    assertion.assertTrue(app.results().selectStarsCount(filter), "Count of stars has not selected!");

    for (WebElement hotel : app.results().getAll()) {
      int starsCount = app.results().getStarsCount(hotel);
      String hotelName = app.results().getHotelName(hotel);
      softAssert.assertEquals(starsCount, filter.getStars(), "For hotel " + hotelName + " stars count is not valid!");
    }
    softAssert.assertAll();
  }
}