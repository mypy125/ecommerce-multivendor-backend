CREATE TABLE IF NOT EXISTS cart_item(
                                        id SERIAL PRIMARY KEY,
                                        cart_id BIGINT NOT NULL ,
                                        product_id BIGINT NOT NULL ,
                                        size VARCHAR(50),
                                        quantity INT NOT NULL DEFAULT 1,
                                        mr_price NUMERIC(10,2),
                                        selling_price NUMERIC(10,2),
                                        user_id BIGINT ,

                                        FOREIGN KEY (cart_id) REFERENCES cart(id)ON DELETE CASCADE,
                                        FOREIGN KEY (product_id) REFERENCES product(id)ON DELETE CASCADE
);