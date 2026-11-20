-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.4.3 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for db_ngombe
CREATE DATABASE IF NOT EXISTS `db_ngombe` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db_ngombe`;

-- Dumping structure for table db_ngombe.products
CREATE TABLE IF NOT EXISTS `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nama_menu` varchar(50) NOT NULL,
  `kategori` enum('Milk Tea','Jasmine Tea','Coffee','Smoothies','Freshmilk','Black Tea') NOT NULL,
  `harga` int NOT NULL,
  `stok` int NOT NULL DEFAULT '0',
  `gambar` varchar(100) DEFAULT NULL,
  `aktif` tinyint(1) NOT NULL DEFAULT '1',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_ngombe.products: ~43 rows (approximately)
INSERT INTO `products` (`id`, `nama_menu`, `kategori`, `harga`, `stok`, `gambar`, `aktif`, `created_at`) VALUES
	(1, 'Chocolate Milk Tea', 'Milk Tea', 15000, 8, 'images/Chocolate_Milk_Tea.jpg', 0, '2026-11-17 15:02:29'),
	(6, 'Black Milk Tea', 'Milk Tea', 15000, 25, 'images/Black_Milk_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(7, 'Chocolate Milk Tea', 'Milk Tea', 15000, 30, 'images/Chocolate_Milk_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(8, 'Jasmine Milk Tea', 'Milk Tea', 15000, 30, 'images/Jasmine_Milk_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(9, 'Strawberry Milk Tea', 'Milk Tea', 15000, 30, 'images/Strawberry_Milk_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(10, 'Taro Milk Tea', 'Milk Tea', 15000, 30, 'images/Taro_Milk_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(11, 'Honeydew Tea', 'Jasmine Tea', 10000, 30, 'images/Honeydew_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(12, 'Lemon Tea', 'Jasmine Tea', 10000, 30, 'images/Lemon_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(13, 'Lychee Tea', 'Jasmine Tea', 10000, 30, 'images/Lychee_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(14, 'Manggo Tea', 'Jasmine Tea', 10000, 30, 'images/Manggo_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(15, 'Mocca Tea', 'Jasmine Tea', 10000, 30, 'images/Mocca_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(16, 'Orange Tea', 'Jasmine Tea', 10000, 30, 'images/Orange_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(17, 'Redguava Tea', 'Jasmine Tea', 10000, 30, 'images/Redguava_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(18, 'Soursop Tea', 'Jasmine Tea', 10000, 30, 'images/Soursop_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(19, 'Strawberry Tea', 'Jasmine Tea', 10000, 30, 'images/Strawberry_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(20, 'Vanilla Tea', 'Jasmine Tea', 10000, 30, 'images/Vanilla_Tea.jpg', 1, '2026-11-20 04:12:43'),
	(21, 'Es Kopi Susu', 'Coffee', 15000, 30, 'images/Es_Kopi_Susu.jpg', 1, '2026-11-20 04:12:43'),
	(22, 'Es Kopi Susu Aren', 'Coffee', 17000, 30, 'images/Es_Kopi_Susu_Aren.jpg', 1, '2026-11-20 04:12:43'),
	(23, 'Es Kopi Susu Coklat', 'Coffee', 17000, 30, 'images/Es_Kopi_Susu_Coklat.jpg', 1, '2026-11-20 04:12:43'),
	(24, 'Kopi Susu Arabika 250ml', 'Coffee', 18000, 30, 'images/Kopi_Susu_Arabika_250ml.jpg', 1, '2026-11-20 04:12:43'),
	(25, 'Kopi Susu Nangka', 'Coffee', 17000, 30, 'images/Kopi_Susu_Nangka.jpg', 1, '2026-11-20 04:12:43'),
	(26, 'Chocolate Smoothies', 'Smoothies', 18000, 30, 'images/Chocolate_Smoothies.jpg', 1, '2026-11-20 04:12:43'),
	(27, 'Honeydew Smoothies', 'Smoothies', 18000, 30, 'images/Honeydew_Smoothies.jpg', 1, '2026-11-20 04:12:43'),
	(28, 'Matcha Smoothies', 'Smoothies', 18000, 30, 'images/Matcha_Smoothies.jpg', 1, '2026-11-20 04:12:43'),
	(29, 'Strawberry Smoothies', 'Smoothies', 18000, 30, 'images/Strawberry_Smoothies.jpg', 1, '2026-11-20 04:12:43'),
	(30, 'Taro Smoothies', 'Smoothies', 18000, 30, 'images/Taro_Smoothies.jpg', 1, '2026-11-20 04:12:43'),
	(31, 'Chocolate Freshmilk', 'Freshmilk', 15000, 30, 'images/Chocolate_Freshmilk.jpg', 1, '2026-11-20 04:12:43'),
	(32, 'Matcha Freshmilk', 'Freshmilk', 15000, 30, 'images/Matcha_Freshmilk.jpg', 1, '2026-11-20 04:12:43'),
	(33, 'Matcha Latte', 'Freshmilk', 16000, 30, 'images/Matcha_Latte.jpg', 1, '2026-11-20 04:12:43'),
	(34, 'Mocca Freshmilk', 'Freshmilk', 15000, 30, 'images/Mocca_Freshmilk.jpg', 1, '2026-11-20 04:12:43'),
	(35, 'Original Ngombe Freshmilk', 'Freshmilk', 13000, 30, 'images/Original_Ngombe_Freshmilk.jpg', 1, '2026-11-20 04:12:43'),
	(36, 'Strawberry Freshmilk', 'Freshmilk', 15000, 30, 'images/Strawberry_Freshmilk.jpg', 1, '2026-11-20 04:12:43'),
	(37, 'Taro Freshmilk', 'Freshmilk', 15000, 30, 'images/Taro_Freshmilk.jpg', 1, '2026-11-20 04:12:43'),
	(38, 'Tiramisu Freshmilk', 'Freshmilk', 15000, 30, 'images/Tiramisu_Freshmilk.jpg', 1, '2026-11-20 04:12:43'),
	(39, 'Vanilla Freshmilk', 'Freshmilk', 15000, 30, 'images/Vanilla_Freshmilk.jpg', 1, '2026-11-20 04:12:43'),
	(40, 'Honeydew Black Tea', 'Black Tea', 10000, 30, 'images/Honeydew_Blacktea.jpg', 1, '2026-11-20 04:12:43'),
	(41, 'Lemon Black Tea', 'Black Tea', 10000, 30, 'images/Lemon_Blacktea.jpg', 1, '2026-11-20 04:12:43'),
	(42, 'Mocca Black Tea', 'Black Tea', 10000, 30, 'images/Mocca_Blacktea.jpg', 1, '2026-11-20 04:12:43'),
	(43, 'Orange Black Tea', 'Black Tea', 10000, 30, 'images/Orange_Blacktea.jpg', 1, '2026-11-20 04:12:43'),
	(44, 'Original Black Tea', 'Black Tea', 8000, 30, 'images/Original_Blacktea.jpg', 1, '2026-11-20 04:12:43'),
	(45, 'Redguava Black Tea', 'Black Tea', 10000, 30, 'images/Redguava_Blacktea.jpg', 1, '2026-11-20 04:12:43'),
	(46, 'Strawberry Black Tea', 'Black Tea', 10000, 30, 'images/Strawberry_Blacktea.jpg', 1, '2026-11-20 04:12:43'),
	(47, 'Vanilla Black Tea', 'Black Tea', 10000, 30, 'images/Vanilla_Blacktea.jpg', 1, '2026-11-20 04:12:43');

-- Dumping structure for table db_ngombe.transactions
CREATE TABLE IF NOT EXISTS `transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `total_harga` int DEFAULT NULL,
  `bayar` int DEFAULT NULL,
  `kembalian` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_ngombe.transactions: ~4 rows (approximately)
