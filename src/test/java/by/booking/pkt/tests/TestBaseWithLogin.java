package by.booking.pkt.tests;

import by.booking.pkt.application.ApplicationProperties;
import org.testng.annotations.BeforeMethod;

public class TestBaseWithLogin extends TestBase {

   @BeforeMethod(alwaysRun = true)
   public void loginUser(){
      app.account().loginAs(
              ApplicationProperties.getUsername(),
              ApplicationProperties.getPassword());
   }
}

