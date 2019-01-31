package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TFilterByBudget extends TBase{
  @Test
  public void testFilterByBudget() {

    double budget = 13000;
    SoftAssert softAssert = new SoftAssert();
    goToMainSearchPage();
 //   logIn();
    selectCurrency();
    enterAccomodaition("нью-йорк");
    selectDates("2019-03-24", "2019-04-03");
    selectRoomsCount(5);
    selectAdultsCount(2);
    selectChildrenCount(4);
    submitMainSearch();
    webDriver.findElement(By.cssSelector("[data-name=oos] div")).click();
    wait.until(visibilityOfElementLocated(By.cssSelector("#bodyconstraint-inner")));

    List<WebElement> filterItems = webDriver.findElements(By.cssSelector("a[data-id^=pri]"));
    WebElement filterElement = null;
    for(int i = 0; i<filterItems.size(); i++){
      filterElement = filterItems.get(i);
      if (budget < Integer.parseInt(filterElement.getAttribute("data-value"))) {
        break;
      }
    }
    System.out.println(Integer.parseInt(filterElement.getAttribute("data-count")));
    filterElement.click();
    Assertion assertion = new Assertion();
    assertion.assertTrue(Integer.parseInt(filterElement.getAttribute("data-count"))>=3, "Count of items less than 3");

    List<WebElement> accomodationItems = webDriver.findElements(By.cssSelector("#hotellist_inner>div.sr_item"));
    System.out.println("Количество записей на странице: " + accomodationItems.size());
    for(WebElement item:accomodationItems){
      System.out.println(item.findElement(By.cssSelector("div.totalPrice")).getText());
    }
  }
}
