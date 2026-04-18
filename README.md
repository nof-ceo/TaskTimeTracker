Инструкция по запуску:
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
Обязательно сохраните taskId для вызова findByTaskId
остальные эндпоинты будут работать после этих действий
