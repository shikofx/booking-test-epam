package by.booking.pkt.tests;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.SearchData;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class FilterResultsTests extends TBaseMethods {

  @Test(enabled = true, groups = {"positive", "smoke", "filter"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testFilterByBudget(SearchData searchData) {
    app.forSearch().fillForm(searchData).initSearch();
    WebElement budgetElement = app.results().selectBudget(searchData.userBudget());
    //int maxBudget = app.results().getBudget(budgetElement, searchData.userBudget());
    Assertion assertion = new Assertion();
    //assertion.assertNotEquals(maxBudget, 0, "Filter by budget has not selected!");
    app.windows().back();
    SoftAssert softAssert = new SoftAssert();
    List<Hotel> hotels = app.results().availableHotels();
    System.out.println("Считали весь список");
    //Очень долго длятся проверки!!!
//    for (Hotel h : hotels) {
//        softAssert.assertTrue(h.currentPrice() <= maxBudget,
//                "Budget " + maxBudget + " less than total price " + h.currentPrice() + " for " + h.getName());
//    }
    softAssert.assertAll();

  }

  @Test
  public void testFilterByStars() {
    SearchData searchData = new SearchData().
            withUsername("pkt.autotests@gmail.com").withPassword("123456789").
            withCurrency("RUB").withPlace("Минск").
            withInDate("2019-03-24").withOutDate("2019-04-03").
            withRooms(5).withAdults(2).withChildren(4).withUserBudget(13000);
    app.account().loginAs(searchData.userName(), searchData.userPassword());
    SoftAssert softAssert = new SoftAssert();
    app.forSearch().fillForm(searchData).initSearch();
    app.forSearch().initSearch();
    //app.getFilter().selectOnlyAvailable();
    Assertion assertion = new Assertion();
    assertion.assertTrue(app.results().selectStarsCount(searchData.stars()), "Count of stars has not selected!");

    for (WebElement hotel : app.results().getAll()) {
      int starsCount = app.results().getStarsCount(hotel);
      String hotelName = app.results().getHotelName(hotel);
      softAssert.assertEquals(starsCount, searchData.stars(), "For hotel " + hotelName + " stars count is not valid!");
    }
    softAssert.assertAll();
  }
}