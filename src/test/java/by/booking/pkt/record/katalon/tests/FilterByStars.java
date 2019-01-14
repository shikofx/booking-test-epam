package by.booking.pkt.record.katalon.tests;

import by.booking.pkt.record.katalon.tests.MainSearchFixture;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class FilterByStars extends MainSearchFixture {
  @Test
  public void testFilterByStars() throws Exception {
    appManager.fillCityForm("Минск");
    //клик в поле повыше, чтобы закрыть выпадающий список поиска
    AppManager.driver.findElement(By.cssSelector("label[for=ss")).click();
    appManager.selectAdultsCount("2 взрослых");
    appManager.selectChildrenCount("3 детей");
    appManager.selectRoomsCount("3 номера");
    appManager.selectStarsCount();

  }

}
