INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');
INSERT INTO users (username, password, role_id) VALUES ('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH', 1);
INSERT INTO products (name, sku, description, price, stock, category) VALUES
('Laptop Dell XPS', 'LAP-001', 'High performance laptop', 1200.00, 10, 'Electronics'),
('Wireless Mouse', 'MOU-002', 'Ergonomic wireless mouse', 25.50, 50, 'Accessories'),
('Mechanical Keyboard', 'KEY-003', 'RGB Backlit Keyboard', 85.00, 15, 'Accessories'),
('Monitor 24 inch', 'MON-004', 'Full HD IPS Display', 150.00, 20, 'Electronics'),
('USB-C Hub', 'HUB-005', 'Multi-port adapter', 35.00, 30, 'Accessories');
INSERT INTO customers (name, email, phone, address) VALUES
('John Doe', 'john.doe@example.com', '555-0101', '123 Main St, Springfield'),
('Jane Smith', 'jane.smith@example.com', '555-0102', '456 Oak Ave, Shelbyville'),
('Acme Corp', 'contact@acme.com', '555-0103', '789 Industrial Pk, Capital City');