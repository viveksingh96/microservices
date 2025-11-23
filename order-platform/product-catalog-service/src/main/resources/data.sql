INSERT INTO category (name, description) VALUES
('Mobiles', 'Smartphones'),
('Laptops', 'Personal computers'),
('Accessories', 'Tech accessories');

INSERT INTO product (name, description, price, stock, category_id)
VALUES
('iPhone 15', 'Apple flagship', 79999, 50, 1),
('Samsung S24', 'Latest Samsung phone', 74999, 40, 1),
('MacBook Air M3', 'Apple laptop', 119999, 30, 2),
('Dell XPS 13', 'Premium laptop', 99999, 20, 2),
('AirPods Pro', 'Wireless earbuds', 24999, 100, 3);
