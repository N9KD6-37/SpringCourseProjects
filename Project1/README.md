# Проект 1
## Описание

Веб-приложение для цифрового учёта книг. Приложение позволяет регистрировать читателей, выдавать им книги, производить 
возврат книг.

## Используемый стек

- Spring Core
- Spring MVC
- Spring JDBC
- Thymeleaf

Для валидации используется Hibernate Validator и Spring Validator.

[Схема БД](src/main/resources/project1-db.sql)

## Скриншоты страниц приложения
Страница со списком книг

![alt text](etc/books.png "Список книг")

Страница добавления книги

![alt text](etc/books.new.png "Добавление новой книги")

Страница со списком читателей

![alt text](etc/people.png "Список читателей")

Страница добавления читателя

![alt text](etc/people.new.png "Добавление нового читателя")

Страница книги

![alt text](etc/book.png "Книга в наличии")
![alt text](etc/handled.book.png "Выданная книга")

Страница читателя

![alt text](etc/person.png "Читатель без книг")
![alt text](etc/reading.person.png "Читатель с книгой")