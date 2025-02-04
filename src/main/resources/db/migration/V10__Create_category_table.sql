CREATE TABLE IF NOT EXISTS category(
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(255),
                                    category_id VARCHAR(255)UNIQUE NOT NULL,
                                    parent_category_id BIGINT,
                                    level INTEGER NOT NULL,
                                    FOREIGN KEY (parent_category_id) REFERENCES category(id) ON DELETE SET NULL
);