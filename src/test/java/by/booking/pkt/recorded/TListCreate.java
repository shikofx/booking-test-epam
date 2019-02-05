package by.booking.pkt.recorded;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TListCreate extends TBase {

  @Test(enabled = true)
  public void testListCreate() throws InterruptedException {
    SoftAssert softAssert = new SoftAssert();
    hdr_login("pkt.autotests@gmail.com", "123456789");
    hfr_goToWishlistsPage();
    int oldListsCount = wl_getCount();
    wl_createNewWishlist("Go to British");
    int newListsCount = wl_getCount();
    softAssert.assertEquals(newListsCount, oldListsCount + 1, "Lists count don't chahge!");

    String defaultListName = wl_getDefaultWishlist().getText();
    softAssert.assertEquals(defaultListName, "Go to British", "New list is't default!");

    softAssert.assertAll();
    hdr_logOut();
  }
}
