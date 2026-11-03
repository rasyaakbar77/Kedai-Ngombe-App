# Aplikasi Kasir Kedai Ngombe

Aplikasi desktop berbasis Java Swing untuk memudahkan pemesanan minuman (kopi, non-kopi, dan teh) di Kedai Ngombe. Proyek ini dibuat untuk praktikum Pemrograman Berorientasi Objek, Program Studi S-1 Teknik Informatika, Universitas Padjadjaran.

## Fitur

- Register dan login menggunakan email
  - Password minimal 8 karakter
  - Email yang sudah terdaftar tidak bisa dipakai lagi
- Memilih minuman dan jumlahnya (tidak boleh melebihi stok)
- Keranjang belanja: ubah jumlah dan hapus item
- Checkout dengan input uang bayar, total dan kembalian dihitung otomatis
- Struk pembelian setelah checkout

## Teknologi

- Java (Swing)
- MySQL

## Struktur Database

| Tabel | Isi |
|---|---|
| `users` | Data pengguna (nama, email, password hash) |
| `products` | Daftar menu minuman, diisi langsung lewat database |
| `transactions` | Data transaksi (total, bayar, kembalian) |
| `transaction_details` | Item yang dibeli per transaksi |

## Struktur Class

- **Model**: `User`, `Product`, `CartItem`, `Cart`, `Transaction`
- **Data**: `DatabaseConnection`, `UserDAO`, `ProductDAO`, `TransactionDAO`
- **Logic**: `AuthService`, `CheckoutService`, `ReceiptGenerator`
- **GUI**: `RegisterFrame`, `LoginFrame`, `MenuFrame`, `CartFrame`, `ReceiptDialog`

## Cara Menjalankan

1. Buat database MySQL, lalu jalankan skrip SQL untuk membuat tabel dan mengisi data menu.
2. Sesuaikan URL, user, dan password database di `DatabaseConnection`.
3. Pastikan MySQL Connector/J sudah ditambahkan ke project.
4. Jalankan aplikasi dari `LoginFrame`.

## Anggota

- Rasya Islami Akbar (140810250009)
- Ghiyats Khoirul Mala (140810250102)