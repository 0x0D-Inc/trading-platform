CREATE TABLE IF NOT EXISTS schema_versions (
    version INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    applied_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    description TEXT
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS users (
    schema_version INT DEFAULT 0,
    id BINARY(16) NOT NULL PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255),
    user_role VARCHAR(255) NOT NULL,
--     verification_type VARCHAR(255) NOT NULL,
    two_factor_auth_is_enabled TINYINT(1) DEFAULT 0 NOT NULL,
    two_factor_auth_send_to VARCHAR(255) NOT NULL,
    is_locked TINYINT(1) DEFAULT 0 NOT NULL,
    is_enabled TINYINT(1) DEFAULT 1 NOT NULL,
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    INDEX idx_users__full_name (full_name),
    INDEX idx_users__email (email)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/* v2 */
CREATE TABLE IF NOT EXISTS roles (
--     id INT AUTO_INCREMENT PRIMARY KEY,
    id BINARY(16) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    description VARCHAR(255),
    is_locked TINYINT(1) DEFAULT 0 NOT NULL,
    is_enabled TINYINT(1) DEFAULT 1 NOT NULL,
    created_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    INDEX idx_roles__name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS user_role (
    schema_version INT DEFAULT 0,
--     role_id INT,
    role_id BINARY(16),
    user_id BINARY(16),
    granted_at TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) on DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) on DELETE CASCADE,
    INDEX idx_user_role__user_id (user_id),
    INDEX idx_user_role__role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO users(
    id,
    full_name,
    email,
    user_role,
    two_factor_auth_is_enabled,
    two_factor_auth_send_to,
    is_locked,
    is_enabled
)
VALUES (
           UUID_TO_BIN(UUID()),
           "John Doe",
           "john@example.com",
           "CUSTOMER",
        0,
           "EMAIL",
           0,
           1
       );

INSERT INTO users(
    id,
    full_name,
    email,
    user_role,
    two_factor_auth_is_enabled,
    two_factor_auth_send_to,
    is_locked,
    is_enabled
)
VALUES (
           UUID_TO_BIN(UUID()),
           "Jane Smith",
           "jane@example.com",
           "ADMIN",
        0,
           "MOBILE",
           1,
           1
       );


INSERT INTO users(
    id,
    full_name,
    email,
    user_role,
    two_factor_auth_is_enabled,
    two_factor_auth_send_to,
    is_locked,
    is_enabled
)
VALUES (
           UUID_TO_BIN(UUID()),
           "Bob Johnson",
           "bob@example.com",
           "CUSTOMER",
            0,
           "EMAIL",
           0,
           0
       );
