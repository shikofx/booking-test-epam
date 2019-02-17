package by.booking.pkt.web;

import by.booking.pkt.model.Hotel;
import by.booking.pkt.model.Wishlist;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WishlistsPage extends HelperBase {

  public WishlistsPage(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public Wishlist createNewWithName(String listName) throws InterruptedException {
    List<Wishlist> beforeList = getWishlists();
    Alert alertCreateList = alertAfterClick(By.cssSelector("button[class*=js-listview-create-list] span"));
    alertCreateList.sendKeys(listName);
    alertCreateList.accept();
    wait.until((WebDriver d) -> {
      return d.findElement(By.cssSelector(".js-listview-header-dropdown span")).getText().equals(listName);
    });
    List<Wishlist> afterList = getWishlists();
    if(afterList.size()==(beforeList.size()+1)){
      Comparator<? super Wishlist> byId = (wl1, wl2)->Integer.compare(wl1.getId(),wl2.getId());
      return afterList.stream().max(byId).get();
    } else {
      return null;
    }
  }

  public WishlistsPage deleteWishlist(Wishlist list) throws InterruptedException {
    displayDropDown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector("div:last-of-type .listview__lists"), 5);
    WebElement wishlistElement = webDriver.findElement(By.cssSelector("div:last-of-type .listview__lists div[data-id='" + list.getId() + "']"));
    Alert alertDeleteList = alertAfterClick(wishlistElement.findElement(By.cssSelector("span.listmap__remove_list")));
    alertDeleteList.accept();
    //refreshDriver();
    return this;
  }

  public WishlistsPage setAsDefault(Wishlist wishlist) {
    displayDropDown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector("div:last-of-type .listview__lists"), 5);
    WebElement list = webDriver.findElement(By.cssSelector("div:last-of-type .listview__lists div[data-id='" + wishlist.getId() + "']"));
    if (!list.getAttribute("class").contains("selected"))
      list.click();
    return this;
  }

  private void refreshWislists() {
    displayDropDown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector("div:last-of-type .listview__lists"), 5);
    hideDropdown(By.cssSelector("div[class*=bui-dropdown] span"), By.cssSelector("div:last-of-type .listview__lists"));
  }

  public List<Wishlist> getWishlists() {
    refreshWislists();
    List<Wishlist> wishlists = new ArrayList<>();
    for(WebElement e:getWishlistsElements()){
      Wishlist wl=new Wishlist().
              withId(getWishlistId(e)).
              withName(getWishlistName(e)).
              withHotels(getWishlistHotels(e));
      wishlists.add(wl);
    }
    return wishlists;
  }

  private List<Hotel> getWishlistHotels(WebElement e) {
    List<Hotel> hotels = new ArrayList<>();
    return hotels;
  }

  private String getWishlistName(WebElement e) {
    return getTextByPattern(".+(?=\\s)", e.findElement(By.cssSelector("span.listmap__list_name")).getAttribute("textContent"));
  }

  private int getWishlistId(WebElement e) {
    int id=Integer.parseInt(e.getAttribute("data-id"));
    return id;
  }

  private List<WebElement> getWishlistsElements() {
    List<WebElement> list = webDriver.findElements(By.cssSelector("div:last-of-type .listview__lists div"));//("div:not(.fly-dropdown_hidden) .listview__lists div"));
    return list;
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

  private Alert alertAfterClick(WebElement toClick) {
    wait.until(visibilityOfAllElements(toClick));
    return wait.until((WebDriver d) -> {
      toClick.click();
      try {
        return d.switchTo().alert();
      } catch (NoAlertPresentException e) {
        return null;
      }
    });
  }

  public String sendUrl() {
    wait.until(visibilityOfElementLocated(By.cssSelector("div[class=bui-dropdown]+button")));
    displayDropDown(By.cssSelector("div[class=bui-dropdown]+button"), By.cssSelector("div.listview-share"), 5);
    wait.until(visibilityOfElementLocated(By.cssSelector("p[class*=content] input")));
    String url = webDriver.findElement(By.cssSelector("p[class*=content] input")).getAttribute("defaultValue");
    return url;
  }
  public String defaultList() {
    wait.until(presenceOfElementLocated(By.cssSelector("div[class*=bui-dropdown] span")));
    return webDriver.findElement(By.cssSelector("div[class*=bui-dropdown] span")).getText();
  }


  public String sendedListName() {
    wait.until(presenceOfElementLocated(By.cssSelector("div.wl-bui-header h1")));
    return webDriver.findElement(By.cssSelector("div.wl-bui-header h1")).getText();
  }

  public List<WebElement> allHotelElements() {
    return webDriver.findElements(By.cssSelector(".bui-carousel__item"));
  }

  public List<Hotel> hotelsInList() {
    List<WebElement> hotelsElements = allHotelElements();
    List<Hotel> hotels = new ArrayList<>();
    for (WebElement e : hotelsElements) {
      Hotel h = elementToHotel(e);
      System.out.println(h.toString());
      hotels.add(elementToHotel(e));

    }
    return hotels;
  }

  public Hotel elementToHotel(WebElement item) {
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
