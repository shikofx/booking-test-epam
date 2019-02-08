package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;

public class TopListManager extends ManagerBase {
  public TopListManager(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public WebElement createWishlistInTop(String listName) {
    showDropdown(By.cssSelector("#wrap-hotelpage-top .js-wl-dropdown-handle"), By.cssSelector("#hotel-wishlists"), 5);
    List<WebElement> oldWishlists = webDriver.findElements(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"));
    typeText(By.cssSelector("#hotel-wishlists input[type=text]"), listName);
    submitWishlistCreating();
    wait.until(numberOfElementsToBe(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"), oldWishlists.size() + 1));
    List<WebElement> newWishlists = webDriver.findElements(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"));
    unselectAllElementsInList(oldWishlists);
    newWishlists.removeAll(oldWishlists);
    return newWishlists.iterator().next();
  }

  private void submitWishlistCreating() {
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).sendKeys(Keys.ENTER);
  }

  public boolean goToCreatedWishlist(WebElement newWishlist) {
    if (newWishlist.findElement(By.cssSelector("input")).isSelected()) {
      clickOn(newWishlist, By.cssSelector("input~span a"));
      return true;
    } else {
      return false;
    }
  }

  private void unselectAllElementsInList(List<WebElement> list) {
    for (WebElement e : list) {
      if (e.findElement(By.cssSelector("input")).isSelected())
        clickOn(e, By.cssSelector("input~span.bui-checkbox__label"));
    }
  }
}