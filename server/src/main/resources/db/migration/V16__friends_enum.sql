ALTER TABLE friendship
MODIFY COLUMN status ENUM('PENDING', 'ACCEPTED', 'DENIED','BLOCKED');