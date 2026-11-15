package com.mycompany.kedaingombeapp;

import com.mycompany.kedaingombeapp.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthService {

    // {Proses Login
    public User login(String email, String password) {
        String query = "SELECT * FROM user WHERE email = ? AND PASSWORD = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id")); 
                user.setNama(rs.getString("NAME")); 
                user.setEmail(rs.getString("email"));
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
        if (nama.isEmpty() || email.isEmpty() || password.isEmpty()) {
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