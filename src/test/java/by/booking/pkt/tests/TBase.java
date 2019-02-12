package by.booking.pkt.tests;

import by.booking.pkt.application.ApplicationManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class TBase {

  protected ApplicationManager app = new ApplicationManager();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    app.init(10);

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
}

