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
  arrAirportId int(11) NOT NULL,
  arrDate date DEFAULT NULL,
  arrTime time DEFAULT NULL,
  depAirportId int(11) NOT NULL,
  depDate date DEFAULT NULL,
  depTime time DEFAULT NULL, 
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

insert into Airport values(1, 'Ha Noi');
insert into Airport values(2, 'Sai Gon');
insert into Airport values(3, 'Hai Phong');
insert into Airport values(4, 'Quang Nam');
insert into Airport values(5, 'Da Nang');
insert into Airport values(6, 'Hue');

insert into Flight values(1, 1, '2021-05-31', '07:41:54', 2, '2021-05-30', '07:41:54', 123.23, 200);
insert into Flight values(2, 3, '2021-05-31', '07:41:54', 4, '2021-05-30', '07:41:54', 123.23, 200);
insert into Flight values(3, 5, '2021-05-31', '07:41:54', 6, '2021-05-30', '07:41:54', 123.23, 200);

insert into OrderFlight values(1, '2021-06-01 07:41:54', 'ONE-WAY', 1, 1, 123.23);
insert into OrderFlight values(2, '2021-06-01 07:41:54', 'ROUND-TRIP', 1, 1, 123.23);

