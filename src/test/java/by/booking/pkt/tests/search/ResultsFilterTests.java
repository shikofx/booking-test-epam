package by.booking.pkt.tests.search;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.TestData;
import by.booking.pkt.tests.TBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ResultsFilterTests extends TBase {

  Logger logger = LoggerFactory.getLogger(ResultsFilterTests.class);

  @Test(enabled = true, groups = {"positive"},
          dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
  @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
  public void filterByBudget(TestData testData) {
  /*  app.searchPage().searchFor(testData);
    boolean isSelectBudget = app.filters().selectBudget(testData.minBudget(), testData.maxBudget());
    assertThat("There is no selected budget!", isSelectBudget, equalTo(true));
    int maxTotalPrice = app.filters().getMaxTotalPrice();
    int minTotalPrice = app.filters().getMinTptalPrice();
    SoftAssert softAssert = new SoftAssert();
    List<HotelTests> hotels = app.results().availableHotels();
//    assetThat(hotels, everyItem());
//    assertThat(hotels, CoreMatchers.everyItem(hote ls).);
    for (HotelTests h : hotels) {

      softAssert.assertTrue(h.currentPrice() <= maxTotalPrice,
              "Budget " + maxTotalPrice + " less than total price " + h.currentPrice() + " for " + h.name());
      softAssert.assertTrue(h.currentPrice() > minTotalPrice,
              "Budget " + minTotalPrice + " more than total price " + h.currentPrice() + " for " + h.name());
    }
    softAssert.assertAll();*/

  }

  @Test(enabled = true, groups = {"positive", "smoke"},
          dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
  @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
  public void filterByStars(TestData testData) {
//    app.searchPage().searchFor(testData);
//    Assertion assertion = new Assertion();
//    assertion.assertTrue(app.filters().selectStars(testData.stars()), "Count of stars has not selected!");
//    SoftAssert softAssert = new SoftAssert();
//    List<HotelTests> hotels = app.results().availableHotels();
//    for (HotelTests h : hotels) {
//      softAssert.assertEquals(h.starsCount(), testData.stars(), "For hotel " + h.name() + " stars count is not valid!");
//    }
//    softAssert.assertAll();
//
  }
}