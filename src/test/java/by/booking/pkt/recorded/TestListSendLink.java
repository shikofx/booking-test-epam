package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TestListSendLink extends TBase {

  @Test
  public void testListSendLink() throws Exception {
    goToMainSearchPage();
    logIn();
    goToWishlists();

    logOut();
    webDriver.get(getUrlForList());
    String s = webDriver.findElement(By.cssSelector("h1[class*=[shared]")).getText();
    assertion.assertEquals(s, "Go to British", "Ссылка на список не работает");
    //
  }

  private String getUrlForList() {
    wait.until(visibilityOfElementLocated(By.cssSelector("div[class=bui-dropdown]+button span")));
    webDriver.findElement(By.cssSelector("div[class=bui-dropdown]+button span")).click();

/*
    wait.until(textMatches(By.cssSelector("span[class^=bui-button]"), Pattern.compile("Go to British")));*/
    return "strung";
  }

}
