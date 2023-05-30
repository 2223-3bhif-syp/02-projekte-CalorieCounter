package at.htl.caloriecounter.entity;

import java.time.LocalDateTime;

public class Goal {
    private Long id;
    private double weight;
    private LocalDateTime deadline;

    private User user;

    public Goal() {
    }

    public Goal(double weight, LocalDateTime deadline, User user) {
        setWeight(weight);
        this.deadline = deadline;
        this.user = user;
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

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        if(deadline.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Deadline cannot be in past");
        }

        this.deadline = deadline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "weight=" + weight +
                ", deadline=" + deadline +
                '}';
    }
}
