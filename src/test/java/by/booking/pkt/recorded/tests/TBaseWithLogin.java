package by.booking.pkt.recorded.tests;

import by.booking.pkt.recorded.model.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TBaseWithLogin extends TBase {

 // protected ApplicationManager app = new ApplicationManager();

  @BeforeMethod(alwaysRun = true)
  public void startAccount() throws Exception {
    app.account().loginAs(new User("pkt.autotests@gmail.com", "123456789"));
  }

  @AfterMethod(alwaysRun = true)
  public void stopAccount() throws Exception {
  }
}

