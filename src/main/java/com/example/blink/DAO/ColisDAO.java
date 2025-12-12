package com.example.blink.DAO;

import com.example.blink.Class.Colis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ColisDAO {

    // Get all colis with id_commande NULL or 0
    public ObservableList<Colis> getAllColis() {
        ObservableList<Colis> list = FXCollections.observableArrayList();

        String query = "SELECT id, id_commande, nom, source, poids, quantite " +
                "FROM Colis " +
                "WHERE id_commande IS NULL OR id_commande = 0 " +
                "ORDER BY id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Colis(
                        rs.getInt("id"),
                        rs.getObject("id_commande") == null ? 0 : rs.getInt("id_commande"),
                        rs.getString("nom"),
                        rs.getString("source"),
                        rs.getDouble("poids"),
                        rs.getInt("quantite")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Error loading Colis data: " + e.getMessage());
        }

        return list;
    }
}
