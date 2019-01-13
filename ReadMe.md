# epam_test_task
Test task from EPAM
Задача: Реализовать 7 автотестов для предложенной предметной области с использованием связки Java+WebDriver+TestNG+PageObject. В результате должен получиться maven проект, содержащий:
- соответствующие тесты
- объекты
- сопутствующие утилитарные классы

Предоставить возможность запуска набора тестов средствами TestNG и Maven. 

Также необходимо учитывать общие требования к построению тестовых фреймворков.

Использовать PageObject дизайн с аннотациями @FindBy и PageFactory.initElements(). 

Приветствуется (но не обязательно) использование RemoteWebDriver и Selenium Grid. 

Исходный код проекта (проект целиком) должен быть залит в публичный репозиторий на Github/BitBucket и быть самодостаточным – т.е. при клонировании проектного репозитория давать возможность беспроблемного запуска тестов на новой рабочей станции, на которой уже есть необходимые системные переменные среды (JAVA_HOME, webdriver.chrome.driver и т.д.). 

Ссылку на репозиторий нужно прислать ответом на это письмо. 

Приложение/предметная область: https://www.booking.com/ . Тестовые сценарии необходимо придумать самостоятельно, ориентируясь на фичи специфичные для booking.com. Хороший сценарий - "я могу найти как минимум 3 доступных номера в гостинице в минске на двоих на ближайший уикэнд.". Плохой сценарий - тест логина/логаута.