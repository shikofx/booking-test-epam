package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class TListCreate extends TBase {

  @Test(enabled = true)
  public void testListCreate() throws InterruptedException {
    goToMainSearchPage();
    logIn();
    goToWishlists();
    createNewWishlist();
    String actual=webDriver.findElement(By.cssSelector("span.wl-bui-header")).getText();
    assertion.assertEquals(actual, "Go to British", "Новая группа не создана");
    logOut();
  }
}
