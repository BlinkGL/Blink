package com.example.blink.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class SignUpController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;

    public void handleSignUp() {
        System.out.println("SignUp clicked");
        // TODO: insert into DB
    }

    public void goToLogin() {
        // TODO: load login.fxml
    }
}