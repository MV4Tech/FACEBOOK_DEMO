CREATE TABLE confirmation_token (
    id BIGINT NOT NULL,
    token VARCHAR(255) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    expires_at DATETIME(6) NOT NULL,
    confirmed_at DATETIME(6),
    user_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);