DROP TABLE flightseat;
CREATE table flightseat (
	id INT NOT NULL AUTO_INCREMENT,
    FlightNum char(10),
    FlightType Char(10),
    FirstClassNum int,
    EconomyClassNum int,
    Primary key (id)
);

INSERT INTO flightseat (flightnum)
SELECT DISTINCT flightnumber FROM deltas;

SELECT * FROM flightseat;

SET SQL_SAFE_UPDATES = 0;
update flightseat set FlightType = 'Domestic' WHERE  LENGTH(flightseat.FlightNum) = 6;
update flightseat set FlightType = 'Intl' WHERE  LENGTH(flightseat.FlightNum) = 5;

update flightseat set FirstClassNum = 16 WHERE LENGTH(flightseat.FlightNum) = 6;
update flightseat set FirstClassNum = 24 WHERE LENGTH(flightseat.FlightNum) = 5;

update flightseat set EconomyClassNum = 140 WHERE LENGTH(flightseat.FlightNum) = 6;
update flightseat set EconomyClassNum = 300 WHERE LENGTH(flightseat.FlightNum) = 5;

DROP table flight_capacity;

CREATE TABLE flight_capacity AS
SELECT l.*, s.FirstClassNum, s.FlightNum, s.FlightType, s.EconomyClassNum
FROM deltas l
JOIN flightseat s ON l.flightnumber = s.FlightNum;
ALTER TABLE flight_capacity DROP COLUMN flightnum;
ALTER TABLE flight_capacity DROP COLUMN flight_type;

SELECT * FROM flight_capacity f
WHERE f.flightnumber = "DL1600";

SELECT * FROM deltas;
SELECT * FROM flightseat;
SELECT * FROM flight_capacity;




