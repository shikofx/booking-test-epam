package by.booking.pkt.tests;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.SearchData;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

public class SortResultsTests extends TBase {
  @Test(enabled = true, groups = {"positive", "smoke", "sort"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testSortByDistance(SearchData searchData) {
    app.searchPage().searchFor(searchData);
    app.results().initSortByDistance();
    List<Hotel> availableHotels = app.results().availableHotels();
    for (int i = 1; i < availableHotels.size(); i++) {
      String currentHotelName = availableHotels.get(i).getName();
      double currentDistance = availableHotels.get(i).getDistance();
      String previousHotelName = availableHotels.get(i - 1).getName();
      double previousDistance = availableHotels.get(i - 1).getDistance();
      Assertion assertion = new Assertion();
      assertion.assertTrue(currentDistance >= previousDistance,
              "Unvalid sort by distance.\n" +
                      "The place " + currentHotelName + " with distance " + currentDistance +
                      " must be higher than " + previousHotelName + " with distance " + previousDistance);
    }
  }

  @Test(enabled = true, groups = {"positive", "smoke", "sort"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testSortByPrice(SearchData searchData) {
    app.searchPage().searchFor(searchData);
    app.results().initSortByPrice();
    List<Hotel> availableHotels = app.results().availableHotels();
    for (int i = 1; i < availableHotels.size(); i++) {
      String currentHotel = availableHotels.get(i).getName();
      int currentPrice = availableHotels.get(i).currentPrice();
      String previousHotel = availableHotels.get(i - 1).getName();
      int previousPrice = availableHotels.get(i - 1).currentPrice();
      Assertion assertion = new Assertion();
      assertion.assertTrue(currentPrice >= previousPrice,
              "Unvalid sort by total price.\n" +
                      "The place '" + currentHotel + "' with total price " + currentPrice +
                      " must be higher than '" + previousHotel + "' with total price " + previousPrice);
    }
  }
}
