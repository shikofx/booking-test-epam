package by.booking.pkt.tests;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.SearchData;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class FilterResultsTests extends TBaseMethods {

  @Test(enabled = true, groups = {"positive", "smoke", "filter"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testFilterByBudget(SearchData searchData) {
    app.forSearch().fillForm(searchData).initSearch();
    app.filters().selectBudget(searchData.minBudget(),searchData.maxBudget());
    int maxBudget = app.filters().getMax();
    int minBudget = app.filters().getMin();
    Assertion assertion = new Assertion();
    assertion.assertNotEquals(maxBudget, 0, "Filter by budget has not selected!");
    int nightsCount = app.filters().getNigtsCount();
    int maxTotalPrice = nightsCount*maxBudget;
    int minTotalPrice = nightsCount*minBudget;
    SoftAssert softAssert = new SoftAssert();
    List<Hotel> hotels = app.results().availableHotels();
    for (Hotel h : hotels) {
        softAssert.assertTrue(h.currentPrice() <= maxTotalPrice,
                "Budget " + maxTotalPrice + " less than total price " + h.currentPrice() + " for " + h.getName());
      softAssert.assertTrue(h.currentPrice() > minTotalPrice,
              "Budget " + minTotalPrice + " more than total price " + h.currentPrice() + " for " + h.getName());
    }
    softAssert.assertAll();

  }

  @Test(enabled = true, groups = {"positive", "smoke", "filter"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testFilterByStars(SearchData searchData) {
    app.account().loginAs(searchData.userName(), searchData.userPassword());
    app.forSearch().fillForm(searchData).initSearch();
    Assertion assertion = new Assertion();
    assertion.assertTrue(app.filters().selectStarsCount(searchData.stars()), "Count of stars has not selected!");
    SoftAssert softAssert = new SoftAssert();
    List<Hotel> hotels = app.results().availableHotels();
    for (Hotel h : hotels) {
      softAssert.assertEquals(h.starsCount(), searchData.stars(), "For hotel " + h.getName() + " stars count is not valid!");
    }
    softAssert.assertAll();
  }
}