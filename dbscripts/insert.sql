INSERT INTO colors (color) VALUES
('белый'), ('черный'), ('красный'), ('синий'), ('желтый'), ('зеленый'), ('серебристый');

INSERT INTO gearboxes (gearbox) VALUES
('ручная'), ('автомат'), ('полуавтомат');

INSERT INTO marks (mark) VALUES
('LADA'), ('Ferrari'), ('BMW'), ('Renault'), ('Seat'), ('Fiat'), ('Mazda'), ('Ford'), ('VolksWagen'), ('Mercedes');

INSERT INTO cars (mark, gearbox, volume, color) VALUES
(1, 1, 1.6, 2), (3, 1, 2.0, 4), (4, 2, 1.8, 1), (5, 1, 1.6, 3), (8, 2, 2.5, 5);

INSERT INTO users (name, surname, login, password, address, role) VALUES
('Сергей', 'Демидов', 'admin', '202cb962ac59075b964b07152d234b70', 'ул. Ленина, 7', 1), 
('Виталий', 'Петров', 'user1', '202cb962ac59075b964b07152d234b70', 'ул. Гагарина, 34', 0), 
('Даниил', 'Квят', 'user2', '202cb962ac59075b964b07152d234b70', 'ул. Чкалова, 18', 0);

INSERT INTO requests (mark, gearbox, volume, color, start_date, end_date, comment, user, processed) VALUES
(1, 1, 1.6, 2, '2019-08-09', '2019-08-11', 'Цвет можно любой', 2, true), 
(3, 2, 1.8, 4, '2019-08-10', '2019-08-15', 'No comments', 3, false);

INSERT INTO rents (request, car, start_date, end_date) VALUES
(1, 1, '2019-08-09', '2019-08-11');