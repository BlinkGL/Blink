package com.example.blink.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {
    @FXML private Label lblTotalOrders;
    @FXML private Label lblPending;
    @FXML private Label lblActiveCouriers;

    @FXML private TableView<?> ordersTable;
    @FXML private TableColumn<?, ?> colOrderId;
    @FXML private TableColumn<?, ?> colStoreName;
    @FXML private TableColumn<?, ?> colCustomer;
    @FXML private TableColumn<?, ?> colStatus;
    @FXML private TableColumn<?, ?> colCourier;
    @FXML private TableColumn<?, ?> colAction;

    @FXML
    public void initialize() {
        // TODO: populate table with data
        // TODO: update labels with real stats
    }

    @FXML
    private void ajouterLivreur(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/blink/Sign-up.fxml"));

        Scene scene = new Scene(root,320,240);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setFullScreen(true);

        stage.show();
    }
}
