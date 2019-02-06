package by.booking.pkt.recorded.tests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.Set;

public class WishlistTest extends TBase {

  @Test
  public void testListSendLink() throws Exception {
    Assertion assertion = new Assertion();
    app.getHeaderHelper().goToWishlistPage();
    String currentListName = app.getWishlistHelper().defaultListName();
    String urlForList = app.getWishlistHelper().urlToSend();
    app.getWindowNavigator().goToUrl(urlForList);
    String sendedListName = app.getWishlistHelper().headerOfWishlist().getText();
    assertion.assertEquals(currentListName, sendedListName, "Url fo list is not valid!");
  }

  @Test(enabled = true)
  public void testListCreate() throws InterruptedException {
    String newListName = "Go to France";
    SoftAssert softAssert = new SoftAssert();
    app.getHeaderHelper().goToWishlistPage();
    int oldListsCount = app.getWishlistHelper().getCount();
    app.getWishlistHelper().newList(newListName);
    int newListsCount = app.getWishlistHelper().getCount();
    softAssert.assertEquals(newListsCount, oldListsCount + 1, "Lists count don't chahge!");
    String defaultListName = app.getWishlistHelper().defaultListName();
    softAssert.assertEquals(defaultListName, newListName, "New list is't default!");
    softAssert.assertAll();
  }

  @Test
  public void testItemAddToNewList() {
    String newListName = "Go to France";
    SoftAssert softAssert = new SoftAssert();
    app.getHeaderHelper().selectCurrency("RUB");
    app.getSearchHelper().fillSearchForm("Минск", "2019-03-24", "2019-04-03", 5, 2, 4);
    app.getSearchHelper().initSearch();

    String searchResultsWindow = app.getWindowNavigator().currentWindowHadle();
    Set<String> searchResultsWindowSet = app.getWindowNavigator().windowHandles();
    app.getSearchResultsHelper().goToHotelPage(1);
    String hotelWindow = app.getWindowNavigator().app_switchToNewWindow(searchResultsWindowSet);
    softAssert.assertNotEquals(hotelWindow, null, "Item info didn't open in new window!");

    String itemUrlHeader = app.getWindowNavigator().getCurrentUrlHead();

    Set<String> hotelWindowSet = app.getWindowNavigator().windowHandles();

    WebElement newWishlist = app.getTopListNavigator().createWishlistInTop(newListName);

    softAssert.assertNotEquals(newWishlist, null, "New wish list didn't creat");

    app.getTopListNavigator().goToCreatedWishlist(newWishlist);
    String wishlistWindow = app.getWindowNavigator().app_switchToNewWindow(hotelWindowSet);
    softAssert.assertNotEquals(wishlistWindow, null, "New list didn't open in new window!");

    String actual = app.getWishlistHelper().getTitle();
    softAssert.assertEquals(actual, newListName, "New list didn't opened as current!");

    String itemUrlInListHeader = app.getWishlistHelper().getHotelUrlInWishlist();
    softAssert.assertEquals(itemUrlInListHeader, itemUrlHeader, "New item didn't add!");

    softAssert.assertAll();

    app.getWindowNavigator().closeCurrentWindows();
    app.getWindowNavigator().switchToWindow(hotelWindow);
    app.getWindowNavigator().closeCurrentWindows();
    app.getWindowNavigator().switchToWindow(searchResultsWindow);
  }
}
