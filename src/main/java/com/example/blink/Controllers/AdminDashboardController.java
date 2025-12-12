package com.example.blink.Controllers;

import com.example.blink.Class.Colis;
import com.example.blink.Class.Commande;
import com.example.blink.Class.Livreur;
import com.example.blink.Class.StatutCommande;
import com.example.blink.DAO.ColisDAO;
import com.example.blink.DAO.CommandDAO;
import com.example.blink.DAO.LivreurDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.HBox;
import javafx.util.Callback;

public class AdminDashboardController {

    /* ------------------- Dashboard Labels ------------------- */
    @FXML private Label lblTotalOrders;
    @FXML private Label lblPending;
    @FXML private Label lblActiveCouriers;

    /* ------------------- Livreurs Table ------------------- */
    @FXML private TableView<Livreur> couriersTable;
    @FXML private TableColumn<Livreur, Integer> colCourierId;
    @FXML private TableColumn<Livreur, String> colCourierName;
    @FXML private TableColumn<Livreur, String> colCourierPrenom;
    @FXML private TableColumn<Livreur, String> colCourierEmail;
    @FXML private TableColumn<Livreur, String> colCourierPassword;
    @FXML private TableColumn<Livreur, Boolean> colCourierDisponibilite;
    @FXML private TableColumn<Livreur, Void> colCourierActions;

    /* ------------------- Colis Table ------------------- */
    @FXML private TableView<Colis> ColisTable;
    @FXML private TableColumn<Colis, Boolean> colColisSelect;
    @FXML private TableColumn<Colis, Integer> colColisId;
    @FXML private TableColumn<Colis, Integer> colColisIdCommande;
    @FXML private TableColumn<Colis, Integer> colColisIdClient;
    @FXML private TableColumn<Colis, String> colColisNom;
    @FXML private TableColumn<Colis, String> colColisSource;
    @FXML private TableColumn<Colis, Double> colColisPoids;
    @FXML private TableColumn<Colis, Integer> colColisQuantite;
    @FXML private Label lblTotalWeight;
    @FXML private Label lblTotalPackages;
    /* ------------------- Commande Table ------------------- */
    @FXML private TableView<Commande> CommandeTable;
    @FXML private TableColumn<Commande, Boolean> colCommandeSelect;
    @FXML private TableColumn<Commande, Integer> colCommandeId;
    @FXML private TableColumn<Commande, Integer> colCommandeIdClient;
    @FXML private TableColumn<Commande, Integer> colCommandeIdLivraison;
    @FXML private TableColumn<Commande, StatutCommande> colCommandeStatue;

    /* ------------------- DAOs ------------------- */
    private final LivreurDAO livreurDAO = new LivreurDAO();
    private final ColisDAO colisDAO = new ColisDAO();
    private final CommandDAO commandDAO = new CommandDAO();

    @FXML
    public void initialize() {
        setupCourierTable();
        loadLivreurs();

        setupColisTable();
        loadColis();

        setupCommandeTable();
        loadCommandesTable();

        updateDashboardStats();
    }

    /* ------------------- Livreur Table ------------------- */
    private void setupCourierTable() {
        colCourierId.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colCourierName.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        colCourierPrenom.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        colCourierEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colCourierPassword.setCellValueFactory(cellData -> cellData.getValue().motDePassProperty());
        colCourierDisponibilite.setCellValueFactory(cellData -> cellData.getValue().disponibiliteProperty());
        colCourierDisponibilite.setCellFactory(CheckBoxTableCell.forTableColumn(colCourierDisponibilite));

        couriersTable.setEditable(true);

        colCourierDisponibilite.setOnEditCommit(event -> {
            Livreur livreur = event.getRowValue();
            boolean newValue = event.getNewValue();
            livreur.setDisponibilite(newValue);

            boolean success = LivreurDAO.updateDisponibilite(livreur.getId(), newValue);
            if (!success) {
                showAlert("Erreur", "Impossible de mettre à jour la disponibilité", Alert.AlertType.ERROR);
                livreur.setDisponibilite(!newValue);
                couriersTable.refresh();
            }
            updateDashboardStats();
        });

        setupCourierActionColumn();
    }

    /* ------------------- Commande Table ------------------- */
    private void setupCommandeTable() {
        colCommandeSelect.setCellValueFactory(c -> c.getValue().selectedProperty());
        colCommandeSelect.setCellFactory(CheckBoxTableCell.forTableColumn(colCommandeSelect));

        colCommandeId.setCellValueFactory(c -> c.getValue().id_commandeProperty().asObject());
        colCommandeIdClient.setCellValueFactory(c -> c.getValue().id_clientProperty().asObject());
        colCommandeIdLivraison.setCellValueFactory(c -> c.getValue().id_livraisonProperty().asObject());
        colCommandeStatue.setCellValueFactory(c -> new ReadOnlyObjectWrapper<>(c.getValue().getStatut()));

        CommandeTable.setEditable(true);
    }

