insert into user (id, name, surname, roles, age) values
(0, 'superuser', 'superuser', 'ROLE_ADMIN', 4);

insert into user (id, name, surname, roles, age, password) values
(1, 'user', 'user', 'ROLE_ADMIN', 4, 'password');

insert into order_ent (id, accepted, item_id, quantity, user_id) values
(1, false, 1, 4, 1),
(2, false, 2, 1, 1),
(3, false, 3, 2, 0);

insert into item (id, name, itemrole, quantity) values
(1, 'Kozaki', 'SECURE_BOOTS', 10),
(2, 'Mundur', 'SECURE_JACKET', 10),
(3, 'Plecak', 'SECURE_BACKPACK', 10),
(4, 'granaty', 'WEAPON_SIDE', 50);

insert into message_dto (id, sender, reciver, message, read) values
(1, 'system', 0, 'Inicjalizacja konta administracyjnego', 'false');