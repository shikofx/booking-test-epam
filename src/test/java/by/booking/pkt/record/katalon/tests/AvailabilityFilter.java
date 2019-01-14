package by.booking.pkt.record.katalon.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class AvailabilityFilter extends SearchFixture {

  @Test
  public void testAvailabilityFilter() {
    appManager.fillCityForm("Минск");
    //клик в поле повыше, чтобы закрыть выпадающий список поиска
    appManager.driver.findElement(By.cssSelector("label[for=ss")).click();
    appManager.selectAdultsCount("2 взрослых");
    appManager.selectChildrenCount("3 детей");
    appManager.selectRoomsCount("3 номера");
  }

}
