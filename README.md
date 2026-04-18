
Инструкция по запуску: (Если докер не будет работать, пожалуйста, запустите локально, работу докера я не проверял)
Клонируйте репозиторий:

git clone https://github.com/ваш-логин/TaskTimeTracker.git
cd TaskTimeTracker

Запустите проект одной командой:
docker-compose up --build
Приложение будет доступно по адресу: http://localhost:8080
Swagger документация: http://localhost:8080/swagger-ui/index.html

База данных

При первом запуске Docker автоматически выполнит скрипт db/init.sql, который создаст необходимые таблицы. Данные хранятся внутри контейнера.
Основные эндпоинты

POST /jwt/create - Создание JWT

POST /task/create — Создание задачи

POST /time/create — Фиксация времени

GET /time/employee/{id} — Отчет за период

!!!!!!!! Инструкция по использованию !!!!!!
В первую очередь получаете jwt через /jwt/create, POST запрос, никакого тела запроса не надо. (если вдруг эндпоинты будут выдавать Unauthorized, создайте jwt заново, срок жизни прошлого - истек)
JWT вставлять в Bearer Token
<img width="1282" height="743" alt="Screenshot 2026-04-18 at 10 31 48 PM" src="https://github.com/user-attachments/assets/8e2819a1-8396-49fb-8023-eb7819375e7a" />



Далее создаете таску через /task/create
<img width="1292" height="711" alt="Screenshot 2026-04-19 at 1 16 14 AM" src="https://github.com/user-attachments/assets/e271aff2-0c67-442f-abd1-d189a01fb100" />



Обязательно сохраните taskId для вызова findByTaskId
<img width="1205" height="769" alt="Screenshot 2026-04-19 at 1 17 13 AM" src="https://github.com/user-attachments/assets/98c84d60-77ad-44c6-8ca0-91943bfef665" />



остальные эндпоинты будут работать после этих действий

Создание записи о времение через /time/create
<img width="1266" height="726" alt="Screenshot 2026-04-19 at 1 14 44 AM" src="https://github.com/user-attachments/assets/04fd8569-ebb3-494e-92b7-ae427bba5442" />




Получение за определенный период + затраченное время (duration)
<img width="1297" height="835" alt="Screenshot 2026-04-19 at 1 18 06 AM" src="https://github.com/user-attachments/assets/66ec62a1-c584-4a55-a5eb-a4d2b5f5e383" />


Работа всех тестов:
<img width="300" height="124" alt="Screenshot 2026-04-19 at 1 19 08 AM" src="https://github.com/user-attachments/assets/9f27020b-9ebd-4ce8-b31b-2ee4e906a7d9" />
<img width="305" height="164" alt="Screenshot 2026-04-19 at 1 18 53 AM" src="https://github.com/user-attachments/assets/95d9d3ea-98c4-45e5-8803-72f1a2de6925" />




Использование ИИ:
ИИ использовался для написания тестов, вот промпт, который я использовал
<img width="754" height="131" alt="Screenshot 2026-04-19 at 1 20 02 AM" src="https://github.com/user-attachments/assets/16cf971f-e1b9-4b6f-a375-9ce48cc580a7" />

Дальше отправил данные, которые он попросил, в тесты были внесены некоторые корректировки, для корректной работы

