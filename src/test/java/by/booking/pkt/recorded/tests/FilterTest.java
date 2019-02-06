package by.booking.pkt.recorded.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FilterTest extends TBase{
  @Test
  public void testFilterByBudget() {
    int budget = 13000;
    app.getHeaderHelper().selectCurrency("RUB");
    app.getSearchHelper().fillSearchForm("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchHelper().initSearch();
    app.getFilterBoxHelper().selectOnlyAvailable();
    int maxBudget = app.getFilterBoxHelper().selectBudgetAndGetMax(budget);
    SoftAssert softAssert = new SoftAssert();
    for (WebElement item : app.getSearchResultsHelper().getAllResults()) {
      int totalPrice = app.getSearchResultsHelper().totalPrice(item);
      String hotelName = app.getSearchResultsHelper().getHotelName(item);
      softAssert.assertTrue(totalPrice<=maxBudget, "Budget "+maxBudget+" less than total price "+totalPrice+" for "+ hotelName);
    }
    softAssert.assertAll();
  }

  @Test
  public void testFilterByStars() {
    int stars = 3;
    SoftAssert softAssert = new SoftAssert();
    app.getHeaderHelper().selectCurrency("RUB");
    app.getSearchHelper().fillSearchForm("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchHelper().initSearch();
    app.getFilterBoxHelper().selectOnlyAvailable();
    app.getFilterBoxHelper().setStarsCount(stars);
    for (WebElement item : app.getSearchResultsHelper().getAllResults()) {
      int starsCount = app.getSearchResultsHelper().getStarsCount(item);
      String hotelName = app.getSearchResultsHelper().getHotelName(item);
      softAssert.assertEquals(starsCount, stars, "For hotel " + hotelName + " stars count is not valid!");
    }

    softAssert.assertAll();
  }
}