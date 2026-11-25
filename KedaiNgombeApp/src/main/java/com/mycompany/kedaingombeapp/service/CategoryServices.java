package com.mycompany.kedaingombeapp.service;

import com.mycompany.kedaingombeapp.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryServices {

    // Ambil semua nama kategori
    public List<String> getNama() {
        List<String> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement("SELECT nama FROM categories ORDER BY nama")) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("nama"));
            }

        } catch (SQLException e) {
            System.out.println("Error pada proses ambil kategori: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    // Kategori beserta jumlah menu aktifnya: {nama, jumlah}
    public List<Object[]> getDenganJumlah() {
        List<Object[]> list = new ArrayList<>();
        String query = "SELECT c.nama, COUNT(p.id) AS jumlah FROM categories c "
                + "LEFT JOIN products p ON p.kategori = c.nama AND p.aktif = 1 "
                + "GROUP BY c.nama ORDER BY c.nama";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Object[]{rs.getString("nama"), rs.getInt("jumlah")});
            }

        } catch (SQLException e) {
            System.out.println("Error pada proses ambil kategori: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }

    // Tambah kategori baru (false kalau nama sudah ada)
    public boolean insert(String nama) {
        String query = "INSERT INTO categories (nama) VALUES (?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nama);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error pada proses insert kategori: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Ganti nama kategori sekaligus menu yang memakainya (satu transaksi)
    public boolean rename(String lama, String baru) {
        String qKategori = "UPDATE categories SET nama = ? WHERE nama = ?";
        String qMenu = "UPDATE products SET kategori = ? WHERE kategori = ?";

        Connection conn = DatabaseConnection.connect();
        if (conn == null) return false;

        try {
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(qKategori)) {
                ps.setString(1, baru);
                ps.setString(2, lama);
                if (ps.executeUpdate() == 0) throw new SQLException("Kategori tidak ditemukan: " + lama);
            }

            try (PreparedStatement ps = conn.prepareStatement(qMenu)) {
                ps.setString(1, baru);
                ps.setString(2, lama);
                ps.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.out.println("Error pada proses ubah kategori: " + e.getMessage());
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            return false;
        } finally {
            try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }

    // Jumlah menu aktif pada satu kategori
    public int jumlahMenu(String nama) {
        String query = "SELECT COUNT(*) FROM products WHERE kategori = ? AND aktif = 1";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nama);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("Error pada proses hitung menu: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    // Hapus kategori, hanya kalau tidak ada menu aktif di dalamnya
    public boolean delete(String nama) {
        if (jumlahMenu(nama) > 0) return false;

        String query = "DELETE FROM categories WHERE nama = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nama);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error pada proses delete kategori: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}