-- Crear una tabla
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    address VARCHAR(100) NOT NULL
);

-- Insertar registros iniciales
INSERT INTO users (name, email, password, address)
VALUES ('John Doe', 'john@example.com', 'password123', 'assdsdsd');

INSERT INTO users (name, email, password, address)
VALUES ('Jane Smith', 'jane@example.com', 'password456', 'asasas');