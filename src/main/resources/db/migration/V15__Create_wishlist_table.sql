CREATE TABLE IF NOT EXISTS wishlist(
                                    id SERIAL PRIMARY KEY ,
                                    user_id BIGINT UNIQUE NOT NULL ,
                                    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);