INSERT INTO `transactions` (`id`, `user_id`, `total_harga`, `bayar`, `kembalian`, `created_at`) VALUES
	(1, 2, 150000, 150000, 0, '2026-11-17 17:57:59'),
	(2, 2, 15000, 50000, 35000, '2026-11-17 17:58:56'),
	(3, 4, 15000, 15000, 0, '2026-11-18 02:54:54'),
	(4, 4, 75000, 75000, 0, '2026-11-20 04:14:14');

-- Dumping structure for table db_ngombe.transactions_detail
CREATE TABLE IF NOT EXISTS `transactions_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transactions_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `jumlah` int DEFAULT NULL,
  `subtotal` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `transactions_id` (`transactions_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `transactions_detail_ibfk_1` FOREIGN KEY (`transactions_id`) REFERENCES `transactions` (`id`),
  CONSTRAINT `transactions_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_ngombe.transactions_detail: ~4 rows (approximately)
INSERT INTO `transactions_detail` (`id`, `transactions_id`, `product_id`, `jumlah`, `subtotal`, `created_at`) VALUES
	(1, 1, 1, 10, 150000, '2026-11-17 17:57:59'),
	(2, 2, 1, 1, 15000, '2026-11-17 17:58:56'),
	(3, 3, 1, 1, 15000, '2026-11-18 02:54:54'),
	(4, 4, 6, 5, 75000, '2026-11-20 04:14:14');

-- Dumping structure for table db_ngombe.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) NOT NULL,
  `email` varchar(60) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `ROLE` enum('admin','customer') DEFAULT 'customer',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_ngombe.user: ~3 rows (approximately)
INSERT INTO `user` (`id`, `NAME`, `email`, `PASSWORD`, `ROLE`, `created_at`) VALUES
	(1, 'admin ngombe', 'adminngombe@gmail.com', 'admin123', 'admin', '2026-11-11 07:30:37'),
	(2, 'customer ngombe', 'custngombe@gmail.com', 'customer123', 'customer', '2026-11-11 07:31:06'),
	(4, 'Rasya', 'rasya@gmail.com', '12345678', 'customer', '2026-11-17 14:53:33');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
