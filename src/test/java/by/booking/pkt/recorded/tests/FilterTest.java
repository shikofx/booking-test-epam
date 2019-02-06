package by.booking.pkt.recorded.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class FilterTest extends TBase{
  @Test
  public void testFilterByBudget() {
    int budget = 13000;
    app.getSearchHelper().hdr_selectCurrency("RUB");
    app.getSearchHelper().ms_fillFields("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchHelper().ms_initSearch();
    app.getFilterBoxHelper().fb_selectOnlyAvailableON();
    int maxBudget = app.getFilterBoxHelper().fb_selectBudget(budget);
    SoftAssert softAssert = new SoftAssert();
    for(WebElement item: app.getSearchResultsHelper().sr_getAll()){
      int totalPrice = app.getSearchResultsHelper().sr_totalPrice(item);
      String hotelName = app.getSearchResultsHelper().hotel_getHotelName(item);
      softAssert.assertTrue(totalPrice<=maxBudget, "Budget "+maxBudget+" less than total price "+totalPrice+" for "+ hotelName);
    }
    softAssert.assertAll();
  }

  @Test
  public void testFilterByStars() {
    int stars = 3;
    SoftAssert softAssert = new SoftAssert();
    app.getSearchHelper().hdr_selectCurrency("RUB");
    app.getSearchHelper().ms_fillFields("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchHelper().ms_initSearch();
    app.getFilterBoxHelper().fb_selectOnlyAvailableON();
    app.getFilterBoxHelper().fb_setStarsCount(stars);
    for (WebElement item : app.getSearchResultsHelper().sr_getAll()){
      int starsCount = app.getSearchResultsHelper().hotel_getStarsCount(item);
      String hotelName = app.getSearchResultsHelper().hotel_getHotelName(item);
      softAssert.assertEquals(starsCount, stars, "For hotel " + hotelName + " stars count is not valid!");
    }

    softAssert.assertAll();
  }
}