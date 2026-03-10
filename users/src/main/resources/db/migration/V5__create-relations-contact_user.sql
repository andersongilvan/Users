ALTER TABLE contact
ADD COLUMN user_id BIGINT NOT NULL,

ADD CONSTRAINT fk_contact_users
FOREIGN KEY(user_id) REFERENCES users(id)