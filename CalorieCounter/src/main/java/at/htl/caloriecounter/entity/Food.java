package at.htl.caloriecounter.entity;

public class Food {
    private String name;
    private double calories;
    private Long id;

    public Food() {}

    public Food(String name, double calories) {
        this.name = name;
        setCalories(calories);
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
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
