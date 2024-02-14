ALTER TABLE page
ADD COLUMN owner_id BIGINT,
ADD CONSTRAINT fk_owner_id
FOREIGN KEY (owner_id)
REFERENCES user(id);
