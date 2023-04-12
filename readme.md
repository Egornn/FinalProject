## Финальная задача
Организуйте систему учёта для питомника, в котором живут домашние и вьючные животные!
## Задание 1
1. Используя команду cat в терминале операционной системы Linux, создать два файла Домашние животные (заполнив файл собаками, кошками, хомяками) и Вьючные животными заполнив файл Лошадьми, верблюдами и ослы, а затем объединить их. Просмотреть содержимое созданного файла. Переименовать файл, дав ему новое имя (Друзья человека).
![Image task 1](https://github.com/Egornn/FinalProject/blob/main/pic/1.PNG?raw=true)

2. Создать директорию, переместить файл туда.
![Image task 2 part 1](https://github.com/Egornn/FinalProject/blob/main/pic/2.PNG?raw=true)
![Image task 2 part 2](https://github.com/Egornn/FinalProject/blob/main/pic/2-1.PNG?raw=true)

3. Подключить дополнительный репозиторий MySQL. Установить любой пакет из этого репозитория.
![Image task 3](https://github.com/Egornn/FinalProject/blob/main/pic/3.PNG?raw=true)

4. Установить и удалить deb-пакет с помощью dpkg.
![Image task 4 part 1](https://github.com/Egornn/FinalProject/blob/main/pic/4-1.PNG?raw=true)
![Image task 4 part 2](https://github.com/Egornn/FinalProject/blob/main/pic/4-2.PNG?raw=true)
![Image task 4 part 3](https://github.com/Egornn/FinalProject/blob/main/pic/4-3.PNG?raw=true)

5. Выложить историю команд в терминале ubuntu
![Image task 5](https://github.com/Egornn/FinalProject/blob/main/pic/5.PNG?raw=true)

6. Нарисовать [диаграмму](https://github.com/Egornn/FinalProject/blob/main/pic/6.png), в которой есть класс родительский класс, домашние животные и вьючные животные, в составы которых в случае домашних животных войдут классы: собаки, кошки, хомяки, а в класс вьючные животные войдут: Лошади, верблюды и ослы.
![Image task 6](https://github.com/Egornn/FinalProject/blob/main/pic/6.png?raw=true)
7. В подключенном MySQL репозитории создать базу данных “Друзья
человека”
```sql
CREATE DATABASE Humanity_friends
```
8. Создать таблицы с иерархией из диаграммы в БД
```sql
USE Humanity_friends;
CREATE TABLE animal_classes
(
	animal_id INT PRIMARY KEY,
    animal_type VARCHAR(50)

);

CREATE TABLE DomesticAnimal (
  domestic_animal_id INT PRIMARY KEY,
  animal_id INT,
  domestic_animal_type VARCHAR(50),
  FOREIGN KEY (animal_id) REFERENCES Animal(animal_id)
);

CREATE TABLE Dog (
  dog_id INT PRIMARY KEY,
  domestic_animal_id INT,
  dog_name VARCHAR(50),
  FOREIGN KEY (domestic_animal_id) REFERENCES DomesticAnimal(domestic_animal_id)
);

CREATE TABLE Cat (
  cat_id INT PRIMARY KEY,
  domestic_animal_id INT,
  cat_name VARCHAR(50),
  FOREIGN KEY (domestic_animal_id) REFERENCES DomesticAnimal(domestic_animal_id)
);

CREATE TABLE Rats (
  hamster_id INT PRIMARY KEY,
  domestic_animal_id INT,
  hamster_name VARCHAR(50),
  FOREIGN KEY (domestic_animal_id) REFERENCES DomesticAnimal(domestic_animal_id)
);


CREATE TABLE HerdAnimal (
  herd_animal_id INT PRIMARY KEY,
  animal_id INT,
  herd_animal_type VARCHAR(50),
  FOREIGN KEY (animal_id) REFERENCES Animal(animal_id)
);

CREATE TABLE Horse (
  horse_id INT PRIMARY KEY,
  herd_animal_id INT,
  horse_name VARCHAR(50),
  FOREIGN KEY (herd_animal_id) REFERENCES HerdAnimal(herd_animal_id)
);

CREATE TABLE Camel (
  camel_id INT PRIMARY KEY,
  herd_animal_id INT,
  camel_name VARCHAR(50),
  FOREIGN KEY (herd_animal_id) REFERENCES HerdAnimal(herd_animal_id)
);


CREATE TABLE Donkey (
  donkey_id INT PRIMARY KEY,
  herd_animal_id INT,
  donkey_name VARCHAR(50),
  FOREIGN KEY (herd_animal_id) REFERENCES HerdAnimal(herd_animal_id)
);


```

9. Заполнить низкоуровневые таблицы именами(животных), командами
которые они выполняют и датами рождения

``` Sql
INSERT INTO dog (name, command, birthdate)
VALUES
('Псина', 'Охотиться на тюленей', '2013-04-05'),
('уФСИНа', 'Таскать деревья', '2005-11-02'),

INSERT INTO Cat (name, command, birthdate)
VALUES
('Котя', 'Охотиться', '2010-05-12'),
('Мотя', 'Плавать и ловить рыбу', '2007-01-30');

INSERT INTO Rats (name, command, birthdate)
VALUES
('Здоровяк', 'Есть бамбук', '2014-09-08'),
('Летун', 'Прыгать по деревьям', '2011-03-20'),


INSERT INTO Camel (name, command, birthdate)
VALUES
('Бычара', 'Бегать на высоких скоростях', '2009-12-18'),
('Длинношей', 'Пить воду из верхушек деревьев', '2015-08-10'),


INSERT INTO Horse (name, command, birthdate)
VALUES
('Попрыгун', 'Прыгать', '2008-06-25'),


INSERT INTO Donkey (name, command, birthdate)
VALUES
('Овал', 'Тупить', '2011-03-20'),
```
10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой
питомник на зимовку. Объединить таблицы лошади, и ослы в одну таблицу.
``` SQl
SET SQL_SAFE_UPDATES = 0;
DELETE FROM Camel;

SELECT name, command, birthdate FROM Horse
UNION SELECT  name, command, birthdate FROM Donkey;
```
11. Создать новую таблицу “молодые животные” в которую попадут все
животные старше 1 года, но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице
``` SQL
CREATE TEMPORARY TABLE animals AS 
SELECT *, 'песики' AS genus FROM Dog
UNION SELECT *, 'кисы' AS genus FROM Cat
UNION SELECT *, 'хомяки' AS genus FROM Rats
UNION SELECT *, 'лошади' as genus FROM Horse
UNION SELECT *, 'ослики' AS genus FROM Donkey;

CREATE TABLE young_animal AS
SELECT name, command, birthdate, genus, TIMESTAMPDIFF(MONTH, birthdate, CURDATE()) AS age_in_month
FROM animals WHERE birthdate BETWEEN ADDDATE(CURDATE(), INTERVAL -3 YEAR) AND ADDDATE(CURDATE(), INTERVAL -1 YEAR);
 
SELECT * FROM young_animals;
```
12. Объединить все таблицы в одну, при этом сохраняя поля, указывающие на прошлую принадлежность к старым таблицам.

```SQL
SELECT h.name, h.birthdate, h.command, ha.genus_name, ya.age_in_month 
FROM horses h
LEFT JOIN young_animal ya ON ya.name = h.name
LEFT JOIN herd_animals ha ON ha.Id = h.genus_id
UNION 
SELECT d.name, d.birthdate, d.command, ha.genus_name, ya.age_in_month 
FROM donkeys d 
LEFT JOIN young_animal ya ON ya.name = d.name
LEFT JOIN herd_animals ha ON ha.Id = d.genus_id
UNION
SELECT c.name, c.birthdate, c.command, ha.genus_name, ya.age_in_month 
FROM cats c
LEFT JOIN young_animal ya ON ya.name = c.name
LEFT JOIN home_animals ha ON ha.Id = c.genus_id
UNION
SELECT d.name, d.birthdate, d.command, ha.genus_name, ya.age_in_month 
FROM dogs d
LEFT JOIN young_animal ya ON ya.name = d.name
LEFT JOIN home_animals ha ON ha.Id = d.genus_id
UNION
SELECT hm.name, hm.birthdate, hm.command, ha.genus_name, ya.age_in_month 
FROM rats r
LEFT JOIN young_animal ya ON ya.name = r.name
LEFT JOIN home_animals ha ON ha.Id = r.genus_id;

```

13. Создать класс с Инкапсуляцией методов и наследованием по диаграмме.

В папке [model](https://github.com/Egornn/FinalProject/blob/main/model)
14. Написать программу, имитирующую работу реестра домашних животных.
В программе должен быть реализован следующий функционал:
14.1 Завести новое животное
14.2 определять животное в правильный класс
14.3 увидеть список команд, которое выполняет животное
14.4 обучить животное новым командам
14.5 Реализовать навигацию по меню
15. Создайте класс Счетчик, у которого есть метод add(), увеличивающий̆
значение внутренней̆ int переменной̆ на 1 при нажатии “Завести новое
животное” Сделайте так, чтобы с объектом такого типа можно было работать в
блоке try-with-resources. Нужно бросить исключение, если работа с объектом
типа счетчик была не в ресурсном try и/или ресурс остался открыт. Значение
считать в ресурсе try, если при заведении животного заполнены все поля.