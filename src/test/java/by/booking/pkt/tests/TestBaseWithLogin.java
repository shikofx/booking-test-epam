package by.booking.pkt.tests;

import org.testng.annotations.BeforeMethod;

public class TestBaseWithLogin extends TestBase {

   @BeforeMethod(alwaysRun = true)
   public void loginUser(){
      app.account().loginAs(
              app.appProperties().getUsername(),
              app.appProperties().getPassword());
   }
}

