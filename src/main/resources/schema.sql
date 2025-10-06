-- Table structure for city
DROP TABLE IF EXISTS city;
CREATE TABLE city
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(6) NOT NULL
);

-- Table structure for currency
DROP TABLE IF EXISTS currency;
CREATE TABLE currency
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    currency VARCHAR(6) NOT NULL,
    exchange_rate_usd DOUBLE NOT NULL
);

-- Table structure for flight
DROP TABLE IF EXISTS flight;
CREATE TABLE flight
(
    id             BIGINT PRIMARY KEY,
    origin_fk      INT     NOT NULL,
    destination_fk INT     NOT NULL,
    price DOUBLE NOT NULL,
    currency_fk    INT     NOT NULL,
    book           TINYINT NOT NULL,
    CONSTRAINT fk_origin FOREIGN KEY (origin_fk) REFERENCES city (id),
    CONSTRAINT fk_destination FOREIGN KEY (destination_fk) REFERENCES city (id),
    CONSTRAINT fk_currency FOREIGN KEY (currency_fk) REFERENCES currency (id)
);

-- Table structure for reservation
DROP TABLE IF EXISTS reservation;
CREATE TABLE reservation
(
    flight_fk   BIGINT PRIMARY KEY,
    firstname   VARCHAR(30) NOT NULL,
    lastname    VARCHAR(30) NOT NULL,
    date_booked DATETIME    NOT NULL,
    CONSTRAINT fk_flight FOREIGN KEY (flight_fk) REFERENCES flight (id)
);
