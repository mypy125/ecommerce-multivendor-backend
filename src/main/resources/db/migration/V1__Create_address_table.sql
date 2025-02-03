CREATE TABLE IF NOT EXISTS address (
                                    id SERIAL PRIMARY KEY,
                                    name VARCHAR(255),
                                    locality VARCHAR(255),
                                    address VARCHAR(255),
                                    city VARCHAR(255),
                                    state VARCHAR(255),
                                    pinCode VARCHAR(20),
                                    mobile VARCHAR(20)
);
