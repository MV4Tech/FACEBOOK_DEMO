ALTER TABLE notification
ADD CONSTRAINT fk_notification_sender
FOREIGN KEY (sender_id) REFERENCES user(id);
