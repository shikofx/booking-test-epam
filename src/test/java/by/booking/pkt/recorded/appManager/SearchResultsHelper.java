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

  public void goToHotelPage(int itemNumber) {
    WebElement item = getElement(By.cssSelector("#hotellist_inner>.sr_item:nth-of-type(" + itemNumber + ")"));
    clickOn(item, By.cssSelector(".sr-cta-button-row"));
  }

  public List<WebElement> getAllResults() {
    List<WebElement> searchResultItems;
    //Найти все отели до записи: есть еще отели вне определенной локации
    if (isElementPresent(By.cssSelector("div.sr_separator"))) {
      searchResultItems = getElements(By.xpath("//*/div[contains(@class,'sr_separator')]/preceding-sibling::div[contains(@class,'sr_item')]"));
    } else {
      searchResultItems = getElements(By.cssSelector("div.sr_item"));
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
    if (isElementPresentNoWait(item, By.cssSelector("svg[class*=stars]"), 30)) {
      actualStars = Integer.parseInt(textByPatternNoSpace("\\d", item.findElement(By.cssSelector("svg[class*=stars]")).getAttribute("class")));
    } else {
      actualStars = 0;
    }
    return actualStars;
  }

  public void initSortByDistance() {
    By sortByDistance = By.cssSelector("li.sort_distance_from_search a");
    showDropdown(By.cssSelector("#sortbar_dropdown_button"), By.cssSelector("#sortbar_dropdown_options"));
    wait.until(visibilityOfElementLocated(sortByDistance));
    clickOn(sortByDistance);
  }

  public int totalPrice(WebElement item) {
    int totalPrice = 0;
    if (isElementPresentNoWait(item, By.cssSelector("div.totalPrice"), 30)) {
      totalPrice = Integer.parseInt(textByPatternNoSpace("(?<=:).+\\d", item.findElement(By.cssSelector("div.totalPrice")).getText()));
    } else {
      totalPrice = Integer.parseInt(textByPatternNoSpace("[\\d\\s]+\\d", item.findElement(By.cssSelector("strong.price")).getText()));
    }
    return totalPrice;
  }

  public String getHotelName(WebElement item) {
    wait.until(presenceOfNestedElementLocatedBy(item, By.cssSelector("span.sr-hotel__name")));
    return (item.findElement(By.cssSelector("span.sr-hotel__name")).getText());
  }

}
