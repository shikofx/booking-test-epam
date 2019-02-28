package by.booking.pkt.tests.search;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.TestsData;
import by.booking.pkt.tests.TBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ResultsSortTests extends TBase {

  Logger logger = LoggerFactory.getLogger(ResultsSortTests.class);

  @Test(enabled = true, groups = {"positive"},
          dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
  @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
  public void sortByDistance(TestsData testsData) throws InterruptedException {
//    app.searchPage().searchFor(testsData);
//    app.results().initSortByDistance().refreshPage();
//    List<HotelToWishlistTests> availableHotels = app.results().availableHotels();
//    for (int i = 1; i < availableHotels.size(); i++) {
//      String currentHotelName = availableHotels.get(i).getName();
//      double currentDistance = availableHotels.get(i).getDistance();
//      String previousHotelName = availableHotels.get(i - 1).getName();
//      double previousDistance = availableHotels.get(i - 1).getDistance();
//      Assertion assertion = new Assertion();
//      assertion.assertTrue(currentDistance >= previousDistance,
//              "Unvalid sort by distance.\n" +
//                      "The place " + currentHotelName + " with distance " + currentDistance +
//                      " must be higher than " + previousHotelName + " with distance " + previousDistance);
//    }
  }

  @Test(enabled = true, groups = {"positive", "smoke"},
          dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
  @DataSourceFileAnnotation("src/test/resources/data-for-search-positive.json")
  public void sortByPrice(TestsData testsData) throws InterruptedException {
//    app.searchPage().searchFor(testsData);
//    app.results().initSortByPrice().refreshPage();
//    List<HotelToWishlistTests> availableHotels = app.results().availableHotels();
//    for (int i = 1; i < availableHotels.size(); i++) {
//      String currentHotel = availableHotels.get(i).getName();
//      int currentPrice = availableHotels.get(i).currentPrice();
//      String previousHotel = availableHotels.get(i - 1).getName();
//      int previousPrice = availableHotels.get(i - 1).currentPrice();
//      Assertion assertion = new Assertion();
//      assertion.assertTrue(currentPrice >= previousPrice,
//              "Unvalid sort by total price.\n" +
//                      "The place '" + currentHotel + "' with total price " + currentPrice +
//                      " must be higher than '" + previousHotel + "' with total price " + previousPrice);
//    }
  }
}