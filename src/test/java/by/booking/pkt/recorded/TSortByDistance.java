package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TSortByDistance extends TBase{
  @Test
  public void sortByDistance() {
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
    wait.until(elementToBeSelected(By.cssSelector("[data-name=oos] input")));

    By sortByDistance = By.cssSelector("li.sort_distance_from_search a");

    wait.until(presenceOfElementLocated(sortByDistance));
    while(!webDriver.findElement(sortByDistance).isDisplayed())
      activateDropdownMenu(By.cssSelector("#sortbar_dropdown_button"), sortByDistance);//By.cssSelector("#sortbar_dropdown_options"));
    webDriver.findElement(sortByDistance).click();

    List<WebElement> itemsOnPage = webDriver.findElements(By.cssSelector("#hotellist_inner>div.sr_item"));
    System.out.println("Количество записей на странице: " + itemsOnPage.size());
    //Выделить в записях название отелей и стоимость
    //проверить возрастают ли они попорядку
  }
}
