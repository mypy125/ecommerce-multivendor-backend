CREATE TABLE IF NOT EXISTS transaction(
                                        id SERIAL PRIMARY KEY,
                                        customer_id BIGINT NOT NULL,
                                        order_id BIGINT NOT NULL,
                                        seller_id BIGINT NOT NULL,
                                        date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        FOREIGN KEY (customer_id) REFERENCES users(id) ON DELETE CASCADE,
                                        FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                                        FOREIGN KEY (seller_id) REFERENCES seller(id) ON DELETE CASCADE
);