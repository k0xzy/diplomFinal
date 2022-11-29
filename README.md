## Дипломный проект 
### Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.
#### Приложение представляет собой веб-сервис, который предоставляет возможность купить тур по определённой цене с помощью двух способов:
- Обычная оплата по дебетовой карте 
- Уникальная технология: выдача кредита по данным банковской карты 

#### В процессе работы над данным проектом были созданы следующие документы:
1. [План автоматизации](https://github.com/k0xzy/diplomFinal/blob/master/docs/Plan.md)
2. [Отчет о проведенном тестировании](https://github.com/k0xzy/diplomFinal/blob/master/docs/Report.md)
3. [Отчет о проведённой автоматизации тестирования](https://github.com/k0xzy/diplomFinal/blob/master/docs/Summary.md)

#### Необходимое окружение:
- установленный Docker;
- убедитесь, что порты 8080, 9999 и 5432 или 3306 (в зависимости от выбранной базы данных) свободны;
#### Инструкции по установке
1. Скачайте архив;

2. Запустите контейнер, в котором разворачивается база данных (далее БД) `docker-compose up -d --force-recreate`

3. Убедитесь в том, что БД готова к работе (логи смотреть через `docker-compose logs -f <applicationName>` (mysql или postgres)
4. Запустить SUT во вкладке Terminal Intellij IDEA командой:
- Для БД MySQL `java -Dspring.datasource.url=jdbc:mysql://localhost:3306/base_mysql -jar artifacts/aqa-shop.jar`
- Для БД Postgresql `java -Dspring.datasource.url=jdbc:postgresql://localhost:3306/base_postgresql -jar artifacts/aqa-shop.jar`
5. Для запуска авто-тестов в Terminal Intellij IDEA открыть новую сессию и ввести команду:
`gradlew clean test allureReport -Dheadless=true`
где:
`allureReport` - подготовка данных для отчета Allure;
`-Dheadless=true` - запускает авто-тесты в headless-режиме (без открытия браузера).
6. Для просмотра отчета Allure в терминале ввести команду:
`gradlew allureServe`



