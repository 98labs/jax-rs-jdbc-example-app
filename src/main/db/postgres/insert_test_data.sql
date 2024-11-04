-- Insert sample data into CUSTOMERS
INSERT INTO CUSTOMERS (NAME, EMAIL) VALUES
('Alice Johnson', 'alice.johnson@example.com'),
('Bob Smith', 'bob.smith@example.com'),
('Carol Williams', 'carol.williams@example.com'),
('David Brown', 'david.brown@example.com'),
('Eve Davis', 'eve.davis@example.com'),
('Frank Miller', 'frank.miller@example.com'),
('Grace Wilson', 'grace.wilson@example.com'),
('Hank Moore', 'hank.moore@example.com'),
('Ivy Taylor', 'ivy.taylor@example.com'),
('Jack Anderson', 'jack.anderson@example.com'),
('Kara Thomas', 'kara.thomas@example.com'),
('Leo Jackson', 'leo.jackson@example.com'),
('Mia White', 'mia.white@example.com'),
('Nick Harris', 'nick.harris@example.com'),
('Olivia Martin', 'olivia.martin@example.com'),
('Paul Thompson', 'paul.thompson@example.com'),
('Quinn Martinez', 'quinn.martinez@example.com'),
('Rachel Garcia', 'rachel.garcia@example.com'),
('Steve Robinson', 'steve.robinson@example.com'),
('Tina Clark', 'tina.clark@example.com');

-- Insert sample data into PRODUCTS
INSERT INTO PRODUCTS (NAME, PRICE) VALUES
('Laptop', 999.99),
('Smartphone', 599.99),
('Tablet', 299.99),
('Headphones', 49.99),
('Keyboard', 19.99),
('Mouse', 14.99),
('Monitor', 199.99),
('External Hard Drive', 89.99),
('USB Flash Drive', 9.99),
('Wireless Charger', 24.99),
('Webcam', 39.99),
('Printer', 149.99),
('Router', 79.99),
('Smartwatch', 199.99),
('Bluetooth Speaker', 29.99),
('Gaming Console', 399.99),
('VR Headset', 299.99),
('Drone', 499.99),
('Action Camera', 149.99),
('Fitness Tracker', 49.99);

-- Insert sample data into ORDERS
-- Assign each order to a customer (customer_id from 1 to 20)
-- For variety, we'll set different order dates and amounts
INSERT INTO ORDERS (CUSTOMER_ID, ORDER_DATE, AMOUNT) VALUES
(1, CURRENT_TIMESTAMP - INTERVAL '20 days', 1299.98),
(2, CURRENT_TIMESTAMP - INTERVAL '19 days', 1049.98),
(3, CURRENT_TIMESTAMP - INTERVAL '18 days', 699.98),
(4, CURRENT_TIMESTAMP - INTERVAL '17 days', 399.98),
(5, CURRENT_TIMESTAMP - INTERVAL '16 days', 24.99),
(6, CURRENT_TIMESTAMP - INTERVAL '15 days', 149.99),
(7, CURRENT_TIMESTAMP - INTERVAL '14 days', 69.98),
(8, CURRENT_TIMESTAMP - INTERVAL '13 days', 249.98),
(9, CURRENT_TIMESTAMP - INTERVAL '12 days', 99.98),
(10, CURRENT_TIMESTAMP - INTERVAL '11 days', 199.98),
(11, CURRENT_TIMESTAMP - INTERVAL '10 days', 1249.98),
(12, CURRENT_TIMESTAMP - INTERVAL '9 days', 1049.98),
(13, CURRENT_TIMESTAMP - INTERVAL '8 days', 89.98),
(14, CURRENT_TIMESTAMP - INTERVAL '7 days', 299.99),
(15, CURRENT_TIMESTAMP - INTERVAL '6 days', 39.99),
(16, CURRENT_TIMESTAMP - INTERVAL '5 days', 399.98),
(17, CURRENT_TIMESTAMP - INTERVAL '4 days', 79.98),
(18, CURRENT_TIMESTAMP - INTERVAL '3 days', 699.98),
(19, CURRENT_TIMESTAMP - INTERVAL '2 days', 149.99),
(20, CURRENT_TIMESTAMP - INTERVAL '1 days', 249.98);

-- Insert sample data into ORDER_PRODUCTS
-- Assign products to orders with varying quantities
INSERT INTO ORDER_PRODUCTS (ORDER_ID, PRODUCT_ID, QUANTITY) VALUES
(1, 1, 1), (1, 2, 1),
(2, 3, 2), (2, 4, 1),
(3, 5, 3), (3, 6, 1),
(4, 7, 1), (4, 8, 1),
(5, 9, 1), (5, 10, 2),
(6, 11, 1), (6, 12, 1),
(7, 13, 2), (7, 14, 1),
(8, 15, 1), (8, 16, 1),
(9, 17, 1), (9, 18, 1),
(10, 19, 1), (10, 20, 2),
(11, 1, 1), (11, 3, 1),
(12, 2, 1), (12, 5, 2),
(13, 4, 1), (13, 7, 1),
(14, 9, 2), (14, 11, 1),
(15, 10, 1), (15, 13, 1),
(16, 14, 1), (16, 15, 2),
(17, 16, 1), (17, 19, 1),
(18, 17, 2), (18, 12, 1),
(19, 18, 1), (19, 20, 1),
(20, 6, 2), (20, 8, 1);