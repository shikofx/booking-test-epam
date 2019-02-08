package by.booking.pkt.recorded.tests;

import by.booking.pkt.recorded.appManager.ApplicationManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TBaseWithLogin extends TBase {

 // protected ApplicationManager app = new ApplicationManager();

  @BeforeMethod(alwaysRun = true)
  public void startAccount() throws Exception {
   // app.init(10);
    app.getAccountManager().login("pkt.autotests@gmail.com", "123456789");
  }

  @AfterMethod(alwaysRun = true)
  public void stopAccount() throws Exception {
   // app.stop();
  }
}

