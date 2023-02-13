package at.htl.caloriecounter.entity;

public class Customer {
    private String email;
    private String username;
    private String password;
    private double weight;
    private double height;

    public Customer (String email, String username, String password, double weight, double height) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.weight = weight;
        this.height = height;
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
}
