CREATE TABLE Sensor
(
    id   INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE Measurement
(
    id                    INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    value                 DOUBLE PRECISION NOT NULL,
    raining               BOOLEAN          NOT NULL,
    measurement_date_time TIMESTAMP        NOT NULL,
    sensor                VARCHAR(100) REFERENCES Sensor (name)
);
