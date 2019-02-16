package by.booking.pkt.application.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class WindowHelper extends HelperBase {


  public WindowHelper(WebDriver webDriver, WebDriverWait wait, int implicitlyWait) {
    super(webDriver, wait, implicitlyWait);
  }

  public void goTo(String url) {
    webDriver.get(url);
  }

  public String getNew(Set<String> oldWindowSet) {
    Set<String> newWindowSet = webDriver.getWindowHandles();
    String newWindow = null;
    if (newWindowSet.removeAll(oldWindowSet) && newWindowSet.size() == 1) {
      newWindow = newWindowSet.iterator().next();
    }
    return newWindow;
  }


  public void maximazeWindow() {
    webDriver.manage().window().maximize();
  }

  public void switchTo(String hotelWindow) {
    webDriver.switchTo().window(hotelWindow);
  }

  public void close() {
    webDriver.close();
  }

  public Set<String> all() {
    return webDriver.getWindowHandles();
  }

  public String handle() {
    return webDriver.getWindowHandle();
  }

  public void back() {
    String currentUrl = webDriver.getCurrentUrl();
    webDriver.navigate().back();
  }

  public void refreshPage() {
    webDriver.navigate().refresh();
  }
}
