package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TListSendLink extends TBase {

  @Test
  public void testListSendLink() throws Exception {
    goToMainSearchPage();
    logIn( );
    goToWishlistsPage();
    String urlForList = getUrlToSend();
    logOut();
    webDriver.get(urlForList);
    assertion.assertTrue(isElementPresent(webDriver, By.xpath("//h1[text()='Go to British']")));
  }
}
