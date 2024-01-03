# rest-cache-service
Горизонтально масштабируемый HTTP REST-сервис с кэшированием

### Особенности:
- PostgreSQL
- Spring Cloud микросервисы
- Basic-авторизация Spring Security
- OpenAPI (Swagger ui)
- Кэш Hazelcast
- Spring Boot

### Сборка и запуск
```
mvn clean package
```
Eureka-сервер:
```
java -jar discovery-service\target\discovery-service-0.0.1-SNAPSHOT.jar
```
Масштабируемый REST-сервис (динамические порты):
```
java -jar rest-service\target\rest-service-0.0.1-SNAPSHOT.jar
```
Gateway:
```
java -jar api-gateway\target\api-gateway-0.0.1-SNAPSHOT.jar
```
Для запуска другого экземпляра Gateway:
```
java -jar api-gateway\target\api-gateway-0.0.1-SNAPSHOT.jar --server.address=127.0.0.2
```
### Данные
#### Модель данных <i>Book</i>
<pre>
<i><b> id </b></i>     long
<i><b> name </b></i>   String
<i><b> author </b></i> String
<i><b> year </b></i>   String
</pre>
#### Начальное заполнение БД
Тестовые данные для БД: [database/data.sql](rest-service/src/main/resources/database/data.sql)

### Swagger интерфейс
http://127.0.0.1/swagger-ui/index.html
<p align=center>
    <img width= 80% src=https://github.com/Mihail-Ko/rest-cache-service/assets/98303471/9cc348a1-6f4b-468e-89d8-ff9d43619351 alt="swagger screenshot"/>
</p>

### Микросервисы и горизонтальное масштабирование
- #### api-gateway
Прокси между пользователем и запущенными сервисами: http://localhost/
- #### discovery-service
Регистрация микросервисов.

Панель Eureka-сервера: http://localhost:8081
- #### rest-service
Для реализации горизонтального масштабирования необходимо запустить несколько экземпляров.
<p align=center>
    <img width=500px src=https://github.com/Mihail-Ko/rest-cache-service/assets/98303471/58362c31-b73c-4b1d-935f-6f80e92394fd alt="services scheme"/>
</p>
<p align=center> Схема микросервисов </p>

### Обработка исключений
Реализована централизованная обработка исключений классом [DefaultAdvice](rest-service/src/main/java/com/example/restservice/exception/DefaultAdvice.java)

### Эмуляция ресурсоёмкости
Настраиваемые задержки в [application.yml](rest-service/src/main/resources/application.yml) для демонстрации быстродействия получения данных из кэша.

```yaml
delay:
  getOne: 1500
  getAll: 3000
  delete: 1500
  update: 1500
  insert: 500
  ```

### Кэширование
В [hazelcast.yml](rest-service/src/main/resources/hazelcast.yml) включена синхронизация и установлено время жизни кэша.
<p align=center>
    <img src=https://github.com/Mihail-Ko/rest-cache-service/assets/98303471/50bfde3e-0f26-4835-b569-a463ab223319 alt="hazelcast scheme"/>
</p>
<p align=center> Hazelcast синхронизация </p>
