package com.example.blink.DAO;


import com.example.blink.DAO.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // Méthode pour créer un utilisateur
    public static boolean createUtilisateur(String nom, String prenom, String email, String motDePass) {

        String sql = "INSERT INTO Utilisateur(nom, prenom, email, motDePass, disponibilite, adresseClient, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, email);
            pst.setString(4, motDePass);
            pst.setObject(5, true, java.sql.Types.BOOLEAN);
            pst.setString(6, null);
            pst.setObject(7, "livreur", java.sql.Types.OTHER);

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String validateUser(String email, String password) {
        String sql = "SELECT role, motDePass FROM Utilisateur WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("motDePass");
                String role = rs.getString("role");

                // Compare passwords (you should use BCrypt in production)
                if (password.equals(storedPassword)) {
                    System.out.println("Login successful for user: " + email + " with role: " + role);
                    return role;
                }
            }

            System.out.println("Login failed for user: " + email);
            return null;

        } catch (SQLException e) {
            System.out.println("Error validating user: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

