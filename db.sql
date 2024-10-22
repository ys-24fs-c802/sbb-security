CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    enabled  BOOLEAN     DEFAULT TRUE,
    role     VARCHAR(20) DEFAULT 'ROLE_USER'
);

ALTER TABLE users DROP COLUMN role;

-- 권한 테이블
CREATE TABLE roles
(
    id   BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- 사용자-권한 매핑 테이블
CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

-- 기본 권한 데이터 추가
INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_MANAGER');