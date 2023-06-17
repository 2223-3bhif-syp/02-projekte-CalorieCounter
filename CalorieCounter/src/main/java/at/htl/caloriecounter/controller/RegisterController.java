package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

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
    private TextField ageField;

    @FXML
    private void onRegistration(ActionEvent actionEvent) throws IOException {
        String username = this.username.getText();
        String email = this.email.getText();
        String password = this.password.getText();
        double weight = Double.parseDouble(this.weight.getText());
        double height = Double.parseDouble(this.height.getText());
        Date age = new Date(this.ageField.getText());

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || weight == 0 || height == 0) {
            (new Alert(Alert.AlertType.ERROR, "You have to fill out every field")).show();
            return;
        };

        if (UserService.isValidRegistration(username, email, password, weight, height)) {

            try {
                UserService.getInstance().insert(new User(email, username, password, weight, height, age));
            } catch (Exception e) {
                (new Alert(Alert.AlertType.ERROR, "Invalid parameters")).show();
            }

            Stage stage = (Stage) this.username.getScene().getWindow();
            stage.setScene(new Scene(loadFXML("/calorie-counter"), 640, 550));
        } else {
            (new Alert(Alert.AlertType.WARNING, "Invalid registration")).show();
        }
    }

    public void onLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.username.getScene().getWindow();
        stage.setScene(new Scene(loadFXML("/login"), 640, 550));
    }
}
