package by.booking.pkt.recorded.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TListSendLink extends TBase {

  @Test
  public void testListSendLink() throws Exception {
    app.hdr_login("pkt.autotests@gmail.com", "123456789");
    app.hfr_goToWishlistsPage();
    String currentListName = app.getWishlistHelper().defaultList().getText();
    String urlForList = app.getWishlistHelper().urlToSend();
    app.hdr_logOut();
    goToUrl(urlForList);
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    app.assertion.assertEquals(currentListName, headerOfSendedWishlist().getText());
  }

  private WebElement headerOfSendedWishlist() {
    return app.getNavigationHelper().webDriver.findElement(By.xpath("//h1"));
  }

  private void goToUrl(String urlForList) {
    app.getNavigationHelper().webDriver.get(urlForList);
  }


}
