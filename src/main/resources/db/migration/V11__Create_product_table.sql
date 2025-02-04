CREATE TABLE IF NOT EXISTS product(
                                    id SERIAL PRIMARY KEY ,
                                    title VARCHAR(255),
                                    description TEXT,
                                    quantity INT NOT NULL ,
                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    mrp_price INT NOT NULL ,
                                    selling_price INT NOT NULL ,
                                    discount_percent INT NOT NULL,
                                    color VARCHAR(50),
                                    num_ratings INT DEFAULT 0,
                                    category_id BIGINT,
                                    seller_id BIGINT,
                                    FOREIGN KEY (category_id)REFERENCES category(id) ON DELETE SET NULL ,
                                    FOREIGN KEY (seller_id) REFERENCES seller(id) ON DELETE SET NULL
);