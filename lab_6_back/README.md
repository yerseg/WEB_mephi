# Лабораторная работа №6 (backend)

## Задание:

Подготовить backend для последующих лаб по фронтенду

	1) Ваши контроллеры должны обрабатывать только маршруты вида имя-домена/api/………
	2) Описать сущности БД User(id, login, password(hashed), name, CreatedAt(тип Date)), Message(id, body, отправитель(OneToOne связь), получатель(OneToOne связь), время(Date))
	3) На основе описанных сущностей с помощью команды-миграции создать базу с такими таблицами в БД Postgres
	4) Для каждой сущности сделать api, то есть CRUD
	пример:
	/api/user - для данного адреса реализовать 4 http метода( создание пользователя, получение пользователя, изменение пользователя, удаление пользователя) 
	/api/message - аналогично
	5) Протестить API вручную с помощью http-клиента типа Postman. Сдача 4 пункта лабы будет происходить путем теста вашего api с помощью вашего http-клиента
