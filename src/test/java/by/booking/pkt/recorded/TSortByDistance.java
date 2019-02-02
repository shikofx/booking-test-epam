package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TSortByDistance extends TBase{
  @Test
  public void sortByDistance() {
    Assertion assertion = new Assertion();
    goToMainSearchPage();
 //   logIn();
    selectCurrency();
    enterAccomodaition("Минск");
    selectDates("2019-03-24", "2019-04-03");
    selectRoomsCount(1);
    selectAdultsCount(2);
    selectChildrenCount(0);
    submitMainSearch();
    onlyAvailableSelect();

    By sortByDistance = By.cssSelector("li.sort_distance_from_search a");


      activateDropdownMenu(By.cssSelector("#sortbar_dropdown_button"), sortByDistance);
    wait.until(visibilityOfElementLocated(sortByDistance));
    webDriver.findElement(sortByDistance).click();


    WebElement currentElement = null;
    WebElement previousElement = null;
    List<WebElement> seachResults = getSearchResults();
    for(int i=1; i<seachResults.size();i++){
      currentElement = seachResults.get(i);
      previousElement = seachResults.get(i-1);
      assertion.assertTrue(getDistance(currentElement)>=getDistance(previousElement), "Дистанция до текущего элемента менше чем до предыдущего");
      System.out.println("current: "+getHotelName(currentElement)+"  "+getDistance(currentElement));
      System.out.println("previous: "+ getHotelName(currentElement)+"  "+getDistance(currentElement));
    }
  }

  private int getDistance(WebElement item) {
    int distance=0;
    distance = Integer.parseInt(getTextByPatternNoSpace("\\d+", item.findElement(By.cssSelector("span.distfromdest")).getText()));
    String meterField = getTextByPatternNoSpace("\\s.*?\\s", item.findElement(By.cssSelector("span.distfromdest")).getText());
    if(meterField.length()>1) {
      distance = 1000 * distance;
    }
    return distance;
  }
}
