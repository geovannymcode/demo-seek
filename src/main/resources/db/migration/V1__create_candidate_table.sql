CREATE TABLE candidates
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    name             VARCHAR(100) NOT NULL,
    email            VARCHAR(255) NOT NULL,
    gender           VARCHAR(10)  NOT NULL,
    expected_salary  DECIMAL(10, 2),
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    active           BOOLEAN   DEFAULT TRUE,
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    type_of_contract VARCHAR(50)
);