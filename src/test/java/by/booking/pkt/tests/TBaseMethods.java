package by.booking.pkt.tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TBaseMethods extends TBase {


  @BeforeMethod(alwaysRun = true)
  public void goToURL() throws Exception {
    app.windows().goTo("https://booking.com");
  }

  @AfterMethod(alwaysRun = true)
  public void screenShot(ITestResult result) {
    if (!result.isSuccess()) {
      System.out.println("Screenshot after failure of: " + result.getName() + " in test " + result.getTestName());
    }
  }
}

