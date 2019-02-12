package by.booking.pkt.application.web;

import by.booking.pkt.model.Wishlist;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WishlistsPage extends HelperBase {

  public WishlistsPage(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public void create(Wishlist listName) throws InterruptedException {
    wait.until(visibilityOfElementLocated(By.cssSelector("button[class*=js-listview-create-list] span")));
    clickOn(By.cssSelector("button[class*=js-listview-create-list] span"));
    Alert alertCreateList = wait.until(alertIsPresent());
    alertCreateList.sendKeys(listName.getName());
    alertCreateList.accept();
    wait.until(visibilityOfElementLocated(By.cssSelector("[data-id=currency_selector]")));  //refreshDriver());
  }

  public List<WebElement> getAll() {
    showDropdown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector(".listview__lists"), 5);
    List<WebElement> wishlists = webDriver.findElements(By.cssSelector(".listview__lists div"));
    hideDropdown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector(".listview__lists"), 5);
    return wishlists;
  }

  public String sendUrl() {
    wait.until(visibilityOfElementLocated(By.cssSelector("div[class=bui-dropdown]+button")));
    showDropdown(By.cssSelector("div[class=bui-dropdown]+button"), By.cssSelector("div.listview-share"), 5);
    wait.until(visibilityOfElementLocated(By.cssSelector("p[class*=content] input")));
    String url = getAttribute(By.cssSelector("p[class*=content] input"), "defaultValue");
    return url;
  }

  public Wishlist defaultList() {
    wait.until(presenceOfElementLocated(By.cssSelector("div[class*=bui-dropdown]")));
    return new Wishlist(webDriver.findElement(By.cssSelector("div[class*=bui-dropdown] span")).getText());
  }

  public Wishlist currentList() {
    wait.until(presenceOfElementLocated(By.cssSelector("div.wl-bui-header h1")));
    return new Wishlist(getText(By.cssSelector("div.wl-bui-header h1")));
  }

  public List<WebElement> allHotels() {
    return webDriver.findElements(By.cssSelector(".bui-carousel__item"));
  }

  public String getUrlOf(WebElement hotel) {
    return getTextByPattern(getAttribute(hotel, By.cssSelector("header[class*=header] a"), "href"), ".+/[A-Za-z0-9_-]*");
  }
}
