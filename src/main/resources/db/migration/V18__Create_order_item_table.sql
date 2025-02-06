CREATE TABLE IF NOT EXISTS order_item(
                                        id SERIAL PRIMARY KEY ,
                                        order_id BIGINT NOT NULL ,
                                        product_id BIGINT NOT NULL ,
                                        size VARCHAR(50),
                                        quantity INTEGER NOT NULL CHECK ( quantity > 0 ),
                                        mr_price NUMERIC (10,2) NOT NULL ,
                                        selling_price NUMERIC (10,2) NOT NULL ,
                                        user_id BIGINT NOT NULL ,

                                        CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE ,
                                        CONSTRAINT fk_product_item_product FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);