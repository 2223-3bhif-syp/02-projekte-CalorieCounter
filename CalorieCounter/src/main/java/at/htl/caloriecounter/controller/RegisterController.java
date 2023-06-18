package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

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
    private DatePicker ageDatePicker;

    @FXML
    private void onRegistration(ActionEvent actionEvent) throws IOException {
        String username = this.username.getText();
        String email = this.email.getText();
        String password = this.password.getText();
        double weight;
        double height;
        try {
            weight = Double.parseDouble(this.weight.getText().replace(',', '.'));
            height = Double.parseDouble(this.height.getText().replace(',', '.'));
        } catch (Exception ex) {
            (new Alert(Alert.AlertType.ERROR, "Weight or height is not a number!")).showAndWait();
            return;
        }
        LocalDate birthDate = ageDatePicker.getValue();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || weight == 0 || height == 0) {
            (new Alert(Alert.AlertType.ERROR, "You have to fill out every field")).showAndWait();
            return;
        };

        if (UserService.isValidRegistration(username, email, password, weight, height)) {
            try {
                UserService.getInstance().setUser(UserService.getInstance().insert(
                        new User(email,
                                username,
                                password,
                                weight,
                                height,
                                birthDate)));
            } catch (Exception e) {
                (new Alert(Alert.AlertType.ERROR, "Invalid parameters")).showAndWait();
            }

            Stage stage = (Stage) this.username.getScene().getWindow();
            stage.setScene(new Scene(loadFXML("/calorie-counter"), 640, 550));
        } else {
            (new Alert(Alert.AlertType.WARNING, "Invalid registration")).showAndWait();
        }
    }

    public void onLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.username.getScene().getWindow();
        stage.setScene(new Scene(loadFXML("/login"), 640, 550));
    }
}
