package com.example.blink.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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
}
