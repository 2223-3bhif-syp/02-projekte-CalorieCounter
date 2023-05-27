package at.htl.caloriecounter.controller;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static at.htl.caloriecounter.App.loadFXML;
import static at.htl.caloriecounter.repositories.UserRepository.isValidUser;

public class LoginController {

    public TextField username;
    public PasswordField password;
    public Button submitLogin;

    public void onLoginBtn(ActionEvent actionEvent) throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            (new Alert(Alert.AlertType.ERROR, "You have to fill out every field")).show();
            return;
        }

        if (isValidUser(username, password)) {
            System.out.println("Load");
            Stage stage = (Stage) submitLogin.getScene().getWindow();
            stage.setScene(new Scene(loadFXML("/calorie-counter"), 800, 800));
        } else {
            (new Alert(Alert.AlertType.ERROR, "Invalid username or password")).show();
        }
    }

}