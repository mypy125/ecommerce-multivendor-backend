CREATE TABLE IF NOT EXISTS product_images(
                                        product_id BIGINT NOT NULL ,
                                        image_url TEXT NOT NULL ,
                                        FOREIGN KEY (product_id)REFERENCES product(id)ON DELETE CASCADE
);