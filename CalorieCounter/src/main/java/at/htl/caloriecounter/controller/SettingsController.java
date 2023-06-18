package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.repositories.UserRepository;
import at.htl.caloriecounter.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static at.htl.caloriecounter.App.loadFXML;

public class SettingsController {
    @FXML
    private DatePicker ageDatePicker;
    @FXML
    private TextField weightField;
    @FXML
    private TextField heightField;
    @FXML
    private Button saveButton;

    UserRepository userRepository = new UserRepository();

    public void initialize() {
        ageDatePicker.setValue(UserService.getInstance().getUser().getAge());
        weightField.setText(String.valueOf(UserService.getInstance().getUser().getWeight()));
        heightField.setText(String.valueOf(UserService.getInstance().getUser().getHeight()));
    }

    @FXML
    private void onBtnChange(ActionEvent event) {
        ageDatePicker.setDisable(false);
        weightField.setDisable(false);
        heightField.setDisable(false);
        saveButton.setDisable(false);
    }

    @FXML
    private void onBtnBack(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.ageDatePicker.getScene().getWindow();
        stage.setScene(new Scene(loadFXML("/calorie-counter"), 640, 550));
    }

    @FXML
    private void onBtnSave(ActionEvent actionEvent) {
        ageDatePicker.setDisable(true);
        weightField.setDisable(true);
        heightField.setDisable(true);
        saveButton.setDisable(true);

        User currentUser = UserService.getInstance().getUser();
        currentUser.setAge(ageDatePicker.getValue());
        currentUser.setWeight(Double.parseDouble(weightField.getText()));
        currentUser.setHeight(Double.parseDouble(heightField.getText()));

        userRepository.update(currentUser);
    }
}
