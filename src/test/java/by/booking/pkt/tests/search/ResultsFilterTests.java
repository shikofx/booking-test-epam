package by.booking.pkt.tests.search;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.TestData;
import by.booking.pkt.tests.TestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResultsFilterTests extends TestBase {

  Logger logger = LoggerFactory.getLogger(ResultsFilterTests.class);

  @Test(enabled = true, groups = {"positive"},
          dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
  @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
  public void filterByBudget(TestData testData) {
     app.searchPage().searchFor(testData);
     boolean budgetIsSelect = app.filters().selectBudget(testData.minBudget(), testData.maxBudget());
     assertThat("There is no selected budget!", budgetIsSelect, is(true));
    int maxTotalPrice = app.filters().getMaxTotalPrice();
    int minTotalPrice = app.filters().getMinTptalPrice();
    SoftAssert softAssert = new SoftAssert();
     List<Hotel> hotels = app.resultsPage().availableHotels();
//    assetThat(hotels, everyItem());
//    assertThat(hotels, CoreMatchers.everyItem(hote ls).);
     for (Hotel h : hotels) {

      softAssert.assertTrue(h.currentPrice() <= maxTotalPrice,
              "Budget " + maxTotalPrice + " less than total price " + h.currentPrice() + " for " + h.getName());
      softAssert.assertTrue(h.currentPrice() > minTotalPrice,
              "Budget " + minTotalPrice + " more than total price " + h.currentPrice() + " for " + h.getName());
    }
     softAssert.assertAll();

  }

  @Test(enabled = true, groups = {"positive", "smoke"},
          dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
  @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
  public void filterByStars(TestData testData) {
     app.searchPage().searchFor(testData);
     Assertion assertion = new Assertion();
     boolean starsIsSelected = app.filters().selectStars(testData.stars());
     assertThat("Count of stars has not selected!", starsIsSelected, is(true));
     SoftAssert softAssert = new SoftAssert();
     List<Hotel> hotels = app.resultsPage().availableHotels();
     for (Hotel h : hotels) {
        softAssert.assertEquals(h.starsCount(), testData.stars(), "For hotelPage " + h.getName() + " stars count is not valid!");
     }
     softAssert.assertAll();
//
  }
}