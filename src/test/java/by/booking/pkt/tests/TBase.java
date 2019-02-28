package by.booking.pkt.tests;

import by.booking.pkt.application.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TBase {

   protected static final ApplicationManager app =
           new ApplicationManager();

   public static Logger logger = LoggerFactory.getLogger(TBase.class);

   @BeforeSuite(alwaysRun = true)
   public void setUp() throws Exception {
      app.init(30);

   }

   @AfterSuite(alwaysRun = true)
   public void tearDown() throws Exception {
      app.stop();
   }

   @BeforeClass(enabled = true)
   public void goToMainPage() {
      app.toBaseUrl();
   }

   @BeforeMethod(alwaysRun = true)
   public void startLogging(Method m, Object[] p) {
      logger.info("START:     test " + m.getName()
              + " with parameters {" + Arrays.asList(p) + '}'
              + " from " + m.getDeclaringClass());
   }

   @AfterMethod(alwaysRun = true)
   public void stopLogging(ITestResult result, Method m) {
      if (!result.isSuccess()) {
         logger.info("FAILED:    test " + m.getName()
                 + " from " + m.getDeclaringClass()+'\n');
      } else {
         logger.info("SUCCESS:   test " + m.getName()
                 + " from " + m.getDeclaringClass()+'\n');
      }
   }

//   @AfterMethod(alwaysRun = true)
//   public void clearCookies(ITestResult result) {
//      app.clear();
////
//   }


//   @BeforeMethod(alwaysRun = true)
//   public void goToMainPage() throws Exception {
//   }

   @AfterMethod(alwaysRun = true)
   public void clearCookies(ITestResult result) {
      app.clear();
//    if (!result.isSuccess()) {
//      System.out.println("Screenshot after failure of: " + result.getName() + " in test " + result.getTestName());
//    }
   }
}

