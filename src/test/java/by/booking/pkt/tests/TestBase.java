package by.booking.pkt.tests;

import by.booking.pkt.application.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

   protected static final ApplicationManager app =
           new ApplicationManager();

   public static Logger logger = LoggerFactory.getLogger(TestBase.class);

   @BeforeSuite(alwaysRun = true)
   public void setUp() throws Exception {
      app.init(10);

   }

   @AfterSuite(alwaysRun = true)
   public void tearDown() {
      app.deinit();
   }

   @BeforeMethod(alwaysRun = true)
   public void startTest(Method m, Object[] p) {
      logger.info("START:     test " + m.getName()
              + " with parameters {" + Arrays.asList(p) + '}'
              + " from " + m.getDeclaringClass());
      app.pageNavigation().goTo(app.appProperties().getBaseUrl());
   }

   @AfterMethod(enabled = true)
   public void finishTest(ITestResult result, Method m) {
      app.account().logout();
      app.pageNavigation().clearBrowserData();
      if (!result.isSuccess()) {
         logger.info("FAILED:    test " + m.getName()
                 + " from " + m.getDeclaringClass() + '\n');
      } else {
         logger.info("SUCCESS:   test " + m.getName()
                 + " from " + m.getDeclaringClass() + '\n');
      }
   }
}

