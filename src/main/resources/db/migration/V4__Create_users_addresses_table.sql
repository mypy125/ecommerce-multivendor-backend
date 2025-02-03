CREATE TABLE IF NOT EXISTS users_addresses (
                                            user_id BIGINT NOT NULL,
                                            address_id BIGINT NOT NULL,
                                            PRIMARY KEY (user_id, address_id),
                                            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                            FOREIGN KEY (address_id) REFERENCES address(id) ON DELETE CASCADE
);