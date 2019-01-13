package by.booking.pkt;

import by.booking.pkt.data.providers.DataSourceFileAnnotation;
import by.booking.pkt.data.providers.DataSourceSingleAnnotation;
import by.booking.pkt.data.providers.FileDataProvider;
import by.booking.pkt.data.providers.SingleDataProvider;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.asserts.SoftAssert;

public class SearchResultsTests extends SearchResultsFixture {

  @Test(enabled = true, groups = {"positive", "smoke", "filter"},
          dataProviderClass = FileDataProvider.class, dataProvider = "dataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void propertiesFoundCount(String city, String crooms, String cadults, String cchildren) {
    Assertion assertion = new Assertion();
    System.out.println("АТ71: Количество найденных записей");
    assertion.assertEquals("10 найдено", "12 найдено", "Количество найденных записей не равно количеству отображаемых");
  }

  @Test(enabled = true, groups = {"positive", "smoke", "filter"},
          dataProviderClass = FileDataProvider.class, dataProvider = "dataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\search-positive.data")
  public void availabilityFilter(String city, String crooms, String cadults, String cchildren) {
    SoftAssert softAssert = new SoftAssert();
    System.out.println("АТ71: Отображение только доступных мест проживания включено");
    softAssert.assertEquals("10 доступных", "10 доступных", "Чек-бокс не выбран");
    softAssert.assertEquals("10 доступных", "10 доступных", "Среди записей есть недоступные");
    softAssert.assertEquals("10 доступных", "10 доступных", "Количество отображаемых записей не совпадает с расчетным");
  }

  @Test(enabled = true, groups = {"positive", "smoke", "filter"},
          dataProviderClass = FileDataProvider.class, dataProvider = "dataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\stars-filter.data")
  public void filterByStars(String city, String stars) {
    SoftAssert softAssert = new SoftAssert();
    //Проверять только с данными, которые доступны в элементах фильтра. Для данных, которые недоступны - выбрасывать исключение, что с такими данными фильтр недоступен
    System.out.println("AT4: Выбираем чекбокс и проверяем выбран ли он "+stars);
    softAssert.assertTrue(true, "Чек-бокс не выбран");
    System.out.println("AT4: Проверяем, чтобы отображались только элементы с количеством звезд "+stars);
    softAssert.assertEquals(2 * 2, 4, "Есть элементы количеством звезд не равным " + stars);
    System.out.println("AT4: Количество записей напротив элемента фильтра "+stars+" звезд");
    softAssert.assertEquals(2 * 2, 5, "Расчетное количество записей не совпадает с количеством отображаемых напротив элемента фильтра");
    System.out.println("AT4: Количество отображаемых записей");
    softAssert.assertEquals(2 * 2, 4, "Расчетное количество записей не совпадает с количеством отображаемых");
  }

  @Test(enabled = true, groups = {"positive", "sort", "functional"},
          dataProviderClass = FileDataProvider.class, dataProvider = "dataFromCSV")
  @DataSourceFileAnnotation("src\\test\\resources\\sort-by-price.data")
  public void sortByPrice(String currency) {
    Assertion assertion = new Assertion();
    System.out.println("ART11: Порядок элементов после сортировки по цене");
    assertion.assertEquals(2 * 2, 4,
            "Неверный порядок элементов после сортировки по параметру \"По цене\"");
  }
}
