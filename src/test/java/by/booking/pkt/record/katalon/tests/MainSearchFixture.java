package by.booking.pkt.record.katalon.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class MainSearchFixture extends TBase {

  @BeforeClass(alwaysRun = true)
  public void setUp() {
    appManager.goToSearchPage();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() {
  }
}
