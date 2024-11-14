CREATE TABLE IF NOT EXISTS users (
                                     id BINARY(16) NOT NULL PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255),
    user_role VARCHAR(255) NOT NULL,
    verification_type VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
    );

INSERT INTO users(
    id,
    full_name,
    email,
    user_role,
    verification_type)
VALUES (
           UUID_TO_BIN(UUID()),
           "John Doe",
           "john@example.com",
           "CUSTOMER",
           "EMAIL"
       );

INSERT INTO users(
    id,
    full_name,
    email,
    user_role,
    verification_type)
VALUES (
           UUID_TO_BIN(UUID()),
           "Jane Smith",
           "jane@example.com",
           "ADMIN",
           "MOBILE"
       );


INSERT INTO users(
    id,
    full_name,
    email,
    user_role,
    verification_type)
VALUES (
           UUID_TO_BIN(UUID()),
           "Bob Johnson",
           "bob@example.com",
           "CUSTOMER",
           "EMAIL"
       );
