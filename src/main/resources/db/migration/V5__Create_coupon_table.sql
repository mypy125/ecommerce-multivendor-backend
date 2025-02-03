CREATE TABLE IF NOT EXISTS coupon (
                                    id SERIAL PRIMARY KEY,
                                    code VARCHAR(50) NOT NULL UNIQUE,
                                    discount_percentage DOUBLE PRECISION NOT NULL CHECK (discount_percentage >= 0 AND discount_percentage <= 100),
                                    validity_start_date DATE NOT NULL,
                                    validity_end_date DATE NOT NULL,
                                    minimum_order_value DOUBLE PRECISION NOT NULL CHECK (minimum_order_value >= 0),
                                    is_active BOOLEAN NOT NULL DEFAULT TRUE
);