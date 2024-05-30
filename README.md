## Общее описание
Учебный проект по созданию собственного SpringBoot starter.
Проект состоит из 2 модулей:
1. RequestLogger
2. App

## RequestLogger
Этот модуль собственно и является тем самым  SpringBoot starter, который позволяет логировать HTTP request направляемые в сервис, а также response нашего сервиса.
Процесс логирования реализован с помощью `Interceptor`. Класс `LoggingInterceptor` имплементирует `HandlerInterceptor` и реализует соответствующие методы `preHandle`, `postHandle` и `afterCompletion`
Логируются:
1. URL запроса
2. Метод запроса
3. Все хэдеры запроса и их значения
4. Тело запроса
5. Параметры запроса (имя + значение)
6. Статус ответа
7. Все хэдеры ответа и их значения
8. Срок выполнения запроса в мс

Для того, чтобы подключить RequestLogger к проекту надо в pom.xml прописать следующие зависимост
```xml
<dependency>
  <groupId>ru.sergeysemenov</groupId>
      <artifactId>request-logger-spring-boot-starter</artifactId>
      <version>0.0.1-SNAPSHOT</version>
</dependency>
```
Также доступно включение/выключение функционала RequestLogger через настройку:
```yml
request-logger:
  active: true
```
```yml
request-logger:
  active: false
```

Пример логирования
![image](https://github.com/Abrazacs/SpringBootStarterExample/assets/84628800/3b199883-cd16-4aff-af5e-b652d06c5582)

## App
Приложение создана для удобства проверки работспособности RequestLogger.

Возвращает случайное число по запросу
```
curl GET 'http://localhost:8080/api/v1/random-num'
```

Возвращает случайное число в заданом диапазоне. Диапазон задается параметрами `min` и `max`
```
curl GET 'http://localhost:8080/api/v1/random-num/range?min=20&max=100'
```

