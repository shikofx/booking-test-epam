package by.booking.pkt.tests.hotel;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.TestsData;
import by.booking.pkt.tests.TBaseMethods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class HotelToWishlistTests extends TBaseMethods {

  Logger logger = LoggerFactory.getLogger(HotelToWishlistTests.class);

  @Test(enabled = true, groups = {"positive", "hotel"},
          dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
  @DataSourceFileAnnotation("src\\test\\resources\\data-for-hotel-positive.json")
  public void testAddHotelToNewList(TestsData testsData) {
    logger.info("Start test testAddHotelToNewList");
//    app.account().loginAs(testsData.userName(), testsData.userPassword());
//    app.searchPage().searchFor(testsData);
//
//    String resultsWindow = app.windows().handle();
//    Set<String> oldWindows = app.windows().all();
//
//    HotelToWishlistTests first = app.results().getFirstHotel();
//    app.results().goToHotelPage(first);
//
//    String hotelWindow = app.windows().getNew(oldWindows);
//    app.windows().switchTo(hotelWindow);
//    oldWindows = app.windows().all();
//
//    SoftAssert softAssert = new SoftAssert();
//    softAssert.assertNotEquals(hotelWindow, null, "HotelToWishlistTests info doesn't open in new window!");
//
//    String newListName = testsData.wishlistName();
//    Wishlist newWishlist = app.hotel().createWishlist(newListName, first);
//    softAssert.assertNotEquals(newWishlist, null, "New wishlist creating failed!");
//    app.hotel().goToWishlist(newWishlist);
//
//    String wishlistWindow = app.windows().getNew(oldWindows);
//    softAssert.assertNotEquals(wishlistWindow, null, "New wishlist doesn't open in new window!");
//
//    app.windows().switchTo(wishlistWindow);
//    String defaultList = app.wishlists().defaultList();
//    softAssert.assertEquals(defaultList, newListName, "New list doesn't opened as default!");
//
//    Assertion assertion = new Assertion();
//    assertion.assertTrue(app.wishlists().findWishlist(newWishlist), "There is no such wishlist!");
//    HotelToWishlistTests hotel = app.wishlists().hotelsInList().get(0);
//    assertion.assertTrue(hotel.equals(newWishlist.getHotelList().get(0)), "There is no suh hotel in wishlist");
//    softAssert.assertAll();

//    app.account().wishlists().logout();
//    app.windows().close();
//    app.windows().switchTo(hotelWindow);
//    app.windows().close();
//    app.windows().switchTo(resultsWindow);
    logger.info("Stop test testAddHotelToNewList");
  }
}
