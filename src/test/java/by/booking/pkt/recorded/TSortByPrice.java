package by.booking.pkt.recorded;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class TSortByPrice extends TBase {
  @Test
  public void sortByPrice() {
    SoftAssert softAssert = new SoftAssert();
    ms_fillFields("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    ms_initSearch();
    fb_setOnlyAvailableON();
    sr_initSortByPrice();
    List<WebElement> searchResults = sr_getAll();
    for (int i = 1; i < searchResults.size(); i++) {
      assertion.assertTrue(hotel_totalPrice(searchResults.get(i)) >= hotel_totalPrice(searchResults.get(i - 1)),
              "Unvalid sort by total price.\n" +
                      "The place '" + hotel_getHotelName(searchResults.get(i)) + "' with total price " + hotel_totalPrice(searchResults.get(i)) +
                      " must be higher than '" + hotel_getHotelName(searchResults.get(i - 1)) + "' with total price " + hotel_totalPrice(searchResults.get(i - 1)));
    }
  }
}
