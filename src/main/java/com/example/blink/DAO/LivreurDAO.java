package com.example.blink.DAO;

import com.example.blink.Class.Livreur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreurDAO {

    public static List<Livreur> getAllLivreurs() {
        List<Livreur> list = new ArrayList<>();

        String sql = "SELECT id, nom, prenom, email, motDePass, role, disponibilite " +
                "FROM Utilisateur WHERE role = 'livreur'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Livreur livreur = new Livreur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getString("motDePass"),
                        rs.getString("role"),
                        rs.getBoolean("disponibilite")
                );

                list.add(livreur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static boolean deleteLivreur(int id) {
        String sql = "DELETE FROM Utilisateur WHERE id = ? AND role = 'livreur'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateDisponibilite(int id, boolean disponibilite) {
        String sql = "UPDATE Utilisateur SET disponibilite = ? WHERE id = ? AND role = 'livreur'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setBoolean(1, disponibilite);
            pst.setInt(2, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}