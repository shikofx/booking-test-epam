package by.booking.pkt.recorded;

import by.booking.pkt.utils.StringParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Set;

public class TItemAddToNewList extends TBase {

  @Test
  public void testItemAddToNewList() {
    SoftAssert softAssert = new SoftAssert();
    goToMainSearchPage();
    logIn();
    selectCurrency();
    enterAccomodaition("Черногория");
    selectDates("2019-03-24", "2019-04-03");
    selectRoomsCount(5);
    selectAdultsCount(2);
    selectChildrenCount(4);
    submitMainSearch();

    String firstWindow = webDriver.getWindowHandle();
    Set<String> oldWindowSet = webDriver.getWindowHandles();

    goToItemFromSearchResults(1);

    Set<String> newWindowSet = webDriver.getWindowHandles();

    String secondWindow = switchToNewWindow(oldWindowSet, newWindowSet);
    softAssert.assertNotEquals(secondWindow, null,"Item info didn't open in new window!");

    String itemURLString = webDriver.getCurrentUrl();
    String itemUrlHeader = new StringParser(itemURLString).getHeader();

    activateDropdownMenu(By.cssSelector("#wrap-hotelpage-top .js-wl-dropdown-handle"), By.cssSelector("#hotel-wishlists"));

    oldWindowSet = webDriver.getWindowHandles();
    //Добавить новый список
    WebElement newWishlist = addNewWishlistInItemPage();

    softAssert.assertNotEquals(newWishlist, null, "New wish list didn't creat");

    goToWishCreatesListFromItem(newWishlist);

    newWindowSet = webDriver.getWindowHandles();
    String thirdWindow = switchToNewWindow(oldWindowSet, newWindowSet);

    softAssert.assertNotEquals(secondWindow, null, "New list didn't open in new window!");

    //Провертить появление соответствующего списка на странице списков:
    //1. Количество списков соответствует новому увеличенному на 1
    String actual = getTextWithWait();

    softAssert.assertEquals(actual, "Go to France", "New list didn't opened as current!");

    String itemURLInListString = getAttributeWithWait();
    String  itemUrlInListHeader = new StringParser(itemURLInListString).getHeader();

    softAssert.assertEquals(itemUrlInListHeader, itemUrlHeader, "New item didn't add!");
    softAssert.assertAll();
    webDriver.close();
    webDriver.switchTo().window(secondWindow);
    webDriver.close();
    webDriver.switchTo().window(firstWindow);
    logOut();

  }

}
