package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

public class TSortByDistance extends TBase {
  @Test
  public void testSortByDistance() {
    Assertion assertion = new Assertion();
    ms_fillFields("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    ms_initSearch();
    fb_setOnlyAvailableON();
    By sortByDistance = By.cssSelector("li.sort_distance_from_search a");
    app_activateDropdownMenu(By.cssSelector("#sortbar_dropdown_button"), By.cssSelector("#sortbar_dropdown_options"));
    sr_initSortByDistance(sortByDistance);
    List<WebElement> searchResults = sr_getAll();
    for (int i = 1; i < searchResults.size(); i++) {
      assertion.assertTrue(hotel_distanceToDest(searchResults.get(i)) >= hotel_distanceToDest(searchResults.get(i - 1)),
              "Unvalid sort by distance.\n" +
                      "The place " + hotel_getHotelName(searchResults.get(i)) + " with distance " + hotel_distanceToDest(searchResults.get(i)) +
                      " must be higher than " + hotel_getHotelName(searchResults.get(i - 1)) + " with distance " + hotel_distanceToDest(searchResults.get(i - 1)));
    }
  }
}
