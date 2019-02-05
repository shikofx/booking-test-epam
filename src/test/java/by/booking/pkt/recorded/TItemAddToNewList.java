package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Set;

public class TItemAddToNewList extends TBase {

  @Test
  public void testItemAddToNewList() {
    SoftAssert softAssert = new SoftAssert();
    hdr_login("pkt.autotests@gmail.com", "123456789");
    hdr_selectCurrency("RUB");
    ms_fillFields("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    ms_initSearch();
    //Save handler for current window
    String searchResultsWindow = webDriver.getWindowHandle();
    Set<String> searchResultsWindowSet = webDriver.getWindowHandles();
    sr_goToSearchResult(1);
    String hotelWindow = app_switchToNewWindow(searchResultsWindowSet);
    softAssert.assertNotEquals(hotelWindow, null,"Item info didn't open in new window!");

    String itemUrlHeader = app_getUrlHead();
    app_activateDropdownMenu(By.cssSelector("#wrap-hotelpage-top .js-wl-dropdown-handle"), By.cssSelector("#hotel-wishlists"));
    Set<String> hotelWindowSet = webDriver.getWindowHandles();
    WebElement newWishlist = hotel_addWishlistFromSearchResultPage("Go to France");
    softAssert.assertNotEquals(newWishlist, null, "New wish list didn't creat");

    hotel_goToCreatedWishlist(newWishlist);
    String wishlistWindow = app_switchToNewWindow(hotelWindowSet);
    softAssert.assertNotEquals(wishlistWindow, null, "New list didn't open in new window!");

    String actual = wl_getHeader();
    softAssert.assertEquals(actual, "Go to France", "New list didn't opened as current!");

    String  itemUrlInListHeader = app_getTextByPattern(app_getAttributeWithWait(By.cssSelector("header[class*=header]"), "href"), ".+/[A-Za-z0-9_-]*");
    softAssert.assertEquals(itemUrlInListHeader, itemUrlHeader, "New item didn't add!");

    softAssert.assertAll();

    webDriver.close();
    webDriver.switchTo().window(hotelWindow);
    webDriver.close();
    webDriver.switchTo().window(searchResultsWindow);
    hdr_logOut();
  }
}
