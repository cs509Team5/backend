DROP TABLE flight_capacity;

CREATE TABLE flight_capacity AS
SELECT id, DepartDateTime, ArriveDateTime, DepartAirport, ArriveAirport, FlightNumber FROM deltas
UNION ALL
SELECT id, DepartDateTime, ArriveDateTime, DepartAirport, ArriveAirport, FlightNumber FROM southwests;


ALTER TABLE flight_capacity CHANGE COLUMN `type` flightType datatype;
ALTER TABLE flight_capacity ADD COLUMN `type` VARCHAR(255);

ALTER TABLE `flight_capacity` 
RENAME COLUMN TYPE TO flightType;

ALTER TABLE flight_capacity
ADD COLUMN FirstClassNum INT,
ADD COLUMN EconomyClassNum INT;


UPDATE flight_capacity
SET flight_capacity.type = 'international'
WHERE EXISTS (
    SELECT 1
    FROM intlairport
    WHERE intlairport.Airport = flight_capacity.DepartAirport
)
OR EXISTS (
    SELECT 1
    FROM intlairport
    WHERE intlairport.Airport = flight_capacity.ArriveAirport
);



UPDATE flight_capacity
SET TYPE = 'domestic'
WHERE TYPE != 'international' OR TYPE IS NULL;


SELECT * FROM flight_capacity WHERE TYPE='domestic';

UPDATE flight_capacity
SET FirstClassNum = CASE
                        WHEN TYPE = 'international' THEN 20
                        WHEN TYPE = 'domestic' THEN 12
                        ELSE FirstClassNum
                    END,
    EconomyClassNum = CASE
                          WHEN TYPE = 'international' THEN 200
                          WHEN TYPE = 'domestic' THEN 80
                          ELSE EconomyClassNum
                      END;



UPDATE intlairport -- A table to record international airports.
SET Airport = REPLACE(Airport, CHAR(13), '')
WHERE Airport LIKE '%\r%';
UPDATE intlairport
SET Airport = REPLACE(Airport, CHAR(10), '')
WHERE Airport LIKE '%\n%';




