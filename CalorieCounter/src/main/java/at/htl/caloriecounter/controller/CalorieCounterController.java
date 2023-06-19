package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.control.Button;


import static at.htl.caloriecounter.App.loadFXML;

public class CalorieCounterController {
    //region Buttons
    @FXML
    private Button trackCaloriesBtn;

    @FXML
    private Button statisticsBtn;

    @FXML
    private Button improveGoalsBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button fmaBtn;

    @FXML
    private Button csmBtn;

    @FXML
    private Button insertOtherValuesBtn;

    @FXML
    private Button toolbarSexBtn;

    @FXML
    private Button toolbarAgeBtn;

    @FXML
    private Button toolbarWeightBtn;

    @FXML
    private Button toolbarHeightBtn;

    @FXML
    private Button toolbarDesiredWeightBtn;

    //endregion

    @FXML
    private void initialize() {
        System.out.println(UserService.getInstance().getUser().getUsername());
    }

    @FXML
    private void trackCaloriesPressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) settingsBtn.getScene().getWindow();
        stage.setScene(new Scene(loadFXML("/tracker"), 640, 550));
    }

    @FXML
    private void statisticsPressed(ActionEvent actionEvent) {
    }

    @FXML
    private void improveGoalsPressed(ActionEvent actionEvent) {
    }

    @FXML
    private void settingsPressed(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) settingsBtn.getScene().getWindow();
        stage.setScene(new Scene(loadFXML("/settings"), 640, 550));
    }
}
