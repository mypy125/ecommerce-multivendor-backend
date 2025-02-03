CREATE TABLE IF NOT EXISTS seller_report (
                                            id SERIAL PRIMARY KEY,
                                            seller_id BIGINT UNIQUE NOT NULL,
                                            total_earnings BIGINT NOT NULL DEFAULT 0,
                                            total_sales BIGINT NOT NULL DEFAULT 0,
                                            total_refunds BIGINT NOT NULL DEFAULT 0,
                                            total_tax BIGINT NOT NULL DEFAULT 0,
                                            net_earnings BIGINT NOT NULL DEFAULT 0,
                                            total_orders INTEGER NOT NULL DEFAULT 0,
                                            canceled_orders INTEGER NOT NULL DEFAULT 0,
                                            total_transactions INTEGER NOT NULL DEFAULT 0,
                                            FOREIGN KEY (seller_id) REFERENCES seller(id) ON DELETE CASCADE
);
