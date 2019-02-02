package by.booking.pkt.recorded;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TFilterByBudget extends TBase{
  @Test
  public void testFilterByBudget() {

    int budget = 13000;
    SoftAssert softAssert = new SoftAssert();
    goToMainSearchPage();
 //   logIn();
    selectCurrency();
    enterAccomodaition("Минск");
    selectDates("2019-03-24", "2019-04-03");
    selectRoomsCount(5);
    selectAdultsCount(2);
    selectChildrenCount(4);
    submitMainSearch();
    onlyAvailableSelect();

    int maxBudget = selectBudget(budget);
    for(WebElement item: getSearchResults()){
      int totalPrice = getTotalPriceForHotel(item);
      softAssert.assertTrue(totalPrice<=maxBudget, "Budget "+maxBudget+" less than total price "+totalPrice+" for "+getHotelName(item));
      System.out.println("Budget "+maxBudget+" less than total price "+totalPrice+" for "+getHotelName(item));
    }

    softAssert.assertAll();
  }

}
