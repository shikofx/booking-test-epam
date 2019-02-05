package by.booking.pkt.recorded;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TFilterByBudget extends TBase{
  @Test
  public void testFilterByBudget() {
    int budget = 13000;
    hdr_selectCurrency("RUB");
    ms_fillFields("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    ms_initSearch();
    fb_setOnlyAvailableON();
    int maxBudget = fb_setBudget(budget);
    SoftAssert softAssert = new SoftAssert();
    for(WebElement item: sr_getAll()){
      int totalPrice = sr_totalPrice(item);
      softAssert.assertTrue(totalPrice<=maxBudget, "Budget "+maxBudget+" less than total price "+totalPrice+" for "+ hotel_getHotelName(item));
    }
    softAssert.assertAll();
  }
}