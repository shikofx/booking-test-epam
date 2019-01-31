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
    assertion.assertTrue(isElementPresent(By.xpath("//h1[text()='Go to British']")));
  }

  private String getUrlForList() {
    activateDropdownMenu(By.cssSelector("div[class=bui-dropdown]+button span"), By.cssSelector("div.listview-share"));
    wait.until(visibilityOfElementLocated(By.cssSelector("p[class*=content] input")));
    String url = webDriver.findElement(By.cssSelector("p[class*=content] input")).getAttribute("defaultValue");
    return url;
  }

}
