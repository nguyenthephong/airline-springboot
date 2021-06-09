DROP DATABASE IF EXISTS Faber;

CREATE DATABASE Faber DEFAULT CHARACTER SET utf8 ;
USE Faber;

DROP TABLE IF EXISTS Airport;
CREATE TABLE Airport (
  id int(11) NOT NULL AUTO_INCREMENT,
  name VARCHAR(250) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS Flight;
CREATE TABLE Flight (
  id int(11) NOT NULL AUTO_INCREMENT,
  depAirportId int(11) NOT NULL,
  depDate date DEFAULT NULL,
  depTime time DEFAULT NULL, 
  arrAirportId int(11) NOT NULL,
  arrDate date DEFAULT NULL,
  arrTime time DEFAULT NULL,
  price decimal(20,2) NOT NULL,
  avaTickets int(11) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (arrAirportId) REFERENCES Airport(id),
  FOREIGN KEY (depAirportId) REFERENCES Airport(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS OrderFlight;
CREATE TABLE OrderFlight (
  id int(11) NOT NULL AUTO_INCREMENT,
  timeOfOrder datetime DEFAULT NULL,
  returnType enum('ONE-WAY', 'ROUND-TRIP') NOT NULL,
  flightId int(11) NOT NULL,
  totalPeople int(11) NOT NULL, 
  totalPrice decimal(20,2) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (flightId) REFERENCES Flight(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

insert into Airport values(1, 'Sai Gon');
insert into Airport values(2, 'Ha Noi');
insert into Airport values(3, 'Hai Phong');
insert into Airport values(4, 'Quang Nam');
insert into Airport values(5, 'Da Nang');
insert into Airport values(6, 'Hue');
insert into Airport values(7, 'Lam Dong');
insert into Airport values(8, 'Ca Mau');
insert into Airport values(9, 'Bac Ninh');
insert into Airport values(10, 'Dong Nai');
insert into Airport values(11, 'Phu Yen');
insert into Airport values(12, 'Dac Lac');

insert into Flight values(1, 1, '2021-06-11', '07:30:00', 2, '2021-06-12', '19:00:00', 123.23, 200);
insert into Flight values(2, 2, '2021-05-30', '07:30:00', 1, '2021-05-31', '19:00:00', 123.23, 200);
insert into Flight values(3, 3, '2021-06-01', '07:30:00', 4, '2021-06-02', '19:00:00', 123.23, 200);
insert into Flight values(4, 4, '2021-06-01', '07:30:00', 3, '2021-06-02', '19:00:00', 123.23, 200);
insert into Flight values(5, 5, '2021-06-02', '07:30:00', 6, '2021-06-03', '19:00:00', 123.23, 200);
insert into Flight values(6, 6, '2021-06-02', '07:30:00', 5, '2021-06-03', '19:00:00', 123.23, 200);
insert into Flight values(7, 2, '2021-06-03', '08:30:00', 1, '2021-06-04', '19:00:00', 123.23, 200);
insert into Flight values(8, 3, '2021-06-04', '07:30:00', 4, '2021-06-05', '19:00:00', 123.23, 200);
insert into Flight values(9, 4, '2021-06-04', '07:30:00', 3, '2021-06-05', '19:00:00', 123.23, 200);
insert into Flight values(10, 5, '2021-06-05', '07:30:00', 6, '2021-06-06', '19:00:00', 123.23, 200);
insert into Flight values(11, 6, '2021-06-05', '07:30:00', 5, '2021-06-06', '19:00:00', 123.23, 200);
insert into Flight values(12, 6, '2021-06-05', '07:30:00', 7, '2021-06-06', '19:00:00', 123.23, 200);
insert into Flight values(13, 7, '2021-06-05', '07:30:00', 6, '2021-06-06', '19:00:00', 123.23, 200);
insert into Flight values(14, 7, '2021-06-06', '07:30:00', 8, '2021-06-07', '19:00:00', 123.23, 200);
insert into Flight values(15, 8, '2021-06-06', '07:30:00', 7, '2021-06-07', '19:00:00', 123.23, 200);
insert into Flight values(16, 8, '2021-06-07', '07:30:00', 9, '2021-06-08', '19:00:00', 123.23, 200);
insert into Flight values(17, 9, '2021-06-07', '07:30:00', 10, '2021-06-08', '19:00:00', 123.23, 200);
insert into Flight values(18, 10, '2021-06-08', '07:30:00', 11, '2021-06-09', '19:00:00', 123.23, 200);
insert into Flight values(19, 11, '2021-06-08', '07:30:00', 12, '2021-06-09', '19:00:00', 123.23, 200);
insert into Flight values(20, 10, '2021-06-09', '07:30:00', 11, '2021-06-10', '19:00:00', 123.23, 200);
insert into Flight values(21, 11, '2021-06-09', '07:30:00', 12, '2021-06-10', '19:00:00', 123.23, 200);
insert into Flight values(22, 10, '2021-06-10', '07:30:00', 11, '2021-06-11', '19:00:00', 123.23, 200);
insert into Flight values(23, 11, '2021-06-10', '07:30:00', 12, '2021-06-11', '19:00:00', 123.23, 200);
insert into Flight values(24, 1, '2021-06-11', '07:30:00', 3, '2021-06-12', '19:00:00', 123.23, 200);
insert into Flight values(25, 1, '2021-06-11', '07:30:00', 4, '2021-06-12', '19:00:00', 123.23, 200);
insert into Flight values(26, 1, '2021-06-11', '07:30:00', 5, '2021-06-12', '19:00:00', 123.23, 200);
insert into Flight values(27, 1, '2021-06-11', '07:30:00', 6, '2021-06-12', '19:00:00', 123.23, 200);
insert into Flight values(28, 1, '2021-06-11', '07:30:00', 7, '2021-06-12', '19:00:00', 123.23, 200);
insert into Flight values(29, 1, '2021-06-11', '07:30:00', 8, '2021-06-12', '19:00:00', 123.23, 200);
insert into Flight values(30, 1, '2021-06-11', '07:30:00', 9, '2021-06-12', '19:00:00', 123.23, 200);
insert into Flight values(31, 1, '2021-06-11', '07:30:00', 10, '2021-06-12', '19:00:00', 123.23, 200);
insert into Flight values(32, 1, '2021-06-11', '07:30:00', 11, '2021-06-12', '19:00:00', 123.23, 200);
insert into Flight values(33, 1, '2021-06-11', '07:30:00', 12, '2021-06-12', '19:00:00', 123.23, 200);
insert into Flight values(34, 1, '2021-06-11', '08:30:00', 2, '2021-06-12', '20:00:00', 123.23, 200);
insert into Flight values(35, 1, '2021-06-11', '09:30:00', 2, '2021-06-12', '21:00:00', 123.23, 200);
insert into Flight values(36, 1, '2021-06-11', '010:30:00', 2, '2021-06-12', '22:00:00', 123.23, 200);

-- Testing purpose
--insert into OrderFlight values(1, '2021-06-01 07:41:54', 'ONE-WAY', 1, 1, 123.23);
--insert into OrderFlight values(2, '2021-06-01 07:41:54', 'ROUND-TRIP', 1, 1, 123.23);






