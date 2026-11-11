# Aplikasi Kasir Kedai Ngombe

Aplikasi desktop berbasis Java Swing untuk memudahkan pemesanan minuman seperti kopi dan teh di Kedai Ngombe, Jatinangor. 
Aplikasi ini dibuat untuk memudahkan pemesanan menu di dalam kedai, menginputkan nama, pesanan hingga pembayaran tanpa harus mengantre di kasir.
Proyek ini dibuat untuk praktikum Pemrograman Berorientasi Objek, Program Studi S-1 Teknik Informatika, Universitas Padjadjaran.

## Fitur

- Memilih minuman dan jumlahnya (tidak boleh melebihi stok)
- Keranjang belanja: ubah jumlah dan hapus item
- Menginputkan Nama pada pesanan
- Checkout dengan input uang bayar, total dan kembalian dihitung otomatis
- Struk pembelian setelah checkout

## Teknologi

- Java (Swing)
- MySQL

## Struktur Database

|        Tabel          |                       Isi                          |
|-----------------------|----------------------------------------------------|
| `users`               | Data pengguna (nama)                               |
| `products`            | Daftar menu minuman, diisi langsung lewat database |
| `transactions`        | Data transaksi (total harga, bayar, kembalian)     |
| `transaction_details` | Item yang dibeli per transaksi                     |

## Struktur Class

- **Model**: `User`, `Product`, `CartItem`, `Cart`, `Transaction`
- **Data**: `DatabaseConnection`, `UserDAO`, `ProductDAO`, `TransactionDAO`
- **Logic**: `AuthService`, `CheckoutService`, `ReceiptGenerator`
- **GUI**: `RegisterFrame`, `LoginFrame`, `MenuFrame`, `CartFrame`, `ReceiptDialog`

## Cara Menjalankan

1. Buat database MySQL, lalu jalankan Query SQL untuk membuat tabel dan mengisi data menu.
2. Sesuaikan URL, user, dan password database di `DatabaseConnection`.
3. Pastikan MySQL Connector/J sudah ditambahkan ke project.
4. Jalankan aplikasi dari `LoginFrame`.

## Anggota

- Rasya Islami Akbar (140810250009)
- Ghiyats Khoirul Mala (140810250102)