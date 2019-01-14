package by.booking.pkt.record.katalon.tests;

import by.booking.pkt.record.katalon.appmanager.AppManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.fail;

public class TBase {

  protected final AppManager appManager = new AppManager();

  @BeforeClass(alwaysRun = true)
  public void startBrowser() throws Exception {
    appManager.init();
  }

  @AfterClass(alwaysRun = true)
  public void stopBrowser() throws Exception {
    appManager.stop();
  }

}
