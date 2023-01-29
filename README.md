# Комплект автотестов для проверки работы элементов сайта банка "Открытие" [www.open.ru](https://www.open.ru)
### Технологический стек
________
<img src="images/logo/Java.svg" width="50" height="50"/>   <img src="images/logo/Intelij_IDEA.svg" width="50" height="50"/>   <img src="images/logo/Gradle.svg" width="50" height="50"/>   <img src="images/logo/JUnit5.svg" width="50" height="50"/>   <img src="images/logo/Selenide.svg" width="50" height="50"/>   <img src="images/logo/GitHub.svg" width="50" height="50"/>   <img src="images/logo/Allure_Report.svg" width="50" height="50"/>   <img src="images/logo/Telegram.svg" width="50" height="50"/>

-----
* Написан на ``Java`` с использованием фреймворка ``Selenide``
* Применяется фреймворк для модульного тестирования ``JUnit 5``
* ``Gradle`` используется для автоматизированной сборки проекта
* Пока тесты запускаются только локально (сайт недоступен для сервера Selenoid вне России)
* Система ``Allure Report`` формирует отчет о запуске тестов
* ``Telegram``-бот отправляет уведомление о результатах прохождения тестов

###  Содержание тест-кейсов
Проверяем:
1) заголовок первого раздела сайта,
2) заголовки разделов на английской версии сайта,
3) работу поиска по сайту при введении запроса в поисковую строку,
4) корректность мин/макс значений сумм в онлайн-калькуляторе вкладов,
5) корректность отображаемых курсов покупки и продажи USD в отделениях,
6) корректность URL страницы Интернет-банка для частных лиц,
7) невозможность авторизации в Интернет-банке с заведомо некорректными авторизационными данными

### <img src="images/logo/Allure_Report.svg" width="50" height="50"/> Формирование отчета **"Allure Report"** локально:

 <img src="images/screenshots/allure-overview.PNG" width="80%" height="80%"/>

 <img src="images/screenshots/allure-behaviors.PNG" width="80%" height="80%"/>

### <img src="images/logo/Telegram.svg" width="50" height="50"/> Уведомления о прохождении тестов в Telegram отправляем локально:

<img src="images/screenshots/telegram.PNG" width="30%" height="30%" />  