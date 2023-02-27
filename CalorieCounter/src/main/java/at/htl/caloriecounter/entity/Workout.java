package at.htl.caloriecounter.entity;

public class Workout {
    private Long id;
    private String name;
    private double calories;
    private double duration;

    public Workout() {}

    public Workout(String name, double calories, double duration) {
        this.name = name;
        this.calories = calories;
        this.duration = duration;
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
        this.calories = calories;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
}
