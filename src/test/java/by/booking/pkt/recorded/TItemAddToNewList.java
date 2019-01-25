package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TItemAddToNewList extends TBase{

  @Test
  public void testItemAddToNewList(){
    goToMainSearchPage();
    logIn();
    selectCurrency();
    enterAccomodaition();
    selectDates();
//    selectRoomsCount();
//    selectAdultsCount();
//    selectChildrenCount();
//    submitMainSearch();
  }

  private void submitMainSearch() {
    webDriver.findElement(By.cssSelector("button[data-sb-id=main][type=submit]")).click();
  }

  private void selectChildrenCount() {
    if(webDriver.findElement(By.cssSelector("labal#xp__guests__toggle div")).getAttribute("className").contains("hidden")){
      webDriver.findElement(By.cssSelector("labal#xp__guests__toggle")).click();
    }
  }

  private void selectAdultsCount() {
    if(webDriver.findElement(By.cssSelector("labal#xp__guests__toggle div")).getAttribute("className").contains("hidden")){
      webDriver.findElement(By.cssSelector("labal#xp__guests__toggle")).click();
    }
  }

  private void selectRoomsCount() {
    if(webDriver.findElement(By.cssSelector("labal#xp__guests__toggle div")).getAttribute("className").contains("hidden")){
      webDriver.findElement(By.cssSelector("labal#xp__guests__toggle")).click();
    }
  }

  private void selectDates() {

    webDriver.findElement(By.cssSelector("div.xp__dates.xp__group")).click();
    String firsDate = webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date^='20']")).getAttribute("data-date");
    System.out.println(firsDate);
    while (!firsDate.equals("2019-03-01")) {
      webDriver.findElement(By.cssSelector("div[data-bui-ref=calendar-next]")).click();
      firsDate = webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date^='20']")).getAttribute("data-date");
      System.out.println(firsDate);
    }
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='2019-03-25']")).click();
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='2019-04-20']")).click();
    webDriver.findElement(By.cssSelector("div.xp__dates.xp__group")).click();
  }

  private void enterAccomodaition() {
    webDriver.findElement(By.cssSelector("#ss")).click();
    webDriver.findElement(By.cssSelector("#ss")).clear();
    webDriver.findElement(By.cssSelector("#ss")).sendKeys("Черногория");
  }

  private void selectCurrency() {
    wait.until(stalenessOf(webDriver.findElement(By.cssSelector("form[target=log_tar]"))));
    wait.until(visibilityOfElementLocated(By.cssSelector("[data-id=currency_selector]")));
    webDriver.findElement(By.cssSelector("[data-id=currency_selector]")).click();
    wait.until(visibilityOfElementLocated(By.cssSelector("#current_currency_foldout")));
    webDriver.findElement(By.cssSelector("a[data-currency=RUB")).click();
  }
}
