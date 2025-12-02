package com.example.blink.Controllers;

import com.example.blink.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;


    public void handleLogin() {

        // TODO: validate + check database
    }

    @FXML
    private void goToSignUp(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/blink/Sign-up.fxml"));

        Scene scene = new Scene(root,320,240);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setFullScreen(true);

        stage.show();
    }
}

