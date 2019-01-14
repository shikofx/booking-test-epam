package by.booking.pkt.record.katalon.tests;

import by.booking.pkt.record.katalon.tests.MainSearchFixture;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class PropertiesFoundCount extends MainSearchFixture {

  @Test
  public void testPropertiesFoundCount() throws Exception {
    appManager.fillCityForm("Минск");
    //клик в поле повыше, чтобы закрыть выпадающий список поиска
    AppManager.driver.findElement(By.cssSelector("label[for=ss")).click();
    appManager.selectAdultsCount("2 взрослых");
    appManager.selectChildrenCount("3 детей");
    appManager.selectRoomsCount("3 номера");
    AppManager.driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='вс, 27 янв. 2019'])[2]/following::span[2]")).click();
    AppManager.driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Свернуть'])[1]/following::h1[1]")).click();
    AppManager.driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Показать только доступные'])[1]/following::a[7]")).click();
    AppManager.driver.findElement(By.linkText("5")).click();
    AppManager.driver.findElement(By.cssSelector("svg.bk-icon.-iconset-navarrow_right.bui-pagination__icon > path")).click();
    AppManager.driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Результаты поиска по направлению Минск'])[1]/following::div[2]")).click();
  }
}
