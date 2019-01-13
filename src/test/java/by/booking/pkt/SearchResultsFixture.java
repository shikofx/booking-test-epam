package by.booking.pkt;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class SearchResultsFixture extends TestBase {
  @BeforeClass
  public void startATF() {
    driver.get("https://www.booking.com/searchresults.ru.html");
  }

  @AfterClass
  public void stopATF() {
    System.out.println("stopATF");
  }

//  @BeforeMethod(inheritGroups = true, groups = "mainSearch")
//  public void startATM() {
//    driver.get("https://www.booking.com");
//    System.out.println("startATM");
//  }
//
//  @AfterMethod
//  public void stopATM(ITestResult result) {
//    if (!result.isSuccess()) {
//      System.out.println("Screenshot after failure of: " + result.getName() + " in test " + result.getTestName());
//    }
//    System.out.println("stopATM");
//  }
}
