package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.service.UserService;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXML;

import java.io.IOException;

import static at.htl.caloriecounter.App.loadFXML;

public class RegisterController {
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private TextField weight;
    @FXML
    private TextField height;

    @FXML
    private void onRegistration(ActionEvent actionEvent) throws IOException {
        String username = this.username.getText();
        String email = this.email.getText();
        String password = this.password.getText();
        String weightStr = this.weight.getText();
        String heightStr = this.height.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || weightStr.isEmpty() || heightStr.isEmpty()) {
            (new Alert(Alert.AlertType.WARNING, "You have to fill out every field!")).show();
            return;
        }

        double weight = -1.0;
        double height = -1.0;

        try {
            weight = Double.parseDouble(weightStr);
            height = Double.parseDouble(heightStr);
        } catch (NumberFormatException e) {
            (new Alert(Alert.AlertType.WARNING, "Weight and height have to be numbers")).show();
            return;
        }

        if (UserService.isValidRegistration(username, email, password, weight, height)) {
            User user = null;

            try {
                user = new User(email, username, password, weight, height, 0);
            } catch (Exception e) {
                (new Alert(Alert.AlertType.ERROR, "Invalid parameters")).show();
            }

            UserService.getInstance().insert(user);

            Stage stage = (Stage) this.username.getScene().getWindow();
            stage.setScene(new Scene(loadFXML("/login")));
        } else {
            (new Alert(Alert.AlertType.WARNING, "Invalid registration")).show();
        }
    }
}