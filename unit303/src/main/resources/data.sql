insert into user (id, name, surname, roles, age) values
(0, 'superuser', 'superuser', 'ROLE_ADMIN', 4);

insert into user (id, name, surname, roles, age, password) values
(1, 'user', 'user', 'ROLE_GENERAL', 4, '{bcrypt}$2a$10$.A8vkw4A3/0LdRMVI6CaIePmcrTkWfH3Y965p.jHlu17tZWKvIn6O');

insert into order_ent (id, accepted, item_id, quantity, user_id) values
(1, false, 1, 4, 1),
(2, false, 2, 1, 1),
(3, false, 3, 2, 0);

insert into order_archive_entry (id, item_id, quantity, user_id, post_time) values
(1, 1, 4, 1, '2020-01-01 00:59:59.179214'),
(2, 2, 1, 1, '2020-01-01 00:59:59.179214'),
(3, 3, 2, 0, '2020-01-01 00:59:59.179214');

insert into item (id, name, itemrole, quantity) values
(1, 'Oficerki', 'SECURE_BOOTS', 10),
(2, 'Mundur', 'SECURE_JACKET', 10),
(3, 'Plecak', 'SECURE_BACKPACK', 10),
(4, 'granaty', 'WEAPON_SIDE', 50);

insert into message_dto (id, sender, reciver, message, read, date) values
(1, 'system', 0, 'Inicjalizacja konta administracyjnego', 'false', '2020-01-01 00:59:59.179214');