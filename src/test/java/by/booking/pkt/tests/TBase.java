package by.booking.pkt.tests;

import by.booking.pkt.application.ApplicationManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TBase {

  protected static final ApplicationManager app = new ApplicationManager();

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
  //  app.init(30);

  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    //app.stop();
  }

  @BeforeMethod(alwaysRun = true)
  public void goToBooking() throws Exception {
    //app.windows().goTo("https://booking.com");
  }

  @AfterMethod(alwaysRun = true)
  public void clearCookies(ITestResult result) {
   .. app.clear();
//    if (!result.isSuccess()) {
//      System.out.println("Screenshot after failure of: " + result.getName() + " in test " + result.getTestName());
//    }
  }
}

