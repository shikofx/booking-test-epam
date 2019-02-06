package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfNestedElementLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class SearchResultsHelper extends HelperBase {
  public SearchResultsHelper(WebDriver webDriver, WebDriverWait wait) {
    super(webDriver, wait);
  }

  public void sr_goToSearchResult(int itemNumber) {
    WebElement item = webDriver.findElement(By.cssSelector("#hotellist_inner>.sr_item:nth-of-type(" + itemNumber + ")"));
    item.findElement(By.cssSelector(".sr-cta-button-row")).click();
  }

  public int sr_totalPrice(WebElement item) {
    wait.until(presenceOfNestedElementLocatedBy(item, By.cssSelector("div.totalPrice")));
    return Integer.parseInt(app_getTextByPatternNoSpace("(?<=:).+\\d", item.findElement(By.cssSelector("div.totalPrice")).getText()));
  }

  public List<WebElement> sr_getAll() {
    List<WebElement> searchResultItems;
    //Найти все отели до записи: есть еще отели вне определенной локации
    if (app_isElementPresent(By.cssSelector("div.sr_separator"))) {
      searchResultItems = webDriver.findElements(By.xpath("//*/div[contains(@class,'sr_separator')]/preceding-sibling::div[contains(@class,'sr_item')]"));
    } else {
      searchResultItems = webDriver.findElements(By.cssSelector("div.sr_item"));
    }
    return searchResultItems;
  }

  public void sr_initSortByPrice() {
    wait.until(visibilityOfElementLocated(By.cssSelector("li.sort_price")));
    webDriver.findElement(By.cssSelector("li.sort_price")).click();
  }

  public int hotel_distanceToDest(WebElement item) {
    int distance = 0;
    distance = Integer.parseInt(app_getTextByPatternNoSpace("[\\,\\d]+", item.findElement(By.cssSelector("span.distfromdest")).getText().replace(',', '.')));
    String meterField = app_getTextByPatternNoSpace("\\s.*?\\s", item.findElement(By.cssSelector("span.distfromdest")).getText());
    if (meterField.length() > 1) {
      distance = 1000 * distance;
    }
    return distance;
  }

  public int hotel_getStarsCount(WebElement item) {
    int actualStars;
    if (app_isElementInPresentNoWait(item, By.cssSelector("svg[class*=stars]"), 30)) {
      actualStars = Integer.parseInt(app_getTextByPatternNoSpace("\\d", item.findElement(By.cssSelector("svg[class*=stars]")).getAttribute("class")));
    } else {
      actualStars = 0;
    }
    return actualStars;
  }

  public String sr_getUrlHead() {
    return app_getTextByPattern(webDriver.getCurrentUrl(), ".+/[A-Za-z0-9_-]*");
  }

  public void sr_initSortByDistance(By sortByDistance) {
    wait.until(visibilityOfElementLocated(sortByDistance));
    webDriver.findElement(sortByDistance).click();
  }

  public int hotel_totalPrice(WebElement item) {
    int totalPrice = 0;
    if (app_isElementInPresentNoWait(item, By.cssSelector("div.totalPrice"), 30)) {
      totalPrice = Integer.parseInt(app_getTextByPatternNoSpace("(?<=:).+\\d", item.findElement(By.cssSelector("div.totalPrice")).getText()));
    } else {
      totalPrice = Integer.parseInt(app_getTextByPatternNoSpace("[\\d\\s]+\\d", item.findElement(By.cssSelector("strong.price")).getText()));
    }
    return totalPrice;
  }

  public String hotel_getHotelName(WebElement item) {
    wait.until(presenceOfNestedElementLocatedBy(item, By.cssSelector("span.sr-hotel__name")));
    return (item.findElement(By.cssSelector("span.sr-hotel__name")).getText());
  }

}
