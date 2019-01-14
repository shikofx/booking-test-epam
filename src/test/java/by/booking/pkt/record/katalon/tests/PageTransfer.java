package by.booking.pkt.record.katalon.tests;

import by.booking.pkt.record.katalon.tests.MainSearchFixture;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class PageTransfer extends MainSearchFixture {
  @Test
  public void testPageTransfer() throws Exception {
    AppManager.driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Проживание'])[1]/following::span[1]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_1 | ]]
    AppManager.driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    AppManager.driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Авиабилеты'])[1]/following::span[1]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_2 | ]]
    AppManager.driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
    AppManager.driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Аренда машин'])[1]/following::span[1]")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_3 | ]]
    AppManager.driver.close();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | win_ser_local | ]]
  }
}
