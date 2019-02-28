package by.booking.pkt.tests;

import org.testng.annotations.BeforeClass;

public class TBaseWithLogin extends TBase {

   @BeforeClass
   public void loginUser(){
      app.login();
   }
}

