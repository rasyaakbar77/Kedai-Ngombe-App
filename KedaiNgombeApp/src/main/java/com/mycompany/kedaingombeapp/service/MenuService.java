/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kedaingombeapp.service;

import com.mycompany.kedaingombeapp.database.DatabaseConnection;
import com.mycompany.kedaingombeapp.model.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LOQ
 */
public class MenuService {
    // Ambil semua menu aktif
    public List<Menu> getAll() {
        return query("SELECT * FROM products WHERE aktif = 1 ORDER BY nama_menu", null);
    }

    // Filter per kategori
    public List<Menu> getByKategori(String kategori) {
        return query("SELECT * FROM products WHERE aktif = 1 AND kategori = ? ORDER BY nama_menu", kategori);
    }

    // Cari berdasarkan nama
    public List<Menu> search(String kata) {
        return query("SELECT * FROM products WHERE aktif = 1 AND nama_menu LIKE ? ORDER BY nama_menu", "%" + kata.trim() + "%");
    }

    // Tambah menu baru
    public boolean insert(Menu m) {
        String query = "INSERT INTO products (nama_menu, kategori, harga, stok, gambar) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, m.getNama());
            pstmt.setString(2, m.getKategori());
            pstmt.setInt(3, m.getHarga());
            pstmt.setInt(4, m.getStok());
            pstmt.setString(5, m.getGambar());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error pada proses insert menu: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Ubah menu
    public boolean update(Menu m) {
        String query = "UPDATE products SET nama_menu = ?, kategori = ?, harga = ?, stok = ?, gambar = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, m.getNama());
            pstmt.setString(2, m.getKategori());
            pstmt.setInt(3, m.getHarga());
            pstmt.setInt(4, m.getStok());
            pstmt.setString(5, m.getGambar());
            pstmt.setInt(6, m.getId());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error pada proses update menu: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Hapus menu (soft delete, biar riwayat transaksi tetap aman)
    public boolean delete(int id) {
        String query = "UPDATE products SET aktif = 0 WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error pada proses delete menu: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // Helper: jalanin SELECT, hasilnya jadi List<Menu>
    private List<Menu> query(String query, String param) {
        List<Menu> list = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            if (param != null) {
                pstmt.setString(1, param);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Menu m = new Menu();
                m.setId(rs.getInt("id"));
                m.setNama(rs.getString("nama_menu"));
                m.setKategori(rs.getString("kategori"));
                m.setHarga(rs.getInt("harga"));
                m.setStok(rs.getInt("stok"));
                m.setGambar(rs.getString("gambar"));
                list.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Error pada proses ambil menu: " + e.getMessage());
            e.printStackTrace();
        }

        return list;
    }
}
