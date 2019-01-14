package by.booking.pkt.application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class ApplicationManager {
  protected WebDriver driver;
  private WebDriverWait wait;
  private StringBuffer verificationErrors = new StringBuffer();

  public void init() {
    driver = WebDriverPool.DEFAULT.getDriver(DesiredCapabilities.chrome());
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    System.out.println("startBrowser");
  }

  public void stop() {
    WebDriverPool.DEFAULT.dismissAll();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
    driver = null;
    System.out.println("stopBrowser");
  }
}
