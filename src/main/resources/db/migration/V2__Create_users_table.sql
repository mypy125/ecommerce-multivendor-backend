CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       mobile VARCHAR(20),
                       password TEXT NOT NULL,
                       role VARCHAR(50) NOT NULL DEFAULT 'ROLE_CUSTOMER',
                       full_name VARCHAR(255),
                       CONSTRAINT chk_role CHECK (role IN ('ROLE_CUSTOMER', 'ROLE_ADMIN', 'ROLE_SELLER'))
);


