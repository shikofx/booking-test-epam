package by.booking.pkt.recorded.appManager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WishlistHelper extends HelperBase{

  public WishlistHelper(WebDriver webDriver, WebDriverWait wait) {
    super(webDriver, wait);
  }

  public void newList(String listName) {
    wait.until(visibilityOfElementLocated(By.cssSelector("button[class*=js-listview-create-list] span")));
    webDriver.findElement(By.cssSelector("button[class*=js-listview-create-list] span")).click();
    Alert alertCreateList = wait.until(alertIsPresent());
    alertCreateList.sendKeys(listName);
    alertCreateList.accept();
    webDriver.navigate().refresh();
  }

  public int getCount() {
    wait.until(presenceOfElementLocated(By.cssSelector(".js-listview-header-dropdown")));
    webDriver.findElement(By.cssSelector(".js-listview-header-dropdown")).click();
    wait.until(presenceOfElementLocated(By.cssSelector(".listview__lists")));
    int count = webDriver.findElements(By.cssSelector(".listview__lists div")).size();
    webDriver.findElement(By.cssSelector(".js-listview-header-dropdown")).click();
    return count;
  }

  public String urlToSend() {
    app_activateDropdownMenu(By.cssSelector("div[class=bui-dropdown]+button span"), By.cssSelector("div.listview-share"));
    wait.until(visibilityOfElementLocated(By.cssSelector("p[class*=content] input")));
    String url = webDriver.findElement(By.cssSelector("p[class*=content] input")).getAttribute("defaultValue");
    return url;
  }

  public WebElement defaultList() {
    return webDriver.findElement(By.cssSelector("div[class*=bui-dropdown] span"));
  }

  public String wl_getHeader() {
    return app_getTextWithWait(By.cssSelector("div.wl-bui-header h1"));
  }
}
