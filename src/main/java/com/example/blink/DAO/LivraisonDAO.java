package com.example.blink.DAO;

import com.example.blink.Class.Livraison;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class LivraisonDAO {

    // Get all Livraisons
    public ObservableList<Livraison> getAllLivraisons() {
        ObservableList<Livraison> list = FXCollections.observableArrayList();

        String query = "SELECT id, statut, id_livreur FROM Livraison ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                // For id_livreur, check if null
                Integer idLivreur = rs.getObject("id_livreur") != null ? rs.getInt("id_livreur") : null;

                list.add(new Livraison(
                        rs.getInt("id"),
                        rs.getString("statut"),
                        idLivreur
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error loading Livraison data: " + e.getMessage());
        }

        return list;
    }



    // Create a new Livraison (returns new ID)
    public int createLivraison() {
        String sql = "INSERT INTO Livraison (statut) VALUES ('en_attente') RETURNING id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            System.err.println("Error creating Livraison: " + e.getMessage());
        }

        return -1; // Error
    }

    // Assign a Livreur to a Livraison
    public boolean assignLivreur(int livraisonId, int livreurId) {
        String sql = "UPDATE Livraison SET id_livreur = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, livreurId);
            stmt.setInt(2, livraisonId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error assigning Livreur: " + e.getMessage());
        }

        return false;
    }

    // Optional: Update statut
    public boolean updateStatut(int livraisonId, String statut) {
        String sql = "UPDATE Livraison SET statut = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, statut);
            stmt.setInt(2, livraisonId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating Livraison statut: " + e.getMessage());
        }

        return false;
    }

}