    /* ------------------- Colis Table ------------------- */
    private void setupColisTable() {
        // Make the column selectable
        colColisSelect.setCellValueFactory(c -> c.getValue().selectedProperty());
        colColisSelect.setCellFactory(CheckBoxTableCell.forTableColumn(colColisSelect));

        // Other columns
        colColisId.setCellValueFactory(c -> c.getValue().idProperty().asObject());
        colColisIdCommande.setCellValueFactory(c -> c.getValue().id_commandeProperty().asObject());
        colColisIdClient.setCellValueFactory(c -> c.getValue().id_clientProperty().asObject());
        colColisNom.setCellValueFactory(c -> c.getValue().nomProperty());
        colColisSource.setCellValueFactory(c -> c.getValue().sourceProperty());
        colColisPoids.setCellValueFactory(c -> c.getValue().poidsProperty().asObject());
        colColisQuantite.setCellValueFactory(c -> c.getValue().quantiteProperty().asObject());

        // Enable editing on table
        ColisTable.setEditable(true);
    }


    private void setupCourierActionColumn() {
        colCourierActions.setCellFactory(new Callback<TableColumn<Livreur, Void>, TableCell<Livreur, Void>>() {
            @Override
            public TableCell<Livreur, Void> call(TableColumn<Livreur, Void> param) {
                return new TableCell<>() {
                    private final Button editBtn = new Button("✏️");
                    private final Button deleteBtn = new Button("🗑️");
                    private final HBox pane = new HBox(5, editBtn, deleteBtn);

                    {
                        editBtn.setOnAction(event -> editLivreur(getTableView().getItems().get(getIndex())));
                        deleteBtn.setOnAction(event -> deleteLivreur(getTableView().getItems().get(getIndex())));
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        setGraphic(empty ? null : pane);
                    }
                };
            }
        });
    }

    private void loadLivreurs() {
        couriersTable.getItems().setAll(livreurDAO.getAllLivreurs());
    }




    private void loadColis() {
        ObservableList<Colis> allColis = colisDAO.getAllColis();
        ObservableList<Colis> availableColis = allColis.filtered(colis -> colis.getId_commande() == 0);
        ColisTable.setItems(availableColis);

        // Add listener to update totals whenever a checkbox is toggled
        for (Colis colis : availableColis) {
            colis.selectedProperty().addListener((obs, oldVal, newVal) -> updateSelectedColisTotals());
        }

        // Initial totals
        updateSelectedColisTotals();
    }

    private void updateSelectedColisTotals() {
        ObservableList<Colis> selected = ColisTable.getItems().filtered(Colis::isSelected);

        double totalWeight = selected.stream().mapToDouble(Colis::getPoids).sum();
        int totalPackages = selected.size();

        lblTotalWeight.setText(String.format("%.1f kg", totalWeight));
        lblTotalPackages.setText(String.valueOf(totalPackages));
    }

    private void calculateColisTotals(ObservableList<Colis> list) {
        double totalWeight = list.stream().mapToDouble(Colis::getPoids).sum();
        int totalPackages = list.size();

        lblTotalWeight.setText(totalWeight + " kg");
        lblTotalPackages.setText(String.valueOf(totalPackages));
    }

    /* Get selected colis */
    public ObservableList<Colis> getSelectedColis() {
        return ColisTable.getItems().filtered(Colis::isSelected);
    }

    public ObservableList<Commande> getSelectedCommandes() {
        return CommandeTable.getItems().filtered(Commande::isSelected);
    }

    /* ------------------- Dashboard Stats ------------------- */
    private void updateDashboardStats() {
        int totalLivreurs = livreurDAO.getAllLivreurs().size();
        long activeLivreurs = livreurDAO.getAllLivreurs().stream()
                .filter(Livreur::getDisponibilite)
                .count();

        if (lblTotalOrders != null) lblTotalOrders.setText(String.valueOf(totalLivreurs));
        if (lblActiveCouriers != null) lblActiveCouriers.setText(String.valueOf(activeLivreurs));
        if (lblPending != null) lblPending.setText(String.valueOf(totalLivreurs - activeLivreurs));
    }

