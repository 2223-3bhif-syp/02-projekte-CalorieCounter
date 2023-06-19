package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.repositories.UserRepository;
import at.htl.caloriecounter.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    UserRepository userRepository = new UserRepository();

    @FXML
    private void onLoginBtn(ActionEvent event) throws IOException {
        String username = this.username.getText();
        String password = this.password.getText();

        if (username.isEmpty() || password.isEmpty()) {
            (new Alert(Alert.AlertType.ERROR, "You have to fill out every field")).show();
            return;
        }

        if (isValidUser(username, password)) {
            UserService.getInstance().setUser(userRepository.getUserByUsername(username));
            Stage stage = (Stage) submitLogin.getScene().getWindow();
            stage.setScene(new Scene(loadFXML("/calorie-counter"), 640, 550));
        } else {
            (new Alert(Alert.AlertType.ERROR, "Invalid username or password")).show();
        }
    }

    @FXML
    private void switchToRegisterPage() throws IOException {
        Stage stage = (Stage) submitLogin.getScene().getWindow();
        stage.setScene(new Scene(loadFXML("/register"), 640, 550));
    }

    @FXML
    private void handleKeyPress(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case ENTER:{
                try {
                    onLoginBtn(new ActionEvent());
                }catch (IOException ignored) {
                }
                break;
            }
            case ESCAPE:{
                System.exit(0);
            }
        }
    }

    @FXML
    private void about() {
        Alert about = new Alert(Alert.AlertType.INFORMATION);
        about.setTitle("CalorieCounter");
        about.setHeaderText("CalorieCounter\nSYP-Project 3BHIF 22/23\nWTFPL Licensed");
        about.setContentText("Aichinger Tobias\nGruber Moritz\nStroschneider Fabian\nBreinesberger Markus");
        about.show();
    }
}
