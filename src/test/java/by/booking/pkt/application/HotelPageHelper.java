package by.booking.pkt.application;

import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.Wishlist;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;

public class HotelPageHelper extends HelperBase {
  public HotelPageHelper(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
    PageFactory.initElements(webDriver, this);
  }

  @FindBy(css = "#hotel-wishlists input[type=text]")
  private WebElement newListInput;

  @FindBy(css = "#wrap-hotelpage-top .js-wl-dropdown-handle")
  private WebElement saveButton;

  @FindBy(css = "#hotel-wishlists")
  private WebElement wishlistsPanel;

  @FindBy(css = "#hotel-wishlists label.js-wl-dropdown-item")
  private List<WebElement> wishlistsOnPanel;

  public Wishlist createWishlist(String listName, Hotel hotel) {
    displayDropDown(saveButton, wishlistsPanel, 5);
    List<WebElement> oldWishlists = wishlistsOnPanel;
    enterWishlistName(listName).newListInput.sendKeys(Keys.ENTER);
    wait.until((WebDriver d)-> (wishlistsOnPanel.size()==(oldWishlists.size()+1)));
    unselectAllElements(oldWishlists);
    List<WebElement> newWishlists = wishlistsOnPanel;
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

  private HotelPageHelper enterWishlistName(String listName) {
    inputText(newListInput, listName);
    return this;
  }

  private HotelPageHelper submitWishlistCreating(int size) {
    //List<WebElement>ol
    newListInput.sendKeys(Keys.ENTER);
    wait.until((WebDriver d)-> (wishlistsOnPanel.size()==size+1));
//    wait.until(numberOfElementsToBe(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"), size + 1));
    return this;
  }

  public HotelPageHelper goToWishlist(Wishlist wishlist) {
    WebElement wishlistElement = wishlistToElement(wishlist);
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
