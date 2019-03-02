package by.booking.pkt.tests.hotel;

import by.booking.pkt.data.DataSourceFileAnnotation;
import by.booking.pkt.data.FileDataProvider;
import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.TestData;
import by.booking.pkt.model.Wishlist;
import by.booking.pkt.tests.TBaseWithLogin;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HotelTests extends TBaseWithLogin {

   @Test(enabled = true, groups = {"positive", "hotel"},
           dataProviderClass = FileDataProvider.class, dataProvider = "testDataFromJSON")
   @DataSourceFileAnnotation("src/test/resources/data-for-hotel-positive.json")
   public void addHotelToNewList(TestData testData) {
      Hotel hotel = app.goToFirstHotelPage(testData);
      Wishlist wishlistToCreate = new Wishlist().
              withName(testData.wishlistName()).
              withHotels(Arrays.asList(hotel));
      app.hotel().createWishlist(wishlistToCreate);
      app.goToWishlist(wishlistToCreate);
      String defaultListName = app.wishlists().defaultWishlistName();
      assertThat("New list name doesn't match with default name!", defaultListName, is(wishlistToCreate.name()));
      boolean wishlistIsCreated = app.wishlists().findWishlist(wishlistToCreate);
      assertThat("New wishlist doesn't found in wishlist panel", wishlistIsCreated, is(true));
      boolean wishlistIsSellectedAsDafault = app.wishlists().isSelectAsDefault(wishlistToCreate);
      assertThat("New wishlist doesn't selected as default!", wishlistIsSellectedAsDafault, is(true));
      List<Hotel> hotelsInWIshlist = app.wishlists().getWishlistHotels();
      assertThat("There is more then one hotel in wishlist!", hotelsInWIshlist.size(), is(1));
      Hotel hotelInWishlist = hotelsInWIshlist.get(0);
      assertThat("The hotel in wishlist do not mutch with added hotel", hotelInWishlist, is(hotel));
      app.wishlists().deleteWishlist(wishlistToCreate);
   }
}
