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
import java.sql.Statement;
import java.util.Map;

/**
 *
 * @author LOQ
 */
public class TransaksiService {

    // Simpan transaksi. Balikin id transaksi, atau -1 kalau gagal.
    public int simpan(int userId, Map<Integer, Integer> keranjang,
                      Map<Integer, Menu> infoMenu, int bayar) {

        int total = 0;
        for (Map.Entry<Integer, Integer> e : keranjang.entrySet()) {
            total += infoMenu.get(e.getKey()).getHarga() * e.getValue();
        }
        int kembalian = bayar - total;
        if (kembalian < 0) return -1;

        String qTransaksi = "INSERT INTO transactions (user_id, total_harga, bayar, kembalian) VALUES (?, ?, ?, ?)";
        String qDetail = "INSERT INTO transactions_detail (transactions_id, product_id, jumlah, subtotal) VALUES (?, ?, ?, ?)";
        String qStok = "UPDATE products SET stok = stok - ? WHERE id = ? AND stok >= ?";

        Connection conn = DatabaseConnection.connect();
        if (conn == null) return -1;

        try {
            conn.setAutoCommit(false);

            int idTransaksi;
            try (PreparedStatement ps = conn.prepareStatement(qTransaksi, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, userId);
                ps.setInt(2, total);
                ps.setInt(3, bayar);
                ps.setInt(4, kembalian);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) throw new SQLException("ID transaksi tidak didapat");
                    idTransaksi = rs.getInt(1);
                }
            }

            try (PreparedStatement psDetail = conn.prepareStatement(qDetail);
                 PreparedStatement psStok = conn.prepareStatement(qStok)) {

                for (Map.Entry<Integer, Integer> e : keranjang.entrySet()) {
                    int menuId = e.getKey();
                    int qty = e.getValue();
                    int subtotal = infoMenu.get(menuId).getHarga() * qty;

                    // kurangi stok dulu, kalau 0 baris berarti stok kurang
                    psStok.setInt(1, qty);
                    psStok.setInt(2, menuId);
                    psStok.setInt(3, qty);
                    if (psStok.executeUpdate() == 0) {
                        throw new SQLException("Stok tidak cukup untuk menu id " + menuId);
                    }

                    psDetail.setInt(1, idTransaksi);
                    psDetail.setInt(2, menuId);
                    psDetail.setInt(3, qty);
                    psDetail.setInt(4, subtotal);
                    psDetail.executeUpdate();
                }
            }

            conn.commit();
            return idTransaksi;

        } catch (SQLException e) {
            System.out.println("Error pada proses transaksi: " + e.getMessage());
            try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            return -1;
        } finally {
            try { conn.setAutoCommit(true); conn.close(); } catch (SQLException ex) { ex.printStackTrace(); }
        }
    }
}