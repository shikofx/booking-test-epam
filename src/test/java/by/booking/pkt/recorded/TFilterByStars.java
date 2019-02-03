package by.booking.pkt.recorded;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TFilterByStars extends TBase{
  @Test
  public void testFilterByStars() {
    int stars = 3;
    SoftAssert softAssert = new SoftAssert();
    goToMainSearchPage();
 //   logIn();
    selectCurrency();
    enterAccomodaition("Черногория");
    selectDates("2019-03-24", "2019-04-03");
    selectRoomsCount(5);
    selectAdultsCount(2);
    selectChildrenCount(4);
    submitMainSearch();
    onlyAvailableSelect();
    WebElement filterByStars = getFilterByStarsItem(stars);
    filterByStars.click();

    int actualStars = 0;
    for(WebElement item: getSearchResults()){
      if (isElementInPresentNoWait(item, By.cssSelector("svg[class*=stars]"), 30)) {
        actualStars = Integer.parseInt(getTextByPatternNoSpace("\\d", item.findElement(By.cssSelector("svg[class*=stars]")).getAttribute("class")));
      }else {
        actualStars = 0;
      }
      softAssert.assertEquals(actualStars, stars, "For hotel "+ getHotelName(item) +" stars count is not valid!");
    }
  }
}
