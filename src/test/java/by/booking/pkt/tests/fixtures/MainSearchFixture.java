package by.booking.pkt.tests.fixtures;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class MainSearchFixture extends TestBase {

//  @BeforeClass
//  public void startATF() {
//    webDriver.get("https://www.booking.com");
//    System.out.println("startATF");
//  }

//  @AfterClass
//  public void stopATF() {
//    System.out.println("stopATF");
//  }

  @BeforeMethod(inheritGroups = true, groups = "mainSearch")
  public void startATM() {
    //app.webDriver.get("https://www.booking.com");
    System.out.println("startATM");
  }

  @AfterMethod
  public void stopATM(ITestResult result) {
    if (!result.isSuccess()) {
      System.out.println("Screenshot after failure of: " + result.getName() + " in test " + result.getTestName());
    }
    System.out.println("stopATM");
  }
}
