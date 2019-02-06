package by.booking.pkt.tests.fixtures;

import by.booking.pkt.application.ApplicationManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

//@Listeners(ScreenVideoListener.class)
public class TestBase {

  protected final ApplicationManager app = new ApplicationManager();

  @BeforeClass(alwaysRun = true)
  public void startBrowser() {
    app.init();
  }

  @AfterClass(alwaysRun = true)
  public void stopBrowser(ITestResult result) {
    app.stop();

  }

}
