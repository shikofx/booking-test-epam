
package by.booking.pkt.application.web;

import by.booking.pkt.model.Hotel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

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


  public List<WebElement> getAllResults() {
    List<WebElement> searchResult;
    //Найти все отели до записи: "Есть еще отели вне заданной местности"
    if (isElementPresent(By.cssSelector("div.sr_separator"), 0)) {
      searchResult = webDriver.findElements(By.xpath("//*/div[contains(@class,'sr_separator')]/preceding-sibling::div[contains(@class,'sr_item')]"));
    } else {
      searchResult = webDriver.findElements(By.cssSelector("div.sr_item"));
    }
    return searchResult;
  }

  public List<Hotel> availableHotels() {
    List<Hotel> hotels = new ArrayList<Hotel>();
    List<WebElement> availableList=getOnlyAvailable();
    for (WebElement e : availableList) {
      hotels.add(resultToHotel(e));
    }
    return hotels;
  }

  private Hotel resultToHotel(WebElement e) {
    Hotel hotel = new Hotel().
            withID(getID(e)).
            withName(getHotelName(e)).
            withDistance(distanceToDest(e)).
            withStars(getStarsCount(e)).
            withTotalPrice(totalPrice(e));
    return hotel;
  }

  public String getID(WebElement item) {
    return item.getAttribute("data-hotelid");
  }

  public double distanceToDest(WebElement item) {
    double distance = 0;
    String s = textByPatternNoSpace("[\\,\\d]+", item.findElement(By.cssSelector("span.distfromdest")).getText());
    distance =Double.parseDouble(textByPatternNoSpace("[\\,\\d]+", item.findElement(By.cssSelector("span.distfromdest")).getText()).replace(',', '.'));
    String meterField = textByPatternNoSpace("\\s.*?\\s", item.findElement(By.cssSelector("span.distfromdest")).getText());
    if (meterField.length() > 1) {
      distance = 1000 * distance;
    }
    return distance;
  }

  public int getStarsCount(WebElement item) {
    int actualStars;
    if (this.isElementPresent(item, By.cssSelector("svg[class*=stars]"),0)) {
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
    List<WebElement> allResults = getAllResults();
    List<WebElement> availableResults = new ArrayList<WebElement>();
    for (WebElement e : allResults) {
      if (!this.getAttribute(e, "className").contains("soldout_property")) {
        availableResults.add(e);
      }
    }
    return availableResults;
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
