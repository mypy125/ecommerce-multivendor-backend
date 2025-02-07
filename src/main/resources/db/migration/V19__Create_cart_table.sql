CREATE TABLE IF NOT EXISTS cart(
                                id SERIAL PRIMARY KEY ,
                                user_id BIGINT NOT NULL ,
                                total_selling_price NUMERIC(10,2) NOT NULL DEFAULT 0.00,
                                total_item INT NOT NULL DEFAULT 0 ,
                                total_mrp_price INT NOT NULL DEFAULT 0 ,
                                discount INT NOT NULL DEFAULT 0 ,
                                coupon_code VARCHAR(50),

                                FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);