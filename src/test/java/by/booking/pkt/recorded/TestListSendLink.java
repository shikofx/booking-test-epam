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
    String urlForList = getUrlForList();

//    logOut();
    webDriver.get(urlForList);

//    String s = webDriver.findElement(By.cssSelector("h1[class*=[shared]")).getText();
//    assertion.assertEquals(s, "Go to British", "Ссылка на список не работает");
    //
  }

  private String getUrlForList() {
    wait.until(visibilityOfElementLocated(By.cssSelector("div[class=bui-dropdown]+button span")));
    webDriver.findElement(By.cssSelector("div[class=bui-dropdown]+button span")).click();
    System.out.println("ссылка");
    //wait.until(visibilityOfElementLocated(By.cssSelector("input[class*=listview-share__url]")));
    String s = webDriver.findElement(By.cssSelector("input[defaultValue^=https]")).getText();
    System.out.println(s);
    return s;
  }

}
