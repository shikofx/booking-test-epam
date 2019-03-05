package by.booking.pkt.tests.hotel;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.TestData;
import by.booking.pkt.model.Wishlist;
import by.booking.pkt.tests.TestBaseWithLogin;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddToWishlist extends TestBaseWithLogin {

   @Test(enabled = true, groups = {"positive", "hotel"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src/test/resources/data-for-hotel-positive.json")
   public void addHotelToNewList(TestData testData) {
//      app.searchPage().searchFor(testData);
//      String searchWindowHandle = app.pageNavigation().getCurrent();
//      Set<String> oldWindows = app.pageNavigation().all();
//      Hotel hotel = app.resultsPage().getFirstHotel();
//      app.resultsPage().goToHotelPage(hotel);
//      app.pageNavigation().switchToNew(oldWindows);
//      Wishlist wishlistToCreate = new Wishlist().
//              withName(testData.wishlistName()).
//              withHotels(Arrays.asList(hotel));
//      app.hotelPage().createWishlist(wishlistToCreate);
//
//      String hotelWindowHandle = app.pageNavigation().getCurrent();
//      oldWindows = app.pageNavigation().all();
//      app.hotelPage().goToWishlist(wishlistToCreate);
//      app.pageNavigation().switchToNew(oldWindows);
//      String wishlistWindowHandle = app.pageNavigation().getCurrent();
//      String defaultListName = app.wishlistsPage().defaultWishlistName();
//      assertThat("New list name doesn't match with default name!", defaultListName, is(wishlistToCreate.getName()));
//
//      boolean wishlistIsCreated = app.wishlistsPage().findWishlist(wishlistToCreate);
//      assertThat("New wishlist doesn't found in wishlist panel", wishlistIsCreated, is(true));
//
//      boolean wishlistIsSellectedAsDafault = app.wishlistsPage().isSelectAsDefault(wishlistToCreate);
//      assertThat("New wishlist doesn't selected as default!", wishlistIsSellectedAsDafault, is(true));
//
//      List<Hotel> hotelsInWIshlist = app.wishlistsPage().getWishlistHotels();
//      assertThat("There is more then one hotelPage in wishlist!", hotelsInWIshlist.size(), is(1));
//
//      Hotel hotelInWishlist = hotelsInWIshlist.get(0);
//      assertThat("The hotelPage in wishlist do not mutch with added hotelPage", hotelInWishlist, is(hotel));
//
//      app.wishlistsPage().deleteWishlist(wishlistToCreate);
//      app.pageNavigation().close(wishlistWindowHandle).close(hotelWindowHandle).switchTo(searchWindowHandle);
   }
}
