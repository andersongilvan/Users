ALTER TABLE contact
DROP CONSTRAINT fk_contact_users;

ALTER TABLE contact
DROP COLUMN user_id;

ALTER TABLE contact
ADD COLUMN username VARCHAR(100) NOT NULL;

ALTER TABLE contact
ADD CONSTRAINT fk_contact_users
FOREIGN KEY(username) REFERENCES users(email);