package by.booking.pkt.recorded.tests;

import by.booking.pkt.recorded.Model.AccountData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TBaseWithLogin extends TBase {

 // protected ApplicationManager app = new ApplicationManager();

  @BeforeMethod(alwaysRun = true)
  public void startAccount() throws Exception {
    app.getAccountManager().login(new AccountData("pkt.autotests@gmail.com", "123456789"));
  }

  @AfterMethod(alwaysRun = true)
  public void stopAccount() throws Exception {
  }
}

