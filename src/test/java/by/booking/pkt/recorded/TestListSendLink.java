package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TestListSendLink extends TBase {

  @Test
  public void testListSendLink() throws Exception {
    goToMainSearchPage();
    logIn( );
    goToWishlists();
    String urlForList = getUrlForList();
    logOut();
    webDriver.get(urlForList);
    String actual = webDriver.findElement(By.xpath("//h1[text()='Go to British']")).getAttribute("innerText");
    System.out.println(actual);
    assertion.assertTrue(isElementPresent(By.xpath("//h1[text()='Go to British']")));
    //
  }

  private String getUrlForList() {
    System.out.println("GET URL button");
    wait.until(visibilityOfElementLocated(By.cssSelector("div[class=bui-dropdown]+button span")));
    webDriver.findElement(By.cssSelector("div[class=bui-dropdown]+button span")).click();
    System.out.println("GET URL text");
    wait.until(visibilityOfElementLocated(By.cssSelector("p[class*=content]")));
    String s=webDriver.findElement(By.cssSelector("p[class*=content] input")).getAttribute("defaultValue");
    System.out.println(s);
    return s;
  }

}
