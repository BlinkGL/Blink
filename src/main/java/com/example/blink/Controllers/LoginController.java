package com.example.blink.Controllers;

import com.example.blink.DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();


        if (email.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Veuillez remplir tous les champs.");
            return;
        }


        // Check credentials in database
        String userRole = UserDAO.validateUser(email, password);

        if (userRole != null) {
            // Login successful
            showAlert(Alert.AlertType.INFORMATION, "Succès", "Connexion réussie !");

            // Navigate based on role
            switch (userRole) {
                case "admin":
                    SceneManager.switchScene(event, "/com/example/blink/admin.fxml");
                    break;
                case "livreur":
                    SceneManager.switchScene(event, "/com/example/blink/livreur-view.fxml");
                    break;
                case "client":
                    SceneManager.switchScene(event, "/com/example/blink/client-view.fxml");
                    break;
                default:
                    showAlert(Alert.AlertType.ERROR, "Erreur", "Rôle utilisateur inconnu.");
            }
        } else {
            // Login failed
            showAlert(Alert.AlertType.ERROR, "Erreur", "Email ou mot de passe incorrect.");
            passwordField.clear();
        }
    }

    @FXML
    private void goToSignUp(ActionEvent event) throws IOException {
        SceneManager.switchScene(event, "/com/example/blink/Sign-up.fxml");
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}