package by.booking.pkt.tests;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.SearchData;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class FilterResultsTests extends TBase {

  @Test(enabled = true, groups = {"positive", "smoke", "filter"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testFilterByBudget(SearchData searchData) {
  /*  app.searchPage().searchFor(searchData);
    boolean isSelectBudget = app.filters().selectBudget(searchData.minBudget(), searchData.maxBudget());
    assertThat("There is no selected budget!", isSelectBudget, equalTo(true));
    int maxTotalPrice = app.filters().getMaxTotalPrice();
    int minTotalPrice = app.filters().getMinTptalPrice();
    SoftAssert softAssert = new SoftAssert();
    List<Hotel> hotels = app.results().availableHotels();
//    assetThat(hotels, everyItem());
//    assertThat(hotels, CoreMatchers.everyItem(hote ls).);
    for (Hotel h : hotels) {

      softAssert.assertTrue(h.currentPrice() <= maxTotalPrice,
              "Budget " + maxTotalPrice + " less than total price " + h.currentPrice() + " for " + h.getName());
      softAssert.assertTrue(h.currentPrice() > minTotalPrice,
              "Budget " + minTotalPrice + " more than total price " + h.currentPrice() + " for " + h.getName());
    }
    softAssert.assertAll();*/

  }

  @Test(enabled = true, groups = {"positive", "smoke", "filter"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testFilterByStars(SearchData searchData) {
    app.searchPage().searchFor(searchData);
    Assertion assertion = new Assertion();
    assertion.assertTrue(app.filters().selectStars(searchData.stars()), "Count of stars has not selected!");
    SoftAssert softAssert = new SoftAssert();
    List<Hotel> hotels = app.results().availableHotels();
    for (Hotel h : hotels) {
      softAssert.assertEquals(h.starsCount(), searchData.stars(), "For hotel " + h.getName() + " stars count is not valid!");
    }
    softAssert.assertAll();

  }
}