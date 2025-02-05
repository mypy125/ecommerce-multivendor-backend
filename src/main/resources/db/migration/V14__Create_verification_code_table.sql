CREATE TABLE IF NOT EXISTS verification_code(
                                                id SERIAL PRIMARY KEY ,
                                                otp VARCHAR(255) NOT NULL ,
                                                email VARCHAR(255) NOT NULL ,
                                                user_id BIGINT UNIQUE ,
                                                seller_id BIGINT UNIQUE ,
                                                FOREIGN KEY (user_id)REFERENCES users(id)ON DELETE CASCADE ,
                                                FOREIGN KEY (seller_id) REFERENCES seller(id) ON DELETE CASCADE
);