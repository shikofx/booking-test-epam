package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static by.booking.pkt.utils.StringParser.getNightsCount;
import static by.booking.pkt.utils.StringParser.getTotalPrice;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TFilterByBudget extends TBase{
  @Test
  public void testFilterByBudget() {

    double budget = 13000;
    SoftAssert softAssert = new SoftAssert();
    goToMainSearchPage();
 //   logIn();
    selectCurrency();
    enterAccomodaition("Минск");
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
    int itemsCountInFilterBlock = Integer.parseInt(filterElement.getAttribute("data-count"));
    filterElement.click();
    Assertion assertion = new Assertion();
    assertion.assertTrue(Integer.parseInt(filterElement.getAttribute("data-count"))>=3, "Count of items less than 3");
    wait.until(visibilityOfElementLocated(By.cssSelector("#bodyconstraint-inner")));
    //Найти все отели до записи: есть еще отели вне определенной локации
    List<WebElement> searchResultItems = new ArrayList<>();
    if(isElementPresent(By.cssSelector("div.sr_separator"))) {
      searchResultItems = webDriver.findElements(By.xpath("//*/div[contains(@class,'sr_separator')]/preceding-sibling::div[contains(@class,'sr_item')]"));
    } else {
      searchResultItems = webDriver.findElements(By.cssSelector("div.sr_item"));
    }
    System.out.println(searchResultItems.size());
    int nigtsCount = Integer.parseInt(getNightsCount(webDriver.findElement(By.cssSelector("div.sb-dates__los")).getText()));
    for(WebElement item:searchResultItems){
      wait.until(presenceOfElementLocated(By.cssSelector("div.totalPrice")));
      System.out.println(item.findElement(By.cssSelector("span.sr-hotel__name")).getText());
      System.out.println(Integer.parseInt(getTotalPrice(item.findElement(By.cssSelector("div.totalPrice")).getText())));
      if(isElementPresent(By.cssSelector("svg[className*=stars]"))) {
        System.out.println("Количество звезд: "+item.findElement(By.cssSelector("svg[className*=stars]")).getAttribute("className"));
      }else {
        System.out.println("Количество звезд: "+0);
      }
    }
  }
}
