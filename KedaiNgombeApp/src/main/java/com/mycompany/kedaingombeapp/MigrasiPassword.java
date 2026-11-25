package com.mycompany.kedaingombeapp;

import com.mycompany.kedaingombeapp.database.DatabaseConnection;
import com.mycompany.kedaingombeapp.util.PasswordUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Dijalankan SEKALI untuk meng-hash password lama yang masih teks asli.
// Aman dijalankan ulang: password yang sudah berformat hash dilewati.
public class MigrasiPassword {

    public static void main(String[] args) {
        int diubah = 0, dilewati = 0;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement select = conn.prepareStatement("SELECT id, PASSWORD FROM user");
             PreparedStatement update = conn.prepareStatement("UPDATE user SET PASSWORD = ? WHERE id = ?");
             ResultSet rs = select.executeQuery()) {

            while (rs.next()) {
                String lama = rs.getString("PASSWORD");
                if (PasswordUtil.sudahDihash(lama)) {
                    dilewati++;
                    continue;
                }
                update.setString(1, PasswordUtil.hash(lama));
                update.setInt(2, rs.getInt("id"));
                update.executeUpdate();
                diubah++;
            }

        } catch (SQLException e) {
            System.out.println("Error pada proses migrasi password: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        System.out.println("Selesai. Di-hash: " + diubah + ", dilewati: " + dilewati);
    }
}