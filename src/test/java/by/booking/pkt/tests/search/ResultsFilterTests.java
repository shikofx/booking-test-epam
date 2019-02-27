package by.booking.pkt.tests.search;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.TestsData;
import by.booking.pkt.tests.TBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ResultsFilterTests extends TBase {

  Logger logger = LoggerFactory.getLogger(ResultsFilterTests.class);

  @Test(enabled = true, groups = {"positive"},
          dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
  @DataSourceFileAnnotation("src\\test\\resources\\data-for-search-positive.json")
  public void testFilterByBudget(TestsData testsData) {
    logger.info("Start test testAddHotelToNewList");
  /*  app.searchPage().searchFor(testsData);
    boolean isSelectBudget = app.filters().selectBudget(testsData.minBudget(), testsData.maxBudget());
    assertThat("There is no selected budget!", isSelectBudget, equalTo(true));
    int maxTotalPrice = app.filters().getMaxTotalPrice();
    int minTotalPrice = app.filters().getMinTptalPrice();
    SoftAssert softAssert = new SoftAssert();
    List<HotelToWishlistTests> hotels = app.results().availableHotels();
//    assetThat(hotels, everyItem());
//    assertThat(hotels, CoreMatchers.everyItem(hote ls).);
    for (HotelToWishlistTests h : hotels) {

      softAssert.assertTrue(h.currentPrice() <= maxTotalPrice,
              "Budget " + maxTotalPrice + " less than total price " + h.currentPrice() + " for " + h.getName());
      softAssert.assertTrue(h.currentPrice() > minTotalPrice,
              "Budget " + minTotalPrice + " more than total price " + h.currentPrice() + " for " + h.getName());
    }
    softAssert.assertAll();*/
    logger.info("Start test testAddHotelToNewList");

  }

  @Test(enabled = true, groups = {"positive", "smoke"},
          dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
  @DataSourceFileAnnotation("src\\test\\resources\\data-for-search-positive.json")
  public void testFilterByStars(TestsData testsData) {
    logger.info("Start test testAddHotelToNewList");
//    app.searchPage().searchFor(testsData);
//    Assertion assertion = new Assertion();
//    assertion.assertTrue(app.filters().selectStars(testsData.stars()), "Count of stars has not selected!");
//    SoftAssert softAssert = new SoftAssert();
//    List<HotelToWishlistTests> hotels = app.results().availableHotels();
//    for (HotelToWishlistTests h : hotels) {
//      softAssert.assertEquals(h.starsCount(), testsData.stars(), "For hotel " + h.getName() + " stars count is not valid!");
//    }
//    softAssert.assertAll();
//
    logger.info("Start test testAddHotelToNewList");
  }
}