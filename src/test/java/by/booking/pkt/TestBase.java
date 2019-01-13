package by.booking.pkt;

import by.booking.pkt.utils.ScreenVideoListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import ru.stqa.selenium.factory.WebDriverPool;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

//@Listeners(ScreenVideoListener.class)
public class TestBase {

  protected WebDriver driver;
  private WebDriverWait wait;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void startBrowser() {
    driver = WebDriverPool.DEFAULT.getDriver(DesiredCapabilities.chrome());
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    System.out.println("startBrowser");
  }

  @AfterClass(alwaysRun = true)
  public void stopBrowser() {
    WebDriverPool.DEFAULT.dismissAll();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
    driver = null;
    System.out.println("stopBrowser");
  }
}
