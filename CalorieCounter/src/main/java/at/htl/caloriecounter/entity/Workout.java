package at.htl.caloriecounter.entity;

public class Workout {
    private Long id;
    private String name;
    private double calories;
    private double duration;
    private User user;

    public Workout() {}

    public Workout(String name, double calories, double duration, User user) {
        this.name = name;
        setCalories(calories);
        setDuration(duration);
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        if(calories < 0){
            throw new IllegalArgumentException("calories cannot be less than zero");
        }

        this.calories = calories;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        if(duration < 0){
            throw new IllegalArgumentException("duration cannot be less than zero");
        }

        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
