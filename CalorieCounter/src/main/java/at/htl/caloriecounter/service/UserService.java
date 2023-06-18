package at.htl.caloriecounter.service;

import at.htl.caloriecounter.entity.User;
import at.htl.caloriecounter.repositories.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class UserService {
    private static final String emailRegex = "^(.+)@(\\S+)$";
    private static final int MIN_USERNAME_LENGTH = 3;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private User currentUser;
    private static UserService userService = null;

    private UserService() {
        currentUser = new User();
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }

        return userService;
    }

    public User insert(User user) {
        UserRepository userRepository = new UserRepository();
        userRepository.insert(user);
        return user;
    }

    public static boolean isValidRegistration(String username, String email, String password, double weight, double height) {
        return username.length() >= MIN_USERNAME_LENGTH &&
                Pattern.compile(emailRegex).matcher(email).matches() &&
                password.length() >= MIN_PASSWORD_LENGTH &&
                weight >= 30.0 &&
                height >= 50.0;
    }

    public User getUser() {
        return this.currentUser;
    }

    public void setUser(User user) {
        this.currentUser = user;
    }
}
