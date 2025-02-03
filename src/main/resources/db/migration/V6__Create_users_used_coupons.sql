CREATE TABLE IF NOT EXISTS users_used_coupons (
                                                  used_by_users_id BIGINT NOT NULL,
                                                  used_coupons_id BIGINT NOT NULL,
                                                  PRIMARY KEY (used_by_users_id, used_coupons_id),
                                                  FOREIGN KEY (used_by_users_id) REFERENCES users(id) ON DELETE CASCADE,
                                                  FOREIGN KEY (used_coupons_id) REFERENCES coupon(id) ON DELETE CASCADE
);