package by.booking.pkt.recorded.tests;

import by.booking.pkt.recorded.appManager.ApplicationManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TBaseWithLogin extends TBase {

  protected ApplicationManager app = new ApplicationManager();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    app.init(10);
    app.getAccountManager().login("pkt.autotests@gmail.com", "123456789");
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();
  }
}

