package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfNestedElementLocatedBy;

public class TopListNavigator extends HelperBase {
  public TopListNavigator(WebDriver webDriver, WebDriverWait wait) {
    super(webDriver, wait);
  }

  public boolean hotel_goToCreatedWishlist(WebElement newWishlist) {
    if (newWishlist.findElement(By.cssSelector("input")).isSelected()) {
      newWishlist.findElement(By.cssSelector("input~span a")).click();
      return true;
    } else {
      return false;
    }
  }

  public WebElement hotel_addWishlistFromSearchResultPage(String listName) {
    List<WebElement> oldWishlists = webDriver.findElements(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"));
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).click();
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).clear();
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).sendKeys(listName);
    webDriver.findElement(By.cssSelector("#hotel-wishlists input[type=text]")).sendKeys(Keys.ENTER);
    wait.until(numberOfElementsToBe(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"), oldWishlists.size() + 1));
    List<WebElement> newWishlists = webDriver.findElements(By.cssSelector("#hotel-wishlists label.js-wl-dropdown-item"));
    hotel_unselectAllElementsInList(oldWishlists);
    newWishlists.removeAll(oldWishlists);
    return newWishlists.iterator().next();
  }

  private void hotel_unselectAllElementsInList(List<WebElement> list) {
    for (WebElement e : list) {
      if (e.findElement(By.cssSelector("input")).isSelected())
        e.findElement(By.cssSelector("input~span.bui-checkbox__label")).click();
    }
  }
}
