package at.htl.caloriecounter.entity;

import java.util.Objects;
import java.util.regex.Pattern;

public class User {
    private static final String mailRegex =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern mailValidator = Pattern.compile(mailRegex);
    private String email;
    private String username;
    private String password;
    private double weight;
    private double height;
    private int age;
    private Long id;

    public User() {}

    public User (String email, String username, String password, double weight, double height, int age) {
        this.setEmail(email);
        this.setWeight(weight);
        this.setHeight(height);
        this.setAge(age);
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!mailValidator.matcher(email).matches()){
            throw new IllegalArgumentException("invalid email " + email);
        }

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if(height <= 0){
            throw new IllegalArgumentException("height cannot be 0 or less");
        }

        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if(weight <= 0){
            throw new IllegalArgumentException("weight cannot be 0 or less");
        }

        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age <= 0 || age > 130){
            throw new IllegalArgumentException("Invalid age");
        }

        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (o == null || getClass() != o.getClass()){
            return false;
        }

        User user = (User) o;
        return Double.compare(user.weight, weight) == 0
                && Double.compare(user.height, height) == 0
                && Objects.equals(email, user.email)
                && Objects.equals(username, user.username)
                && Objects.equals(password, user.password)
                && Objects.equals(id, user.id);
    }
}
