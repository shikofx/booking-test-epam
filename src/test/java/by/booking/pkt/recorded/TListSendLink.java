package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TListSendLink extends TBase {

  @Test
  public void testListSendLink() throws Exception {
    hdr_login("pkt.autotests@gmail.com", "123456789");
    hfr_goToWishlistsPage();
    String currentListName = wl_getDefaultWishlist().getText();
    String urlForList = wl_getUrlToSend();
    hdr_logOut();
    goToUrl(urlForList);
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    assertion.assertEquals(currentListName, headerOfSendedWishlist().getText());
  }

  private WebElement headerOfSendedWishlist() {
    return webDriver.findElement(By.xpath("//h1"));
  }

  private void goToUrl(String urlForList) {
    webDriver.get(urlForList);
  }


}
