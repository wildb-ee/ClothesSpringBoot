-- DROP DATABASE IF EXISTS my_db ;
-- CREATE DATABASE my_db;
USE my_db;

INSERT INTO brand (name, description) VALUES 
('Nike', 'Global brand specializing in athletic footwear, apparel, and accessories'),
('Levi\'s', 'Iconic American denim brand known for its jeans and casual wear'),
('H&M', 'International fashion retailer offering trendy and affordable clothing for men, women, and kids'),
('Zara', 'Spanish apparel retailer known for its fast-fashion clothing and accessories'),
('Gap', 'American clothing and accessories retailer offering casual apparel for men, women, and kids'),
('Forever 21', 'American fast-fashion retailer known for its trendy and affordable clothing and accessories'),
('Adidas', 'Global sportswear brand offering athletic footwear, apparel, and accessories'),
('Calvin Klein', 'American luxury fashion brand known for its minimalist designs and iconic underwear'),
('Tommy Hilfiger', 'American fashion brand offering preppy, classic apparel and accessories'),
('Ralph Lauren', 'American fashion brand known for its timeless, classic designs and luxury clothing');


INSERT INTO brand (name, description) VALUES 
('Armani Exchange', 'Fashion brand offering modern, urban clothing and accessories'),
('Gucci', 'Luxury fashion brand known for its high-end clothing, accessories, and leather goods'),
('Versace', 'Italian luxury fashion company known for its bold and glamorous designs'),
('Prada', 'Italian luxury fashion house specializing in leather handbags, clothing, and accessories'),
('Chanel', 'French fashion house known for its timeless elegance and luxury clothing, accessories, and fragrance'),
('Burberry', 'British luxury fashion house known for its iconic trench coats and check pattern'),
('Dior', 'French luxury goods company known for its haute couture, ready-to-wear clothing, and accessories'),
('Louis Vuitton', 'French luxury fashion brand known for its leather goods, accessories, and ready-to-wear clothing'),
('Balenciaga', 'Luxury fashion brand known for its avant-garde designs and high-fashion clothing and accessories'),
('Saint Laurent', 'French luxury fashion house known for its edgy, rock-and-roll-inspired clothing and accessories');



INSERT INTO category (name) VALUES 
('T-Shirt'),
('Jeans'),
('Dress'),
('Sweater'),
('Jacket'),
('Skirt'),
('Polo Shirt'),
('Blouse'),
('Jumper'),
('Coat'),
('Hoodie'),
('Pants'),
('Blazer'),
('Dress Shirt'),
('Sneakers'),
('Purse'),
('Skirt'),
('Trench Coat'),
('Handbag'),
('Sunglasses');



INSERT INTO product (name, brand_id, price, category_id, size, color, image, description, stock_quantity) VALUES 
('T-Shirt 1', 1, 19.99, 1, 'M', 'Blue', 'tshirt1.jpg', 'Comfortable cotton t-shirt for everyday wear', 100),
('Jeans 1', 2, 39.99, 2, '32x32', 'Blue', 'jeans1.jpg', 'Classic slim-fit jeans made of durable denim', 80),
('Dress 1', 3, 29.99, 3, 'S', 'Black', 'dress1.jpg', 'Elegant black dress perfect for any occasion', 120),
('Sweater 1', 4, 49.99, 4, 'L', 'Gray', 'sweater1.jpg', 'Cozy knit sweater to keep you warm in colder weather', 90),
('Jacket 1', 5, 59.99, 5, 'M', 'Brown', 'jacket1.jpg', 'Stylish leather jacket for a rugged look', 110),
('Skirt 1', 6, 34.99, 6, 'S', 'Red', 'skirt1.jpg', 'Flirty red skirt with a playful design', 70),
('Polo Shirt 1', 7, 24.99, 7, 'L', 'Green', 'poloshirt1.jpg', 'Casual polo shirt for a sporty look', 150),
('Blouse 1', 8, 29.99, 8, 'M', 'White', 'blouse1.jpg', 'Chic white blouse suitable for formal or casual occasions', 130),
('Jumper 1', 9, 39.99, 9, 'XL', 'Navy', 'jumper1.jpg', 'Versatile navy jumper for a laid-back style', 100),
('Coat 1', 10, 79.99, 10, 'L', 'Beige', 'coat1.jpg', 'Classic beige coat to complete your winter wardrobe', 80);


INSERT INTO product (name, brand_id, price, category_id, size, color, image, description, stock_quantity) VALUES 
('Hoodie 1', 11, 59.99, 11, 'L', 'Black', 'hoodie1.jpg', 'Cozy hooded sweatshirt for casual and athletic wear', 120),
('Pants 1', 12, 49.99, 12, '30x32', 'Khaki', 'pants1.jpg', 'Classic khaki pants for a polished yet casual look', 100),
('Blazer 1', 13, 199.99, 13, 'M', 'Navy', 'blazer1.jpg', 'Sophisticated navy blazer for formal occasions', 80),
('Dress Shirt 1', 14, 129.99, 14, '16.5', 'White', 'dressshirt1.jpg', 'Tailored white dress shirt for a sharp and professional appearance', 150),
('Sneakers 1', 15, 149.99, 15, 'US 10', 'White', 'sneakers1.jpg', 'Stylish white sneakers for everyday wear', 200),
('Purse 1', 16, 399.99, 16, 'One Size', 'Brown', 'purse1.jpg', 'Luxurious brown leather purse with gold hardware', 90),
('Skirt 2', 17, 79.99, 17, 'XS', 'Black', 'skirt2.jpg', 'Chic black skirt with a flattering A-line silhouette', 120),
('Trench Coat 1', 18, 299.99, 18, 'S', 'Beige', 'trenchcoat1.jpg', 'Iconic beige trench coat for timeless style in any weather', 80),
('Handbag 1', 19, 699.99, 19, 'One Size', 'Black', 'handbag1.jpg', 'Elegant black leather handbag with silver chain strap', 100),
('Sunglasses 1', 20, 249.99, 20, 'One Size', 'Black', 'sunglasses1.jpg', 'Classic black sunglasses with gold accents for a glamorous look', 150);




INSERT INTO order_status (name) VALUES ('Pending');
INSERT INTO order_status (name) VALUES ('Processing');
INSERT INTO order_status (name) VALUES ('Shipped');
INSERT INTO order_status (name) VALUES ('Delivered');
INSERT INTO order_status (name) VALUES ('Cancelled');