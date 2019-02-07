package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class WindowManager extends ManagerBase {


  public WindowManager(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public void goToUrl(String url) {
    webDriver.get(url);
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

  public void switchToWindow(String hotelWindow) {
    webDriver.switchTo().window(hotelWindow);
  }

  public void closeCurrentWindows() {
    webDriver.close();
  }

  public Set<String> windowHandles() {
    return webDriver.getWindowHandles();
  }

  public String currentWindowHadle() {
    return webDriver.getWindowHandle();
  }

  public String getCurrentUrlHead() {
    return getTextByPattern(webDriver.getCurrentUrl(), ".+/[A-Za-z0-9_-]*");
  }


}
