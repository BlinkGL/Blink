package com.example.blink.Controllers;

import com.example.blink.DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
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


        boolean success = UserDAO.createUtilisateur(nom, prenom, email, password);

        if (success) {
            showAlert("Succès", "Compte livreur créé avec succès !");
            System.out.println("Livreur CREATED SUCCESSFULLY");
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

        SceneManager.switchScene(event, "Login.fxml");
    }
}