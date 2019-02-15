package by.booking.pkt.tests;

import by.booking.pkt.application.ApplicationManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TBase {

  protected ApplicationManager app = new ApplicationManager();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    app.init(30);

  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }

  @AfterMethod(alwaysRun = true)
  public void screenShot(ITestResult result) {
    if (!result.isSuccess()) {
      System.out.println("Screenshot after failure of: " + result.getName() + " in test " + result.getTestName());
    }
  }

  @BeforeMethod(alwaysRun = true)
  public void goToBooking() throws Exception {
    app.windows().goTo("https://booking.com");
  }

  @AfterMethod(alwaysRun = true)
  public void clearCookies(ITestResult result) {
    //app.clear();
//    if (!result.isSuccess()) {
//      System.out.println("Screenshot after failure of: " + result.getName() + " in test " + result.getTestName());
//    }
  }
}

