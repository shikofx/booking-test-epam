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
    String s=webDriver.findElement(By.cssSelector("span[class^=bui-button]")).getText();
    assertion.assertEquals(s, "Go to British", "Новая группа не создана");
    logOut();
  }
}
