-- DROP TABLE IF EXISTS film;
-- DROP TABLE IF EXISTS app_user;

CREATE TABLE IF NOT EXISTS film (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    date_year INT,
    rating DOUBLE,
    overview CLOB,
    genre VARCHAR(255),
    adult BOOLEAN,
    language VARCHAR(50),
    image_path VARCHAR(255),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES app_user(id)
);


CREATE TABLE IF NOT EXISTS app_user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255)
);