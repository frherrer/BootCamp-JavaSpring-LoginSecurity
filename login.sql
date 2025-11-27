CREATE DATABASE IF NOT EXISTS login_security CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE login_security;

CREATE TABLE IF NOT EXISTS usuarios (
  username VARCHAR(100) PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(50) NOT NULL
);


