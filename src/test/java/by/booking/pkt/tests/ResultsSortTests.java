package by.booking.pkt.tests;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.TestData;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ResultsSortTests extends TestBase {

   @Test(groups = {"positive", "independent"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
   public void sortByDistance(TestData testData) {
     app.searchPage().search(testData);
     app.resultsPage().initSortByDistance();

     List<Hotel> hotels = app.resultsPage().availableHotels();
     String hotelListSortChecker = app.resultsPage().checkSortByDistance(hotels);
     assertThat(hotelListSortChecker, is("sorted"));
   }

   @Test(groups = {"positive", "smoke", "independent"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
   public void sortByPrice(TestData testData) {
     app.searchPage().search(testData);
     app.resultsPage().initSortByPrice();

     List<Hotel> hotels = app.resultsPage().availableHotels();
     String hotelListSortChecker = app.resultsPage().checkSortByPrice(hotels);
     assertThat(hotelListSortChecker, is("sorted"));
   }
}
