package by.booking.pkt.tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TBaseMethods extends TBase {

  @BeforeMethod(alwaysRun = true)
  public void goToBooking() throws Exception {
    ;
  }

  @AfterMethod(alwaysRun = true)
  public void clearCookies(ITestResult result) {
    app.clear();
//    if (!result.isSuccess()) {
//      System.out.println("Screenshot after failure of: " + result.getName() + " in test " + result.getTestName());
//    }
  }
}

