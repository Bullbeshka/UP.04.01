CREATE TABLE address (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(100) NOT NULL CHECK (LENGTH(street) <= 100),
    city VARCHAR(50) NOT NULL CHECK (LENGTH(city) <= 50),
    postalCode VARCHAR(5) CHECK (postalCode REGEXP '\\d{5}')
);

CREATE TABLE cart (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    quantity INT NOT NULL CHECK (quantity >= 1 AND quantity <= 100),
    name VARCHAR(50) NOT NULL CHECK (LENGTH(name) <= 50)
);

CREATE TABLE category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL CHECK (LENGTH(name) <= 255)
);

CREATE TABLE model_user (
    ID_User BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255),
    password VARCHAR(255),
    active BOOLEAN
);

CREATE TABLE ord (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ordDate DATE NOT NULL,
    status VARCHAR(255) NOT NULL,
    cart_id BIGINT,
    address_id BIGINT,
    FOREIGN KEY (cart_id) REFERENCES cart(id),
    FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE payment (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    amount DOUBLE NOT NULL CHECK (amount > 0),
    paymentDate DATE NOT NULL,
    ord_id BIGINT UNIQUE,
    FOREIGN KEY (ord_id) REFERENCES ord(id)
);

CREATE TABLE product (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL CHECK (LENGTH(name) <= 255),
    description VARCHAR(1000) NOT NULL CHECK (LENGTH(description) <= 1000),
    price DOUBLE NOT NULL CHECK (price >= 0),
    category_id BIGINT,
    ord_id BIGINT,
    FOREIGN KEY (category_	id) REFERENCES category(id),
    FOREIGN KEY (ord_id) REFERENCES ord(id)
);

CREATE TABLE review (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    text VARCHAR(255) NOT NULL CHECK (LENGTH(text) <= 255),
    rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),
    product_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES product(id)
);
