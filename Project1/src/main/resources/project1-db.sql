CREATE TABLE Person
(
    id            int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          varchar(30) NOT NULL UNIQUE,
    year_of_birth int CHECK ( year_of_birth BETWEEN 1900 AND date_part('year', now()))
);

CREATE TABLE Book
(
    id                  int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    title               varchar(50) NOT NULL,
    author              varchar(30) NOT NULL,
    year_of_publication int CHECK ( year_of_publication BETWEEN 1900 AND date_part('year', now())),
    person_id           int         REFERENCES Person (id) ON DELETE SET NULL
);
