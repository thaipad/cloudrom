# Cloudrom

## Описание проекта
Реализация личного кабинета пользователя для управления своими виртуальными серверами (VDS)

## Логические модули
- Управление серверами - создание и выбор параметров, удаление, запуск, остановка, просмотр статистики.
- Биллинг - пополнение счета, автоматическая оплата услуг, просмотр статистики списания средств.
- Начальная страница - различные вьюхи с показом сводок по ресурсам и статистики.
- Пользователь - регистрация и авторизация, подтверждение email, изменение данных эккаунта.
- Администрирование - блокирование и архивирование пользователей, установка лимитов и других параметров.
- Новостной блок - публикация новостей, новостные рассылки и уведомления.

## Что в репозитории
С разрешения работодателя я залил сюда свою первую итерацию (sprint) работы над проектом.  
В ней реализованы тестовые версии модулей "Управление серверами" и "Пользователи".
Дизайн сайта примерный, библиотека OpenStack не подключена - только интерфейс адаптера и реализация-заглушка.

## Стек технологий
- JAVA;
- Spring (MVC, Data, Security);
- Hibernate;
- PostgreSQL;
- Tomcat;
- JSP/JSTL, JavaScript, JQuery, AJAX, HTML/CSS, Bootstrap;
- JUnit; 
- Lombok;
- IDEA.

=============

## Description
Designed to manage VDS (create, delete, start, stop, pay and collect statistics).

## Project have to include follow logic:
- Instance (VDS) management - CRUD, start, stop.
- Billing - choice of tariffs, deposite and withdraw money, show of paying statistics.
- Dashboard - main page with various views of resources and statistics.
- Security - registration and authorization of user, email verification, change option (name, password, language, deleting account).
- Administration - blocking and archiving users, set limits on resources.
- News and notifications - public news, organize sending notifications.

## What is it in repository?
I pushed a part of project (with owner's agreement) - my implementation of instances management and security. 
It's my first sprint of project.
Web-design is as example, Openstack API didn't connected, mock implementation insteed.
