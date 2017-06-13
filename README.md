# NeuroX

WebSocket на реактивной тяге с нейронной сетью.

### Зачем?
В сети есть много так называемых рулеток на игровые вещи (Dota 2, CS: GO), которые имеют огромный оборот. Какое-то время я и сам тратил свои кровные на это занятие. Однако, частота выпадения "паровозов" (5-20 "черных" или "красных" подряд) меня насторожила. Собственно, это приложение и создается, чтобы подтвердить или опровергнуть мою догадку.

### Что мы имеем?
Имеем открытый WebSocket без авторизации. Парсим события, сохраняем в БД, тренируем нейронную сеть и пытаемся предугадать результат.
### TODO

  - ~~подключиться к WS~~
  - ~~транслировать события сокета в Observable~~
  - ~~пушить только нужные нам события (рулетка)~~
  - ~~преобразовать поток событий в Game объекты~~
  - ~~сделать соединение постоянным (ping-pong)~~
  - ~~записать объекты Game в БД~~
  - тренировать нейронную сеть
  - предсказывать следующие роллы на основе предыдущих, а также на основе суммы каждой ставки
  - пушить уведомления в vk/telegram

### Технологии, фреймворки
  - Java 8
  - ReactiveX
  - JPA Hibernate
  - okhttp3
  - Google Gson
  - Apache log4j
