package by.booking.pkt.tests;

import by.booking.pkt.model.SearchData;
import by.booking.pkt.model.Wishlist;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Set;

public class WishlistTests extends TBase {

  @Test
  public void testListSendLink() throws Exception {
    app.account().to().wishlists();
    Wishlist listToSend = app.wishlists().currentList();
    String urlToSend = app.wishlists().sendUrl();
    app.account().to().logout();
    app.windows().goTo(urlToSend);
    Wishlist actualList = app.wishlists().currentList();
    Assertion assertion = new Assertion();
    assertion.assertEquals(listToSend.getName(), actualList.getName(), "Url of list is not valid!");
  }

  @Test(enabled = true)
  public void testListCreate() throws InterruptedException {
    app.account().to().wishlists();
    List<WebElement> oldLists = app.wishlists().getAll();
    Wishlist newList = new Wishlist("Go BR");
    app.wishlists().create(newList);
    List<WebElement> newLists = app.wishlists().getAll();
    Wishlist defaultList = app.wishlists().defaultList();
    Assertion assertion = new Assertion();
    //assertion.assertTrue(newLists.contains(oldLists), "New wishlists doesn't contain old wishlists");
    assertion.assertEquals(newLists.size(), oldLists.size() + 1, "Lists count does not change!");
    assertion.assertEquals(defaultList.getName(), newList.getName(), "New list is not default!");

  }

  @Test
  public void testItemAddToNewList() {
    app.forSearch().fillForm(new SearchData().withCurrency("RUB").withPlace("Минск").
            withInDate("2019-03-24").withOutDate("2019-04-03").
            withRooms(5).withAdults(2).withChildren(4));
    app.forSearch().initSearch();

    String resultsWindow = app.windows().handle();
    Set<String> oldWindows = app.windows().all();

    List<WebElement> allHotels = app.results().getAll();
    app.results().goTo(allHotels.get(1));

    String hotelWindow = app.windows().getNew(oldWindows);
    app.windows().switchTo(hotelWindow);
    oldWindows = app.windows().all();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertNotEquals(hotelWindow, null, "Hotel info doesn't open in new window!");

    String hotelUrl = app.hotel().getUrl();

    Wishlist newList = new Wishlist("Go to");
    WebElement newWishlist = app.hotel().createWishlist(newList);
    softAssert.assertNotEquals(newWishlist, null, "New wishlist doesn't create!");
    app.hotel().goTo(newWishlist);

    String wishlistWindow = app.windows().getNew(oldWindows);
    softAssert.assertNotEquals(wishlistWindow, null, "New wishlist doesn't open in new window!");

    Wishlist actual = app.wishlists().defaultList();
    softAssert.assertEquals(actual, newList, "New list doesn't opened as default!");

    List<WebElement> hotels = app.wishlists().allHotels();
    String hotelUrlIntWishlist = app.wishlists().getUrlOf(hotels.get(1));
    softAssert.assertEquals(hotelUrlIntWishlist, hotelUrl, "New hotel doesn't add!");

    softAssert.assertAll();

    app.account().to().logout();
    app.windows().close();
    app.windows().switchTo(hotelWindow);
    app.windows().close();
    app.windows().switchTo(resultsWindow);
  }
}
