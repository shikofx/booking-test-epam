package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class TSortByPrice extends TBase{
  @Test
  public void sortByPrice() {
    SoftAssert softAssert = new SoftAssert();
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
    sortByPriceSelect();
    for(WebElement item: getSearchResults()){
      wait.until(presenceOfElementLocated(By.cssSelector("div.totalPrice")));
      System.out.println(item.findElement(By.cssSelector("span.sr-hotel__name")).getText());
      //Если запись расположена не в порядке возрастания стоимости - это неверно
      System.out.println(Integer.parseInt(getTextByPatternNoSpace("(?<=:).+\\d", item.findElement(By.cssSelector("div.totalPrice")).getText())));
    }
  }
}
