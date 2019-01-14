package by.booking.pkt.record.katalon.tests;

import by.booking.pkt.record.katalon.tests.SearchFixture;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class SortByPrice extends SearchFixture {
  @Test
  public void testSortByPrice() throws Exception {
    appManager.fillCityForm("Минск");
    //клик в поле повыше, чтобы закрыть выпадающий список поиска
    AppManager.driver.findElement(By.cssSelector("label[for=ss")).click();
    appManager.selectAdultsCount("2 взрослых");
    appManager.selectChildrenCount("3 детей");
    appManager.selectRoomsCount("3 номера");
    AppManager.driver.findElement(By.linkText("Цена (сначала самая низкая)")).click();
  }
}
