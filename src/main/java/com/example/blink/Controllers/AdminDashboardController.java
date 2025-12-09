package com.example.blink.Controllers;

import com.example.blink.Class.Livreur;
import com.example.blink.DAO.LivreurDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import java.io.IOException;

public class AdminDashboardController {

    @FXML private Label lblTotalOrders;
    @FXML private Label lblPending;
    @FXML private Label lblActiveCouriers;

    /* COURIER TABLE (LIVREURS) */
    @FXML private TableView<Livreur> couriersTable;

    @FXML private TableColumn<Livreur, Integer> colCourierId;
    @FXML private TableColumn<Livreur, String> colCourierName;
    @FXML private TableColumn<Livreur, String> colCourierPrenom;
    @FXML private TableColumn<Livreur, String> colCourierEmail;
    @FXML private TableColumn<Livreur, String> colCourierPassword;
    @FXML private TableColumn<Livreur, Boolean> colCourierDisponibilite;
    @FXML private TableColumn<Livreur, Void> colCourierActions;

    @FXML
    public void initialize() {
        setupCourierTable();
        loadLivreurs();
        updateDashboardStats();
    }

    /** Set mapping between columns and Livreur fields */
    private void setupCourierTable() {
        // Use property references directly (cleaner with JavaFX properties)
        colCourierId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colCourierName.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        colCourierPrenom.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        colCourierEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colCourierPassword.setCellValueFactory(cellData -> cellData.getValue().motDePassProperty());
        colCourierDisponibilite.setCellValueFactory(cellData -> cellData.getValue().disponibiliteProperty());

        // Make the disponibilite column editable with checkbox
        colCourierDisponibilite.setCellFactory(CheckBoxTableCell.forTableColumn(colCourierDisponibilite));

        // Update database when checkbox is changed
        colCourierDisponibilite.setOnEditCommit(event -> {
            Livreur livreur = event.getRowValue();
            boolean newValue = event.getNewValue();
            livreur.setDisponibilite(newValue);

            // Update in database
            boolean success = LivreurDAO.updateDisponibilite(livreur.getId(), newValue);
            if (!success) {
                showAlert("Erreur", "Impossible de mettre à jour la disponibilité", Alert.AlertType.ERROR);
                // Revert the change if DB update failed
                livreur.setDisponibilite(!newValue);
                couriersTable.refresh();
            }
            updateDashboardStats();
        });

        // Allow table editing
        couriersTable.setEditable(true);

        // Add action buttons (Edit/Delete)
        setupActionColumn();
    }

    /** Setup actions column with buttons */
    private void setupActionColumn() {
        colCourierActions.setCellFactory(new Callback<TableColumn<Livreur, Void>, TableCell<Livreur, Void>>() {
            @Override
            public TableCell<Livreur, Void> call(final TableColumn<Livreur, Void> param) {
                return new TableCell<Livreur, Void>() {
                    private final Button editBtn = new Button("✏️");
                    private final Button deleteBtn = new Button("🗑️");
                    private final HBox pane = new HBox(5, editBtn, deleteBtn);

                    {
                        editBtn.getStyleClass().add("edit-button");
                        deleteBtn.getStyleClass().add("delete-button");

                        editBtn.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-padding: 5 10;");
                        deleteBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-padding: 5 10;");

                        editBtn.setOnAction(event -> {
                            Livreur livreur = getTableView().getItems().get(getIndex());
                            editLivreur(livreur);
                        });

                        deleteBtn.setOnAction(event -> {
                            Livreur livreur = getTableView().getItems().get(getIndex());
                            deleteLivreur(livreur);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(pane);
                        }
                    }
                };
            }
        });
    }

    /** Load livreurs from DB */
    private void loadLivreurs() {
        couriersTable.getItems().setAll(LivreurDAO.getAllLivreurs());
    }

    /** Update dashboard statistics */
    private void updateDashboardStats() {
        int totalLivreurs = LivreurDAO.getAllLivreurs().size();
        long activeLivreurs = LivreurDAO.getAllLivreurs().stream()
                .filter(Livreur::getDisponibilite)
                .count();

        // Update labels
        if (lblTotalOrders != null) {
            lblTotalOrders.setText(String.valueOf(totalLivreurs));
        }
        if (lblActiveCouriers != null) {
            lblActiveCouriers.setText(String.valueOf(activeLivreurs));
        }
        if (lblPending != null) {
            lblPending.setText(String.valueOf(totalLivreurs - activeLivreurs));
        }
    }

    /** Edit livreur */
    private void editLivreur(Livreur livreur) {
        // TODO: Implement edit functionality - open a dialog or form
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modifier Livreur");
        alert.setHeaderText("Modification du livreur");
        alert.setContentText("Fonctionnalité de modification pour " + livreur.getNom() + " " + livreur.getPrenom() + " à implémenter.");
        alert.showAndWait();
    }

    /** Delete livreur */
    private void deleteLivreur(Livreur livreur) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText("Supprimer le livreur");
        alert.setContentText("Êtes-vous sûr de vouloir supprimer " + livreur.getNom() + " " + livreur.getPrenom() + "?\nCette action est irréversible.");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                boolean success = LivreurDAO.deleteLivreur(livreur.getId());
                if (success) {
                    showAlert("Succès", "Livreur supprimé avec succès", Alert.AlertType.INFORMATION);
                    loadLivreurs(); // Refresh table
                    updateDashboardStats(); // Update stats
                } else {
                    showAlert("Erreur", "Impossible de supprimer le livreur", Alert.AlertType.ERROR);
                }
            }
        });
    }

    /** Show alert message */
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void ajouterLivreur(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "Sign-up.fxml");
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        loadLivreurs();
        updateDashboardStats();
        showAlert("Rafraîchissement", "Liste des livreurs mise à jour", Alert.AlertType.INFORMATION);
    }
}