CREATE TABLE IF NOT EXISTS userProfiles(
    id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name varchar,
    email varchar,
    password varchar,
    status varchar,
    dateAdd date
);
