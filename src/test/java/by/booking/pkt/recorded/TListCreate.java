package by.booking.pkt.recorded;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.textMatches;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class TListCreate extends TBase {

  @Test(enabled = true)
  public void testListCreate() {
    goToMainSearchPage();
    logIn();
    goToWishlists();
    createNewWishlist();
    String s=webDriver.findElement(By.cssSelector("span[class^=bui-button]")).getText();
    assertion.assertEquals(s, "Go to British", "Новая группа не создана");

    //logOut();
  }
  public void createNewWishlist(){
    System.out.println("СОЗДАТЬ СПИСОК");
    wait.until(visibilityOfElementLocated(By.cssSelector("div[class^=listview] button[class^=bui-button] span")));
    System.out.println("СОЗДАТЬ СПИСОК");
    webDriver.findElement(By.cssSelector("button[class*=js-listview-create-list] span")).click();
    Alert alertCreateList = wait.until(alertIsPresent());
    alertCreateList.sendKeys("Go to British");
    alertCreateList.accept();
    //!!!Список должен увеличиться на 1. Должна быть выбрана созданная группа
    wait.until(textMatches(By.cssSelector("div[class*=bui-dropdown] span"), Pattern.compile("Go to British")));

  }
}
