ALTER TABLE share
ADD CONSTRAINT fk_sharer_user
FOREIGN KEY (sharer) REFERENCES user(id);
