package com.example.blink.DAO;

import com.example.blink.Class.Commande;
import com.example.blink.Class.StatutCommande;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CommandDAO {

    // 🔹 Create a new Commande and return its generated id_commande
    public int createCommande(int idClient) {
        String sql = "INSERT INTO Commande (id_client, statut) VALUES (?, 'en attente') RETURNING id_commande";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idClient);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id_commande");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error creating Commande: " + e.getMessage());
        }

        return -1; // if failed
    }


    // 🔹 Update Commande status
    public boolean updateStatut(int idCommande, StatutCommande newStatut) {
        String sql = "UPDATE Commande SET statut = ? WHERE id_commande = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Convert enum to PostgreSQL enum string (e.g., EN_ATTENTE -> "en attente")
            String pgStatut = newStatut.name().toLowerCase().replace("_", " ");
            stmt.setString(1, pgStatut);
            stmt.setInt(2, idCommande);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating Commande status: " + e.getMessage());
        }

        return false;
    }


    // 🔹 Get all commandes (optional method)
    public ObservableList<Commande> getAllCommandes() {
        ObservableList<Commande> list = FXCollections.observableArrayList();

        String sql = "SELECT id_commande, id_client, id_livraison, statut FROM Commande ORDER BY id_commande";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {



            while (rs.next()) {
                StatutCommande statut = StatutCommande.valueOf(rs.getString("statut").toUpperCase().replace(" ", "_"));

                list.add(new Commande(
                        rs.getInt("id_commande"),
                        rs.getInt("id_client"),
                        rs.getObject("id_livraison") != null ? rs.getInt("id_livraison") : null,
                        statut
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error loading Commandes: " + e.getMessage());
        }

        return list;
    }

    // 🔹 Assign a livreur to a command
    public boolean assignLivreur(int idCommande, int idLivreur) {
        String sql = "UPDATE Commande SET id_livraison = ? WHERE id_commande = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLivreur);
            stmt.setInt(2, idCommande);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error assigning livreur: " + e.getMessage());
        }

        return false;
    }
}
