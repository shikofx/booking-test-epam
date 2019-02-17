package by.booking.pkt.tests;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.SearchData;
import by.booking.pkt.model.Wishlist;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

public class WishlistTests extends TBase {


  @Test(enabled = true, groups = {"positive", "smoke", "sort"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testListCreate(SearchData searchData) throws InterruptedException {
    app.account().loginAs(searchData.userName(), searchData.userPassword());
//    app.account().navigationMenu().wishlists();
//    Wishlist newWishlist = app.wishlists().createNewWithName(searchData.getWishlistName());
//    String defaultListName = app.wishlists().defaultList();
//    app.wishlists().deleteWishlist(newWishlist);
//    app.account().logout();
//    Assertion assertion = new Assertion();
//    assertion.assertNotEquals(newWishlist, null, "Wishlist create failed!");
//    assertion.assertEquals(newWishlist.getName(), searchData.getWishlistName(), "Name of new list is not valid!");
//    assertion.assertEquals(defaultListName, searchData.getWishlistName(), "Name of default list is not '" + searchData.getWishlistName() + "'!");


  }

  @Test(enabled = true, groups = {"positive", "smoke", "sort"},
          dataProviderClass = FileDataProvider.class, dataProvider = "searchDataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void testListSendLink(SearchData searchData) throws Exception {
    app.account().loginAs(searchData.userName(), searchData.userPassword());
    app.account().navigationMenu().wishlists();
    Wishlist newWishlist = app.wishlists().createNewWithName(searchData.getWishlistName());
    app.windows().refreshPage();
    app.wishlists().setAsDefault(newWishlist);
    String urlToSend = app.wishlists().sendUrl();
    app.windows().goTo(urlToSend);
    String sendedListName = app.wishlists().sendedListName();
    app.account().navigationMenu().wishlists();
    app.wishlists().deleteWishlist(newWishlist);
    app.account().logout();
    Assertion assertion = new Assertion();
    assertion.assertEquals(newWishlist.getName(), sendedListName, "Name of list is not valid!");
  }
}
