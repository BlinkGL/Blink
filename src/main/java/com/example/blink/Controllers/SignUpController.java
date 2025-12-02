package com.example.blink.Controllers;

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


public class SignUpController {

    @FXML private TextField nomSignUpField;
    @FXML private TextField prenomSignUpField;
    @FXML private TextField emailSignUpField;
    @FXML private PasswordField passwordSignUpField;


    public void handleSignUp() {
        System.out.println("SignUp clicked");
        // TODO: insert into DB
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