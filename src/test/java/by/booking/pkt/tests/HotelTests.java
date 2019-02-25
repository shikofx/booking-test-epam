package by.booking.pkt.tests;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.SearchData;
import org.testng.annotations.Test;

public class HotelTests extends TBaseMethods {


  @Test(enabled = true, groups = {"positive", "smoke", "sort"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromJSON")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.json")
  public void testaddHotelToNewList(SearchData searchData) {
//    app.account().loginAs(searchData.userName(), searchData.userPassword());
//    app.searchPage().searchFor(searchData);
//
//    String resultsWindow = app.windows().handle();
//    Set<String> oldWindows = app.windows().all();
//
//    Hotel first = app.results().getFirstHotel();
//    app.results().goToHotelPage(first);
//
//    String hotelWindow = app.windows().getNew(oldWindows);
//    app.windows().switchTo(hotelWindow);
//    oldWindows = app.windows().all();
//
//    SoftAssert softAssert = new SoftAssert();
//    softAssert.assertNotEquals(hotelWindow, null, "Hotel info doesn't open in new window!");
//
//    String newListName = searchData.wishlistName();
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
//    Hotel hotel = app.wishlists().hotelsInList().get(0);
//    assertion.assertTrue(hotel.equals(newWishlist.getHotelList().get(0)), "There is no suh hotel in wishlist");
//    softAssert.assertAll();

//    app.account().wishlists().logout();
//    app.windows().close();
//    app.windows().switchTo(hotelWindow);
//    app.windows().close();
//    app.windows().switchTo(resultsWindow);
  }
}
