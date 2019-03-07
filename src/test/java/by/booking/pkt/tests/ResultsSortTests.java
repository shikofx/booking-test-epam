package by.booking.pkt.tests;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.TestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ResultsSortTests extends TestBase {

   Logger logger = LoggerFactory.getLogger(ResultsSortTests.class);

   @Test(enabled = true, groups = {"positive", "independent"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
   public void sortByDistance(TestData testData) {
//     app.searchPage().searchFor(testData);
//     app.resultsPage().initSortByDistance();
//
//     List<Hotel> hotels = app.resultsPage().availableHotels();
//     String hotelListByDistance = app.resultsPage().checkSortByDistance(hotels);
//     assertThat("Unvalid sort by distance", hotelListByDistance, is("sorted"));
   }

   @Test(enabled = true, groups = {"positive", "smoke", "independent"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
   public void sortByPrice(TestData testData) {
//     app.searchPage().searchFor(testData);
//     app.resultsPage().initSortByPrice();
//
//     List<Hotel> hotels = app.resultsPage().availableHotels();
//     String hotelListByPrice = app.resultsPage().checkSortByPrice(hotels);
//     assertThat("Unvalid sort by price", hotelListByPrice, is("sorted"));
   }
}
