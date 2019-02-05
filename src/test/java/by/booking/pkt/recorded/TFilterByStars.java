package by.booking.pkt.recorded;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TFilterByStars extends TBase {
  @Test
  public void testFilterByStars() {
    int stars = 3;
    SoftAssert softAssert = new SoftAssert();
    hdr_selectCurrency("RUB");
    ms_fillFields("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    ms_initSearch();
    fb_setOnlyAvailableON();
    fb_setStarsCount(stars);
    for (WebElement item : sr_getAll())
      softAssert.assertEquals(hotel_getStarsCount(item), stars, "For hotel " + hotel_getHotelName(item) + " stars count is not valid!");
    softAssert.assertAll();
  }

}
