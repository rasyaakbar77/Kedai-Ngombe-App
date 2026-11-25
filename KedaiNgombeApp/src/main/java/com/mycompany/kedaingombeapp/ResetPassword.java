package com.mycompany.kedaingombeapp;

import com.mycompany.kedaingombeapp.database.DatabaseConnection;
import com.mycompany.kedaingombeapp.util.PasswordUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Utilitas sekali pakai: set ulang password akun (disimpan sebagai hash).
// Isi daftar AKUN di bawah, jalankan, lalu KOSONGKAN lagi daftarnya
// supaya password tidak tertinggal di kode.
public class ResetPassword {

    // {email, passwordBaru}
    private static final String[][] AKUN = {
        {"adminngombe@gmail.com", "12345678"},
        {"custngombe@gmail.com", "12345678"},
    };

    public static void main(String[] args) {
        String query = "UPDATE user SET PASSWORD = ? WHERE email = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (String[] akun : AKUN) {
                pstmt.setString(1, PasswordUtil.hash(akun[1]));
                pstmt.setString(2, akun[0]);
                int baris = pstmt.executeUpdate();
                System.out.println(akun[0] + " -> " + (baris > 0 ? "berhasil" : "email tidak ditemukan"));
            }

        } catch (SQLException e) {
            System.out.println("Error pada proses reset password: " + e.getMessage());
            e.printStackTrace();
        }
    }
}