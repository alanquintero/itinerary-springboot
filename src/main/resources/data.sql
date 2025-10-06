-- Insert initial data for city
INSERT INTO city (id, code)
VALUES (1, 'SFO'),
       (2, 'DME'),
       (3, 'LHR'),
       (4, 'SJS'),
       (5, 'GDL'),
       (6, 'CDMX'),
       (7, 'AGS');

-- Insert initial data for currency
INSERT INTO currency (id, currency, exchange_rate_usd)
VALUES (1, 'USD', 1.0),
       (2, 'EUR', 0.91),
       (3, 'MXN', 18.77);

-- Insert initial data for flight
INSERT INTO flight (id, origin_fk, destination_fk, price, currency_fk, book)
VALUES (1, 1, 2, 1000, 1, 0),
       (2, 2, 3, 100, 2, 0),
       (3, 5, 4, 10000, 3, 0),
       (4, 4, 5, 320, 1, 0),
       (5, 7, 1, 20000, 3, 0),
       (6, 3, 6, 400, 2, 0);

-- Insert initial data for reservation (empty initially)
-- INSERT INTO reservation (flight_fk, firstname, lastname, date_booked) VALUES (...);
