package by.booking.pkt.record.katalon.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SearchFixture extends TBase {

  @BeforeMethod(alwaysRun = true)
  public void setUp() {
    appManager.goToSeachResultPage();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
  }
}
