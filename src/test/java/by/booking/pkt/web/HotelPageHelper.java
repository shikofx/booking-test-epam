package by.booking.pkt.web;

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

   public void createWishlist(Wishlist wishlist) {
      displayDropDown(saveButton, wishlistsPanel, 5);
      final List<Wishlist> oldWishlists = webToWishlits();
      enterWishlistName(wishlist.getName()).submitCreating();
      wait.until((WebDriver d) -> (wishlistsOnPanel.size() > oldWishlists.size()));
      List<Wishlist> newWishlists = webToWishlits();
      unselectAllElements(oldWishlists);
      newWishlists.removeAll(oldWishlists);
      Wishlist newWishlist = newWishlists.iterator().next();
      wishlist.withId(newWishlist.getId());
   }

   private List<Wishlist> webToWishlits() {
      List<Wishlist> wishlists = new ArrayList<>();
      for (WebElement e : wishlistsOnPanel) {
         wishlists.add(new Wishlist().
                 withId(getWishlistID(e)).
                 withName(getName(e)));
      }
      return wishlists;
   }

   private int getWishlistID(WebElement e) {
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

   private void submitCreating() {
      newListInput.sendKeys(Keys.ENTER);
   }

   public void goToWishlist(Wishlist wishlist) {
      WebElement wishlistElement = wishlistToWeb(wishlist);
      if (wishlistElement.findElement(By.cssSelector("input")).isSelected()) {
         wishlistElement.findElement(By.cssSelector("input~span a")).click();
      }
   }


   private void unselectAllElements(List<Wishlist> wishlists) {
      for (Wishlist w : wishlists) {
         WebElement e = wishlistToWeb(w);
         if (e.findElement(By.cssSelector("input")).isSelected())
            e.findElement(By.cssSelector("input~span.bui-checkbox__label")).click();
      }
   }

   private WebElement wishlistToWeb(Wishlist wishlist) {
      By wishlistLocator = By.xpath("//input[@data-list-id='" + wishlist.getId() + "']/parent::*");
      return webDriver.findElement(wishlistLocator);
   }
}
