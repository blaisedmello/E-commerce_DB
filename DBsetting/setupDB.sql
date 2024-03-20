-- call 'source ./setupDB.sql;' in mySQL to setup the database using this script

-- create database as OnlineShoppingDB
CREATE DATABASE IF NOT EXISTS OnlineShoppingDB;

-- specify db
USE OnlineShoppingDB;

-- Disable foreign key checks
SET FOREIGN_KEY_CHECKS = 0;

-- setup schema: ItemMaster
CREATE TABLE IF NOT EXISTS Categories (
    categoryID INT PRIMARY KEY,
    categoryName VARCHAR(255) NOT NULL
);
CREATE TABLE IF NOT EXISTS ItemMaster (
    itemID INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);


-- setup schema: ItemCategory
CREATE TABLE IF NOT EXISTS ItemCategory (
    itemID INT,
    categoryID INT,
    PRIMARY KEY (itemID, categoryID),
    FOREIGN KEY (itemID) REFERENCES ItemMaster(itemID),
    FOREIGN KEY (categoryID) REFERENCES Categories(categoryID)
);

-- setup schema: Inventory
CREATE TABLE IF NOT EXISTS Inventory (
    inventoryID INT PRIMARY KEY,
    itemID INT,
    stockQuantity INT CHECK (stockQuantity >= 0),
    FOREIGN KEY (itemID) REFERENCES ItemMaster(itemID)
);

