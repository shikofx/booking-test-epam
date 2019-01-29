package by.booking.pkt.recorded;

import by.booking.pkt.utils.ItemUrlParser;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class TItemAddToNewList extends TBase{

  @Test
  public void testItemAddToNewList(){
    SoftAssert softAssert = new SoftAssert();
    goToMainSearchPage();
    logIn();
    selectCurrency();
    enterAccomodaition();
    selectDates("2019-03-24", "2019-04-03");
    selectRoomsCount(5);
    selectAdultsCount(2);
    selectChildrenCount(4);
    submitMainSearch();

    String firstWindow = webDriver.getWindowHandle();
    Set<String> oldWindowSet = webDriver.getWindowHandles();

    WebElement item = webDriver.findElement(By.cssSelector("#hotellist_inner>.sr_item:nth-of-type(1)"));
    item.findElement(By.cssSelector(".sr-cta-button-row")).click();

    Set<String> newWindowSet = webDriver.getWindowHandles();
    boolean openeItemInNewWindow = false;
    String secondWindow="";
    if(newWindowSet.removeAll(oldWindowSet) && newWindowSet.size()==1) {
      secondWindow = newWindowSet.iterator().next();
      webDriver.switchTo().window(secondWindow);
      openeItemInNewWindow = true;
    }
    softAssert.assertTrue(openeItemInNewWindow, "Item info didn't open in new window!");
    //нажать иконку, чтобы выпало меню списков
    wait.until(visibilityOfElementLocated(By.cssSelector("#wrap-hotelpage-top .js-wl-dropdown-handle")));
    webDriver.findElement(By.cssSelector("#wrap-hotelpage-top .js-wl-dropdown-handle")).click();
    String itemURLString = webDriver.getCurrentUrl();
    ItemUrlParser itemUrl = new ItemUrlParser(itemURLString);
    //Добавить новый список
    WebElement selectWishlistForm = webDriver.findElement(By.cssSelector("#hotel-wishlists"));
    List<WebElement> oldWishlists = selectWishlistForm.findElements(By.cssSelector("label.js-wl-dropdown-item"));
    selectWishlistForm.findElement(By.cssSelector("input[type=text]")).click();
    selectWishlistForm.findElement(By.cssSelector("input[type=text]")).clear();
    selectWishlistForm.findElement(By.cssSelector("input[type=text]")).sendKeys("Go to France1");
    selectWishlistForm.findElement(By.cssSelector("input[type=text]")).sendKeys(Keys.ENTER);
    wait.until(numberOfElementsToBe(By.cssSelector("label.js-wl-dropdown-item"), oldWishlists.size()+1));
    List<WebElement> newWishlists = selectWishlistForm.findElements(By.cssSelector("label.js-wl-dropdown-item"));
    unselectAllElementsInList(oldWishlists);
    WebElement newWishlist = null;
    oldWindowSet = webDriver.getWindowHandles();
    if(newWishlists.removeAll(oldWishlists)&&newWishlists.size()==1){
      newWishlist=newWishlists.iterator().next();
      if(newWishlist.findElement(By.cssSelector("input")).isSelected()){
        newWishlist.findElement(By.cssSelector("input~span a")).click();
      }
    }
    newWindowSet= webDriver.getWindowHandles();
    boolean openeListInNewWindow = false;
    String thirdWindow;
    if(newWindowSet.removeAll(oldWindowSet) && newWindowSet.size()==1) {
      thirdWindow = newWindowSet.iterator().next();
      webDriver.switchTo().window(thirdWindow);
      openeListInNewWindow = true;
    }
    softAssert.assertTrue(openeListInNewWindow, "New list didn't open in new window!");

    //Провертить появление соответствующего списка на странице списков:
    //1. Количество списков соответствует новому увеличенному на 1
    wait.until(presenceOfElementLocated(By.cssSelector("div.wl-bui-header")));
    String actual=webDriver.findElement(By.cssSelector("div.wl-bui-header h1")).getText();
    softAssert.assertEquals(actual, "Go to France", "New list didn't opened as current!");
    wait.until(presenceOfElementLocated(By.cssSelector("header[class*=header]")));
    String itemURLInListString = webDriver.findElement(By.cssSelector("header[class*=header] a")).getAttribute("href");
    ItemUrlParser itemUrlInList = new ItemUrlParser(itemURLInListString);
    softAssert.assertEquals(itemUrlInList.getHeader(), itemUrl.getHeader(), "New item didn't add!");
    System.out.println(itemUrlInList.getHeader()+"\n"+itemURLInListString+"\nitemURL = "+itemUrl.getHeader()+"\nSTRING = "+itemURLString);
    softAssert.assertAll();


    //
    webDriver.close();
    if(!secondWindow.isEmpty()) {
      webDriver.switchTo().window(secondWindow);
      webDriver.close();
    }

    if(!firstWindow.isEmpty()){
      webDriver.switchTo().window(firstWindow);
    }

    logOut();


  }

  private void unselectAllElementsInList(List<WebElement> list) {
    for(WebElement e:list){
      if(e.findElement(By.cssSelector("input")).isSelected())
        e.findElement(By.cssSelector("input~span.bui-checkbox__label")).click();
    }
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
