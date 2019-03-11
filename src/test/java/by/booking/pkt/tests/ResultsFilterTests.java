package by.booking.pkt.tests;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.TestData;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ResultsFilterTests extends TestBase {

   @Test(groups = {"positive", "independent"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
   public void filterByBudget(TestData testData) {
      app.searchPage().search(testData);

      boolean budgetIsSelected = app.filters().selectBudget(testData.minBudget(), testData.maxBudget());
      assertThat("There is no selected budget!", budgetIsSelected, is(true));

      int maxTotalPrice = app.filters().getMaxTotalPrice();
      int minTotalPrice = app.filters().getMinTotalPrice();

      List<Hotel> hotels = app.resultsPage().availableHotels();
      assertThat(hotels,
              everyItem(hasProperty("totalPrice", greaterThan(minTotalPrice))));

      assertThat(hotels,
              everyItem(hasProperty("totalPrice", lessThan(maxTotalPrice))));
   }

   @Test(groups = {"positive", "smoke", "independent"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
   public void filterByStars(TestData testData) {
      app.searchPage().search(testData);
      boolean starsSelected = app.filters().selectStars(testData.stars());

      assertThat("Count of stars has not selected!", starsSelected, is(true));

      List<Hotel> hotels = app.resultsPage().availableHotels();
      assertThat(hotels,
              everyItem(hasProperty("stars", equalTo(testData.stars()))));
   }
}