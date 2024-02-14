ALTER TABLE reaction
ADD CONSTRAINT fk_reaction_user
FOREIGN KEY (sender_id) REFERENCES user(id);

ALTER TABLE comment
ADD CONSTRAINT fk_comment_user
FOREIGN KEY (sender_id) REFERENCES user(id);