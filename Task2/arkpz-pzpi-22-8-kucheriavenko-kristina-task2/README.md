# API Документація для проєкту "Програмна система для управлінням медичними біологічними матеріалами"

API забезпечує функціональність для управління медичними біологічними матеріалами. Він дозволяє реєструвати, відстежувати та аналізувати матеріали, забезпечуючи інтеграцію з IoT пристроями для моніторингу умов зберігання.

## Встановлення та налаштування

### Передумови

Для взаємодії з проєктом необхідно встановити: 
- [Java](https://www.java.com/en/) (версія 21 та вище)
- [Apache Maven](https://maven.apache.org/) (версія 4.0 та вище)
- [PostgreSQL](https://www.postgresql.org/) (версія 17 та вище)
- [Spring Framework](https://spring.io/) (3.4.0 та вище)
- [Project Lombok](https://projectlombok.org/) (1.18 та вище)
- [IntelliJ IDEA](https://www.jetbrains.com/idea/)(не обов'язково) використовується як альтернативний спосіб запуску серверу
- [MQTT Explorer](https://mqtt-explorer.com/)для взаємодії з IOT пристроями

### Крок 1: Клонування репозиторію

Виконати клонування репозиторію на локальний комп'ютер:

git clone https://github.com/NureKucheriavenkoKristina/arkpz-pzpi-22-8-kucheriavenko-kristina/tree/22bef0682379c628666cd4cd298504c8a114fb73/Task5/arkpz-pzpi-22-8-kucheriavenko-kristina-task5

### Крок 2: Створення бази даних

Створити базу даних з назвою "system-for-managing-medical-biological-materials". При першому запуску проєкту таблиці автоматично створяться в базі даних.

### Крок 3: Запуск проекту

В main/resources/application.properties змінити username та password на свої власні дані, які використовуються під час створення бази даних. 
В url змінити порт на свій (встановлюється під час інсталяції PostgreSQL). 
У файлі main/java/com/BiologicalMaterialsSystem/config/SecurityConfig.java змінити username та password на власний. Використовується для доступу до сервера. 
В main/java/com/BiologicalMaterialsSystem/config/MqttConfig.java змінити MQTT_BROKER_URL, CLIENT_ID відповідно до своїх значень. MQTT_BROKER_URL - посилання на сервер брокеру, CLIENT_ID - айді клієнту у брокері. 
1 спосіб. Перейдіть у кореневу директорію проєкту та виконайте команду запуску сервера:
cd ...\BiologicalMaterialsSystem\src\main\java\com\BiologicalMaterialsSystem
mvn exec:java -Dexec.mainClass="com.BiologicalMaterialsSystem.BiologicalMaterialsSystemApplication".
2 спосіб. Завантажити застосунок IntelliJ IDEA, відкрити папку з проєктом, відкрити файл BiologicalMaterialsSystemApplication.java та запустити його. 

### Крок 4: Доступ до API

Для доступу до АРІ треба знати свій порт. Посилання: "http://localhost:8080/", 8080 - треба змінити.

### Крок 5: Доступ до ІОТ

Для доступу до ІОТ необхідно знати дані брокера (див. крок 3). Далі на веб-сторінці Wokwi (https://wokwi.com/) завантажити папку з назвою "esp32-dht22". Змінити порт, сервер, назву підключення mqtt, логін та пароль на власні значення. 
Щоб дані надходили до бази даних, необхідно відкрити  MQTT Explorer. Створити підключення з власними даними та внести їх до коду. Після чого запустити основний проєкт, а потім проєкт з ІОТ. 

## Контролери 

# BiologicalMaterialController API

## Маршрути

### Add Biological Material

**POST** '/api/biological-materials/admin/{userId}/add'

#### Опис

Додає новий біологічний матеріал від імені адміністратора.

#### Параметри запиту
Path:
- userId (Long): Ідентифікатор адміністратора, що виконує дію.
Body:

#### Відповідь 

- 200 OK:
  - BiologicalMaterial (object): Об'єкт створеного матеріалу.
- 400 Bad Request:
  - message (string): Повідомлення про помилку у вхідних даних.
- 403 Forbidden:
  - message (string): Відсутність дозволу на виконання дії.
- 404 Not Found:
  - message (string): Адміністратора не знайдено.

### Get Biological Material By ID

**GET** '/api/biological-materials/{materialID}'

#### Опис

Отримує біологічний матеріал за його унікальним ідентифікатором.

#### Параметри запиту
Path:
- materialID (Long): Ідентифікатор матеріалу.

#### Відповідь 

- 200 OK:
  - BiologicalMaterial (object): Біологічний матеріал за вказаним ідентифікатором.
- 404 Not Found:
  - message (string): Повідомлення про те, що матеріал не знайдено.

### Get All Biological Materials

**GET** '/api/biological-materials'

#### Опис

Отримує всі біологічні матеріали.

#### Параметри запиту
Path:
- materialID (Long): Ідентифікатор матеріалу.

#### Відповідь 

- 200 OK:
  - List<BiologicalMaterial> (Array): Список усіх біологічних матеріалів.


### Update Biological Material

**PUT** '/api/biological-materials/admin/{userId}/{materialID}'

#### Опис

Оновлює існуючий біологічний матеріал від імені адміністратора.

#### Параметри запиту
Path:
- userId (Long): Ідентифікатор адміністратора, що виконує дію.
- materialID (Long): Ідентифікатор матеріалу, який потрібно оновити.
Body:
- materialName (String): Назва біологічного матеріалу.
- expirationDate (Date): Дата закінчення терміну придатності.
- status (String): Статус донорства.
- transferDate (Date): Дата передачі матеріалу.
- donorID (Long): Ідентифікатор донора.

#### Відповідь 

- 200 OK:
  - BiologicalMaterial (Object): Оновлений біологічний матеріал.
- 400 Bad Request:
  - message (String): Повідомлення про помилку у вхідних даних.
- 403 Forbidden:
  - message (String): Відсутність дозволу на виконання дії.
- 404 Not Found:
  - message (String): Повідомлення про те, що матеріал не знайдено.

### Delete Biological Material

**DELETE** '/api/biological-materials/admin/{userId}/{materialID}'

#### Опис

Видаляє біологічний матеріал від імені адміністратора.

#### Параметри запиту
Path:
- userId (Long): Ідентифікатор адміністратора, що виконує дію.
- materialID (Long): Ідентифікатор матеріалу, який потрібно видалити.

#### Відповідь 

- 204 No Content:
  - Видалення матеріалу відбулося успішно.
- 404 Not Found:
  - message (String): Повідомлення про те, що матеріал не знайдено.
