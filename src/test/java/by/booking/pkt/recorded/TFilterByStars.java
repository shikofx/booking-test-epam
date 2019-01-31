package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TFilterByStars extends TBase{
  @Test
  public void testFilterByStars() {
    int stars = 3;
    SoftAssert softAssert = new SoftAssert();
    goToMainSearchPage();
 //   logIn();
    selectCurrency();
    enterAccomodaition("Черногория");
    selectDates("2019-03-24", "2019-04-03");
    selectRoomsCount(5);
    selectAdultsCount(2);
    selectChildrenCount(4);
    submitMainSearch();
    webDriver.findElement(By.cssSelector("[data-name=oos] div")).click();
    wait.until(visibilityOfElementLocated(By.cssSelector("#bodyconstraint-inner")));

    webDriver.findElement(By.cssSelector("a[data-id^=class][data-value='"+stars+"']")).click();

    List<WebElement> itemsOnPage = webDriver.findElements(By.cssSelector("#hotellist_inner>div.sr_item"));
    System.out.println("Количество записей на странице: " + itemsOnPage.size());
    //Выделить в записях название отелей и стоимость
    //проверить возрастают ли они попорядку
    //стоимость первого элемента должна быть ниже остальных

  }
}
