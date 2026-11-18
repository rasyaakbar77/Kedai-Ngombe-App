package com.mycompany.kedaingombeapp.service;

import com.mycompany.kedaingombeapp.database.DatabaseConnection;
import com.mycompany.kedaingombeapp.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {

    // Proses Login
    public User login(String email, String password) {
        String query = "SELECT id, NAME, email, ROLE FROM user WHERE email = ? AND PASSWORD = ?";

        try (Connection conn = DatabaseConnection.connect();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email.trim());
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setNama(rs.getString("NAME"));
            user.setEmail(rs.getString("email"));
            user.setRole(rs.getString("ROLE"));   
            return user;
        }

        } catch (SQLException e) {
            System.out.println("Error pada proses login: " + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
 
    // Proses Register
    public boolean register(String nama, String email, String password) {
        nama = nama.trim();
        email = email.trim();

        if (nama.isEmpty() || email.isEmpty() || password.isEmpty()) {
            return false;
        }
        if (!email.matches("^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)+$")) {
            return false;
        }
        if (password.length() < 8) {
            return false;
        }

        String query = "INSERT INTO user (NAME, email, PASSWORD, ROLE) VALUES (?, ?, ?, 'customer')";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, nama);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.out.println("Error pada proses register: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}