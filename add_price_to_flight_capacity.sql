ALTER TABLE flight_capacity
ADD COLUMN Price DOUBLE;

UPDATE flight_capacity
SET Price = ROUND(90 + (RAND() * (350 - 90)), 2);
