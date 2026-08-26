CREATE TABLE users (
    id CHAR(36) PRIMARY KEY,

    username VARCHAR(100) NOT NULL UNIQUE,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100),

    email VARCHAR(255) NOT NULL UNIQUE,

    password VARCHAR(255) NOT NULL,

    role VARCHAR(20) NOT NULL,

    address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    zip_code VARCHAR(20),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
);


-- =========================
-- Dummy Data
-- =========================

INSERT INTO users (
    id,
    username,
    first_name,
    last_name,
    email,
    password,
    role,
    address,
    city,
    state,
    zip_code
) VALUES
(
    '11111111-1111-1111-1111-111111111111',
    'john_doe',
    'John',
    'Doe',
    'john.doe@example.com',
    '$2a$10$dummyhashedpassword1',
    'USER',
    '123 Main Street',
    'Hyderabad',
    'Telangana',
    '500001'
),
(
    '22222222-2222-2222-2222-222222222222',
    'jane_smith',
    'Jane',
    'Smith',
    'jane.smith@example.com',
    '$2a$10$dummyhashedpassword2',
    'USER',
    '78 MG Road',
    'Bengaluru',
    'Karnataka',
    '560001'
),
(
    '33333333-3333-3333-3333-333333333333',
    'admin',
    'Admin',
    'User',
    'admin@example.com',
    '$2a$10$dummyhashedpassword3',
    'ADMIN',
    '10 Business Street',
    'Chennai',
    'Tamil Nadu',
    '600001'
);