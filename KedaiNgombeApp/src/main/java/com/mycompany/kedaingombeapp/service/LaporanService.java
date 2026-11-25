package com.mycompany.kedaingombeapp.service;

import com.mycompany.kedaingombeapp.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    // Total pendapatan dalam rentang tanggal (inklusif)
    public int totalRentang(Date dari, Date sampai) {
        return ambilAngka("SELECT COALESCE(SUM(total_harga), 0) FROM transactions "
                + "WHERE DATE(created_at) BETWEEN ? AND ?", dari, sampai);
    }

    // Daftar transaksi pada satu tanggal: {id, pembeli, total, bayar, kembalian, waktu}
    public List<Object[]> daftarHarian(Date tanggal) {
        return ambilDaftar("WHERE DATE(t.created_at) = ?", tanggal);
    }

    // Daftar transaksi dalam rentang tanggal (inklusif), kolom sama dengan daftarHarian
    public List<Object[]> daftarRentang(Date dari, Date sampai) {
        return ambilDaftar("WHERE DATE(t.created_at) BETWEEN ? AND ?", dari, sampai);
    }

    // Helper: SELECT daftar transaksi dengan kondisi WHERE tertentu
    private List<Object[]> ambilDaftar(String kondisi, Date... params) {
        List<Object[]> list = new ArrayList<>();
        String query = "SELECT t.id, u.NAME, t.total_harga, t.bayar, t.kembalian, t.created_at "
                + "FROM transactions t LEFT JOIN user u ON t.user_id = u.id "
                + kondisi + " ORDER BY t.created_at DESC";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            for (int i = 0; i < params.length; i++) {
                pstmt.setDate(i + 1, params[i]);
            }
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getInt("id"), rs.getString("NAME"), rs.getInt("total_harga"),
                    rs.getInt("bayar"), rs.getInt("kembalian"), rs.getTimestamp("created_at")
                });
            }
        } catch (SQLException e) {
            System.out.println("Error pada proses laporan: " + e.getMessage());
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