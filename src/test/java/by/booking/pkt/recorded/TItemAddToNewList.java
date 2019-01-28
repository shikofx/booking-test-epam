package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TItemAddToNewList extends TBase{

  @Test
  public void testItemAddToNewList(){
    goToMainSearchPage();
    logIn();
    selectCurrency();
    enterAccomodaition();
    selectDates("2019-03-24", "2019-04-03");
    selectRoomsCount(5);
    selectAdultsCount(2);
    selectChildrenCount(4);
    submitMainSearch();

    String oldWindow = webDriver.getWindowHandle();
    Set<String> oldWindowsSet = webDriver.getWindowHandles();

    WebElement item = webDriver.findElement(By.cssSelector("#hotellist_inner>.sr_item:nth-of-type(1)"));
    item.findElement(By.cssSelector(".sr-cta-button-row")).click();

    Set<String> newWindowsSet= webDriver.getWindowHandles();
    newWindowsSet.removeAll(oldWindowsSet);
    String newWindow = newWindowsSet.iterator().next();
    webDriver.switchTo().window(newWindow);
    //нажать иконку, чтобы выпало меню списков
    wait.until(visibilityOfElementLocated(By.cssSelector("#wrap-hotelpage-top .js-wl-dropdown-handle")));
    webDriver.findElement(By.cssSelector("#wrap-hotelpage-top .js-wl-dropdown-handle")).click();

    //Добавить новый список
    WebElement selectWishlistForm = webDriver.findElement(By.cssSelector("#hotel-wishlists"));
    unselectAllWishlists(selectWishlistForm);

    selectWishlistForm.findElement(By.cssSelector("input[type=text]")).click();
    selectWishlistForm.findElement(By.cssSelector("input[type=text]")).clear();
    selectWishlistForm.findElement(By.cssSelector("input[type=text]")).sendKeys("фывапр");
    selectWishlistForm.findElement(By.cssSelector("input[type=text]")).sendKeys(Keys.ENTER);
    wait.until(elementToBeSelected(By.cssSelector("#hotel_wishlists .wl-dropdown-item_new span")));
    wait.until(visibilityOf(selectWishlistForm.findElement(By.cssSelector("[checked=checked]~span a"))));
    System.out.println(selectWishlistForm.findElement(By.cssSelector("[checked=checked]~span a")).getAttribute("href"));
    //goToWishlist
    //проверить есть тот ли текущий список и добавлена ли в него запись
//    webDriver.close();
//    webDriver.switchTo().window(oldWindow);

  }

  private void unselectAllWishlists(WebElement form) {
    List<WebElement> selectedElements = form.findElements(By.cssSelector("input:checked+span.bui-checkbox__label"));
    for(WebElement e:selectedElements){
      System.out.println(e.getText());
      e.click();
    }
  }

  private void selectCurrentWindow() {
    String currentWindowHandle = webDriver.getWindowHandle();
    webDriver.switchTo().window(currentWindowHandle);
  }

  private void submitMainSearch() {
    webDriver.findElement(By.cssSelector("button[data-sb-id=main][type=submit]")).click();
  }

  private void selectChildrenCount(int childrenCount) {
    if(webDriver.findElement(By.cssSelector("label#xp__guests__toggle+div")).getAttribute("className").contains("hidden")){
      webDriver.findElement(By.cssSelector("label#xp__guests__toggle")).click();
    }
    int actualChildrenCount=-1;
    while(childrenCount!=actualChildrenCount) {
      actualChildrenCount = Integer.parseInt(webDriver.findElement(By.cssSelector("#group_children")).getAttribute("defaultValue"));
      if(actualChildrenCount > childrenCount && actualChildrenCount>0){
        webDriver.findElement(By.cssSelector("#group_children~button[class*=subtract-button]")).click();
      } else if(actualChildrenCount < childrenCount){
        webDriver.findElement(By.cssSelector("#group_children~button[class*=add-button]")).click();
      }
    }
  }

  private void selectAdultsCount(int adultsCount) {
    if(webDriver.findElement(By.cssSelector("label#xp__guests__toggle+div")).getAttribute("className").contains("hidden")){
      webDriver.findElement(By.cssSelector("label#xp__guests__toggle")).click();
    }
    int actualAdultsCount=-1;
    while(adultsCount!=actualAdultsCount) {
      actualAdultsCount = Integer.parseInt(webDriver.findElement(By.cssSelector("#group_adults")).getAttribute("defaultValue"));
      if(actualAdultsCount > adultsCount && actualAdultsCount>0){
        webDriver.findElement(By.cssSelector("#group_adults~button[class*=subtract-button]")).click();
      } else if(actualAdultsCount < adultsCount){
        webDriver.findElement(By.cssSelector("#group_adults~button[class*=add-button]")).click();
      }
    }
  }

  private void selectRoomsCount(int roomsCount) {
    if(webDriver.findElement(By.cssSelector("label#xp__guests__toggle+div")).getAttribute("className").contains("hidden")){
      webDriver.findElement(By.cssSelector("label#xp__guests__toggle")).click();
    }
    int actualRooms = Integer.parseInt(webDriver.findElement(By.cssSelector("#no_rooms")).getAttribute("defaultValue"));
    int actualRoomsCount=-1;
    while(roomsCount!=actualRoomsCount) {
      actualRoomsCount = Integer.parseInt(webDriver.findElement(By.cssSelector("#no_rooms")).getAttribute("defaultValue"));
      if(actualRoomsCount > roomsCount && actualRoomsCount>0){
        webDriver.findElement(By.cssSelector("#no_rooms~button[class*=subtract-button]")).click();
      } else if(actualRoomsCount < roomsCount){
        webDriver.findElement(By.cssSelector("#no_rooms~button[class*=add-button]")).click();
      }
    }
  }

  private void selectDates(String checkInDate, String checkOutDate) {
    String[] firstMonthArray = checkInDate.split("-");
    firstMonthArray[2]="01";
    String firstMonth = firstMonthArray[0]+"-"+firstMonthArray[1]+"-"+firstMonthArray[2];
    webDriver.findElement(By.cssSelector("div.xp__dates.xp__group")).click();
   // String currentFirstMonth = ;

    while (!firstMonth.equals(webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date^='20']")).getAttribute("data-date"))) {
      webDriver.findElement(By.cssSelector("div[data-bui-ref=calendar-next]")).click();
    //  currentFirstMonth = webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date^='20']")).getAttribute("data-date");
    }
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='"+checkInDate+"']")).click();
    webDriver.findElement(By.cssSelector("div.bui-calendar td[data-date='"+checkOutDate+"']")).click();
    webDriver.findElement(By.cssSelector("div.xp__dates.xp__group")).click();
  }

  private void enterAccomodaition() {
    webDriver.findElement(By.cssSelector("#ss")).click();
    webDriver.findElement(By.cssSelector("#ss")).clear();
    webDriver.findElement(By.cssSelector("#ss")).sendKeys("Черногория");
  }

  private void selectCurrency() {
    wait.until(visibilityOfElementLocated(By.cssSelector("[data-id=currency_selector]")));
    webDriver.findElement(By.cssSelector("[data-id=currency_selector]")).click();
    wait.until(visibilityOfElementLocated(By.cssSelector("#current_currency_foldout")));
    webDriver.findElement(By.cssSelector("a[data-currency=RUB")).click();
  }
}
