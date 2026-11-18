/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.kedaingombeapp.service;

import com.mycompany.kedaingombeapp.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author LOQ
 */
public class LaporanService {

    // Total pendapatan pada satu tanggal
    public int totalHarian(Date tanggal) {
        return ambilAngka("SELECT COALESCE(SUM(total_harga), 0) FROM transactions WHERE DATE(created_at) = ?", tanggal);
    }

    // Total pendapatan pada bulan dari tanggal tersebut
    public int totalBulanan(Date tanggal) {
        return ambilAngka("SELECT COALESCE(SUM(total_harga), 0) FROM transactions "
                + "WHERE YEAR(created_at) = YEAR(?) AND MONTH(created_at) = MONTH(?)", tanggal, tanggal);
    }

    // Daftar transaksi pada satu tanggal: {id, pembeli, total, bayar, kembalian, waktu}
    public List<Object[]> daftarHarian(Date tanggal) {
        List<Object[]> list = new ArrayList<>();
        String query = "SELECT t.id, u.NAME, t.total_harga, t.bayar, t.kembalian, t.created_at "
                + "FROM transactions t LEFT JOIN user u ON t.user_id = u.id "
                + "WHERE DATE(t.created_at) = ? ORDER BY t.created_at DESC";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setDate(1, tanggal);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getInt("id"), rs.getString("NAME"), rs.getInt("total_harga"),
                    rs.getInt("bayar"), rs.getInt("kembalian"), rs.getTimestamp("created_at")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error pada proses laporan harian: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    // Helper: SELECT yang balikin satu angka
    private int ambilAngka(String query, Date... params) {
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                pstmt.setDate(i + 1, params[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            System.out.println("Error pada proses laporan: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}