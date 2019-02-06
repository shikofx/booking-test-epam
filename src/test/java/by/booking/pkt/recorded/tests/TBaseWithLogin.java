package by.booking.pkt.recorded.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TBaseWithLogin extends TBase {
  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    app.getHeaderHelper().hdr_login("pkt.autotests@gmail.com", "123456789");
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    app.getHeaderHelper().logOut();
  }

}
