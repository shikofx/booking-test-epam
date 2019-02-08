package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WishlistManager extends ManagerBase {

  public WishlistManager(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public void newList(String listName) {
    wait.until(visibilityOfElementLocated(By.cssSelector("button[class*=js-listview-create-list] span")));
    clickOn(By.cssSelector("button[class*=js-listview-create-list] span"));
    Alert alertCreateList = wait.until(alertIsPresent());
    alertCreateList.sendKeys(listName);
    alertCreateList.accept();
    refreshDriver();
  }

  public List<WebElement> getAllLists() {
    showDropdown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector(".listview__lists"), 5);
    List<WebElement> wishlists = webDriver.findElements(By.cssSelector(".listview__lists div"));
    hideDropdown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector(".listview__lists"), 5);
    return wishlists;
  }

  public String getUrlToSend() {
    wait.until(visibilityOfElementLocated(By.cssSelector("div[class=bui-dropdown]+button")));
    showDropdown(By.cssSelector("div[class=bui-dropdown]+button"), By.cssSelector("div.listview-share"), 5);
    wait.until(visibilityOfElementLocated(By.cssSelector("p[class*=content] input")));
    String url = getAttribute(By.cssSelector("p[class*=content] input"), "defaultValue");
    return url;
  }

  public String defaultListName() {
    return webDriver.findElement(By.cssSelector("div[class*=bui-dropdown] span")).getText();
  }

  public String getTitle() {
    return getText(By.cssSelector("div.wl-bui-header h1"));
  }

  public String titleOfWishlist() {
    return webDriver.findElement(By.xpath("//h1")).getText();
  }

  public String getHotelUrlInWishlist() {
    wait.until(presenceOfElementLocated(By.cssSelector("header[class*=header]")));
    return getTextByPattern(getAttribute(By.cssSelector("header[class*=header] a"), "href"), ".+/[A-Za-z0-9_-]*");
  }
}
