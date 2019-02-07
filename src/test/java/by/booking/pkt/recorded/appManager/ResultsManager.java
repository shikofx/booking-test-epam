package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfNestedElementLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ResultsManager extends ManagerBase {
  public ResultsManager(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public void goToHotelPage(int itemNumber) {
    WebElement item = webDriver.findElement(By.cssSelector("#hotellist_inner>.sr_item:nth-of-type(" + itemNumber + ")"));
    clickOn(item, By.cssSelector(".sr-cta-button-row"));
  }

  public List<WebElement> getAllResults() {
    List<WebElement> searchResultItems;
    //Найти все отели до записи: есть еще отели вне определенной локации
    if (isElementPresent(By.cssSelector("div.sr_separator"))) {
      searchResultItems = webDriver.findElements(By.xpath("//*/div[contains(@class,'sr_separator')]/preceding-sibling::div[contains(@class,'sr_item')]"));
    } else {
      searchResultItems = webDriver.findElements(By.cssSelector("div.sr_item"));
    }
    return searchResultItems;
  }

  public void initSortByPrice() {
    wait.until(visibilityOfElementLocated(By.cssSelector("li.sort_price")));
    clickOn(By.cssSelector("li.sort_price"));
  }

  public int distanceToDest(WebElement item) {
    int distance = 0;
    distance = Integer.parseInt(textByPatternNoSpace("[\\,\\d]+", item.findElement(By.cssSelector("span.distfromdest")).getText().replace(',', '.')));
    String meterField = textByPatternNoSpace("\\s.*?\\s", item.findElement(By.cssSelector("span.distfromdest")).getText());
    if (meterField.length() > 1) {
      distance = 1000 * distance;
    }
    return distance;
  }

  public int getStarsCount(WebElement item) {
    int actualStars;
    if (this.isElementPresent(item, By.cssSelector("svg[class*=stars]"))) {
      actualStars = Integer.parseInt(textByPatternNoSpace("\\d", getAttribute(item, By.cssSelector("svg[class*=stars]"), "class")));
    } else {
      actualStars = 0;
    }
    return actualStars;
  }

  public void initSortByDistance() {
    By sortByDistance = By.cssSelector("li.sort_distance_from_search a");
    showDropdown(By.cssSelector("#sortbar_dropdown_button"), By.cssSelector("#sortbar_dropdown_options"), 5);
    wait.until(visibilityOfElementLocated(sortByDistance));
    clickOn(sortByDistance);
  }

  public int totalPrice(WebElement item) {
    int totalPrice = 0;
    if (this.isElementPresent(item, By.cssSelector("div.totalPrice"))) {
      totalPrice = Integer.parseInt(textByPatternNoSpace("(?<=:).+\\d", item.findElement(By.cssSelector("div.totalPrice")).getText()));
    } else if (this.isElementPresent(item, By.cssSelector("strong.price"))) {
      totalPrice = Integer.parseInt(textByPatternNoSpace("[\\d\\s]+\\d", item.findElement(By.cssSelector("strong.price")).getText()));
    }
    return totalPrice;
  }

  public String getHotelName(WebElement item) {
    wait.until(presenceOfNestedElementLocatedBy(item, By.cssSelector("span.sr-hotel__name")));
    return (item.findElement(By.cssSelector("span.sr-hotel__name")).getText());
  }

  public List<WebElement> getOnlyAvailable(List<WebElement> searchResults) {
    List<WebElement> availableResults = new ArrayList<>();
    for (WebElement e : searchResults) {
      if (!this.isElementPresent(e, By.cssSelector("div[class^=sold_out_property]"))) {
        availableResults.add(e);
      }
    }
    return availableResults;
  }
}
