package by.booking.pkt.tests;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.SearchData;
import by.booking.pkt.model.Wishlist;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Set;

public class WishlistTests extends TBase {


  @Test(enabled = true, groups = {"positive", "smoke", "sort"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testListCreate(SearchData searchData) throws InterruptedException {
    app.account().loginAs(searchData.userName(), searchData.userPassword());
    app.account().menuTo().wishlists();
    Wishlist newWishlist = app.wishlists().createNewWithName(searchData.getWishlistName());
    String defaultListName = app.wishlists().defaultList();
    System.out.println(newWishlist.getId()+"-"+newWishlist.getName()+"-"+defaultListName);
    Assertion assertion = new Assertion();
    assertion.assertNotEquals(newWishlist, null, "Wishlist create failed!");
    assertion.assertEquals(newWishlist.getName(), searchData.getWishlistName(), "Name of new list is not valid!");
    assertion.assertEquals(defaultListName, searchData.getWishlistName(), "Name of default list is not '"+searchData.getWishlistName()+"'!");


  }

  @Test(enabled = true, groups = {"positive", "smoke", "sort"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testListSendLink(SearchData searchData) throws Exception {
    app.account().loginAs(searchData.userName(), searchData.userPassword());
    app.account().menuTo().wishlists();
    /*Wishlist listToSend = app.wishlists().currentList();
    String urlToSend = app.wishlists().sendUrl();
    app.account().menuTo().logout();
    app.windows().goTo(urlToSend);
    Wishlist actualList = app.wishlists().currentList();
    Assertion assertion = new Assertion();
    assertion.assertEquals(listToSend.getName(), actualList.getName(), "Url of list is not valid!");*/
  }

  @Test(enabled = true, groups = {"positive", "smoke", "sort"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testItemAddToNewList(SearchData searchData) {
    app.forSearch().fillForm(searchData).initSearch();

    String resultsWindow = app.windows().handle();
    Set<String> oldWindows = app.windows().all();

    List<WebElement> allHotels = app.results().getAllResults();
    app.results().goTo(allHotels.get(1));

    String hotelWindow = app.windows().getNew(oldWindows);
    app.windows().switchTo(hotelWindow);
    oldWindows = app.windows().all();

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertNotEquals(hotelWindow, null, "Hotel info doesn't open in new window!");

    String hotelUrl = app.hotel().getUrl();

    /*Wishlist newList = new Wishlist("Go menuTo");
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

    app.account().menuTo().logout();
    app.windows().close();
    app.windows().switchTo(hotelWindow);
    app.windows().close();
    app.windows().switchTo(resultsWindow);*/
  }
}
