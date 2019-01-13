package by.booking.pkt;

import by.booking.pkt.data.providers.DataSourceFileAnnotation;
import by.booking.pkt.data.providers.FileDataProvider;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;


public class MainSearchTests extends MainSearchFixture {

//На вход подать:
  // - город куда еду,
  // - дата выезда,
  // - дата возврата,
  // - количество комнат,
  // - количество взрослых,
  // - количество детей
  // - Минимальный возраст детей,
  // - максимальный возраст детей.
  // - Я путешествую по работе?
  // - Валюта для расчета
  // Тестовые параметры нужно позволить вводить руками или запускать генератор тестовых параметров?

  //Негативный тест: поиск несуществующего города
  @Test(enabled = true, groups = {"negative", "search"},
          dataProviderClass = FileDataProvider.class, dataProvider = "dataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-negative.data")
  public void searchAccomodations(String city) {
    System.out.println("Поиск несуществующего города");
    driver.findElement(By.id("ss")).click();
    driver.findElement(By.id("ss")).clear();
    driver.findElement(By.id("ss")).sendKeys(city);
    driver.findElement(By.cssSelector("button[data-sb-id=main]")).click();
    Assertion assertion = new Assertion();
    assertion.assertTrue(driver.findElement(By.cssSelector("[class^=sb-searchbox__error]")).isDisplayed(), "Unresolved accomodation found!");
  }

  @Test(enabled = false, groups = {"mainSearch", "negative", "smoke", "search"},
          dataProviderClass = FileDataProvider.class, dataProvider = "dataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void searchAccomodations(String city, int crooms, int cadults, int cchildren) {
    driver.findElement(By.id("ss")).click();
    driver.findElement(By.id("ss")).clear();
    driver.findElement(By.id("ss")).sendKeys(city);
    driver.findElement(By.cssSelector("button[data-sb-id=main]")).click();
    //ждем появление страницы общего поиска и сверяем данные с
    SoftAssert softAssert = new SoftAssert();
    System.out.println("AT101: Cовпадение параметра \"Город\"");
    softAssert.assertEquals("Город", "Город", "Города не совпадают");
    System.out.println("AT102: Cовпадение параметра \"Дата выезда\"");
    softAssert.assertEquals("Дата выезда", "Дата выезда", "Даты выезда не совпадают");
    System.out.println("AT103: Cовпадение параметра \"Дата возвращения\"");
    softAssert.assertEquals("Дата возвращения", "Дата возвращения", "Даты возвращения не совпадают");
    System.out.println("AT104: Cовпадение параметра \"Количество номеров\"");
    softAssert.assertEquals("Количество номеров", "Количество номеров", "Количество номеров не совпадают");
    System.out.println("AT105: Cовпадение параметра \"Количество взрослых\"");
    softAssert.assertEquals("Количество взрослых", "Количество взрослых", "Количество взрослых не совпадают");
    System.out.println("AT106: Cовпадение параметра \"Количество детей\"");
    softAssert.assertEquals("Количество детей", "Количество детей", "Количество детей не совпадают");
    //Если количество детей больше 0 то
    System.out.println("AT107: Cовпадение параметра \"Возвраст детей в момент выезда\"");
    softAssert.assertEquals("Возвраст детей в момент выезда 5", "Возвраст детей в момент выезда 5", "Возвраст детей в момент выезда не совпадают");
    System.out.println("AT108: Cовпадение параметра \"Возвраст детей в момент возвращения\"");
    softAssert.assertEquals("Возвраст детей в момент возвращения 6", "Возвраст детей в момент возвращения 6", "Возвраст детей в момент возвращения не совпадают");
    System.out.println("AT109: Cовпадение параметра \"По работе\"");
    softAssert.assertEquals("По работе", "По работе", "По работе не совпадают");
    System.out.println("AT110: Cовпадение параметра \"Валюта\"");
    softAssert.assertEquals("Валюта", "Валюта", "Валюты не совпадают");
    softAssert.assertAll();
  }

  @Test(enabled = false, groups = {"positive", "smoke", "transfer"})
  public void pageTransfer() {
    SoftAssert softAssert = new SoftAssert();

    System.out.println("AT51 - Переход со страницы \"Проживание\" на страницу \"Авиабилеты\"");
    softAssert.assertEquals("url", "url", "AT41 - Неудачный переход со страницы \"Проживание\" на страницу \"Авиабилеты\"");
    System.out.println("AT52 - Переход со страницы \"Проживание\" на страницу \"Аренда машин\"");
    softAssert.assertEquals("url", "url", "AT42 - Неудачный переход со страницы \"Проживание\" на страницу \"Аренда машин\"");
    System.out.println("AT53 - Переход со страницы \"Проживание\" на страницу \"Такси\"");
    softAssert.assertEquals("url", "url", "AT43 - Неудачный переход со страницы \"Проживание\" на страницу \"Такси\"");
    softAssert.assertAll();
  }
  //Если summary отсутствует - баг (в этом случае светится - Нет номеров (ЗАЧЕМ???)
}
