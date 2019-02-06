package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class WindowNavigator extends HelperBase {


  public WindowNavigator(WebDriver webDriver, WebDriverWait wait) {
    super(webDriver, wait);
  }

  public String app_switchToNewWindow(Set<String> oldWindowSet) {
    Set<String> newWindowSet = webDriver.getWindowHandles();
    String newWindow = null;
    if (newWindowSet.removeAll(oldWindowSet) && newWindowSet.size() == 1) {
      newWindow = newWindowSet.iterator().next();
      webDriver.switchTo().window(newWindow);
    }
    return newWindow;
  }

  public void maximazeWindow() {
    webDriver.manage().window().maximize();
  }

  private void switchToWindow(String hotelWindow) {
    webDriver.switchTo().window(hotelWindow);
  }

  private void closeCurrentWindows() {
    webDriver.close();
  }

  private Set<String> windowHandles() {
    return webDriver.getWindowHandles();
  }

  private String currentWindowHadle() {
    return webDriver.getWindowHandle();
  }
}
