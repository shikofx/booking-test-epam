package by.booking.pkt.application.web;

import by.booking.pkt.model.Wishlist;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class WishlistsPage extends HelperBase {

  public WishlistsPage(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public WishlistsPage createNewWithName(String listName) throws InterruptedException {

    System.out.println(webDriver.findElement(By.cssSelector(".js-listview-header-dropdown span")).getText());
    displayDropDown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector("div.listview__lists"), 5);
    List<WebElement> beforeList = webDriver.findElements(By.cssSelector("div:not(.fly-dropdown_hidden) .listview__lists div"));
    hideDropdown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector("div.listview__lists"));
    Alert alertCreateList = alertAfterClick(By.cssSelector("button[class*=js-listview-create-list] span"));
    alertCreateList.sendKeys(listName);
    alertCreateList.accept();

    wait.until((WebDriver d) -> {
      return d.findElement(By.cssSelector(".js-listview-header-dropdown span")).getText().equals(listName);
    });
    System.out.println(webDriver.findElement(By.cssSelector(".js-listview-header-dropdown span")).getText());
    displayDropDown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector("div.listview__lists"), 5);
    List<WebElement> afterList = webDriver.findElements(By.cssSelector("div:not(.fly-dropdown_hidden) .listview__lists div"));
    System.out.println(beforeList.size() + "++" + afterList.size());
    //refreshDriver();
    return this;
  }

  public WishlistsPage deleteWishlist(Wishlist list) throws InterruptedException {

    Alert alertCreateList = alertAfterClick(By.cssSelector("button[class*=js-listview-create-list] span"));
    //alertCreateList.sendKeys(listName);
    alertCreateList.accept();
    //refreshDriver();
    return this;
  }

  private Alert alertAfterClick(By toClick) {
    wait.until(visibilityOfElementLocated(toClick));
    return wait.until((WebDriver d) -> {
      d.findElement(toClick).click();
      try {
        return d.switchTo().alert();
      } catch (NoAlertPresentException e) {
        return null;
      }
    });
  }

  public List<Wishlist> getAll() {
    displayDropDown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector("[class*=listview__lists]:not([class*=hidden])"), 5);
    List<WebElement> wishlistElements = webDriver.findElements(By.cssSelector("[class*=listview__lists]:not([class*=hidden]) div"));
    // hideDropdown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector("[class*=listview__lists]:not[class*=hidden]"), 5);
    List<Wishlist> wishlists = new ArrayList<>();
    return wishlists;
  }

  public String sendUrl() {
    wait.until(visibilityOfElementLocated(By.cssSelector("div[class=bui-dropdown]+button")));
    displayDropDown(By.cssSelector("div[class=bui-dropdown]+button"), By.cssSelector("div.listview-share"), 5);
    wait.until(visibilityOfElementLocated(By.cssSelector("p[class*=content] input")));
    String url = getAttribute(By.cssSelector("p[class*=content] input"), "defaultValue");
    return url;
  }

 /* public Wishlist defaultList() {
    wait.until(presenceOfElementLocated(By.cssSelector("div[class*=bui-dropdown]")));
    return new Wishlist(webDriver.findElement(By.cssSelector("div[class*=bui-dropdown] span")).getText());
  }*/

 /* public Wishlist currentList() {
    wait.until(presenceOfElementLocated(By.cssSelector("div.wl-bui-header h1")));
    return new Wishlist(getText(By.cssSelector("div.wl-bui-header h1")));
  }*/

  public List<WebElement> allHotels() {
    return webDriver.findElements(By.cssSelector(".bui-carousel__item"));
  }

  public String getUrlOf(WebElement hotel) {
    return getTextByPattern(getAttribute(hotel, By.cssSelector("header[class*=header] a"), "href"), ".+/[A-Za-z0-9_-]*");
  }
}
