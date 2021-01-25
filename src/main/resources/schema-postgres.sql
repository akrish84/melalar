
CREATE TABLE IF NOT EXISTS UserDetail (
    id SERIAL NOT NULL PRIMARY KEY,
    firstName varchar(50) NOT NULL,
    lastName varchar(50) NOT NULL,
    email varchar(62) NOT NULL UNIQUE,
    passhash varchar(128) NOT NULL,
    isActive boolean DEFAULT 'true'
);