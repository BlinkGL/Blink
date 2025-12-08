package com.example.blink.Controllers;

import com.example.blink.DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;


import java.io.IOException;


public class SignUpController {

    @FXML private TextField nomSignUpField;
    @FXML private TextField prenomSignUpField;
    @FXML private TextField emailSignUpField;
    @FXML private PasswordField passwordSignUpField;


    public void handleSignUp() {

        String nom = nomSignUpField.getText();
        String prenom = prenomSignUpField.getText();
        String email = emailSignUpField.getText();
        String password = passwordSignUpField.getText();

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert("Erreur", "Tous les champs doivent être remplis.");
            return;
        }

        String role = "admin";

        boolean success = UserDAO.createUtilisateur(nom, prenom, email, password, role);

        if (success) {
            showAlert("Succès", "Compte admin créé avec succès !");
            System.out.println("ADMIN CREATED SUCCESSFULLY");
        } else {
            showAlert("Erreur", "Erreur lors de la création du compte.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }





    public void goToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/blink/Login.fxml"));

        Scene scene = new Scene(root,320,240);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setFullScreen(true);

        stage.show();
    }
}