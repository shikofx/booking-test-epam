package by.booking.pkt.tests.profile;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.TestData;
import by.booking.pkt.model.Wishlist;
import by.booking.pkt.tests.TBaseWithLogin;
import org.testng.annotations.Test;

public class WishlistsTests extends TBaseWithLogin {

   @Test(enabled = true, groups = {"positive", "smoke"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src/test/resources/data-for-wishlist-positive.json")
   public void createWishlist(TestData testData) throws InterruptedException {
      app.account().goToWishlistsPage();
      Wishlist newWishlist = app.wishlists().createNewWithName(testData.wishlistName());
//     String defaultListName = app.goToWishlistsPage().defaultWishlistName();
//     app.goToWishlistsPage().deleteWishlist(newWishlist);
//     app.account().logout();
//     Assertion assertion = new Assertion();
//     assertion.assertNotEquals(newWishlist, null, "Wishlist create failed!");
//     assertion.assertEquals(newWishlist.name(), testData.wishlistName(), "Name of new list is not valid!");
//     assertion.assertEquals(defaultListName, testData.wishlistName(), "Name of default list is not '" + testData.wishlistName() + "'!");

   }

   @Test(enabled = true, groups = {"positive"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src/test/resources/data-for-wishlist-positive.json")
   public void sendLinkOfWishlist(TestData testData) throws Exception {
      app.account().navigationMenu().goToWishlistsPage();
//    Wishlist newWishlist = app.goToWishlistsPage().
//            createNewWithName(testData.wishlistName());
//    app.goToWishlistsPage().refreshPage();
//    app.goToWishlistsPage().setAsDefault(newWishlist);
//    String urlToSend = app.goToWishlistsPage().sendUrl();
//    app.windows().goTo(urlToSend);
//    String sendedListName = app.goToWishlistsPage().sendedListName();
//    app.account().navigationMenu().goToWishlistsPage();
//    app.goToWishlistsPage().deleteWishlist(newWishlist);
//    app.account().logout();
//    Assertion assertion = new Assertion();
//    assertion.assertEquals(newWishlist.name(), sendedListName, "Name of list is not valid!");
   }
}
