package by.booking.pkt.recorded.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Set;

public class WishlistTest extends TBaseWithLogin {

  @Test
  public void testListSendLink() throws Exception {
    Assertion assertion = new Assertion();
  //  app.getAccountManager().login("pkt.autotests@gmail.com", "123456789");
    app.getAccountManager().goToWishlistPage();
    String currentListName = app.getWishlistManager().defaultListName();
    String urlForList = app.getWishlistManager().getUrlToSend();
    app.getAccountManager().logOut();
    app.getWindowManeger().goToUrl(urlForList);
    String sendedListName = app.getWishlistManager().titleOfWishlist();
    assertion.assertEquals(currentListName, sendedListName, "Url fo list is not valid!");
  }

  @Test(enabled = true)
  public void testListCreate() throws InterruptedException {
    String newListName = "Go HOME";
    Assertion assertion = new Assertion();
 //   app.getAccountManager().login("pkt.autotests@gmail.com", "123456789");
    app.getAccountManager().goToWishlistPage();
    List<WebElement> oldLists = app.getWishlistManager().getAllLists();
    app.getWishlistManager().newList(newListName);
    List<WebElement> newLists = app.getWishlistManager().getAllLists();
    assertion.assertEquals(newLists.size(), oldLists.size() + 1, "Lists count do not change!");
    String defaultListName = app.getWishlistManager().defaultListName();
    assertion.assertEquals(defaultListName, newListName, "New list is't default!");
  }

  @Test
  public void testItemAddToNewList() {
    String newListName = "Go to";
    SoftAssert softAssert = new SoftAssert();
   // app.getAccountManager().login("pkt.autotests@gmail.com", "123456789");
    app.getSearchManager().selectCurrency("RUB");
    app.getSearchManager().fillSearchForm("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchManager().initSearch();

    String searchResultsWindow = app.getWindowManeger().currentWindowHadle();
    Set<String> searchResultsWindowSet = app.getWindowManeger().windowHandles();
    app.getResultsManager().goToHotelPage(1);
    String hotelWindow = app.getWindowManeger().app_switchToNewWindow(searchResultsWindowSet);
    softAssert.assertNotEquals(hotelWindow, null, "Item info didn't open in new window!");

    String hotelUrl = app.getWindowManeger().getCurrentUrlHead();

    Set<String> hotelWindowSet = app.getWindowManeger().windowHandles();

    WebElement newWishlist = app.getTopListManager().createWishlistInTop(newListName);

    softAssert.assertNotEquals(newWishlist, null, "New wish list didn't creat");

    app.getTopListManager().goToCreatedWishlist(newWishlist);
    String wishlistWindow = app.getWindowManeger().app_switchToNewWindow(hotelWindowSet);
    softAssert.assertNotEquals(wishlistWindow, null, "New list didn't open in new window!");

    String actual = app.getWishlistManager().getTitle();
    softAssert.assertEquals(actual, newListName, "New list didn't opened as current!");

    String itemUrlInListHeader = app.getWishlistManager().getHotelUrlInWishlist();
    softAssert.assertEquals(itemUrlInListHeader, hotelUrl, "New item didn't add!");

    softAssert.assertAll();

    app.getWindowManeger().closeCurrentWindows();
    app.getWindowManeger().switchToWindow(hotelWindow);
    app.getWindowManeger().closeCurrentWindows();
    app.getWindowManeger().switchToWindow(searchResultsWindow);
  }
}
