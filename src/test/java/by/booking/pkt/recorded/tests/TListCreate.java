package by.booking.pkt.recorded.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TListCreate extends TBase {

  @Test(enabled = true)
  public void testListCreate() throws InterruptedException {
    SoftAssert softAssert = new SoftAssert();
    app.hdr_login("pkt.autotests@gmail.com", "123456789");
    app.hfr_goToWishlistsPage();
    int oldListsCount = app.getWishlistHelper().getCount();
    app.getWishlistHelper().newList("Go to British");
    int newListsCount = app.getWishlistHelper().getCount();
    softAssert.assertEquals(newListsCount, oldListsCount + 1, "Lists count don't chahge!");

    String defaultListName = app.getWishlistHelper().defaultList().getText();
    softAssert.assertEquals(defaultListName, "Go to British", "New list is't default!");

    softAssert.assertAll();
    app.hdr_logOut();
  }
}
