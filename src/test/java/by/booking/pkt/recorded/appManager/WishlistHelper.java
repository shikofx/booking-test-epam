package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WishlistHelper extends HelperBase{

  public WishlistHelper(WebDriver webDriver, WebDriverWait wait) {
    super(webDriver, wait);
  }

  public void newList(String listName) {
    wait.until(visibilityOfElementLocated(By.cssSelector("button[class*=js-listview-create-list] span")));
    clickOn(By.cssSelector("button[class*=js-listview-create-list] span"));
    Alert alertCreateList = wait.until(alertIsPresent());
    alertCreateList.sendKeys(listName);
    alertCreateList.accept();
    refreshDriver();
  }

  public int getCount() {
    wait.until(presenceOfElementLocated(By.cssSelector(".js-listview-header-dropdown")));
    clickOn(By.cssSelector(".js-listview-header-dropdown"));
    wait.until(presenceOfElementLocated(By.cssSelector(".listview__lists")));
    int count = getElements(By.cssSelector(".listview__lists div")).size();
    clickOn(By.cssSelector(".js-listview-header-dropdown"));
    return count;
  }

  public String urlToSend() {
    showDropdown(By.cssSelector("div[class=bui-dropdown]+button span"), By.cssSelector("div.listview-share"));
    wait.until(visibilityOfElementLocated(By.cssSelector("p[class*=content] input")));
    String url = getAttribute(By.cssSelector("p[class*=content] input"), "defaultValue");
    return url;
  }

  public String defaultListName() {
    return getElement(By.cssSelector("div[class*=bui-dropdown] span")).getText();
  }

  public String getTitle() {
    return getText(By.cssSelector("div.wl-bui-header h1"));
  }

  public WebElement headerOfWishlist() {
    return getElement(By.xpath("//h1"));
  }

  public String getHotelUrlInWishlist() {
    wait.until(presenceOfElementLocated(By.cssSelector("header[class*=header]")));
    return getTextByPattern(getAttribute(By.cssSelector("header[class*=header]"), "href"), ".+/[A-Za-z0-9_-]*");
  }
}
