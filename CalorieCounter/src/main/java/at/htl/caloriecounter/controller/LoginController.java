package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

import static at.htl.caloriecounter.App.loadFXML;
import static at.htl.caloriecounter.repositories.UserRepository.isValidUser;

public class LoginController {
    @FXML
    private void initialize(){

    }

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button submitLogin;

    @FXML
    private void onLoginBtn(ActionEvent actionEvent) throws IOException {
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

    @FXML
    private void switchToRegisterPage(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) submitLogin.getScene().getWindow();
        stage.setScene(new Scene(loadFXML("/register"), 640, 550));
    }

    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER){

        }

        switch (keyEvent.getCode()){
            case ENTER:{
                try {
                    onLoginBtn(new ActionEvent());
                }catch (IOException e) {
                }
                break;
            }
            case ESCAPE:{
                System.exit(0);
            }
        }
    }

    @FXML
    private void about(ActionEvent actionEvent) {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("CalorieCounter");
        about.setHeaderText("CalorieCounter\nSYP-Project 3BHIF 22/23\nWTFPL Licensed");
        about.setContentText("Aichinger Tobias\nGruber Moritz\nStroschneider Fabian\nBreinesberger Markus");
        about.show();
    }
}
