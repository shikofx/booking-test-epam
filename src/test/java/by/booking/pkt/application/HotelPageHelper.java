package by.booking.pkt.application;

import by.booking.pkt.model.Wishlist;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

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

   private WebElement newWishlist;

   public HotelPageHelper createWishlist(Wishlist wishlist) {
      displayDropDown(saveButton, wishlistsPanel, 5);
      final List<Wishlist> oldWishlists = webToWishlits();
      enterWishlistName(wishlist.getName()).submitCreating();
      wait.until((WebDriver d) -> (wishlistsOnPanel.size() > oldWishlists.size()));
      List<Wishlist> newWishlists = webToWishlits();
      unselectAllElements(oldWishlists);
      newWishlists.removeAll(oldWishlists);
      Wishlist newWishlist = newWishlists.iterator().next();
      wishlist.withId(newWishlist.getId());
      return this;
   }

   private List<Wishlist> webToWishlits() {
      List<Wishlist> wishlists = new ArrayList<Wishlist>();
      for (WebElement e : wishlistsOnPanel) {
         wishlists.add(new Wishlist().
                 withId(getWishlistID(e)).
                 withName(getName(e)));
      }
      return wishlists;
   }

   public int getWishlistID(WebElement e) {
      return Integer.parseInt(e.findElement(By.cssSelector("input")).getAttribute("data-list-id"));
   }

   private String getName(WebElement e) {
      return e.getAttribute("aria-label");
   }

   private HotelPageHelper enterWishlistName(String listName) {
      wait.until(visibilityOf(newListInput));
      inputText(newListInput, listName);
      return this;
   }

   private HotelPageHelper submitCreating() {
      newListInput.sendKeys(Keys.ENTER);
      return this;
   }

   public HotelPageHelper goToWishlist(Wishlist wlist) {
      WebElement wishlistElement = wishlistToWeb(wlist);
      if (wishlistElement.findElement(By.cssSelector("input")).isSelected()) {
         wishlistElement.findElement(By.cssSelector("input~span a")).click();
      }
      return this;
   }


   private void unselectAllElements(List<Wishlist> wlists) {
      for (Wishlist w : wlists) {
         WebElement e = wishlistToWeb(w);
         if (e.findElement(By.cssSelector("input")).isSelected())
            e.findElement(By.cssSelector("input~span.bui-checkbox__label")).click();
      }
   }

   private WebElement wishlistToWeb(Wishlist wlist) {
      By wlistBy = By.xpath("//input[@data-list-id='" + wlist.getId() + "']/parent::*");
      return webDriver.findElement(wlistBy);
   }
}
