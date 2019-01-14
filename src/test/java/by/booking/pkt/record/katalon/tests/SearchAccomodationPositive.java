package by.booking.pkt.record.katalon.tests;

import by.booking.pkt.record.katalon.tests.MainSearchFixture;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class SearchAccomodationPositive extends MainSearchFixture {
  @Test
  public void testSearchAccomodationPositive(){
    appManager.fillCityForm("Минск");
    AppManager.driver.findElement(By.id("xp__guests__toggle")).click();
    appManager.setRoomsCount();
    appManager.setRoomsCount();
    appManager.setAdultsCount();
    appManager.setChildrenCount();
  }
}
