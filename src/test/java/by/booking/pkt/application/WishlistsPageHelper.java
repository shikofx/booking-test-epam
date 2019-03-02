package by.booking.pkt.application;

import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.Wishlist;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WishlistsPageHelper extends HelperBase {

   public static final String REGEX_WISHLIST_NAME = ".+(?=\\s)";

   public WishlistsPageHelper(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
      super(webDriver, wait, implicitlyWait);
      PageFactory.initElements(webDriver, this);
   }

   @FindBy(css = "button[class*=js-listview-create-list] span")
   private WebElement createButton;

   @FindBy(css = ".js-listview-header-dropdown span")
   private WebElement dropdownListHeader;

   @FindBy(css = "div[class*=bui-dropdown] span")
   private WebElement defaultWishlist;

   @FindBy(css = "div:last-of-type .listview__lists")
   private WebElement actualWishlistPanel;

   @FindBy(css = "span.listmap__remove_list")
   private WebElement removeWishlistButton;

   @FindBy(css = "div:last-of-type .listview__lists div")
   private List<WebElement> allWishlists;

   @FindBy(css = "div[class=bui-dropdown]+button")
   private WebElement shareWishlistButton;

   @FindBy(css = "div.listview-share")
   private WebElement shareWishlistPanel;

   @FindBy(css = "p[class*=content] input")
   private WebElement shareURLInput;

   @FindBy(css = "div.wl-bui-header h1")
   private WebElement wishlistHeader;

   @FindBy(css = ".bui-carousel__item")
   private List<WebElement> hotelsInWishlist;

   public Wishlist createNewWithName(String listName) throws InterruptedException {
      List<Wishlist> beforeList = getWishlists();
      Alert alertCreateList = alertAfterClick(createButton);
      alertCreateList.sendKeys(listName);
      alertCreateList.accept();
      wait.until((WebDriver d) -> {
         return dropdownListHeader.getText().equals(listName);
      });
      List<Wishlist> afterList = getWishlists();
      if (afterList.size() == (beforeList.size() + 1)) {
         Comparator<? super Wishlist> byId = Comparator.comparingInt(Wishlist::getId);
         return afterList.stream().max(byId).get();
      } else {
         return null;
      }
   }

   public WishlistsPageHelper deleteWishlist(Wishlist list) {
      displayDropDown(defaultWishlist, actualWishlistPanel, 5);
      By removeButtonBy = By.cssSelector("span.listmap__remove_list");
      WebElement buttonToRemoveWishlist = wishlistToWeb(list).findElement(removeButtonBy);
      Alert alertDeleteList = alertAfterClick(buttonToRemoveWishlist);
      alertDeleteList.accept();
      return this;
   }

   private WebElement wishlistToWeb(Wishlist wishlist) {
      By wishlistBy = By.cssSelector("div[data-id='" + wishlist.getId() + "']");
      return actualWishlistPanel.findElement(wishlistBy);
   }

   public WishlistsPageHelper setAsDefault(Wishlist wishlist) {
      displayDropDown(defaultWishlist, actualWishlistPanel, 5);
      WebElement list = wishlistToWeb(wishlist);
      if (!list.getAttribute("class").contains("selected"))
         list.click();
      return this;
   }

   public boolean isSelectAsDefault(Wishlist wishlist) {
      displayDropDown(defaultWishlist, actualWishlistPanel, 5);
      WebElement list = wishlistToWeb(wishlist);
      if (list.getAttribute("class").contains("selected"))
         return true;
      else
         return false;
   }

   private void refreshWislists() {
      displayDropDown(defaultWishlist, actualWishlistPanel, 5);
      hideDropdown(defaultWishlist, actualWishlistPanel);
   }

   public List<Wishlist> getWishlists() {
      displayDropDown(defaultWishlist, actualWishlistPanel, 5);
      hideDropdown(defaultWishlist, actualWishlistPanel);
      List<Wishlist> wishlists = new ArrayList<>();
      for (WebElement e : allWishlists) {
         Wishlist wl = new Wishlist().
                 withId(getWishlistId(e)).
                 withName(getWishlistName(e)).
                 withHotels(getWishlistHotels(e));
         wishlists.add(wl);
      }
      return wishlists;
   }

   //NotRealized
   private List<Hotel> getWishlistHotels(WebElement e) {
      List<Hotel> hotels = new ArrayList<>();
      return hotels;
   }

   private String getWishlistName(WebElement e) {
      By wishlistNameBy = By.cssSelector("span.listmap__list_name");
      return textByPattern(REGEX_WISHLIST_NAME, e.findElement(wishlistNameBy).getAttribute("textContent"));
   }

   private int getWishlistId(WebElement e) {
      int id = Integer.parseInt(e.getAttribute("data-id"));
      return id;
   }

   public String sendUrl() {
      isElementPresentAndVisible(shareWishlistButton);
      displayDropDown(shareWishlistButton, shareWishlistPanel, 5);
      String url = shareURLInput.getAttribute("defaultValue");
      return url;
   }

   public String defaultWishlistName() {
      wait.until((WebDriver d) -> defaultWishlist.getText());
      return defaultWishlist.getText();
   }

   public String sendedListName() {
      wait.until((WebDriver d) -> wishlistHeader);
      return wishlistHeader.getText();
   }

   public List<Hotel> getWishlistHotels() {
      List<Hotel> hotels = new ArrayList<>();
      for (WebElement e : hotelsInWishlist) {
         hotels.add(webToHotel(e));
      }
      return hotels;
   }

   public Hotel webToHotel(WebElement item) {
      return new Hotel().withID(item.findElement(By.cssSelector("div")).getAttribute("data-id")).
              withName(item.findElement(By.cssSelector("h1 a")).getText());
      //Для тестового задания опустил считывание всех данных об отеле
   }

   public boolean findWishlist(Wishlist newWishlist) {
      for (Wishlist wl : getWishlists()) {
         if (wl.equals(newWishlist))
            return true;
      }
      return false;
   }
}
