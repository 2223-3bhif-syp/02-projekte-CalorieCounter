package at.htl.caloriecounter.entity;

import java.util.Objects;

public class User {
    private String email;
    private String username;
    private String password;
    private double weight;
    private double height;
    private Long id;

    public User() {}

    public User (String email, String username, String password, double weight, double height) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.weight = weight;
        this.height = height;
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
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Double.compare(user.weight, weight) == 0
                && Double.compare(user.height, height) == 0
                && Objects.equals(email, user.email)
                && Objects.equals(username, user.username)
                && Objects.equals(password, user.password)
                && Objects.equals(id, user.id);
    }
}