    /* ------------------- Livreur Actions ------------------- */
    private void editLivreur(Livreur livreur) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modifier Livreur");
        alert.setHeaderText("Modification du livreur");
        alert.setContentText("Fonctionnalité à implémenter pour " + livreur.getNom());
        alert.showAndWait();
    }

    private void deleteLivreur(Livreur livreur) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText("Supprimer le livreur");
        alert.setContentText("Voulez-vous vraiment supprimer " + livreur.getNom() + "?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                if (LivreurDAO.deleteLivreur(livreur.getId())) {
                    showAlert("Succès", "Livreur supprimé avec succès", Alert.AlertType.INFORMATION);
                    loadLivreurs();
                    updateDashboardStats();
                } else {
                    showAlert("Erreur", "Impossible de supprimer le livreur", Alert.AlertType.ERROR);
                }
            }
        });
    }

    @FXML
    private void createCommande(ActionEvent event) {
        ObservableList<Colis> selectedColis = getSelectedColis();

        if (selectedColis.isEmpty()) {
            showAlert("Erreur", "Aucun colis sélectionné.", Alert.AlertType.WARNING);
            return;
        }

        // --- Check if all colis have the same id_client ---
        int firstClientId = selectedColis.get(0).getId_client();
        boolean sameClient = selectedColis.stream()
                .allMatch(c -> c.getId_client() == firstClientId);

        if (!sameClient) {
            showAlert("Erreur",
                    "Les colis sélectionnés n'appartiennent pas au même client.\nImpossible de créer la commande.",
                    Alert.AlertType.ERROR);
            return;
        }

        // --- Create the new Commande ---
        CommandDAO commandDAO = new CommandDAO();
        int newCommandeId = commandDAO.createCommande(firstClientId);

        if (newCommandeId == -1) {
            showAlert("Erreur", "Impossible de créer la commande", Alert.AlertType.ERROR);
            return;
        }

        // --- Assign selected colis to this new Commande ---
        for (Colis colis : selectedColis) {
            boolean updated = colisDAO.assignToCommande(colis.getId(), newCommandeId);
            if (!updated) {
                showAlert("Erreur",
                        "Un colis n'a pas pu être assigné à la commande.",
                        Alert.AlertType.ERROR);
            }
        }

        showAlert("Succès", "Commande créée avec succès : ID = " + newCommandeId, Alert.AlertType.INFORMATION);

        // Refresh colis table (to remove assigned colis)
        loadColis();

        // Refresh commandes table if you have one
        loadCommandesTable();
    }

    @FXML
    private void createLivraison(ActionEvent event) {
        ObservableList<Commande> selectedCommandes = getSelectedCommandes();

        if (selectedCommandes.isEmpty()) {
            showAlert("Erreur", "Aucune commande sélectionnée.", Alert.AlertType.WARNING);
            return;
        }

        // --- Create new Livraison ---
        CommandDAO commandDAO = new CommandDAO();
        int newLivraisonId = commandDAO.createLivraison(); // implement in DAO

        if (newLivraisonId == -1) {
            showAlert("Erreur", "Impossible de créer la livraison.", Alert.AlertType.ERROR);
            return;
        }

        // --- Assign selected commandes to this new livraison ---
        for (Commande commande : selectedCommandes) {
            boolean updated = commandDAO.assignLivraison(commande.getId_commande(), newLivraisonId);
            if (!updated) {
                showAlert("Erreur",
                        "Une commande n'a pas pu être assignée à la livraison.",
                        Alert.AlertType.ERROR);
            }
        }

        showAlert("Succès", "Livraison créée avec succès : ID = " + newLivraisonId, Alert.AlertType.INFORMATION);

        // --- Refresh CommandeTable (remove commandes assigned to a livraison) ---
        loadCommandesTable();
    }

    private void loadCommandesTable() {
        ObservableList<Commande> allCommandes = commandDAO.getAllCommandes();
        ObservableList<Commande> pendingCommandes = allCommandes.filtered(c -> c.getId_livraison() == 0);
        CommandeTable.setItems(pendingCommandes);

        // Add listener to keep selection if you have CheckBox
        for (Commande commande : pendingCommandes) {
            commande.selectedProperty().addListener((obs, oldVal, newVal) -> {
                // You can update any dashboard or totals if needed
            });
        }
    }


    /* ------------------- Optional Buttons ------------------- */
    @FXML
    private void ajouterLivreur(ActionEvent event) { /* TODO: open form */ }

    @FXML
    private void refreshTable(ActionEvent event) {
        loadLivreurs();
        loadColis();
        updateDashboardStats();
    }

    /* ------------------- Alerts ------------------- */
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
