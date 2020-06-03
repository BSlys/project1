insert into user (id, name, surname, roles, age) values
(0, 'superuser', 'superuser', 'ROLE_ADMIN', 4);

insert into item (id, name, itemrole, quantity) values
(1, 'kozaczki', 'SECURE_BOOTS', 10),
(2, 'granaty', 'WEAPON_SIDE', 50);

insert into message_dto (id, sender, reciver, message, read) values
(1, 'system', 0, 'Inicjalizacja konta administracyjnego', 'false');