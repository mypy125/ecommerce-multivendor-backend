CREATE TABLE users_coupons (
                               user_id BIGINT NOT NULL,
                               coupon_id BIGINT NOT NULL,
                               PRIMARY KEY (user_id, coupon_id),
                               FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                               FOREIGN KEY (coupon_id) REFERENCES coupon(id) ON DELETE CASCADE
);