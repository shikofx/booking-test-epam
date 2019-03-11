package by.booking.pkt.tests;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.TestData;
import by.booking.pkt.model.Wishlist;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HotelTests extends TestBaseWithLogin {

   @Test(enabled = true, groups = {"positive", "hotel"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src/test/resources/data-for-hotel-positive.json")
   public void addHotelToNewList(TestData testData) {
      app.searchPage().search(testData);
      String searchWindowHandle = app.windowsNavigation().getCurrent();
      Set<String> oldWindows = app.windowsNavigation().all();
      Hotel hotel = app.resultsPage().getFirstHotel();
      app.resultsPage().goToHotelPage(hotel);
      app.windowsNavigation().switchToNew(oldWindows);
      Wishlist createdWishlist = new Wishlist().
              withName(testData.wishlistName()).
              withHotels(Arrays.asList(hotel));
      app.hotelPage().createWishlist(createdWishlist);

      String hotelWindowHandle = app.windowsNavigation().getCurrent();
      oldWindows = app.windowsNavigation().all();
      app.hotelPage().goToWishlist(createdWishlist);
      app.windowsNavigation().switchToNew(oldWindows);
      String wishlistWindowHandle = app.windowsNavigation().getCurrent();

      assertThat("New list name doesn't match with default name!",
              app.wishlistsPage().defaultWishlistName(),
              is(createdWishlist.getName()));

      assertThat("New wishlist doesn't found in wishlist panel",
              app.wishlistsPage().isCreated(createdWishlist),
               is(true));

      assertThat("New wishlist doesn't selected as default!",
              app.wishlistsPage().isSelectAsDefault(createdWishlist),
              is(true));

      assertThat("There is more then one hotel in wishlist!",
              app.wishlistsPage().getWishlistHotels().size(),
              is(1));

      assertThat("The hotel in wishlist do not mutch with added hotel",
              app.wishlistsPage().getWishlistHotels().get(0),
              is(hotel));

      app.wishlistsPage().deleteWishlist(createdWishlist);
      app.windowsNavigation().close(wishlistWindowHandle).close(hotelWindowHandle).switchTo(searchWindowHandle);
   }
}
