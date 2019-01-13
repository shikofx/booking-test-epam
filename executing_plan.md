# test_cases
Выполняемые тесты:
##Класс MainSearchTests 
Класс с набором тестов для тестирования главной страницы поиска https://www.booking.com/

###1. Тест searchAccomodations(String city)
####Summary:
Тест для поля ввода направления (негативный) 
####Preconditions: 
Исходные данные в файле search-negative.data.
Он имеет структуру .csv файла, где данные разделены запятыми.
Пример: Минск  
Ограничения исходных данных:
- город (ввод в поле) - невалидный город или направление
####Expected results:
Должны остаться на странице
Должно отобразиться поле Alert

###2. Тест searchAccomodations(String city, int crooms, int cadults, int cchildren)
####Summary
Форма ввода исходных данных. 
####Preconditions:
Исходные данные хранятся в файле search-positive.data

Он имеет структуру .csv файла, где данные разделены запятыми.
Пример: Минск,5,2,3,0  

Ограничения исходных данных:
- город (ввод в поле) - валидный город или направление
- количество номеров (до 10) 
- количество взрослых (до 30)
- количество детей(до 10)

####Expected results:
Данные на странице https://www.booking.com/ должны совпасть с данными на странице результатов https://www.booking.com/searchresults.ru.html

###3. Тест pageTransfer()
####Summary:
Переходы со страницы "Проживание" на страницы "Авиабилеты", "Аренда машин", "Аренда машин от/до аэропорта"
####Preconditions:
####Expected results:
Должно выполниться 2 условия:
- ссылка должна быть рабочей
- должна открываться новая страница

##Класс SearchResultsTests 
Класс с набором тестов для страницы результатов https://www.booking.com/searchresults.ru.html
###1. Тест sortByPrice(String currency)
####Summary:
Порядок элементов при сортировке по цене - от меньшей к большей
####Preconditions:
Файл данных sort-by-price.data
Он имеет структуру .csv файла, где данные разделены запятыми.
Пример: Минск,us 

Ограничения данных:
- город (ввод в поле) - валидный город или направление
- валюта(список кодов доступных валют) 
####Expected results:
Результаты поиска отображаются в порядке возрастания цены

###2. Тест filterByStars(String city, String stars)
####Summary:
Фильтр "Количество звезд"
####Preconditions:
Файл данных stars-filter.data
Он имеет структуру .csv файла, где данные разделены запятыми.
Пример: Минск,5

Ограничения данных:
- город (ввод в поле) - валидный город или направление
- количество звезд(0, 1, 2, 3, 4, 5) 
####Expected results:
Чек-бокс необходимого элемента выбран
Отображаются только элементы с указанным количеством звезд
Количество найденных записей указанное напротив элементов списка совпадает с расчетным
Количество отображаемых записей совпадает с расчетным

###3. Тест propertiesFoundCount(String city, String crooms, String cadults, String cchildren)
####Summary:
Количество найденных записей
####Preconditions:
Исходные данные хранятся в файле search-positive.data

Он имеет структуру .csv файла, где данные разделены запятыми.
Пример: Минск,5,2,3,0  

Ограничения исходных данных:
- город (ввод в поле) - валидный город или направление
- количество номеров (до 10) 
- количество взрослых (до 30)
- количество детей(до 10)
####Expected results:
Количество найденных записей соответствует количеству отображаемых

###4. Тест availabilityCount(String city, String crooms, String cadults, String cchildren)
####Summary:
Фильтр "Отображать только доступные"
####Preconditions:
Исходные данные хранятся в файле search-positive.data

Он имеет структуру .csv файла, где данные разделены запятыми.
Пример: Минск,5,2,3,0  

Ограничения исходных данных:
- город (ввод в поле) - валидный город или направление
- количество номеров (до 10) 
- количество взрослых (до 30)
- количество детей(до 10)
####Expected results:
