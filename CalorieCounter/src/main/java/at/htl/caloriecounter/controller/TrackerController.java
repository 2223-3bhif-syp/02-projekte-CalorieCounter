package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.entity.Food;
import at.htl.caloriecounter.repositories.FoodRepository;
import at.htl.caloriecounter.repositories.UserRepository;
import at.htl.caloriecounter.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static at.htl.caloriecounter.App.loadFXML;

public class TrackerController {

    @FXML
    private Button btnDelete;
    @FXML
    private ListView<Food> foodLv;
    @FXML
    private TextField foodNameField;
    @FXML
    private TextField foodCaloriesField;
    FoodRepository foodRepository = new FoodRepository();
    ObservableList<Food> foodList = FXCollections.observableList(foodRepository.findAll());

    @FXML
    private void initialize() {
        foodLv.setItems(foodList);
    }

    @FXML
    private void onBtnBack(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) this.foodNameField.getScene().getWindow();
        stage.setScene(new Scene(loadFXML("/calorie-counter"), 640, 550));
    }

    @FXML
    private void onBtnSave(ActionEvent actionEvent) {
        try {
            if (foodNameField.getText().isEmpty() || foodCaloriesField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill out every field!");
                alert.showAndWait();
            }

            Food newFood = new Food(foodNameField.getText(), Double.parseDouble(foodCaloriesField.getText()));
            foodRepository.save(newFood);
            foodList.add(newFood);
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Calories are not a number!");
            alert.showAndWait();
        }
    }

    @FXML
    private void onBtnDelete(ActionEvent actionEvent) {
        if (!foodLv.getSelectionModel().isEmpty()) {
            Food selectedFood = foodLv.getSelectionModel().getSelectedItem();
            foodRepository.delete(selectedFood.getId());
            foodList.remove(selectedFood);
            foodNameField.clear();
            foodCaloriesField.clear();
            btnDelete.setDisable(true);
        }
    }

    @FXML
    private void onMouseClickedLv(MouseEvent mouseEvent) {
        if (!foodLv.getSelectionModel().isEmpty()) {
            btnDelete.setDisable(false);
            Food selectedFood = foodLv.getSelectionModel().getSelectedItem();

            foodNameField.setText(selectedFood.getName());
            foodCaloriesField.setText(String.valueOf(selectedFood.getCalories()));
        }
    }
}
