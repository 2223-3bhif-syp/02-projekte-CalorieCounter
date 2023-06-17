package at.htl.caloriecounter.controller;

import at.htl.caloriecounter.repositories.UserRepository;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

public class SettingsController {
    public TextField ageField;
    public TextField weightField;
    public TextField heightField;

    UserRepository userRepository = new UserRepository();

    public void initialize() {
        ageField.setText(String.valueOf(userRepository.logedInUser.getAge()));
        weightField.setText(String.valueOf(userRepository.logedInUser.getWeight()));
        heightField.setText(String.valueOf(userRepository.logedInUser.getHeight()));
    }

    public void change(ActionEvent event) {

    }
}
