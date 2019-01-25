package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class TItemAddToNewList extends TBase{

  @Test
  public void testItemAddToNewList(){
    goToMainSearchPage();
    logIn();
    selectCurrency();
  }

  private void selectCurrency() {
    webDriver.findElement(By.cssSelector("[data-id=currency_selector]")).click();
    wait.until(presenceOfElementLocated(By.cssSelector("#current_currency_foldout")));
    webDriver.findElement(By.cssSelector("a[data-currency=RUB")).click();
  }
}
