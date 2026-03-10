CREATE TABLE address(

    id BIGSERIAL PRIMARY KEY,
    street VARCHAR(100) NOT NULL,
    number INTEGER NOT NULL,
    complement VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    state CHAR(2) NOT NULL,
    zip_code CHAR(9) NOT NULL

);