
package by.booking.pkt.application;

import by.booking.pkt.model.Hotel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;


public class ResultsPageHelper extends HelperBase {

   public static final String REGEX_PRICE = "[,.\\s\\d]+\\d";
   public static final String REGEX_PRICE_WITHOUT_STRONG = "\\d+\\w+\\d+.*\\n";
   public static final String REGEX_STARS_COUNT = "\\d";
   public static final String REGEX_DISTANCE_MEASURE = "\\s.*?\\s";
   public static final String REGEX_DISTANCE_VALUE = "[\\,\\d]+";

   public ResultsPageHelper(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
      super(webDriver, wait, implicitlyWait);
      PageFactory.initElements(webDriver, this);
   }

   @FindBy(css = "div.sr_separator")
   private WebElement separator;

   @FindBy(xpath = "//*/div[contains(@class,'sr_separator')]/preceding-sibling::div[contains(@class,'sr_item')]")
   private List<WebElement> allResultsBeforSeparator;

   @FindBy(css = "div.sr_item")
   private List<WebElement> allResults;

   @FindBy(css = "div.sr_item")
   private WebElement firstHotelItem;

   @FindBy(css = "#sortbar_dropdown_button")
   private WebElement additionalSortsButton;

   @FindBy(css = "#sortbar_dropdown_options")
   private WebElement additionalSortsPanel;

   @FindBy(css = "li.sort_distance_from_search")
   private WebElement initSortByDistance;

   @FindBy(css = "li.sort_price")
   private WebElement initSortByPrice;

//  public void goTo(int resultNumber) {
//    WebElement item = webDriver.findElement(By.cssSelector("#hotellist_inner>.sr_item:nth-of-type(" + resultNumber + ")"));
//    item.findElement(By.cssSelector(".sr-cta-button-row")).click();
//  }

   public ResultsPageHelper goToHotelPage(Hotel hotel) {
      hotelToElement(hotel).findElement(By.cssSelector(".sr_cta_button")).click();
      return this;
   }

   private WebElement hotelToElement(Hotel hotel) {
      By hotelBy = By.cssSelector("div.sr_item[data-hotelid='" + hotel.getId() + "']");
      return webDriver.findElement(hotelBy);
   }


   public List<WebElement> getAllResults() {
      if (isElementPresent(separator, 0)) {
         return allResultsBeforSeparator;
      }
      return allResults;
   }

   public Hotel getFirstHotel() {
      Hotel hotel = resultToHotel(firstHotelItem);
      return hotel;
   }

   public List<Hotel> availableHotels() {
      List<Hotel> hotels = new ArrayList<Hotel>();
      int i = 0;
      for (WebElement e : getOnlyAvailable()) {
         hotels.add(resultToHotel(e));
      }
      return hotels;
   }

   private Hotel resultToHotel(WebElement item) {
      Hotel hotel = new Hotel().
            withID(getID(item)).
            withName(getHotelName(item)).
            withDistance(distanceToDest(item)).
            withStars(getStarsCount(item)).
            withTotalPrice(getTotalPrice(item));
      return hotel;
   }

   public String getID(WebElement item) {
      String id = item.getAttribute("data-hotelid");
      return id;

   }

   public double distanceToDest(WebElement item) {
      double distance = 0;
      By distanceBy = By.cssSelector("span.distfromdest");
      if (isElementPresentNoWait(item, distanceBy)) {
         distance = Double.parseDouble(textByPatternWithout(REGEX_DISTANCE_VALUE, "\\s",
               item.findElement(distanceBy).getText()).replace(',', '.'));
         String measure = textByPatternWithout(REGEX_DISTANCE_MEASURE, "\\s",
               item.findElement(distanceBy).getText());
         if (measure.length() > 1) {
            distance = 1000 * distance;
         }
      }
      return distance;
   }

   public int getStarsCount(WebElement item) {
      int actualStars = 0;
      By starsBy = By.cssSelector("svg[class*=stars]");
      if (this.isElementPresentNoWait(item, starsBy)) {
         actualStars = Integer.parseInt(textByPatternWithout(REGEX_STARS_COUNT, "\\s",
               item.findElement(starsBy).getAttribute("class")));
      }
      return actualStars;
   }

   public int getTotalPrice(WebElement item) {
      By priceInDivBy = By.cssSelector("div.totalPrice");
      By priceInStrongBy = By.cssSelector("strong.price");
      if (isElementPresentNoWait(item, priceInStrongBy)) {
         return Integer.parseInt(textByPatternWithout(REGEX_PRICE,
               "[\\D]", item.findElement(priceInStrongBy).getText()));
      } else if (isElementPresentNoWait(item, priceInDivBy)) {
         return Integer.parseInt(textByPatternWithout(REGEX_PRICE_WITHOUT_STRONG,
               "[\\D]", item.findElement(priceInDivBy).getAttribute("outerText")));
      }
      return 0;
   }


   public String getHotelName(WebElement item) {
      By hotelNameBy = By.cssSelector("span.sr-hotel__name");
      String name = item.findElement(hotelNameBy).getText();
      return (name);
   }

   public List<WebElement> getOnlyAvailable() {
      List<WebElement> allResults = getAllResults();
      List<WebElement> availableResults = new ArrayList<WebElement>();
      for (WebElement e : allResults) {
         if (!e.getAttribute("className").contains("soldout_property")) {
            availableResults.add(e);
         }
      }
      return availableResults;
   }

   public ResultsPageHelper initSortByPrice() {
      initSortByPrice.click();
      refreshPage();
      return this;
   }


   public ResultsPageHelper initSortByDistance() {
      displayDropDown(additionalSortsButton, additionalSortsPanel, 5);
      initSortByDistance.findElement(By.cssSelector("a")).click();
      refreshPage();
      return this;
   }

}
