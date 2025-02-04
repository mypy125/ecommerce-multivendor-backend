CREATE TABLE IF NOT EXISTS product_sizes(
                                        product_id BIGINT NOT NULL ,
                                        size VARCHAR(50),
                                        FOREIGN KEY (product_id) REFERENCES product(id)ON DELETE CASCADE
);