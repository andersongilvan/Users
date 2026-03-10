ALTER TABLE address
DROP CONSTRAINT fk_address_user;

ALTER TABLE address
DROP COLUMN user_id;

ALTER TABLE address
ADD COLUMN username VARCHAR(100) NOT NULL;

ALTER TABLE address
ADD CONSTRAINT fk_address_user
FOREIGN KEY(username) REFERENCES users(email);