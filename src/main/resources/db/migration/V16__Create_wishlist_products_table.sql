CREATE TABLE IF NOT EXISTS wishlist_products(
                                            wishlist_id BIGINT NOT NULL ,
                                            product_id BIGINT NOT NULL ,
                                            PRIMARY KEY (wishlist_id, product_id),
                                            FOREIGN KEY (wishlist_id)REFERENCES wishlist(id) ON DELETE CASCADE ,
                                            FOREIGN KEY (product_id)REFERENCES product(id) ON DELETE CASCADE
);