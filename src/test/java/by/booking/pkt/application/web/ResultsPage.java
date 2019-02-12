
package by.booking.pkt.application.web;

import by.booking.pkt.model.Hotel;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class ResultsPage extends HelperBase {
  public ResultsPage(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public void goTo(int resultNumber) {
    WebElement item = webDriver.findElement(By.cssSelector("#hotellist_inner>.sr_item:nth-of-type(" + resultNumber + ")"));
    clickOn(item, By.cssSelector(".sr-cta-button-row"));
  }

  public void goTo(WebElement result) {
    clickOn(result, By.cssSelector(".sr-cta-button-row"));
  }


  public List<WebElement> getAll() {
    List<WebElement> searchResult;
    //Найти все отели до записи: есть еще отели вне определенной локации
    if (isElementPresent(By.cssSelector("div.sr_separator"), 5)) {
      searchResult = webDriver.findElements(By.xpath("//*/div[contains(@class,'sr_separator')]/preceding-sibling::div[contains(@class,'sr_item')]"));
    } else {
      searchResult = webDriver.findElements(By.cssSelector("div.sr_item"));
    }
    return searchResult;
  }

  public List<Hotel> availableHotels() {
    List<Hotel> hotels = new ArrayList<>();
    for (WebElement e : getOnlyAvailable()) {
      hotels.add(resultToHotel(e));
    }
    return hotels;
  }

  private Hotel resultToHotel(WebElement e) {
    return new Hotel().withID(getID(e)).
            withName(getHotelName(e)).
            withDistance(distanceToDest(e)).
            withStars(getStarsCount(e)).
            withTotalPrice(totalPrice(e));
  }

  public String getID(WebElement item) {
    return item.getAttribute("data-hotelid");
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

  public int totalPrice(WebElement item) {
    int totalPrice = 0;
    if (this.isElementPresent(item, By.cssSelector("div.totalPrice"), 0)) {
      totalPrice = Integer.parseInt(textByPatternNoSpace("(?<=:).+\\d", item.findElement(By.cssSelector("div.totalPrice")).getText()));
    } else if (this.isElementPresent(item, By.cssSelector("strong.price"), 0)) {
      totalPrice = Integer.parseInt(textByPatternNoSpace("[\\d\\s]+\\d", item.findElement(By.cssSelector("strong.price")).getText()));
    }
    return totalPrice;
  }

  public String getHotelName(WebElement hotel) {
    //wait.until(presenceOfNestedElementLocatedBy(hotel, By.cssSelector("span.sr-hotel__name")));
    return (hotel.findElement(By.cssSelector("span.sr-hotel__name")).getText());
  }

  public List<WebElement> getOnlyAvailable() {
    List<WebElement> availableResults = new ArrayList<>();
    for (WebElement e : getAll()) {
      if (!this.isElementPresent(e, By.cssSelector("div[class^=sold_out_property]"), 0)) {
        availableResults.add(e);
      }
    }
    return availableResults;
  }

  public List<WebElement> getAllBudgets() {
    return webDriver.findElements(By.cssSelector("a[data-id^=pri]"));
  }

  public void selectOnlyAvailable() {
    clickOn(By.cssSelector("[data-name=oos] div"));
    refreshDriver();
  }

  public int getNigtsCount() {
    wait.until(presenceOfElementLocated(By.cssSelector("div.sb-dates__los")));
    return Integer.parseInt(textByPatternNoSpace("\\d+", getText(By.cssSelector("div.sb-dates__los"))));
  }

  public boolean selectStarsCount(int stars) {
    WebElement filterElement = webDriver.findElement(By.cssSelector("a[data-id^=class][data-value='" + stars + "']"));
    if (!filterElement.findElement(By.cssSelector("input")).isSelected())
      clickOn(filterElement.findElement(By.cssSelector("div")));
    if (filterElement.findElement(By.cssSelector("input")).isSelected()) {
      return true;
    } else {
      return false;
    }
  }

  @Nullable
  public WebElement selectBudget(int budget) {
    List<WebElement> filterItems = getAllBudgets();
    WebElement filterElement = null;
    for (int i = 0; i < filterItems.size(); i++) {
      filterElement = filterItems.get(i);
      if (budget < Integer.parseInt(getAttribute(filterElement, "data-value"))) {
        break;
      }
    }
    if (!filterElement.findElement(By.cssSelector("input")).isSelected())
      filterElement.click();

    return filterElement;
  }

  public int getBudget(WebElement filterElement, int budget) {
    int totalBudget = 0;
    if (filterElement.findElement(By.cssSelector("input")).isSelected()) {
      totalBudget = getNigtsCount() * Integer.parseInt(getAttribute(filterElement, "data-value"));
      if (budget * getNigtsCount() > totalBudget)
        totalBudget = Integer.MAX_VALUE;
    }
    return totalBudget;
  }

  public void initSortByPrice() {
    wait.until(visibilityOfElementLocated(By.cssSelector("li.sort_price")));
    clickOn(By.cssSelector("li.sort_price"));
  }


  public void initSortByDistance() {
    By sortByDistance = By.cssSelector("li.sort_distance_from_search a");
    showDropdown(By.cssSelector("#sortbar_dropdown_button"), By.cssSelector("#sortbar_dropdown_options"), 5);
    wait.until(visibilityOfElementLocated(sortByDistance));
    clickOn(sortByDistance);
  }

}
