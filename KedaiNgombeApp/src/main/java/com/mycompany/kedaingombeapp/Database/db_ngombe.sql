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
  `nama_menu` varchar(50) DEFAULT NULL,
  `kategori` enum('Milk tea','Jasmine Tea','Coffee','Smoothies','Freshmilk','Black Tea') DEFAULT NULL,
  `harga` int DEFAULT NULL,
  `stok` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_ngombe.products: ~0 rows (approximately)

-- Dumping structure for table db_ngombe.transactions
CREATE TABLE IF NOT EXISTS `transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `total_harga` int DEFAULT NULL,
  `bayar` int DEFAULT NULL,
  `kembalian` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_ngombe.transactions: ~0 rows (approximately)

-- Dumping structure for table db_ngombe.trasactions_detail
CREATE TABLE IF NOT EXISTS `trasactions_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transactions_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `jumlah` int DEFAULT NULL,
  `subtotal` int DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `transactions_id` (`transactions_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `trasactions_detail_ibfk_1` FOREIGN KEY (`transactions_id`) REFERENCES `transactions` (`id`),
  CONSTRAINT `trasactions_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_ngombe.trasactions_detail: ~0 rows (approximately)

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_ngombe.user: ~2 rows (approximately)
INSERT INTO `user` (`id`, `NAME`, `email`, `PASSWORD`, `ROLE`, `created_at`) VALUES
	(1, 'admin ngombe', 'adminngombe@gmail.com', 'admin123', 'admin', '2026-11-11 07:30:37'),
	(2, 'customer ngombe', 'custngombe@gmail.com', 'customer123', 'customer', '2026-11-11 07:31:06');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
