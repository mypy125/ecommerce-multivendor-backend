CREATE TABLE IF NOT EXISTS orders(
                                    id SERIAL PRIMARY KEY ,
                                    order_id VARCHAR(255)UNIQUE NOT NULL ,
                                    user_id BIGINT NOT NULL ,
                                    seller_id BIGINT NOT NULL ,
                                    shipping_address_id BIGINT NOT NULL ,
                                    total_mrp_price NUMERIC(10,2)NOT NULL ,
                                    total_selling_price NUMERIC(10,2)NOT NULL ,
                                    discount INTEGER NOT NULL DEFAULT 0,
                                    order_status VARCHAR(50)NOT NULL ,
                                    total_item INTEGER NOT NULL ,
                                    payment_status VARCHAR(50)NOT NULL DEFAULT 'PENDING',
                                    order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    deliver_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP + INTERVAL '7 days',
                                    payment_details JSONB NOT NULL DEFAULT '{}',

                                    CONSTRAINT fk_orders_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ,
                                    CONSTRAINT fk_orders_address FOREIGN KEY (shipping_address_id) REFERENCES address(id) ON DELETE CASCADE
);