-- setup schema: Customers
CREATE TABLE IF NOT EXISTS Customers (
    customerID INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    address VARCHAR(255),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

-- setup schema: Orders
CREATE TABLE IF NOT EXISTS Orders (
    orderID INT AUTO_INCREMENT PRIMARY KEY,
    date DATE,
    customerID INT,
    orderStatus VARCHAR(50),
    FOREIGN KEY (customerID) REFERENCES Customers(customerID)
);

-- setup schema: OrderItems
CREATE TABLE IF NOT EXISTS OrderItems (
    orderItemID INT AUTO_INCREMENT PRIMARY KEY,
    orderID INT,
    itemID INT,
    quantity INT,
    FOREIGN KEY (orderID) REFERENCES Orders(orderID),
    FOREIGN KEY (itemID) REFERENCES ItemMaster(itemID)
);
-- setup schema: Employees
CREATE TABLE IF NOT EXISTS Employees (
    employeeID INT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL
);
-- setup schema: Admins
CREATE TABLE IF NOT EXISTS Admins (
    adminID INT PRIMARY KEY,
    employeeID INT,
    password VARCHAR(100) NOT NULL,
    FOREIGN KEY (employeeID) REFERENCES Employees(employeeID)
);

-- Enable foreign key checks
SET FOREIGN_KEY_CHECKS = 1;
-- insert data: Categories
INSERT INTO Categories VALUES (1, 'Electronics'),
                              (2, 'Computers'),
                              (3, 'Office'),
                              (4, 'Storage'),
                              (5, 'Home'),
                              (6, 'Kitchen'),
                              (7, 'Outdoor'),
                              (8, 'Sports'),
                              (9, 'Clothing'),
                              (10, 'Books');
-- insert data: ItemMaster
INSERT INTO ItemMaster VALUES (1, 'Laptop', 1200),
                              (2, 'Smartphone', 800),
                              (3, 'Headphones', 100),
                              (4, 'Tablet', 500),
                              (5, 'Camera', 700),
                              (6, 'Keyboard', 50),
                              (7, 'Mouse', 30),
                              (8, 'Monitor', 300),
                              (9, 'Printer', 150),
                              (10, 'External Hard Drive', 200);


-- insert data: ItemCategory
INSERT INTO ItemCategory VALUES (1, 1),
                                (2, 1),
                                (3, 1),
                                (4, 1),
                                (5, 1),
                                (6, 2),
                                (7, 2),
                                (8, 2),
                                (9, 3),
                                (10, 4);

-- insert data: Inventory
INSERT INTO Inventory VALUES (1, 1, 10),
                              (2, 2, 25),
                              (3, 3, 50),
                              (4, 4, 15),
                              (5, 5, 30),
                              (6, 6, 20),
                              (7, 7, 40),
                              (8, 8, 5),
                              (9, 9, 12),
                              (10, 10, 18);

-- insert data: Customers
INSERT INTO Customers (customerID, firstName, lastName, address, email, password) VALUES
    (1,'John', 'Doe', '123 Main St', 'john.doe@email.com', 'P@ssw0rd!'),
    (2, 'Jane', 'Smith', '456 Oak St', 'jane.smith@email.com', 'SecurePwd123'),
    (3, 'Bob', 'Johnson', '789 Pine St', 'bob.johnson@email.com', 'Customer456'),
    (4, 'Alice', 'Williams', '321 Cedar St', 'alice.williams@email.com', 'SecretPass!'),
    (5, 'David', 'Brown', '987 Elm St', 'david.brown@email.com', 'MyP@ssw0rd'),
    (6, 'Emily', 'Taylor', '654 Birch St', 'emily.taylor@email.com', 'Secure12345'),
    (7, 'Chris', 'Anderson', '876 Maple St', 'chris.anderson@email.com', 'Passw0rd!'),
    (8, 'Emma', 'Clark', '234 Pine St', 'emma.clark@email.com', 'Customer789'),
    (9, 'Michael', 'Davis', '789 Oak St', 'michael.davis@email.com', 'P@ssword123'),
    (10, 'Olivia', 'Moore', '543 Elm St', 'olivia.moore@email.com', 'MySecurePwd');


-- insert data: Orders
INSERT INTO Orders VALUES (1, '2024-02-17', 1, 'Processing'),
                           (2, '2024-02-18', 2, 'Shipped'),
                           (3, '2024-02-19', 3, 'Processing'),
                           (4, '2024-02-20', 4, 'Delivered'),
                           (5, '2024-02-21', 5, 'Processing'),
                           (6, '2024-02-22', 6, 'Processing'),
                           (7, '2024-02-23', 7, 'Shipped'),
                           (8, '2024-02-24', 8, 'Processing'),
                           (9, '2024-02-25', 9, 'Processing'),
                           (10, '2024-02-26', 10, 'Shipped');

-- insert data: OrderItems
INSERT INTO OrderItems VALUES (1, 1, 2, 1),
                                 (2, 1, 3, 2),
                                 (3, 2, 1, 2),
                                 (4, 3, 4, 1),
                                 (5, 4, 5, 4),
                                 (6, 5, 6, 1),
                                 (7, 6, 7, 3),
                                 (8, 7, 8, 2),
                                 (9, 8, 9, 4),
                                 (10, 9, 10, 2);

-- insert data: Employees
INSERT INTO Employees (employeeID, firstName, lastName) VALUES
                                                            (1, 'John', 'Doe'),
                                                            (2, 'Jane', 'Smith'),
                                                            (3, 'Bob', 'Johnson'),
                                                            (4, 'Alice', 'Williams'),
                                                            (5, 'David', 'Brown'),
                                                            (6, 'Emily', 'Taylor'),
                                                            (7, 'Chris', 'Anderson'),
                                                            (8, 'Emma', 'Clark'),
                                                            (9, 'Michael', 'Davis'),
                                                            (10, 'Olivia', 'Moore');

-- insert data: Admins
INSERT INTO Admins (adminID, employeeID, password) VALUES
                                                       (1, 1, 'P@ssw0rd!'),
                                                       (2, 2, 'SecurePwd123'),
                                                       (3, 3, 'Customer456'),
                                                       (4, 4, 'SecretPass!'),
                                                       (5, 5, 'MyP@ssw0rd'),
                                                       (6, 6, 'Secure12345'),
                                                       (7, 7, 'Passw0rd!'),
                                                       (8, 8, 'Customer789'),
                                                       (9, 9, 'P@ssword123'),
                                                       (10, 10, 'MySecurePwd');