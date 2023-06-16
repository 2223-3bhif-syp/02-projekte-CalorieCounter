package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;

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
    }

    @FXML
    private void trackCaloriesPressed(ActionEvent actionEvent) {
    }

    @FXML
    private void statisticsPressed(ActionEvent actionEvent) {
    }

    @FXML
    private void improveGoalsPressed(ActionEvent actionEvent) {
    }

    @FXML
    private void settingsPressed(ActionEvent actionEvent) {
    }

    @FXML
    private void fmaPressed(ActionEvent actionEvent) {
    }

    @FXML
    private void csmPressed(ActionEvent actionEvent) {
    }

    @FXML
    private void insertOtherValuesPressed(ActionEvent actionEvent) {
    }

    @FXML
    private void toolbarSexPressed(ActionEvent actionEvent) {
        ChoiceDialog sexDialog = new ChoiceDialog("", "male", "female", "other");
        sexDialog.setTitle("Sex settings");
        sexDialog.setHeaderText("Choose your gender:");
        sexDialog.setContentText("Sex: ");

        Optional<String> result = sexDialog.showAndWait();

        result.ifPresent(sex -> {
            //now change gender in user
        });
    }

    @FXML
    private void toolbarAgePressed(ActionEvent actionEvent) {
        TextInputDialog ageDialog = new TextInputDialog();
        ageDialog.setTitle("Age settings");
        ageDialog.setHeaderText("Enter your age:");
        ageDialog.setContentText("Age: ");

        Optional<String> result = ageDialog.showAndWait();

        result.ifPresent(age -> {
            try{
                int parsedAge = Integer.parseInt(age);
                UserService.getInstance().getUser().setAge(parsedAge);
            } catch (NumberFormatException ex){
                new Alert(Alert.AlertType.ERROR, "Not a number!").show();
            }
        });
    }

    @FXML
    private void toolbarWeightPressed(ActionEvent actionEvent) {
        TextInputDialog weightDialog = new TextInputDialog();
        weightDialog.setTitle("Weight settings");
        weightDialog.setHeaderText("Enter your weight:");
        weightDialog.setContentText("weight: ");

        Optional<String> result = weightDialog.showAndWait();

        result.ifPresent(weight -> {
            try{
                int parsedWeight = Integer.parseInt(weight);
                UserService.getInstance().getUser().setWeight(parsedWeight);
            } catch (NumberFormatException ex){
                new Alert(Alert.AlertType.ERROR, "Not a number!").show();
            }
        });
    }

    @FXML
    private void toolbarHeightPressed(ActionEvent actionEvent) {
        TextInputDialog heightDialog = new TextInputDialog();
        heightDialog.setTitle("Height settings");
        heightDialog.setHeaderText("Enter your height:");
        heightDialog.setContentText("Height: ");

        Optional<String> result = heightDialog.showAndWait();

        result.ifPresent(height -> {
            try{
                int parsedHeight = Integer.parseInt(height);
                UserService.getInstance().getUser().setHeight(parsedHeight);
            } catch (NumberFormatException ex){
                new Alert(Alert.AlertType.ERROR, "Not a number!").show();
            }
        });
    }

    @FXML
    private void toolbarDesiredWeightPressed(ActionEvent actionEvent) {
        TextInputDialog desiredWeightDialog = new TextInputDialog();
        desiredWeightDialog.setTitle("desired weight settings");
        desiredWeightDialog.setHeaderText("Enter your desired weight:");
        desiredWeightDialog.setContentText("Desired Weight: ");

        Optional<String> result = desiredWeightDialog.showAndWait();

        result.ifPresent(desiredWeight -> {
            //
        });
    }
}
