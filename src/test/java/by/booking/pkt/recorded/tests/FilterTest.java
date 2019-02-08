package by.booking.pkt.recorded.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class FilterTest extends TBase {
  @Test
  public void testFilterByBudget() {
    int budget = 13000;
    app.getSearchManager().fillSearchForm("RUB","Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchManager().initSearch();
    //app.getFilterManager().selectOnlyAvailable();
    int maxBudget = app.getFilterManager().selectBudgetAndGetMax(budget);
    Assertion assertion = new Assertion();
    assertion.assertNotEquals(maxBudget, 0, "Filter by budget has not selected!");
    SoftAssert softAssert = new SoftAssert();
    for (WebElement item : app.getResultsManager().getAllResults()) {
      int totalPrice = app.getResultsManager().totalPrice(item);
      String hotelName = app.getResultsManager().getHotelName(item);
      if (totalPrice != 0)
        softAssert.assertTrue(totalPrice <= maxBudget, "Budget " + maxBudget + " less than total price " + totalPrice + " for " + hotelName);
    }
    softAssert.assertAll();
  }

  @Test
  public void testFilterByStars() {
    int stars = 3;
    SoftAssert softAssert = new SoftAssert();
    app.getSearchManager().fillSearchForm("RUB", "Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchManager().initSearch();
    //app.getFilterManager().selectOnlyAvailable();
    Assertion assertion = new Assertion();
    assertion.assertTrue(app.getFilterManager().selectStarsCount(stars), "Count of stars has not selected!");

    for (WebElement hotel : app.getResultsManager().getAllResults()) {
      int starsCount = app.getResultsManager().getStarsCount(hotel);
      String hotelName = app.getResultsManager().getHotelName(hotel);
      softAssert.assertEquals(starsCount, stars, "For hotel " + hotelName + " stars count is not valid!");
    }
    softAssert.assertAll();
  }
}