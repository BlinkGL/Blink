package com.example.blink.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    public void handleLogin() {
        System.out.println("Login clicked");
        // TODO: validate + check database
    }

    public void goToSignUp() {
        // TODO: load signup.fxml
    }
}

