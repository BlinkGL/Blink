package com.example.blink.DAO;


import com.example.blink.DAO.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {

    // Méthode pour créer un utilisateur
    public static boolean createUtilisateur(String nom, String prenom, String email, String motDePass, String role) {

        String sql = "INSERT INTO Utilisateur(nom, prenom, email, motDePass, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, nom);
            pst.setString(2, prenom);
            pst.setString(3, email);
            pst.setString(4, motDePass);
            pst.setString(5, role);

            pst.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

