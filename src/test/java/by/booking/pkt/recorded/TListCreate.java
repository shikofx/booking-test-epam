package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TListCreate extends TBase {

  @Test(enabled = true)
  public void testListCreate() throws InterruptedException {
    SoftAssert softAssert = new SoftAssert();
    goToMainSearchPage();
    logIn();
    goToWishlistsPage();
    int oldListsCount = listsCount();
    createNewWishlist();
    webDriver.navigate().refresh();
    int newListsCount = listsCount();
    softAssert.assertEquals(newListsCount, oldListsCount + 1, "Количество групп не изменилось");
    String actual = webDriver.findElement(By.cssSelector("div[class*=bui-dropdown] span")).getText();
    softAssert.assertEquals(actual, "Go to British", "Новая группа не стала текущей");
    softAssert.assertAll();
    logOut();
  }


}
