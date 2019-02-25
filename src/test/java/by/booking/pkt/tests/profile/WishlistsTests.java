package by.booking.pkt.tests.profile;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.TestsData;
import by.booking.pkt.tests.TBase;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WishlistsTests extends TBase {


   @BeforeClass
   public void goToWishlists() {

   }

   @Test(enabled = true, groups = {"positive", "smoke"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src\\test\\resources\\data-for-wishlist-positive.json")
   public void testListCreate(TestsData testsData) throws InterruptedException {
//
//    Wishlist newWishlist = app.wishlists().createNewWithName(testsData.wishlistName());
//    String defaultListName = app.wishlists().defaultList();
//    app.wishlists().deleteWishlist(newWishlist);
//    app.account().logout();
//    Assertion assertion = new Assertion();
//    assertion.assertNotEquals(newWishlist, null, "Wishlist create failed!");
//    assertion.assertEquals(newWishlist.getName(), testsData.wishlistName(), "Name of new list is not valid!");
//    assertion.assertEquals(defaultListName, testsData.wishlistName(), "Name of default list is not '" + testsData.wishlistName() + "'!");


   }

   @Test(enabled = true, groups = {"positive"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src\\test\\resources\\data-for-wishlist-positive.json")
   public void testListSendLink(TestsData testsData) throws Exception {
//    app.account().loginAs(testsData.userName(), testsData.userPassword());
//    app.account().navigationMenu().wishlists();
//    Wishlist newWishlist = app.wishlists().
//            createNewWithName(testsData.wishlistName());
//    app.wishlists().refreshPage();
//    app.wishlists().setAsDefault(newWishlist);
//    String urlToSend = app.wishlists().sendUrl();
//    app.windows().goTo(urlToSend);
//    String sendedListName = app.wishlists().sendedListName();
//    app.account().navigationMenu().wishlists();
//    app.wishlists().deleteWishlist(newWishlist);
//    app.account().logout();
//    Assertion assertion = new Assertion();
//    assertion.assertEquals(newWishlist.getName(), sendedListName, "Name of list is not valid!");
   }
}
