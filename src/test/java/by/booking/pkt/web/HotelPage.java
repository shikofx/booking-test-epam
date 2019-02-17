package by.booking.pkt.web;

import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.Wishlist;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;

public class HotelPage extends HelperBase {
  public HotelPage(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public String getUrl() {
    return getTextByPattern(webDriver.getCurrentUrl(), ".+/[A-Za-z0-9_-]*");
  }

  public Wishlist createWishlist(String listName, Hotel hotel) {
    displayDropDown(By.cssSelector("#wrap-hotelpage-top .js-wl-dropdown-handle"), By.cssSelector("#hotel-wishlists"), 5);
    List<WebElement> oldWishlists = webDriver.findElements(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"));
    enterWishlistName(listName).submitWishlistCreating(oldWishlists.size());
    unselectAllElements(oldWishlists);
    List<WebElement> newWishlists = webDriver.findElements(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"));
    newWishlists.removeAll(oldWishlists);
    WebElement newWishlistElement = newWishlists.iterator().next();
    Wishlist wishlist = elementToWishList(newWishlistElement, hotel);
    return wishlist;
  }

  private Wishlist elementToWishList(WebElement wl, Hotel hotel) {
    return new Wishlist().withId(getId(wl)).
            withName(getName(wl)).
            withHotels(getHotels(hotel));
  }

  @NotNull
  private List<Hotel> getHotels(Hotel hotel) {
    List<Hotel> hotelList = new ArrayList<>();
    hotelList.add(hotel);
    return hotelList;
  }

  private String getName(WebElement wl) {
    return wl.findElement(By.cssSelector("span")).getText();
  }

  private int getId(WebElement wl) {
    return Integer.parseInt(wl.findElement(By.cssSelector("input")).getAttribute("data-list-id"));
  }

  private HotelPage enterWishlistName(String listName) {
    typeText(By.cssSelector("#hotel-wishlists input[type=text]"), listName);
    return this;
  }

  private HotelPage submitWishlistCreating(int size) {
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).sendKeys(Keys.ENTER);
    wait.until(numberOfElementsToBe(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"), size + 1));
    return this;
  }

  public HotelPage goToWishlist(Wishlist wishlist) {
    WebElement wishlistElement = wishlistToElement(wishlist);
    String url = webDriver.getCurrentUrl();
    if (wishlistElement.findElement(By.cssSelector("input")).isSelected()) {
      wishlistElement.findElement(By.cssSelector("input~span a")).click();
    }
    return this;
  }

  private WebElement wishlistToElement(Wishlist wishlist) {
    return webDriver.findElement(By.xpath("//input[@data-list-id='" + wishlist.getId() + "']/parent::*"));
  }

  private void unselectAllElements(List<WebElement> list) {
    for (WebElement e : list) {
      if (e.findElement(By.cssSelector("input")).isSelected())
        e.findElement(By.cssSelector("input~span.bui-checkbox__label")).click();
    }
  }
}
