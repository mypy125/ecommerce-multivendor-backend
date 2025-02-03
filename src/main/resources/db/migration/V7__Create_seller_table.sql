CREATE TABLE IF NOT EXISTS seller (
                                    id SERIAL PRIMARY KEY,
                                    email VARCHAR(255) UNIQUE NOT NULL,
                                    mobile VARCHAR(20),
                                    password VARCHAR(255) NOT NULL,
                                    role VARCHAR(50) NOT NULL DEFAULT 'ROLE_SELLER',
                                    seller_name VARCHAR(255) NOT NULL,
                                    business_details JSONB NOT NULL DEFAULT '{}',
                                    bank_details JSONB NOT NULL DEFAULT '{}',
                                    pickup_address_id BIGINT UNIQUE,
                                    gstin VARCHAR(15),
                                    is_email_verified BOOLEAN NOT NULL DEFAULT FALSE,
                                    account_status VARCHAR(50) NOT NULL DEFAULT 'PENDING_VERIFICATION',
                                    FOREIGN KEY (pickup_address_id) REFERENCES address(id) ON DELETE SET NULL
);
