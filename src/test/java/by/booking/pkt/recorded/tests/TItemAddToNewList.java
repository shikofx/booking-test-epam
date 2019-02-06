package by.booking.pkt.recorded.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Set;

public class TItemAddToNewList extends TBase {

  @Test
  public void testItemAddToNewList() {
    SoftAssert softAssert = new SoftAssert();
    app.hdr_login("pkt.autotests@gmail.com", "123456789");
    app.hdr_selectCurrency("RUB");
    app.ms_fillFields("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.ms_initSearch();
    //Save handler for current window
    String searchResultsWindow = app.currentWindowHadle();
    Set<String> searchResultsWindowSet = windowHandles();
    app.sr_goToSearchResult(1);
    String hotelWindow = app.getNavigationHelper().app_switchToNewWindow(searchResultsWindowSet);
    softAssert.assertNotEquals(hotelWindow, null,"Item info didn't open in new window!");

    String itemUrlHeader = app.sr_getUrlHead();
    app.getNavigationHelper().app_activateDropdownMenu(By.cssSelector("#wrap-hotelpage-top .js-wl-dropdown-handle"), By.cssSelector("#hotel-wishlists"));
    Set<String> hotelWindowSet = windowHandles();
    WebElement newWishlist = app.hotel_addWishlistFromSearchResultPage("Go to France");
    softAssert.assertNotEquals(newWishlist, null, "New wish list didn't creat");

    app.hotel_goToCreatedWishlist(newWishlist);
    String wishlistWindow = app.getNavigationHelper().app_switchToNewWindow(hotelWindowSet);
    softAssert.assertNotEquals(wishlistWindow, null, "New list didn't open in new window!");

    String actual = app.getWishlistHelper().wl_getHeader();
    softAssert.assertEquals(actual, "Go to France", "New list didn't opened as current!");

    String  itemUrlInListHeader = app.app_getTextByPattern(app.app_getAttributeWithWait(By.cssSelector("header[class*=header]"), "href"), ".+/[A-Za-z0-9_-]*");
    softAssert.assertEquals(itemUrlInListHeader, itemUrlHeader, "New item didn't add!");

    softAssert.assertAll();

    closeCurrentWindows();
    switchToWindow(hotelWindow);
    closeCurrentWindows();
    switchToWindow(searchResultsWindow);
    app.hdr_logOut();
  }

}
